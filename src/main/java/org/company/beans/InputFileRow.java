package org.company.beans;

import com.opencsv.bean.CsvBindByName;

public class InputFileRow {

    @CsvBindByName(column = "ID user")
    private String userId;
    @CsvBindByName(column = "URL")
    private String userUrl;
    @CsvBindByName(column = "time")
    private long timestamp;
    @CsvBindByName(column = "seconds")
    private long duration;

    public InputFileRow() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "userId='" + userId + '\'' +
                        ", userUrl='" + userUrl + '\'' +
                        ", timestamp=" + timestamp +
                        ", duration=" + duration +
                        '}';
    }

    public InputFileRow(String userId, String userUrl, long timestamp, long duration) {
        this.userId = userId;
        this.userUrl = userUrl;
        this.timestamp = timestamp;
        this.duration = duration;
    }


}
