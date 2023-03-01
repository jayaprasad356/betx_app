package com.ayu.bigbillion.model;

public class Withdrawal {
    String id,points,status,date_created;
    public Withdrawal(){

    }

    public Withdrawal(String id, String points, String status, String date_created) {
        this.id = id;
        this.points = points;
        this.status = status;
        this.date_created = date_created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }
}
