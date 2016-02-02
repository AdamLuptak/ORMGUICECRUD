package com.example.aluptak.androidrobo.daoWorktTimeRecord;

import com.example.aluptak.androidrobo.exception.WorkTimeRecord;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aluptak on 02/02/2016.
 */
public interface IWorkTimeRecordRepo {

    public List gettWorkTimeRecords() throws SQLException;

    public WorkTimeRecord getWorkTimeRecord(String id) throws SQLException;

    public void deleteWorkTimeRecord(WorkTimeRecord deleteProduct) throws SQLException;

    public void saveWorkTimeRecord(WorkTimeRecord saveProduct) throws SQLException;

    public void updateWorkTimeRecord(WorkTimeRecord updateProduct) throws SQLException;

}
