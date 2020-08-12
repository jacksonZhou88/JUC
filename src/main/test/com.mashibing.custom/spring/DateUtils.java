package org.springframework.tests;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date formatDateStr(String dateStr) {
		//20180808
		if (dateStr.length() == 8 && !dateStr.contains("-")) {
			String year = dateStr.substring(0, 4);
			String month = dateStr.substring(4, 6);
			String day = dateStr.substring(6, 8);
			String newDateStr = year + "-" + month + "-" + day;
			Date newDate = getDate(newDateStr);
			return newDate;
		}
		//2018-8-9
		String[] dateArr = dateStr.split("\\-");
		if (null == dateArr || dateArr.length <= 0) {
			return new Date();
		}
		String month = dateArr[1];
		String day = dateArr[2];
		if (month.length() == 1) {
			month = "0" + month;
		}
		if (day.length() == 1) {
			day = "0" + day;
		}
		String newDateStr = dateArr[0] + "-" + month + "-" + day;
		Date newDate = getDate(newDateStr);
		return newDate;
	}

	public static Date getDate(String baseDate) {
		if (baseDate != null && baseDate.length() != 0) {
			int year = Integer.parseInt(baseDate.substring(0, 4));
			int month = Integer.parseInt(baseDate.substring(5, 7));
			int date = Integer.parseInt(baseDate.substring(8, 10));
			int hour = 0;
			int minute = 0;
			int second = 0;
			if (baseDate.length() >= 13) {
				hour = Integer.parseInt(baseDate.substring(11, 13));
			}

			if (baseDate.length() >= 16) {
				minute = Integer.parseInt(baseDate.substring(14, 16));
			}

			if (baseDate.length() >= 19) {
				second = Integer.parseInt(baseDate.substring(17, 19));
			}

			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month - 1, date, hour, minute, second);
			Date _date = calendar.getTime();
			return _date;
		} else {
			return null;
		}
	}
}
