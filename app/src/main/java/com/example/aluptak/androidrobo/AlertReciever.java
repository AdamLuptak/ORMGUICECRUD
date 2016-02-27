package com.example.aluptak.androidrobo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.aluptak.androidrobo.services.BackgroundService;

public class AlertReciever extends BroadcastReceiver {
    public AlertReciever() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //createNotification(context, "Times Up", "5 seconds has passed", "alert");
        Intent backIntent1 = new Intent(context, BackgroundService.class);
        context.startService(backIntent1);
    }

    private void createNotification(Context context, String msg, String msgText, String msgAlert) {
        PendingIntent notificIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setContentTitle(msg).setContentText(msgText).setTicker(msgAlert).setSmallIcon(R.drawable.icon);

        mBuilder.setContentIntent(notificIntent);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService((Context.NOTIFICATION_SERVICE));
        notificationManager.notify(1, mBuilder.build());

    }
}
