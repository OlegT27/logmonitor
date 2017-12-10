package org.company.util;

import org.company.beans.DateDurationPair;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class SessionDurationResolver {

    private long sessionLength;
    private LocalDateTime start;
    private LocalDateTime end;

    public SessionDurationResolver(long sessionStart, long sessionLength) {
        this.sessionLength = sessionLength;
        this.start = LocalDateTime.ofInstant(Instant.ofEpochSecond(sessionStart), ZoneId.systemDefault());
        this.end = getEndOfDay(start);
    }

    private LocalDateTime getStartOfDay(LocalDateTime time) {
        return time.toLocalDate().atStartOfDay();
    }

    private LocalDateTime getEndOfDay(LocalDateTime time) {
        return getStartOfDay(time).plusDays(1);
    }

    private void debugPrinter(long capacity) {
        System.out.println(start.toLocalDate());
        System.out.println(capacity);
    }


    public List<DateDurationPair> resolveDuration() {

        ArrayList<DateDurationPair> slices = new ArrayList<>();
        long capacity = ChronoUnit.SECONDS.between(start, end);

        while (capacity < sessionLength) {
            slices.add(new DateDurationPair(start.toLocalDate(), capacity));
            sessionLength -= capacity;
            start = end;
            end = getEndOfDay(start);
            capacity = ChronoUnit.SECONDS.between(start, getEndOfDay(start));
        }
        if (sessionLength != 0) {
            slices.add(new DateDurationPair(start.toLocalDate(), sessionLength));
        }
        return slices;
    }

}

