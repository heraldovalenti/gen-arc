package com.hvalenti.freelance.inmobiliariaDC.util

import org.joda.time.DateTime
import org.joda.time.DateTimeField;
import org.joda.time.LocalDate
import org.joda.time.Months
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class DateUtil {
	
	public static final DEFAULT_FORMAT = "dd/MM/yyyy"
	
	public static int maxDayOfMonth(Date date) {
		DateTime dt = new DateTime(date)
		return dt.dayOfMonth().getMaximumValue()
	}
	
	public static Date addDays(Date date, int days) {
		DateTime dt = new DateTime(date)
		return dt.plusDays(days).toDate()
	}
	
	public static Date addMonths(Date date, int months) {
		DateTime dt = new DateTime(date)
		return dt.plusMonths(months).toDate()
	}
	
	public static Date dateFromNumbers(int year, int month, int day) {
		DateTime dt = new DateTime(year, month, day, 0 ,0)
		return dt.toDate()
	}
	
	public static Date dateFromString(String from) {
		DateTime dt = new DateTime(from)
		return dt.toDate()
	}
	
	public static int getMonthOfYear(Date from) {
		DateTime dt = new DateTime(from)
		return dt.getMonthOfYear()
	}
	
	public static int getYear(Date from) {
		DateTime dt = new DateTime(from)
		return dt.getYear()
	}
	
	public static int getDayOfMonth(Date from) {
		DateTime dt = new DateTime(from)
		return dt.getDayOfMonth()
	}
	
	public static int monthDifference(Date from, Date to) {
		DateTime dt1 = new DateTime(from)
		DateTime dt2 = new DateTime(to)
		Months monthsBetween = Months.monthsBetween(dt1, dt2)
		return monthsBetween.getMonths()
	}
	
	public static int relativeMonthDifference(Date from, Date to) {
		DateTime dt1 = new DateTime(from)
		DateTime dt2 = new DateTime(to)
		Months monthsBetween = Months.monthsBetween(dt1, dt2)
		int result = monthsBetween.getMonths()
		if (result == 0 && (dt1.getMonthOfYear() != dt2.getMonthOfYear()) ) {
			result++;
		}
		return result
	}
	
	public static String formatDate(Date date) {
		return formatDate(DEFAULT_FORMAT, date)
	}
	
	public static String formatDate(String pattern, Date date) {
		DateTime dt = new DateTime(date)
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
		return dt.toString(fmt);
	}
	
	public static Date lastDayOfMonth(Date when) {
		DateTime now = new DateTime(when)
		now = now.withMonthOfYear(now.monthOfYear + 1)
		now = now.withDayOfMonth(1)
		return now.minusDays(1).toDate()
	}

}
