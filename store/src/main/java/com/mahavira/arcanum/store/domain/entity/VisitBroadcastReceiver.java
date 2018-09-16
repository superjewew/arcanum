package com.mahavira.arcanum.store.domain.entity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.arcanum.store.R;
import com.mahavira.arcanum.store.data.StoreRepoImpl;
import com.mahavira.arcanum.store.domain.usecase.SetVisitUseCase;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 13/09/18.
 * 
 */

public class VisitBroadcastReceiver extends BroadcastReceiver {

    SetVisitUseCase mSetVisitUseCase;

    public static final String PARAM_VISIT_USER_EXTRA = "param_user_visit";

    public static final String NOTIFICATION_ID_EXTRA = "notification_id";

    public static final String ACTION_SNOOZE = "snooze";

    public static final String ACTION_LEFT_STORE = "left_store";

    @Override
    public void onReceive(final Context context, final Intent intent) {
        int id = intent.getIntExtra(NOTIFICATION_ID_EXTRA, -1);
        String action = intent.getAction();

        assert action != null;
        switch (action) {
            case ACTION_SNOOZE: {
                break;
            }
            case ACTION_LEFT_STORE: {
                mSetVisitUseCase = new SetVisitUseCase(new StoreRepoImpl(FirebaseFirestore.getInstance()));
                String userParam = intent.getStringExtra(PARAM_VISIT_USER_EXTRA);

                leaveStore(context, userParam);
                removeNotification(context);
                break;
            }
        }

        cancelNotification(context, id);
    }

    private void removeNotification(Context context) {
        Intent intent1 = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (am == null) {
            Toast.makeText(context, R.string.alarm_service_not_supported_error, Toast.LENGTH_SHORT).show();
        } else {
            am.cancel(pendingIntent);
        }
    }

    private void leaveStore(final Context context, final String userParam) {
        try {
            mSetVisitUseCase.execute(new SetVisitParam(userParam, ""))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        } catch (Exception e) {
            Toast.makeText(context, R.string.general_error, Toast.LENGTH_SHORT).show();
        }
    }

    private void cancelNotification(final Context context, final int id) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.cancel(id);
        } else {
            Toast.makeText(context, R.string.notification_service_error, Toast.LENGTH_SHORT).show();
        }
    }
}
