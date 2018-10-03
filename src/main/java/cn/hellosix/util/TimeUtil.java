package cn.hellosix.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lzz on 2018/10/3.
 */
public class TimeUtil {
    public static String timeStamp2Date(String seconds,String format) {
      if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
                  return "";
               }
              if(format == null || format.isEmpty()){
                format = "yyyy-MM-dd HH:mm:ss";
              }
          SimpleDateFormat sdf = new SimpleDateFormat(format);
           return sdf.format(new Date(Long.valueOf(seconds)));
     }

    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
            } catch (Exception e) {
            e.printStackTrace();
               }
        return "";
       }

    public static String timeStamp(){
     long time = System.currentTimeMillis();
       String t = String.valueOf(time/1000);
        return t;}
}
