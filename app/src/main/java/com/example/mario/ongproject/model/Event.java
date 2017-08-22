package com.example.mario.ongproject.model;

import android.graphics.Bitmap;

/**
 * Created by mario on 10/07/2017.
 */

public class Event {

    private String title;
    private String date;
    private String time;
    private String place;
    private String description;
    private Bitmap image;
    private String image_src;

    public Event(String title, String date, String place, String description, String image_src) {
        this.title = title;
        this.date = date.substring(0,10);
        this.time = date.substring(13);
        this.place = place;
        this.description = description;
        this.image_src = image_src;
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

    public String getImg_src(){
        return image_src;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImg_src(Bitmap img_src) {
        this.image = img_src;
    }
}
