package com.example.aluptak.androidrobo.daoWorktTimeRecord;

import android.content.Context;

import javax.inject.Inject;

import com.example.aluptak.androidrobo.exception.WorkTimeRecord;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

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
}