package com.example.aluptak.androidrobo.controller;

import android.util.Log;

import com.example.aluptak.androidrobo.daoWorktTimeRecord.IWorkTimeRecordRepo;
import com.example.aluptak.androidrobo.entity.WorkTimeRecord;
import com.google.inject.Inject;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by adam on 21.2.2016.
 */
public class TimeController {

    private final String workTimePerDay = "08:30:00";
    private final String timeformaterString = "HH:mm:ss";
    private final String workTimePerWeek = "40:00:00";
    private final String lunchBreakTme = "00:30:00";
    private DateFormat format;
    private IWorkTimeRecordRepo workTimeRecordRepo;

    @Inject
    public TimeController(IWorkTimeRecordRepo workTimeRecordRepo) {
        this.workTimeRecordRepo = workTimeRecordRepo;
    }

    /**
     * Get overtime from last days
     *
     * @param today
     * @return
     */
    public String getOverTime(Date today) {
        this.format = new SimpleDateFormat(timeformaterString);
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(today);
        int todayDay = c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DAY_OF_WEEK, -todayDay + Calendar.MONDAY);
        DateFormat sdf1 = new SimpleDateFormat("EEE-MM-dd-yyyy");
        String monday = sdf1.format(new Date(c.getTimeInMillis()));
        List<WorkTimeRecord> actualWorkTimeRecords;
        String[] namesOfDays = DateFormatSymbols.getInstance().getShortWeekdays();
        Log.e("dni", Arrays.toString(namesOfDays));
        Long dayWorkHours = 30600000l;
        try {
            actualWorkTimeRecords = workTimeRecordRepo.getAlldaysForThisWeek(monday);
            String lastDay = null;
            long totalOvertime = 0;
            long overTime = 0;
            //work time for monday
            for (String day : namesOfDays
                    ) {
                for (WorkTimeRecord workTimeRecord : actualWorkTimeRecords
                        ) {
                    if (workTimeRecord.getDayOfWeek().substring(0, 3).equals(day)) {
                        overTime += (workTimeRecord.getLeaveTimeDate().getTime() - workTimeRecord.getArrivalTimeDate().getTime());
                    }
                }
                if (overTime != 0) {
                    overTime = (overTime) - dayWorkHours;
                    totalOvertime += overTime;
                    int seconds = (int) (overTime / 1000) % 60;
                    int minutes = (int) ((overTime / (1000 * 60)) % 60);
                    int hours = (int) ((overTime / (1000 * 60 * 60)) % 24);
                    int secondss = (int) (totalOvertime / 1000) % 60;
                    int minutess = (int) ((totalOvertime / (1000 * 60)) % 60);
                    int hourss = (int) ((totalOvertime / (1000 * 60 * 60)) % 24);
                    //Log.e("ds", String.valueOf(hourss) + ":" + String.valueOf(minutess) + ":" + String.valueOf(secondss));
                    // Log.e("ds", String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds));
                }
                overTime = 0;
            }
            WorkTimeRecord todayWorkTimeRecord = workTimeRecordRepo.getFirstWorkTimeForThisDay(new Date(1455778800000l));

            Long goHoemWithOV = (todayWorkTimeRecord.getArrivalTimeDate().getTime() + dayWorkHours + 3600000l) - totalOvertime;
            int seconds = (int) (goHoemWithOV / 1000) % 60;
            int minutes = (int) ((goHoemWithOV / (1000 * 60)) % 60);
            int hours = (int) ((goHoemWithOV / (1000 * 60 * 60)) % 24);
            Log.e("ds", String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Calculate leave time with overtime use
     *
     * @return
     */
    public String getLeaveTimeWithOvertime(long overTime) {
        //this.format.parse(workTimePerDay);

        return null;
    }

    /**
     * Calculate leave time withour overtime
     *
     * @return
     */
    public String getLeaveTimeWithoutOvertime() {
        return null;
    }

}
