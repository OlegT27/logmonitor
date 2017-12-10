package org.company.util;

import org.company.beans.DateDurationPair;
import org.company.beans.InputFileRow;
import org.company.beans.URLInfo;
import org.company.beans.UserActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputDataPreparation {

    // формируем юзера с одной строки лога
    private static UserActivity prepareUser(InputFileRow row) {
        UserActivity user = new UserActivity();
        user.setUserID(row.getUserId());
        List<URLInfo> urlList = parseUrlDuration(row);
        user.setUrlList(urlList);
        return user;
    }

    // разные времена для одной урлки
    private static List<URLInfo> parseUrlDuration(InputFileRow row) {
        List<URLInfo> list = new ArrayList<>();
        SessionDurationResolver resolver = new SessionDurationResolver(row.getTimestamp(), row.getDuration());
        List<DateDurationPair> pairs = resolver.resolveDuration();
        for (DateDurationPair pair :
                pairs) {
            list.add(new URLInfo(row.getUserUrl(), pair.getSeconds(), pair.getDateTime()));
        }
        return list;
    }

    public static Map<String, UserActivity> mergeLinks(List<InputFileRow> rawData) {
        Map<String, UserActivity> temporalStorage = new HashMap();
        for (InputFileRow row :
                rawData) {
            UserActivity user = InputDataPreparation.prepareUser(row);
            temporalStorage.merge(user.getUserID(), user, UserActivity::concatUrl);
        }
        return temporalStorage;
    }
}

