package me.sandlz.chatuidemo.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by liuzhu on 2016/11/22.
 * Description :
 * Usage :
 */
public class TimeUtil {
    // 获取今天开始/结束时间
    public static List getTodayStartAndEndTime() {

        List result = new ArrayList();
        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.set(Calendar.HOUR_OF_DAY, 0);
        localCalendar1.set(Calendar.MINUTE, 0);
        localCalendar1.set(Calendar.SECOND, 0);
        localCalendar1.set(Calendar.MILLISECOND, 0);
        Date localDate1 = localCalendar1.getTime();
        long l1 = localDate1.getTime();

        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar2.set(Calendar.HOUR_OF_DAY, 23);
        localCalendar2.set(Calendar.MINUTE, 59);
        localCalendar2.set(Calendar.SECOND, 59);
        localCalendar2.set(Calendar.MILLISECOND, 999);
        Date localDate2 = localCalendar2.getTime();
        long l2 = localDate2.getTime();

        result.add(l1);
        result.add(l2);

        return result;
    }

    public static List getYesterdayStartAndEndTime() {
        List result = new ArrayList();
        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.add(Calendar.DAY_OF_MONTH, -1);//5
        localCalendar1.set(Calendar.HOUR_OF_DAY, 0);//11
        localCalendar1.set(Calendar.MINUTE, 0);//12
        localCalendar1.set(Calendar.SECOND, 0);//13
        localCalendar1.set(Calendar.MILLISECOND, 0);//Calendar.MILLISECOND
        Date localDate1 = localCalendar1.getTime();
        long l1 = localDate1.getTime();


        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar2.add(Calendar.DAY_OF_MONTH, -1);//5
        localCalendar2.set(Calendar.HOUR_OF_DAY, 23);//11
        localCalendar2.set(Calendar.MINUTE, 59);//12
        localCalendar2.set(Calendar.SECOND, 59);//13
        localCalendar2.set(Calendar.MILLISECOND, 999);//Calendar.MILLISECOND
        Date localDate2 = localCalendar2.getTime();
        long l2 = localDate2.getTime();

        result.add(l1);
        result.add(l2);

        return result;
    }

}
