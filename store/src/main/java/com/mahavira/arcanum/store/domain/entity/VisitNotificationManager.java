package com.mahavira.arcanum.store.domain.entity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import javax.inject.Inject;

/**
 * Created by norman on 13/09/18.
 *
 */

public class VisitNotificationManager {

    private Context mContext;

    @Inject
    public VisitNotificationManager(Context context) {
        mContext = context;
    }

    public void setupRepeatingAlarm(String userEmail, String storeEmail) {
        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        calendar.set(Calendar.MINUTE, minute + 1);

        SetVisitParam param = new SetVisitParam(userEmail, storeEmail);

        Intent intent1 = new Intent(mContext, AlarmReceiver.class);
        intent1.putExtra("PARAM_EXTRA", param.marshall());
        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(mContext, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 2 * 60000, pendingIntent);
    }

}
