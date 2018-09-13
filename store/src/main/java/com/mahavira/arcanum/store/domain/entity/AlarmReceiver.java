package com.mahavira.arcanum.store.domain.entity;

import static com.mahavira.arcanum.store.domain.entity.VisitBroadcastReceiver.PARAM_VISIT_STORE_EXTRA;
import static com.mahavira.arcanum.store.domain.entity.VisitBroadcastReceiver.PARAM_VISIT_USER_EXTRA;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.mahavira.arcanum.store.presentation.StoreListActivity;
import com.mahavira.base.R;

public class AlarmReceiver extends BroadcastReceiver {

    public static int NOTIFICATION_ID = 10;

    @Override
    public void onReceive(Context context, Intent intent) {
        SetVisitParam param = SetVisitParam
                .unmarshall(intent.getByteArrayExtra("PARAM_EXTRA"), SetVisitParam$$Parcelable.CREATOR);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        Intent notificationIntent = new Intent(context, StoreListActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent snoozeIntent = new Intent(context, VisitBroadcastReceiver.class);
        snoozeIntent.setAction("snooze");
        snoozeIntent.putExtra(PARAM_VISIT_USER_EXTRA, param.getUserEmail());
        snoozeIntent.putExtra(PARAM_VISIT_STORE_EXTRA, param.getStoreEmail());

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder =
                new NotificationCompat.Builder(context, context.getString(R.string.channel_id))
                        .setSmallIcon(R.drawable.ic_notification_visit)
                        .setContentTitle("Hey there!")
                        .setContentText("Are you still playing at Arcanum?").setSound(alarmSound)
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                        .setAutoCancel(true)
                        .addAction(R.drawable.ic_notification_visit, "YES!",
                                PendingIntent.getBroadcast(context, 0, snoozeIntent, 0))
                        .addAction(R.drawable.ic_notification_visit, "NO",
                                PendingIntent.getBroadcast(context, 1, snoozeIntent, 0));
        notificationManager.notify(NOTIFICATION_ID, mNotifyBuilder.build());
        NOTIFICATION_ID++;

    }

}