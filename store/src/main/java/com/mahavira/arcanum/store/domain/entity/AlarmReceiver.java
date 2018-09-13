package com.mahavira.arcanum.store.domain.entity;

import static com.mahavira.arcanum.store.domain.entity.VisitBroadcastReceiver.ACTION_LEFT_STORE;
import static com.mahavira.arcanum.store.domain.entity.VisitBroadcastReceiver.ACTION_SNOOZE;
import static com.mahavira.arcanum.store.domain.entity.VisitBroadcastReceiver.NOTIFICATION_ID_EXTRA;
import static com.mahavira.arcanum.store.domain.entity.VisitBroadcastReceiver.PARAM_VISIT_USER_EXTRA;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.mahavira.base.R;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SetVisitParam param = SetVisitParam
                .unmarshall(intent.getByteArrayExtra("PARAM_EXTRA"), SetVisitParam$$Parcelable.CREATOR);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        final int NOTIFICATION_ID = 10;

        Intent leftStoreIntent = new Intent(context, VisitBroadcastReceiver.class);
        leftStoreIntent.setAction(ACTION_LEFT_STORE);
        leftStoreIntent.putExtra(PARAM_VISIT_USER_EXTRA, param.getUserEmail());
        leftStoreIntent.putExtra(NOTIFICATION_ID_EXTRA, NOTIFICATION_ID);

        Intent snoozeIntent = new Intent(context, VisitBroadcastReceiver.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra(NOTIFICATION_ID_EXTRA, NOTIFICATION_ID);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder =
                new NotificationCompat.Builder(context, context.getString(R.string.channel_id))
                        .setSmallIcon(R.drawable.ic_notification_visit)
                        .setContentTitle("Hey there!")
                        .setContentText("Are you still playing at Arcanum?").setSound(alarmSound)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                        .setAutoCancel(true)
                        .addAction(R.drawable.ic_notification_visit, "YES!",
                                PendingIntent.getBroadcast(context, 0, snoozeIntent, 0))
                        .addAction(R.drawable.ic_notification_visit, "NO",
                                PendingIntent.getBroadcast(context, 1, leftStoreIntent, 0));
        notificationManager.notify(NOTIFICATION_ID, mNotifyBuilder.build());

    }

}