package com.example.urc;

public class BookedTicket extends Ticket{
    String number;

    public BookedTicket(String number, String from, String to, String timeFrom, String timeTo, String overall) {
        super(from, to, timeFrom, timeTo, overall);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
