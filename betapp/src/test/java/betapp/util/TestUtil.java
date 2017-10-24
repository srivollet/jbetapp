package betapp.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class TestUtil {

	@Test
	public void Test() {
		Date now = new Date();
		Date yesterday = DateUtils.addDays(now, -1);
		System.out.println(DateFormatUtils.format(yesterday, "ddMMyyyy"));
	}
}
