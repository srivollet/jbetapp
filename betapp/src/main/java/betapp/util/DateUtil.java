package betapp.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	public final static String DDMMYYYY = "ddMMyyyy";

	public static String getYesterday() {
		Date now = new Date();
		Date yesterday = DateUtils.addDays(now, -1);

		return DateFormatUtils.format(yesterday, "ddMMyyyy");
	}
}
