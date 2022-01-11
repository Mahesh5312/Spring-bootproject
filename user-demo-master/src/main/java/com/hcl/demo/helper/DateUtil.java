package com.hcl.demo.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

public class DateUtil {
	
	private static final Logger log = LogManager.getLogger(DateUtil.class);
	
	@Value("${spring.jackson.time-zone}")
	private String timezone;
	
	@Value("${date.time.format}")
	private String format;
	
	public Date parseDate(String stringDateTime) {
		Date datetime = null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(TimeZone.getTimeZone(timezone));
		try {
			datetime = formatter.parse(stringDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datetime;
	}

}
