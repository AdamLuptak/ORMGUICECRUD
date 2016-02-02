package com.example.aluptak.androidrobo;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.aluptak.androidrobo.daoWorktTimeRecord.WorkTimeRecordRepo;
import com.example.aluptak.androidrobo.exception.WorkTimeRecord;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by aluptak on 02/02/2016.
 */
@ContentView(R.layout.work_time_record_list)
public class WorkTimeRecordListActivity extends RoboActivity {

    @Inject
    private WorkTimeRecordRepo _workTimeRecordRepo;

    @InjectView(R.id.workTimeRecordview)
    private ListView _listViewWorkTimerecord;

    @InjectView(R.id.buttonWorkTimeRecordAdd)
    private Button _buttonWorkTimeRecordAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_time_record_list);
        try {

            List workTimeRecords = _workTimeRecordRepo.gettWorkTimeRecords();
            WorkTimeRecordListAdapter workTimeRecordListAdapter = new WorkTimeRecordListAdapter(
                    getApplicationContext(), workTimeRecords);
            _listViewWorkTimerecord.setAdapter(workTimeRecordListAdapter);

            _buttonWorkTimeRecordAdd.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(WorkTimeRecordListActivity.this,
                            WorkTimeRecordEditActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityForResult(intent, 0);
                    finish();
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        _listViewWorkTimerecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sdfsfd", "sdsdddssd" + parent.getItemAtPosition(position).toString());
                Intent intent = new Intent(WorkTimeRecordListActivity.this,
                        WorkTimeRecordEditActivity.class);
                intent.putExtra("WorkTimeRecord", (WorkTimeRecord) parent.getItemAtPosition(position));
                startActivityForResult(intent, 0);
                finish();

            }
        });
    }

}
