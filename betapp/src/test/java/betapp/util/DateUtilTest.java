package betapp.util;

import java.util.Date;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void getYesterdayTest() {
		DateUtil.getYesterday();
	}

	@Test
	public void getDate_ddMMTest() {
		Date date = DateUtil.getDate_ddMM("01/11");
	}

	@Test
	public void getCalendar_ddMMTest() {
		DateUtil.getCalendar_ddMM("01/11 Ve");
	}
}
