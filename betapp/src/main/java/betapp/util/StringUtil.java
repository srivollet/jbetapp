package betapp.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.math.NumberUtils;

import com.google.common.base.Strings;

public class StringUtil {

	private StringUtil() {

	}

	public static String getString(String value, Charset charset) {
		try {
			return Strings.isNullOrEmpty(value) ? "" : new String(
					value.getBytes(), charset.name());
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static String getString(String value) {
		return getString(value, StandardCharsets.ISO_8859_1);
	}

	public static Float getFloat(String value) {
		return value == null || !NumberUtils.isNumber(value) ? null : Float
				.valueOf(value);
	}

}
