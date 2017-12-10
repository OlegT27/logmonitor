package org.company.util;

import org.company.beans.OutputBean;
import org.company.beans.URLInfo;
import org.company.beans.UserActivity;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OutputDataPreparation {

    public static Map<LocalDate, List<OutputBean>> mapToOutput(Map<String, UserActivity> map) {

        Map<LocalDate, List<OutputBean>> outMap = new HashMap<>();
        for (String key : map.keySet()) {
            UserActivity user = map.get(key);
            countAverageDuration(user);
            for (URLInfo url : user.getUrlList()) {
                List<OutputBean> list = new ArrayList<>();
                OutputBean bean = new OutputBean();
                bean.setUserID(user.getUserID());
                bean.setSeconds(url.getDuration());
                bean.setURL(url.getUrl());
                list.add(bean);
                outMap.merge(url.getDate(), list, (oldValue, newValue) -> {
                    newValue.addAll(oldValue);
                    return newValue;
                });
            }
        }
        return outMap;
    }

    public static UserActivity countAverageDuration(UserActivity userActivity) {
        List<URLInfo> list = userActivity.getUrlList();
        List<URLInfo> modifiedList = list.stream().distinct().collect(Collectors.toList());
        for (URLInfo uniqueElement : modifiedList) {
            double sum = 0;
            for (URLInfo value : list) {
                if (uniqueElement.equals(value)) {
                    sum += value.getDuration();
                }
            }
            double avg = sum / Collections.frequency(list, uniqueElement);
            uniqueElement.setDuration(avg);
        }
        userActivity.setUrlList(modifiedList);
        return userActivity;
    }


}
