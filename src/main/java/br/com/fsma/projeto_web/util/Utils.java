package br.com.fsma.projeto_web.util;

public class Utils {
	public static boolean isEmpty(String value) {
		return (!isNotEmpty(value));
	}

	public static boolean isNotEmpty(String value) {
		return ((value != null) && (value.length() != 0));
	}

	public static boolean isNull(Object value) {
		return (value == null);
	}
	
}
