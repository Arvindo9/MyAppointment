/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.king.methods;

import com.king.algo.CalendarYear;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Arvindo on 19-01-2017.
 * Company KinG
 * email at support@towardtheinfinity.com
 */
public class HandlingCalender {
    
    private CalendarYear calendarObj;

    public HandlingCalender() {
        this.calendarObj = new CalendarYear();
    }
    
    void handlingMonth(){
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");    
        Date resultdate = new Date(yourmilliseconds);
        System.out.println(sdf.format(resultdate));
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        
        int[][] monthArray = calendarObj.calenderMonth(mYear, mMonth+1);

        int af=0;

    }

    public int[][] currentMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);

        return new CalendarYear().calenderMonth(mYear, mMonth+1);
    }

    public int[][] thisMonth(int year, int month){

        return new CalendarYear().calenderMonth(year, month+1);
    }

    public int getCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        return calendar.get(Calendar.YEAR);
    }

    public int getCurrentMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        return calendar.get(Calendar.MONTH);
    }

    public String getCurrentTime(){
        return new Date(System.currentTimeMillis()).getHours() + ":" +
                new Date(System.currentTimeMillis()).getMinutes();
    }

    public String getMonthName(int month){
        String s = "";
        switch (month){
            case 0: s = "Jan";
            break;
            case 1: s = "Feb";
                break;
            case 2: s = "Mar";
            break;
            case 3: s = "Apr";
            break;
            case 4: s = "May";
            break;
            case 5: s = "June";
            break;
            case 6: s = "July";
            break;
            case 7: s = "Aug";
            break;
            case 8: s = "Sep";
            break;
            case 9: s = "Oct";
            break;
            case 10: s = "Nov";
            break;
            case 11: s = "Dec";
            break;
        }
        return s;
    }
    
}
