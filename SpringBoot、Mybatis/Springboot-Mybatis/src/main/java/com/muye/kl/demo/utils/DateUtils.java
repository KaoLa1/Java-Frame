package com.muye.kl.demo.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_Z = "yyyy/MM/dd HH:mm:ss.SSS Z";
    public static final String DEFAULT_FORMAT_X = "yyyy/MM/dd HH:mm:ss";
    public static final String SHORT_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_MMDDHHMM = "MM-dd HH:mm";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FOMATE_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String REPORT_NAME_FORMAT = "yyyyMMddHH";
    public static final String START_FORMAT = "yyyy-MM-dd 00:00:00";
    public static final String END_FORMAT = "yyyy-MM-dd 23:59:59";
    public static final String MM_START_FORMAT = "yyyy-MM-dd 00:00";
    public static final String MM_END_FORMAT = "yyyy-MM-dd 23:59";
    public static final String DATE_HOUR_FORMAT = "yyyy-MM-dd HH";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

    public static String getDate()
    {
        return getDate("yyyy-MM-dd");
    }

    public static String getDate(String pattern)
    {
        return DateFormatUtils.format(new Date(), pattern);
    }

    public static String dateToString(Date date, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    public static String formatDate(Date date, Object[] pattern)
    {
        String formatDate = null;
        if ((pattern != null) && (pattern.length > 0))
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        else
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");

        return formatDate;
    }

    public static String formatDateTime(Date date)
    {
        return formatDate(date, new Object[] { "yyyy-MM-dd HH:mm:ss" });
    }

    public static String getTime()
    {
        return formatDate(new Date(), new Object[] { "HH:mm:ss" });
    }

    public static String getDateTime()
    {
        return formatDate(new Date(), new Object[] { "yyyy-MM-dd HH:mm:ss" });
    }

    public static String getYear()
    {
        return formatDate(new Date(), new Object[] { "yyyy" });
    }

    public static String getMonth()
    {
        return formatDate(new Date(), new Object[] { "MM" });
    }

    public static String getDay()
    {
        return formatDate(new Date(), new Object[] { "dd" });
    }

    public static String getWeek()
    {
        return formatDate(new Date(), new Object[] { "E" });
    }

    public static Date parseDate(Object str)
    {
        if (str == null)
            return null;
        try
        {
            return parseDate(str.toString(), parsePatterns); } catch (ParseException e) {
        }
        return null;
    }

    public static long pastDays(Date date)
    {
        long t = new Date().getTime() - date.getTime();
        return (t / 86400000L);
    }

    public static long pastHour(Date date)
    {
        long t = new Date().getTime() - date.getTime();
        return (t / 3600000L);
    }

    public static long pastMinutes(Date date)
    {
        long t = new Date().getTime() - date.getTime();
        return (t / 60000L);
    }

    public static String formatDateTime(long timeMillis)
    {
        long day = timeMillis / 86400000L;
        long hour = timeMillis / 3600000L - day * 24L;
        long min = timeMillis / 60000L - day * 24L * 60L - hour * 60L;
        long s = timeMillis / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        long sss = timeMillis - day * 24L * 60L * 60L * 1000L - hour * 60L * 60L * 1000L - min * 60L * 1000L - s * 1000L;
        return new StringBuilder().append((day > 4093461968935976960L) ? new StringBuilder().append(day).append(",").toString() : "").append(hour).append(":").append(min).append(":").append(s).append(".").append(sss).toString();
    }

    public static double getDistanceOfTwoDate(Date before, Date after)
    {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return ((afterTime - beforeTime) / 86400000L);
    }

    public static Date getDateAfter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(5);
        c.set(5, day + 1);
        return c.getTime();
    }

    public static Date getDateBefore(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(5);
        c.set(5, day - 1);
        return c.getTime();
    }

    public static Date getFirstDayOfWeek(Date date)
    {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(2);
        c.setTime(date);
        c.set(7, c.getFirstDayOfWeek());
        return c.getTime();
    }

    public static Date getLastDayOfWeek(Date date)
    {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(2);
        c.setTime(date);
        c.set(7, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    public static Date getFirstDayOfMonth(Date date)
    {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(5, 1);
        return c.getTime();
    }

    public static Date getLastDayOfMonth(Date date)
    {
        Calendar c = new GregorianCalendar();

        c.setTime(date);
        c.add(2, 1);
        c.set(5, -1);

        return c.getTime();
    }

    public static String getYearMonth(int num, boolean flag)
    {
        Date date = new Date();
        String cuDate = DateFormatUtils.format(date, "yyyyMM");
        Calendar cal = Calendar.getInstance();
        int cuM = cal.get(2) + 1;
        if (num >= 0) {
            if (flag)
                return String.valueOf(cuM);

            return cuDate;
        }

        int numABS = Math.abs(num);
        if (numABS > 12) {
            if (flag)
                return String.valueOf(cuM);

            return cuDate;
        }

        int month = cal.get(2) + 1;
        int year = cal.get(1);
        int rm = month - numABS;
        if (rm <= 0)
            --year;

        int m = (rm > 0) ? rm : (rm == 0) ? 12 : 12 - Math.abs(rm);
        String mon = (m < 10) ? new StringBuilder().append("0").append(String.valueOf(m)).toString() : String.valueOf(m);
        if (flag)
            return String.valueOf(m);

        return new StringBuilder().append(String.valueOf(year)).append(mon).toString();
    }

    public static String getChineseMonth(int month)
    {
        String[] cMonth = { "零", "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };
        return cMonth[month];
    }

    public static Date addDay(Date beforeDate, int n)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(beforeDate);
        calendar.add(5, n);
        Date afterDate = calendar.getTime();
        return afterDate;
    }

    public static Timestamp string2Timestamp(String dateString)
            throws ParseException
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

        dateFormat.setLenient(false);
        Date timeDate = dateFormat.parse(dateString);
        Timestamp dateTime = new Timestamp(timeDate.getTime());
        return dateTime;
    }

    public static void main(String[] args)
            throws ParseException
    {
        System.out.println(new StringBuilder().append("DateUtils.getDateBefore(new Date())==").append(DateFormatUtils.format(getDateBefore(new Date()), parsePatterns[1])).toString());
        System.out.println(new StringBuilder().append("DateUtils.getDateAfter(new Date())==").append(DateFormatUtils.format(getDateAfter(new Date()), parsePatterns[1])).toString());
        System.out.println(new StringBuilder().append("DateUtils.getFirstDayOfWeek(new Date())==").append(DateFormatUtils.format(getFirstDayOfWeek(new Date()), parsePatterns[1])).toString());
        System.out.println(new StringBuilder().append("DateUtils.getLastDayOfWeek(new Date())==").append(DateFormatUtils.format(getLastDayOfWeek(new Date()), parsePatterns[1])).toString());
        System.out.println(new StringBuilder().append("DateUtils.getFirstDayOfMonth(new Date())==").append(DateFormatUtils.format(getFirstDayOfMonth(new Date()), parsePatterns[1])).toString());
        System.out.println(new StringBuilder().append("DateUtils.getLastDayOfMonth(new Date())==").append(DateFormatUtils.format(getLastDayOfMonth(new Date()), parsePatterns[1])).toString());
    }
}
