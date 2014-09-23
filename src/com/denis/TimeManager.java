package com.denis;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author: Denis Kotkov
 */
public class TimeManager {
    private long startTime;
    private long endTime;

    public void start() {
        startTime = new Date().getTime();
    }

    public void end() {
        endTime = new Date().getTime();
    }

    public long getMillisecondsTimePeriod() {
        return endTime - startTime;
    }

    public String getCurrentDateText(String lang) {
        if (lang == null) {
            lang = "en";
        }
        Locale loc = new Locale(lang);
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, loc);
        return df.format(new Date());
    }
}
