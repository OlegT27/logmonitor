package org.company.beans;

import com.opencsv.bean.CsvBindByPosition;

import java.time.LocalDate;

public class URLInfo {

    @CsvBindByPosition(position = 1)
    private String url;
    @CsvBindByPosition(position = 2)
    private double duration;
    @CsvBindByPosition(position = 3)
    private LocalDate date;

    public URLInfo(String url, double duration, LocalDate date) {
        this.url = url;
        this.duration = duration;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        URLInfo urlInfo = (URLInfo) o;

        if (url != null ? !url.equals(urlInfo.url) : urlInfo.url != null) return false;
        return date != null ? date.equals(urlInfo.date) : urlInfo.date == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "url='" + url + '\'' +
                ", duration=" + duration +
                ", date=" + date +
                '}' + '\n';
    }

}
