package betapp.util;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

import java.io.UnsupportedEncodingException;

import com.google.common.base.Strings;

public class StringUtil {

	public static String getString(String value) {
		try {
			return Strings.isNullOrEmpty(value) ? "" : new String(value.getBytes(), ISO_8859_1.name());
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
