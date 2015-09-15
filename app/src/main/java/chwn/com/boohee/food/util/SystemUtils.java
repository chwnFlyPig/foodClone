package chwn.com.boohee.food.util;

import android.os.Build;

public class SystemUtils {

	public static String getOsVersion() {
		return Build.VERSION.RELEASE;
	}

	public static String getPhoneModel() {
		return Build.MODEL;
	}
}
