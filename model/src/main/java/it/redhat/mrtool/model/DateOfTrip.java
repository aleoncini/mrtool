package it.redhat.mrtool.model;

public class DateOfTrip {
    private int year;
    private int month;
    private int day;

    public int getYear() {
        return year;
    }

    public DateOfTrip setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public DateOfTrip setMonth(int month) {
        this.month = month;
        return this;
    }

    public int getDay() {
        return day;
    }

    public DateOfTrip setDay(int day) {
        this.day = day;
        return this;
    }
}