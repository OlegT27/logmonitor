package org.company.beans;

import java.time.LocalDate;

public class DateDurationPair {
    private LocalDate dateTime;
    private long seconds;

    public DateDurationPair(LocalDate dateTime, long seconds) {
        this.dateTime = dateTime;
        this.seconds = seconds;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }


}
