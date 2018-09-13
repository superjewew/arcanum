package com.mahavira.arcanum.store.domain.entity;

import static android.app.AlarmManager.INTERVAL_HOUR;

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

    public void setupRepeatingAlarm(String userEmail) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay + 2);

        SetVisitParam param = new SetVisitParam(userEmail, "");

        Intent intent1 = new Intent(mContext, AlarmReceiver.class);
        intent1.putExtra("PARAM_EXTRA", param.marshall());
        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(mContext, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 2 * INTERVAL_HOUR, pendingIntent);
    }

}
