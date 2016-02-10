package com.example.aluptak.androidrobo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aluptak.androidrobo.daoWorktTimeRecord.IWorkTimeRecordRepo;
import com.example.aluptak.androidrobo.daoWorktTimeRecord.WorkTimeRecordRepo;
import com.example.aluptak.androidrobo.exception.WorkTimeRecord;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    private boolean startStop = false;

    @InjectView(R.id.arc_progressOvertime)
    private ArcProgress arcProgress;

    @InjectView(R.id.startStopButton)
    private ImageView startStopButton;

    Timer myTimer;


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

}