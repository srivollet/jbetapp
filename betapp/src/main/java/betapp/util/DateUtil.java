package betapp.util;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.AUGUST;
import static java.util.Calendar.DATE;
import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.FEBRUARY;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.JANUARY;
import static java.util.Calendar.JULY;
import static java.util.Calendar.JUNE;
import static java.util.Calendar.MARCH;
import static java.util.Calendar.MAY;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.NOVEMBER;
import static java.util.Calendar.OCTOBER;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.SEPTEMBER;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	private DateUtil() {

	}

	public static final Map<Integer, Integer> m = new HashMap<Integer, Integer>();
	static {
		m.put(Integer.valueOf(1), JANUARY);
		m.put(Integer.valueOf(2), FEBRUARY);
		m.put(Integer.valueOf(3), MARCH);
		m.put(Integer.valueOf(4), APRIL);
		m.put(Integer.valueOf(5), MAY);
		m.put(Integer.valueOf(6), JUNE);
		m.put(Integer.valueOf(7), JULY);
		m.put(Integer.valueOf(8), AUGUST);
		m.put(Integer.valueOf(9), SEPTEMBER);
		m.put(Integer.valueOf(10), OCTOBER);
		m.put(Integer.valueOf(11), NOVEMBER);
		m.put(Integer.valueOf(12), DECEMBER);
	}

	public static final String getYesterday() {
		Date now = new Date();
		Date yesterday = DateUtils.addDays(now, -1);

		return DateFormatUtils.format(yesterday, "ddMMyyyy");
	}

	public static final Date getDate_ddMM(String value) {
		Calendar c = getCalendar_ddMM(value);
		return c == null ? null : c.getTime();
	}

	public static final Calendar getCalendar_ddMM(String value) {
		Calendar c = null;

		if (value != null && value.matches("[0-9]{2}/[0-9]{2}.*")) {
			c = Calendar.getInstance();

			String[] v = value.substring(0, 5).split("/");
			c.set(DATE, Integer.valueOf(v[0]));
			c.set(MONTH, m.get(Integer.valueOf(v[1])));
			c.set(HOUR, 0);
			c.set(MINUTE, 0);
			c.set(SECOND, 0);
			c.set(MILLISECOND, 0);
		}
		return c;
	}
}
