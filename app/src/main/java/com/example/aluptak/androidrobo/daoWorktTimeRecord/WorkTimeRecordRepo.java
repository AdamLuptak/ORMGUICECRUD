package com.example.aluptak.androidrobo.daoWorktTimeRecord;

import android.content.Context;

import com.example.aluptak.androidrobo.entity.WorkTimeRecord;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by aluptak on 02/02/2016.
 */
public class WorkTimeRecordRepo implements IWorkTimeRecordRepo {

    private Dao<WorkTimeRecord, String> _productDao;

    @Inject
    public WorkTimeRecordRepo(Context context, WorkTimeRecordtDaoProvider workTimeRecordtDaoProvider) {
        _productDao = workTimeRecordtDaoProvider.get();
    }

    @Override
    public List gettWorkTimeRecords() throws SQLException {
        return _productDao.queryForAll();
    }

    @Override
    public WorkTimeRecord getWorkTimeRecord(String id) throws SQLException {
        return _productDao.queryForId(id);
    }

    @Override
    public void deleteWorkTimeRecord(WorkTimeRecord deleteProduct) throws SQLException {
        _productDao.delete(deleteProduct);
    }

    @Override
    public void saveWorkTimeRecord(WorkTimeRecord deleteProduct) throws SQLException {
        _productDao.create(deleteProduct);
    }

    @Override
    public void updateWorkTimeRecord(WorkTimeRecord updateProduct) throws SQLException {
        _productDao.update(updateProduct);
    }

    @Override
    public List<WorkTimeRecord> getAlldaysForThisWeek(String monday) throws SQLException {
        return _productDao.queryBuilder().where().ge("dayOfWeek", monday).and().isNotNull("leaveDate").query();
    }

    @Override
    public WorkTimeRecord getFirstWorkTimeForThisDay(Date today) throws SQLException {
        DateFormat sdf1 =  new SimpleDateFormat("EEE-MM-dd-yyyy");
        String todayDay = sdf1.format(today);
        List<WorkTimeRecord> wkReturn =  _productDao.queryBuilder().orderBy("arrivalDate",true).where().eq("dayOfWeek", todayDay).query();
        return wkReturn.get(0);
    }
}