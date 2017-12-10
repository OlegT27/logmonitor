package org.company.beans;

import com.opencsv.bean.CsvBindByName;

public class OutputBean implements Comparable<OutputBean>{
    @CsvBindByName(column = "ID")
    private String userID;
    @CsvBindByName(column = "LINK")
    private String URL;
    @CsvBindByName(column = "SECONDS")
    private double seconds;

    public OutputBean() {
    }

    public OutputBean(String userID, String URL, double seconds) {
        this.userID = userID;
        this.URL = URL;
        this.seconds = seconds;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutputBean that = (OutputBean) o;

        if (Double.compare(that.seconds, seconds) != 0) return false;
        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        return URL != null ? URL.equals(that.URL) : that.URL == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (URL != null ? URL.hashCode() : 0);
        temp = Double.doubleToLongBits(seconds);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public int compareTo(OutputBean o) {
        return this.userID.compareTo(o.userID);
    }
}
