package com.duyi.commons.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 日期时间工具类
 * @author wangyan
 *
 */
public abstract class TimeUtils {

	/**
	 * 指定日期增加分钟数
	 * @param date
	 * @param minutes 分钟
	 * @return
	 */
	public static Date plusMinutes(Date date,long minutes) {
		Instant instant=date.toInstant();
		LocalDateTime dateTime=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		dateTime=dateTime.plusMinutes(minutes);
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		
	}
	public static Date plusDays(Date date,int days) {
		Instant instant=date.toInstant();
		LocalDateTime dateTime=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		dateTime=dateTime.plusDays(days);
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		
	}
	public static Date minusDays(Date date,int days) {
		Instant instant=date.toInstant();
		LocalDateTime dateTime=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		dateTime=dateTime.minusDays(days);
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

}
