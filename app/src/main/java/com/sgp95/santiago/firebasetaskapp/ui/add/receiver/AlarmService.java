package com.sgp95.santiago.firebasetaskapp.ui.add.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.sgp95.santiago.firebasetaskapp.R;
import com.sgp95.santiago.firebasetaskapp.ui.login.LoginActivity;

import static com.sgp95.santiago.firebasetaskapp.ui.add.AddTaskActivity.ALARM_REQUEST_CODE;

public class AlarmService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, LoginActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        context,
                        ALARM_REQUEST_CODE,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.ic_access_alarm)
                        .setContentTitle(intent.getStringExtra("alarmTitle"))
                        .setContentText(intent.getStringExtra("alarmDetail"))
                        .setAutoCancel(true);

        notificationManager.notify(ALARM_REQUEST_CODE,builder.build());
    }
}
