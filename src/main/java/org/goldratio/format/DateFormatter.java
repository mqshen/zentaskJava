package org.goldratio.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.format.Formatter;

/** 
 * ClassName: DateFormatter <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 12, 2013 3:44:16 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class DateFormatter  implements Formatter<Date> {

	private String pattern;
	
	public DateFormatter(String pattern){
		this.pattern = pattern;
	}
	
	public String print(Date date, Locale locale) {
		if (date == null) {
			return "";
		}
		return getDateFormat(locale).format(date);
	}

	public Date parse(String formatted, Locale locale) throws ParseException {
		if (StringUtils.isBlank(formatted)) {
			return null;
		}
		return (Date) getDateFormat(locale).parse(formatted);
	}

	protected SimpleDateFormat getDateFormat(Locale locale) {
		return new SimpleDateFormat(this.pattern);
	}
}