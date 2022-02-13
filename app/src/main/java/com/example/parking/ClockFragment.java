package com.example.parking;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.parking.ClockActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class ClockFragment extends Fragment{

    private View View;
    //你知道我對你不僅僅喜歡
    //你眼中卻沒有我想要的答案
    //這樣若即若離讓我很抓狂
    public TextView txv_enterTime,txv_setTime,txv_enterDate,txv_parktime,txv_cost;
    public TimePicker mTimePicker = null; // 時間選擇器
    public Calendar mCalendar = null; // 日曆
    public int mHour; // 時
    public int mMinute; // 分
    public int mYear, mMonth, mDay;
    public int count;
    public Button btn_ok,btn_cancel;
    private AlarmManager alarmManager;
    private PendingIntent pi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View = inflater.inflate(R.layout.fragment_clock, container, false);
        // UI元件的取得及設定
        txv_enterTime=View.findViewById(R.id.txv_enterTime);
        txv_setTime=View.findViewById(R.id.txv_setTime);
        txv_enterDate=View.findViewById(R.id.txv_enterDate);
        txv_parktime=View.findViewById(R.id.txv_parkTime);
        txv_cost=View.findViewById(R.id.txv_cost);
        txv_enterDate.setOnClickListener(enterdate);
        txv_enterTime.setOnClickListener(entertime);
        txv_setTime.setOnClickListener(set);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        String format = setDateFormat(mYear,mMonth,mDay);
        txv_enterDate.setText(format);
        bindViews();
        return View;
    }
    android.view.View.OnClickListener enterdate = new View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    count=(mYear*365+mMonth*30+mDay)-(year*365+month*30+day);
                    String format = setDateFormat(year,month,day);
                    txv_enterDate.setText(format);
                }

            }, mYear,mMonth, mDay).show();
        }
    };
    private String setDateFormat(int year,int monthOfYear,int dayOfMonth){
        return String.valueOf(year) + "-"
                + String.valueOf(monthOfYear + 1) + "-"
                + String.valueOf(dayOfMonth);
    }
    android.view.View.OnClickListener entertime = new View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {
            mCalendar = Calendar.getInstance();
            mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
            mMinute = mCalendar.get(Calendar.MINUTE);

            new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener(){

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String bminute;
                    if(minute==0){
                        bminute="00";
                    }else if (minute <10)
                    {
                        bminute="0"+String.valueOf(minute);
                    }else
                    {
                        bminute=String.valueOf(minute);
                    }
                    txv_enterTime.setText(hourOfDay + ":" + bminute);
                    txv_parktime.setText((mHour-hourOfDay+(count*24))+"小時"+(mMinute-minute)+"分鐘");
                    if((mMinute-minute)>0) {
                        if ((mHour - hourOfDay + (count * 24)) * 30 +30 > 200) {
                            txv_cost.setText("200元");
                        } else {
                            txv_cost.setText((mHour - hourOfDay + (count * 24)) * 30 + 30 + "元");
                        }
                    }else
                    {
                        if ((mHour - hourOfDay + (count * 24)) * 30 + 30> 200) {
                            txv_cost.setText("200元");
                        } else {
                            txv_cost.setText((mHour - hourOfDay + (count * 24)) * 30 + 30 + "元");
                        }
                    }
                }
            }, mHour, mMinute, false).show();
        }
    };

    private void bindViews() {
        btn_cancel = View.findViewById(R.id.btn_cancel);

        Intent intent = new Intent(getActivity(),ClockActivity.class);
        pi = PendingIntent.getActivity(getActivity(), 0, intent, 0);
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        btn_cancel.setOnClickListener(cancel);

    }
    android.view.View.OnClickListener set = new View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {

            Calendar currentTime = Calendar.getInstance();
            new TimePickerDialog(getActivity(), 0,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view,
                                              int hourOfDay, int minute) {
                            //设置当前时间
                            Calendar c = Calendar.getInstance();
                            c.setTimeInMillis(System.currentTimeMillis());
                            // 根据用户选择的时间来设置Calendar对象
                            c.set(Calendar.HOUR, hourOfDay);
                            c.set(Calendar.MINUTE, minute);
                            mYear = c.get(Calendar.YEAR);
                            mMonth = c.get(Calendar.MONTH);
                            mDay = c.get(Calendar.DAY_OF_MONTH);
                            String format = setDateFormat(mYear,mMonth,mDay);
                            // ②设置AlarmManager在Calendar对应的时间启动Activity
                            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
                            // 提示闹钟设置完毕:
                            txv_setTime.setText(format+"   "+hourOfDay+":"+minute);
                            Toast.makeText(getActivity(), "設置成功",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
                    .get(Calendar.MINUTE), false).show();
            btn_cancel.setVisibility(View.VISIBLE);
        }
    };
    android.view.View.OnClickListener cancel = new View.OnClickListener() {
        @Override
        public void onClick(android.view.View v) {
            alarmManager.cancel(pi);
            txv_setTime.setText("請選擇時間");
            Toast.makeText(getActivity(), "已取消", Toast.LENGTH_SHORT)
                    .show();
        }
    };
}
