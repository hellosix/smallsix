package cn.hellosix.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lzz on 2018/10/3.
 */
public class TimeUtil {
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String res = seconds;
        try {
            res = sdf.format(new Date(Long.valueOf(seconds)));
        } catch (Exception ignore) {

        }
        return seconds;
    }

    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getDay(){
        Date dNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDay = sdf.format(dNow); //格式化当前时间
        return Integer.valueOf( currentDay );
    }
    public static int getHour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinute(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    public static int getTimeStamp(String dateStr, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat( format );
        int timeStemp = 0;
        Date date= null;
        date = simpleDateFormat .parse( dateStr );
        if( null == date ){
            return 0;
        }
        long tmp = date.getTime();
        timeStemp = (int) (tmp / 1000);
        return timeStemp;
    }

    /**
     * 获取当前某个小时的时间戳
     * @param ihour
     * @return
     */
    public static int getBeforeHourTime(int ihour){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - ihour);
        return (int) (calendar.getTimeInMillis()/1000);
    }

    /**
     * 获取当前某分钟的时间戳
     * @param num
     * @return
     */
    public static int getBeforeMinutesTime(int num){
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -num);
        Date beforeD = beforeTime.getTime();
        return (int) (beforeD.getTime()/1000);
    }

    public static int timeStamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }
}
