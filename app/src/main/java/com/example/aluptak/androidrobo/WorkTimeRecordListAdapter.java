package com.example.aluptak.androidrobo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aluptak.androidrobo.exception.WorkTimeRecord;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by aluptak on 02/02/2016.
 */
public class WorkTimeRecordListAdapter extends BaseAdapter {

    private List _productList;
    private Context _context;

    public WorkTimeRecordListAdapter(Context context, List products) {
        _context = context;
        _productList = products;
    }


    static class ViewHolder {
        protected TextView arrivalTime, leaveTime,
                dayOfWeek, workTime;
    }

    @Override
    public int getCount() {
        return _productList.size();
    }

    @Override
    public Object getItem(int position) {
        return _productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(_context);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.work_time_record_list_row, null);
            holder = new ViewHolder();

            holder.arrivalTime = (TextView) convertView
                    .findViewById(R.id.arrivalTime);
            holder.leaveTime = (TextView) convertView
                    .findViewById(R.id.leaveTime);
            holder.dayOfWeek = (TextView) convertView
                    .findViewById(R.id.dayOfWeek);
            holder.workTime = (TextView) convertView
                    .findViewById(R.id.workTime);
//            dayOfWeek.setText(workTimeRecord.getDayOfWeek().toString());
//            workTime.setText(workTimeRecord.getWorkingTime().toString());

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WorkTimeRecord workTimeRecord = (WorkTimeRecord) _productList.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
        if (workTimeRecord != null) {

            try {

                holder.arrivalTime.setText(String.format(_context
                        .getString(R.string.list_product_code_format,
                                sdf.format(workTimeRecord.getArrivalTimeDate()))));
                holder.leaveTime.setText(String
                        .format(_context.getString(
                                R.string.list_product_description_format,
                                sdf.format(workTimeRecord.getLeaveTimeDate()))));
                holder.dayOfWeek.setText(workTimeRecord.getDayOfWeek());
                holder.workTime.setText(workTimeRecord.getWorkingTime());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return convertView;

    }

}
