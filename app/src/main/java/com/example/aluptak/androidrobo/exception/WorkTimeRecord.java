package com.example.aluptak.androidrobo.exception;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import roboguice.RoboGuice;

/**
 * Created by aluptak on 02/02/2016.
 */
@DatabaseTable
public class WorkTimeRecord implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "arrivalDate", canBeNull = false)
    private Date arrivalDate;
    @DatabaseField(columnName = "leaveDate", canBeNull = true)
    private Date leaveDate;
    private final long TUNE_NUMBER = 3600000;
    @DatabaseField(columnName = "dayOfWeek", canBeNull = false)
    private String dayOfWeek;

    public WorkTimeRecord(Date arrivalTimeDate, Date leaveTimeDate) {
        super();
        this.arrivalDate = arrivalTimeDate;
        this.leaveDate = leaveTimeDate;
    }

    public WorkTimeRecord() {

    }

    public int getId(){
        return this.id;
    }

    public WorkTimeRecord(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getArrivalTimeDate() {
        return arrivalDate;
    }

    public void setArrivalTimeDate(Date arrivalTimeDate) {
        this.arrivalDate = arrivalTimeDate;
        DateFormat sdf1 = new SimpleDateFormat("EEE-MM-dd-yyyy");
        this.dayOfWeek = sdf1.format(arrivalTimeDate);
    }

    public Date getLeaveTimeDate() {
        return leaveDate;
    }

    public void setLeaveTimeDate(Date leaveTimeDate) {
        if (leaveTimeDate != null) this.leaveDate = leaveTimeDate;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getWorkingTime() {
        if (this.leaveDate != null && this.arrivalDate != null) {
            Date diff = new Date(this.leaveDate.getTime() - this.arrivalDate.getTime() - TUNE_NUMBER);
            String HH_MM_SS = "HH.mm.ss";
            SimpleDateFormat formatter = new SimpleDateFormat(HH_MM_SS);
            return formatter.format(diff);
        }
        return null;
    }

    @Override
    public String toString() {
        return "WorkTimeRecord{ " +
                "\narrivalDate: " + arrivalDate +
                "\nleaveDate: " + leaveDate +
                "\nDay of week: " + this.dayOfWeek;
    }
}