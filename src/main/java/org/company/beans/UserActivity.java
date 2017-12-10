package org.company.beans;

import java.util.List;

public class UserActivity {

    private String userID;
    private List<URLInfo> urlList;

    public UserActivity() {
    }

    public UserActivity(String userID, List<URLInfo> urlList) {
        this.userID = userID;
        this.urlList = urlList;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<URLInfo> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<URLInfo> urlList) {
        this.urlList = urlList;
    }

    public UserActivity concatUrl(UserActivity userActivity) {
        this.urlList.addAll(userActivity.urlList);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserActivity that = (UserActivity) o;

        if (userID != null ? !userID.equals(that.userID) : that.userID != null) return false;
        return urlList != null ? urlList.equals(that.urlList) : that.urlList == null;
    }

    @Override
    public int hashCode() {
        int result = userID != null ? userID.hashCode() : 0;
        result = 31 * result + (urlList != null ? urlList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "userID='" + userID + '\'' + "\n" +
                ", urlList=" + urlList +
                '}';
    }
}
