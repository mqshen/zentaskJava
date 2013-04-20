package org.goldratio.util;

import java.util.Calendar;
import java.util.Date;

/** 
 * ClassName: DateUtil <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 11, 2013 5:44:24 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class DateUtil {
	
	public static boolean isBeforeOrSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isBeforeOrSameDay(cal1, cal2);
    }
	
	public static boolean isBeforeOrSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA) || 
                cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR) || 
                cal1.get(Calendar.DAY_OF_YEAR) <= cal2.get(Calendar.DAY_OF_YEAR));
    }
	
	
	public static boolean isBeforeDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isBeforeDay(cal1, cal2);
    }
	
	public static boolean isBeforeDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA) || 
                cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR) || 
                cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR));
    }
	
}
