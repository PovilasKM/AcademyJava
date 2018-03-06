package it.academy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    public static Date getDateFromString(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException ex) {
            date = new Date();
        }

        return date;
    }

    public static long differenceInDays(Date date1, Date date2) {
        long millis1 = date1.getTime();
        long millis2 = date2.getTime();

        return TimeUnit.MILLISECONDS.toDays(millis1 - millis2);
    }

    public static Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);

        return cal.getTime();
    }

}
