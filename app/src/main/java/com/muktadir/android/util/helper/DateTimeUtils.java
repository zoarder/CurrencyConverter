/*
 * Copyright (C) 2017 MUKTADIR
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://muktadir.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.muktadir.android.util.helper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.TextView;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * ****************************************************************************
 * * Copyright © 2017 MUKTADIR, All rights reserved.
 * *
 * * Created by:
 * * Name : ZOARDER AL MUKTADIR
 * * Date : 10/25/2018
 * * Email : muktadir@muktadir.com
 * *
 * * Purpose :
 * *
 * * Last Edited by : ZOARDER AL MUKTADIR on 10/25/2018.
 * * History:
 * * 1: Create the Class
 * * 2:
 * *
 * * Last Reviewed by : ZOARDER AL MUKTADIR on 10/25/2018.
 * ****************************************************************************
 */

public class DateTimeUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "hh:mm a";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd, hh:mm a";
    public static final String DATE_TIME_FORMAT_CREATED_AT = "yyyy-MM-dd HH:mm:ss";
    public static final String DURATION_FORMAT = "y M d H m s S";
    public static final String DURATION_FORMAT_CREATED_AT = "y M d H m s";
    private static final String[] DURATION_FORMAT_TOKENS = {" Year", " Month", " Day", " Hour", " Minute", " Second"};
    private static final String[] DURATION_FORMAT_TOKENS_CREATED_AT = {" Year", " Month", " Day", " Hour", " Minute", " Second"};

    public static String getDateTimeFromMilliSeconds(long milliSeconds, String dateTimeFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat, Locale.US);
        formatter.setLenient(false);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static long getMilliSecondsFromDateTime(String dateTime, String dateTimeFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat, Locale.US);
        formatter.setLenient(false);

        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(formatter.parse(dateTime));
//            calendar.set(Calendar.SECOND, 59);
//            calendar.set(Calendar.MILLISECOND, 999);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }

    public static void setDateFromDatePicker(final Activity activity, int year, int month, int day, final String dateFormat, final TextView textView) {

        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(activity,
                (view, selectedYear, selectedMonthOfYear, selectedDayOfMonth) -> {
                    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.US);
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(selectedYear, selectedMonthOfYear, selectedDayOfMonth);
                    Date date = new Date(calendar.getTimeInMillis());
                    textView.setText(formatter.format(date));
                }, year, month, day);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }

    public static void setTimeFromTimePicker(final Activity activity, int hour, int minute, final int second, final String timeFormat, final TextView textView) {
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(activity, (timePicker, selectedHour, selectedMinute) -> {
            SimpleDateFormat formatter = new SimpleDateFormat(timeFormat, Locale.US);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
            calendar.set(Calendar.MINUTE, selectedMinute);
            calendar.set(Calendar.SECOND, second);
            Time time = new Time(calendar.getTimeInMillis());
            textView.setText(formatter.format(time));
        }, hour, minute, false);//Yes 24 hour time
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public static String getDurationFromMilliSeconds1(long milliSeconds, String durationFormat) {
        StringBuilder durationString = new StringBuilder(DurationFormatUtils.formatDuration(milliSeconds, durationFormat));
        StringTokenizer stringTokenizer = new StringTokenizer(durationString.toString());

        durationString = new StringBuilder();
        for (int i = 0; stringTokenizer.hasMoreElements(); i++) {
            int n = Integer.parseInt(stringTokenizer.nextToken());
            if (n > 0) {
                if (durationString.length() > 0) {
                    durationString.append(", ");
                }
                durationString.append(n).append(DURATION_FORMAT_TOKENS[i]);
                if (n > 1) {
                    durationString.append("s");
                }
            }
        }
        return durationString.toString();
    }

    public static String getDurationFromMilliSeconds2(long milliSeconds, String durationFormat) {
        String durationString = DurationFormatUtils.formatDuration(milliSeconds, durationFormat);
        String[] durationStringToken = durationString.split(" ");
        int day = Integer.parseInt(durationStringToken[2]);
//        durationString = durationString.substring(4, durationString.length() - 1);
        if (day > 0) {
            int month = day / 30;
            day = day % 30;
            if (month > 0) {
                int year = month / 12;
                month = month % 12;
                durationStringToken[0] = year + "";
                durationStringToken[1] = month + "";
                durationStringToken[2] = day + "";
            }
        }

        ShowLog.e("durationString: ", durationString);
//        StringTokenizer stringTokenizer = new StringTokenizer(durationString);

        durationString = "";
        for (int i = 0; i < durationStringToken.length; i++) {
            int n = Integer.parseInt(durationStringToken[i]);
            if (n > 0) {
                if (n > 1) {
                    durationString = n + DURATION_FORMAT_TOKENS_CREATED_AT[i] + "s ago";
                } else {
                    durationString = "A" + DURATION_FORMAT_TOKENS_CREATED_AT[i] + " ago";
                }
                break;
            }
        }
        return durationString;
    }

    public static boolean isValidDateTimeFormat(String dateTime, String dateTimeFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateTimeFormat, Locale.US);
        formatter.setLenient(false);

        Calendar calendar = Calendar.getInstance();
        Boolean mark = false;
        try {
            calendar.setTime(formatter.parse(dateTime));
            mark = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mark;
    }
}
