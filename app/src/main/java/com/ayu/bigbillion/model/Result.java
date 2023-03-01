package com.ayu.bigbillion.model;

public class Result {
    String date,day,month,year,DS,GB,GL,FD;
    public Result(){

    }

    public Result(String date, String day, String month, String year, String DS, String GB, String GL, String FD) {
        this.date = date;
        this.day = day;
        this.month = month;
        this.year = year;
        this.DS = DS;
        this.GB = GB;
        this.GL = GL;
        this.FD = FD;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDS() {
        return DS;
    }

    public void setDS(String DS) {
        this.DS = DS;
    }

    public String getGB() {
        return GB;
    }

    public void setGB(String GB) {
        this.GB = GB;
    }

    public String getGL() {
        return GL;
    }

    public void setGL(String GL) {
        this.GL = GL;
    }

    public String getFD() {
        return FD;
    }

    public void setFD(String FD) {
        this.FD = FD;
    }
}
