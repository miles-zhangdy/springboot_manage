package com.zdy.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils implements Serializable{
    /**
	 * @Fields serialVersionUID : TODO
	 */
	
	 
	private static final long serialVersionUID = 7026732724008273471L;
	/**
     * HH:mm:ss
     */
    public static final String TIME_FORMAT = "HH:mm:ss";
    /**
     * yyyy-MM-dd
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * yyyyMMddHHmmss
     */
    public static final String UNIX_TIME_FORMAT = "yyyyMMddHHmmss";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyyMMddHHmmssSSS
     */
    public static final String TIMESTAMP_FORMAT_WEBSERVICE = "yyyyMMddHHmmssSSS";

    public static Integer formatUnixDate(String date, String fmt) {
        if (StringUtils.isBlank(date) || StringUtils.isBlank(fmt)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try {
            return (int) (sdf.parse(date).getTime() / 1000);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Long formatUnixDateMis(String date, String fmt) {
        if (StringUtils.isBlank(date) || StringUtils.isBlank(fmt)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try {
            return (Long) (sdf.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDate(long millis, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(millis));
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * 
     * @param millis
     * @return
     */
    public static String formatDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        return sdf.format(new Date(millis));
    }

    /**
     * yyyyMMddHHmmss
     * 
     * @param millis
     * @return
     */
    public static String formatUnixDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat(UNIX_TIME_FORMAT);
        return sdf.format(new Date(millis));
    }

    /**
     * yyyyMMddHHmmssSSS
     * 
     * @param millis
     * @return
     */
    public static String formatTimestampDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT_WEBSERVICE);
        return sdf.format(new Date(millis));
    }

    /**
     * HHmmss
     * 
     * @param millis
     * @return
     */
    public static String formatHHmmssDate(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        return sdf.format(new Date(millis));
    }

    /****
     * 标准日期格式 转换为 uinx时间戳 标准日期格式格式为 yyyy-MM-dd HH:mm:ss
     * 
     * @return 单位：秒
     */
    public static Integer getUnixTime(String fmtDate) {
        if (fmtDate == null || fmtDate.length() == 0) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        try {
            return (int) (sdf.parse(fmtDate).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /****
     * uinx时间戳转换为标准日期格式 格式为 yyyy-MM-dd HH:mm:ss
     * 
     * @param date 单位：秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String fromUinxTime(Integer date) {
        if (date == null || date.intValue() == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        try {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(date * 1000L);
            return sdf.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 
     * @Description:返回当前秒
     * @Date: 2014年5月15日 下午6:49:38
     * @return
     * @return Integer 单位：秒
     * @throws
     */
    public static Integer getUnixTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }
    
    public static Long getUnixTimeMis() {
        return System.currentTimeMillis();
    }
    
    public static String getWsTime() {
        SimpleDateFormat sf = new SimpleDateFormat(TIMESTAMP_FORMAT_WEBSERVICE);
        return sf.format(new Date());
    }

    /**
     * 
     * @param one
     * @param two
     * @return boolean
     */
    public static boolean isSameDay(long one, long two) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String ones = sdf.format(new Date(one));
        String twos = sdf.format(new Date(two));
        return ones.equals(twos);
    }

    public static class Day {
        public Long clock0 = 0L;
        public Long clock24 = 24L;
    }

    public static Day getDay(Date now) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Day today = new Day();
        today.clock0 = cal.getTimeInMillis();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        today.clock24 = cal.getTimeInMillis();
        return today;
    }

    /**
     * 中国 周一~周日
     */
    public static class Weak {
        public Long firstDayMonday = 0L;
        public Long lastDaySunday = 0L;
    }

    /**
     * 中国 周一~周日
     */
    public static Weak getWeak(Date now) {
        Weak w = new Weak();
        /** 以下先根据第一个时间找出所在周的星期一、星期日, 再对第二个时间进行比较 */
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        w.firstDayMonday = cal.getTimeInMillis();
        cal.add(Calendar.DATE, 6);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        w.lastDaySunday = cal.getTimeInMillis();

        /*
         * DateFormat datetimeDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); System.out.println("星期一开始时间：" +
         * datetimeDf.format(w.firstDayMonday)); System.out.println("星期日结束时间：" + datetimeDf.format(w.lastDaySunday));
         */
        return w;
    }

    public static class Month {
        public Long firstDay = 0L;
        public Long lastDay = 0L;
    }

    public static Month getMonth(Date now) {
        Month m = new Month();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        m.firstDay = cal.getTimeInMillis();

        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        m.lastDay = cal.getTimeInMillis();

        return m;
    }

    public static class Year {
        public Long firstDay = 0L;
        public Long lastDay = 0L;
    }

    public static Year getYear(Date now) {
        Year y = new Year();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        y.firstDay = cal.getTimeInMillis();

        cal.add(Calendar.YEAR, 1);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        y.lastDay = cal.getTimeInMillis();

        return y;
    }

    /**
     * 中国 周一~周日
     */
    public static boolean isSameWeak(long firstDate, long secondDate) {

        Weak w = getWeak(new Date(firstDate));
        // 比较第二个时间是否与第一个时间在同一周
        if (secondDate >= w.firstDayMonday && secondDate <= w.lastDaySunday) {
            return true;
        }
        return false;
    }

    /**
     * 洋玩意儿 周日-周六
     */
    public static boolean isSameWeak(Date d1, Date d2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        // subYear==0,说明是同一年
        if (subYear == 0) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        // 例子:cal1是"2005-1-1"，cal2是"2004-12-25"
        // java对"2004-12-25"处理成第52周
        // "2004-12-26"它处理成了第1周，和"2005-1-1"相同了
        // 大家可以查一下自己的日历
        // 处理的比较好
        // 说明:java的一月用"0"标识，那么12月用"11"
        else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        // 例子:cal1是"2004-12-31"，cal2是"2005-1-1"
        else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }
}
