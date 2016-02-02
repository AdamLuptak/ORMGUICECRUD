package com.example.aluptak.androidrobo;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.aluptak.androidrobo.daoWorktTimeRecord.IWorkTimeRecordRepo;
import com.example.aluptak.androidrobo.daoWorktTimeRecord.WorkTimeRecordRepo;
import com.example.aluptak.androidrobo.exception.WorkTimeRecord;

import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @InjectView(R.id.arrivalTime)
    private TextView arrivalTime;

    @InjectView(R.id.leaveTime)
    private TextView leaveTime;

    @InjectView(R.id.dayOfWeek)
    private TextView dayOfWeek;

    @InjectView(R.id.workTime)
    private TextView workTime;

    @Inject
    WorkTimeRecord workTimeRecord;

    @Inject
    WorkTimeRecordRepo _workTimeRecordRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        workTimeRecord.setArrivalTimeDate(new Date());
        delay1000();
        workTimeRecord.setLeaveTimeDate(new Date());
        setValues(workTimeRecord);
        try {
            _workTimeRecordRepo.saveWorkTimeRecord(workTimeRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<WorkTimeRecord> workTimeRecordList = null;
        try {
            workTimeRecordList =  _workTimeRecordRepo.gettWorkTimeRecords();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Iterator it = workTimeRecordList.iterator();
        while (it.hasNext()){
            WorkTimeRecord wk = (WorkTimeRecord)it.next();
            Log.e("sd",wk.toString() + "   "+ wk.getId());

        }


    }

    private void delay1000() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setValues(WorkTimeRecord workTimeRecord) {
        arrivalTime.setText(workTimeRecord.getArrivalTimeDate().toString());
        leaveTime.setText(workTimeRecord.getLeaveTimeDate().toString());
        dayOfWeek.setText(workTimeRecord.getDayOfWeek().toString());
        workTime.setText(workTimeRecord.getWorkingTime().toString());
    }
}