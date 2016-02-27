package com.example.aluptak.androidrobo.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.aluptak.androidrobo.R;
import com.example.aluptak.androidrobo.daoWorktTimeRecord.DbHelper;
import com.example.aluptak.androidrobo.daoWorktTimeRecord.IWorkTimeRecordRepo;
import com.example.aluptak.androidrobo.daoWorktTimeRecord.WorkTimeRecordRepo;
import com.example.aluptak.androidrobo.daoWorktTimeRecord.WorkTimeRecordtDaoProvider;

import java.sql.SQLException;
import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class UpdaterService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_FOO = "com.example.aluptak.androidrobo.services.action.FOO";
    public static final String ACTION_BAZ = "com.example.aluptak.androidrobo.services.action.BAZ";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "com.example.aluptak.androidrobo.services.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.aluptak.androidrobo.services.extra.PARAM2";
    private static final String TAG = "UpdaterService";

    private boolean rob = false;
    private Handler mHandler;


    private IWorkTimeRecordRepo _workTimeRecordRepo;

    public UpdaterService() {
        super("UpdaterService");

        Log.e(TAG, "vytvoreny service");

    }

    @Override
    public void onCreate() {
        mHandler = new Handler();
        super.onCreate();
    }


    @Override
    protected void onHandleIntent(Intent intent) {





        mHandler.post(new Runnable() {
            @Override
            public void run() {


                _workTimeRecordRepo = new WorkTimeRecordRepo(UpdaterService.this, new WorkTimeRecordtDaoProvider(new DbHelper(UpdaterService.this)));
                Log.e(TAG, "idem zo servicu mal by som ist kazdych 15 sekund " + rob);
                try {
                    List workTimeRecords = _workTimeRecordRepo.gettWorkTimeRecords();
                    for (Object workTimeRecord : workTimeRecords) {
                        Log.e("sdf",workTimeRecord.toString());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                NotificationCompat.Builder notification =
                        new NotificationCompat.Builder(UpdaterService.this)
                                .setSmallIcon(R.drawable.icon)
                                .setContentTitle("Forecast Updated")
                                .setContentText("New Data Available");

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0, notification.build());
            }
        });
    }


}
