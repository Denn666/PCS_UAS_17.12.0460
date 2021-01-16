package com.pcs.uas.model;

public class Data {
    private String title;
    private String date;
    private String score;
    private String away;
    private String home;
    private String image;


    public Data(String title, String date, String score, String away, String home, String image) {
        this.title = title;
        this.date = date;
        this.score = score;
        this.away = away;
        this.home = home;
        this.image = image;

    }





    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
