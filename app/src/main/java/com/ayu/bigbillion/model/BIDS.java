package com.ayu.bigbillion.model;

public class BIDS {
    String number,points;
    public BIDS(){

    }

    public BIDS(String number, String points) {
        this.number = number;
        this.points = points;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
