package com.example.aluptak.androidrobo;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.aluptak.androidrobo.controller.TimeController;
import com.example.aluptak.androidrobo.daoWorktTimeRecord.DbHelper;
import com.example.aluptak.androidrobo.daoWorktTimeRecord.IWorkTimeRecordRepo;
import com.example.aluptak.androidrobo.entity.GenerateTestData;
import com.example.aluptak.androidrobo.entity.WorkTimeRecord;
import com.github.lzyzsd.circleprogress.ArcProgress;

import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    private boolean startStop = false;

    private Context context;

    @Inject
    private IWorkTimeRecordRepo _workTimeRecordRepo;

    @Inject
    private TimeController timeController;

    @InjectView(R.id.arc_progressOvertime)
    private ArcProgress arcProgress;

    @InjectView(R.id.startStopButton)
    private ImageView startStopButton;

    @InjectView(R.id.button)
    private Button show;
    @InjectView(R.id.button2)
    private Button stop;
    @InjectView(R.id.button3)
    private Button alert;

    Timer myTimer;

    NotificationManager notificationManager;

    boolean isNotificActive = false;

    int notifID = 33;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        myTimer = new Timer();
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // If you want to modify a view in your Activity
                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      if (startStop) {
                                          arcProgress.setProgress(arcProgress.getProgress() + 1);
                                      } else {

                                      }
                                  }
                              }
                );
            }
        }, 1000, 1000);


        this.context = this;
//        Intent alarm = new Intent(this.context, AlertReciever.class);
//        boolean alarmRunning = (PendingIntent.getBroadcast(this.context, 0, alarm, PendingIntent.FLAG_NO_CREATE) != null);
//        if (!alarmRunning) {
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.context, 0, alarm, 0);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 15000, pendingIntent);
//        }
    }


    public void startStopRecord(View view) {
        if (!startStop) {
            startStopButton.setImageResource(R.drawable.stop);

        } else {
            startStopButton.setImageResource(R.drawable.start);
        }


        startStop = !startStop;
    }

    private void delay1000() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setValues(WorkTimeRecord workTimeRecord) {


    }


    class Updater implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    arcProgress.setProgress(arcProgress.getProgress() + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(View view) {
//        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(this).setContentTitle("Message").setContentText("New message").setTicker("Alert New message").setSmallIcon(R.drawable.icon);
//
//        Intent list = new Intent(this, WorkTimeRecordListActivity.class);
//
//        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
//
//        taskStackBuilder.addNextIntent(list);
//
//        PendingIntent pedingItent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        noBuilder.setContentIntent(pedingItent);
//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(notifID, noBuilder.build());
//        isNotificActive = true;
//        GenerateTestData gn = new GenerateTestData(_workTimeRecordRepo);
//        try {
//            gn.generateData();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        TimeController timeController = new TimeController(_workTimeRecordRepo);
        timeController.getOverTime(new Date(1455786000000l));
    }

    public void stopNotification(View view) {

//        if (isNotificActive) {
//            notificationManager.cancel((notifID));
//        }

//        Intent actIntent = new Intent(context, UpdaterService.class);
//        PendingIntent pi = PendingIntent.getService(context, 0, actIntent, 0);
//
//        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        am.cancel(pi);
        DbHelper db = new DbHelper(this.context);
        db.resetDatabase(WorkTimeRecord.class);


    }

    public void setAlarm(View view) {
//        Long alertTime = new GregorianCalendar().getTimeInMillis() + 5 * 1000;
//        Intent alertIntent = new Intent(this, AlertReciever.class);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime, PendingIntent.getBroadcast(this, 1, alertIntent, PendingIntent.FLAG_CANCEL_CURRENT));
        GenerateTestData gn = new GenerateTestData(_workTimeRecordRepo);
        try {
            gn.generateData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}