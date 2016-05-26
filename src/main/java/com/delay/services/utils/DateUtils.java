package com.delay.services.utils;

import com.delay.services.BaseService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class DateUtils extends BaseService {

    public Date getLastDayMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public Date getFirstDayMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public Date toDate(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(date);
        calendar.setTime(startDate);
        return calendar.getTime();
    }

    public Date monthToDate(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date startDate = new Date();
        if (date != null) {
            startDate = format.parse(date);
        }
        calendar.setTime(startDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
}
