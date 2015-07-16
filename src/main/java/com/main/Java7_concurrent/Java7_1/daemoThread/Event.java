package com.main.Java7_concurrent.Java7_1.daemoThread;

import java.util.Date;

/**
 * Author:  satansk
 * Date:    17:19 at 2015/7/11
 * Email:   satansk@hotmail.com
 */
public class Event {
    private Date date;
    private String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
