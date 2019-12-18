package com.salt.emma;

public class MyUtil {
	public static int  parseStringToInt(String val, int def) {
		int result = def;
		try {
			result = Integer.parseInt(val);
		} catch(Exception e) {}
		return result;
	}
}
