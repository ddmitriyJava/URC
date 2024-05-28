package com.example.urc;

import java.io.Serializable;

public class Ticket implements Serializable {
    String from;
    String to;
    String timeFrom;
    String timeTo;
    String overall;

    public Ticket(String from, String to, String timeFrom, String timeTo, String overall) {
        this.from = from;
        this.to = to;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.overall = overall;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public String getOverall() {
        return overall;
    }
}
