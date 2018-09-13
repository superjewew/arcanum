package com.mahavira.arcanum.store.domain.entity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import com.mahavira.arcanum.store.presentation.StoreListActivity;
import com.mahavira.base.R;

public class AlarmReceiver extends BroadcastReceiver {

    public static int NOTIFICATION_ID = 10;

    @Override
    public void onReceive(Context context, Intent intent) {
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, StoreListActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent snoozeIntent = new Intent(context, VisitBroadcastReceiver.class);
        final String ACTION_YES = "yes";
        snoozeIntent.setAction(ACTION_YES);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification_visit)
                .setContentTitle("Alarm Fired")
                .setContentText("Are you still playing?").setSound(alarmSound)
                .setAutoCancel(true).setWhen(when)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .addAction(R.drawable.ic_notification_visit, "YES!", snoozePendingIntent);
        notificationManager.notify(NOTIFICATION_ID, mNotifyBuilder.build());
        NOTIFICATION_ID++;

    }

}