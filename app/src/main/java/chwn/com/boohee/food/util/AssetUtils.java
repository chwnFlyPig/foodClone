package chwn.com.boohee.food.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.content.Context;

public class AssetUtils {
	public static String readFile(Context context, String fileName) {
		StringBuffer result = new StringBuffer();
		try {
			InputStreamReader inputReader = new InputStreamReader(context.getAssets().open(fileName));
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";

			while ((line = bufReader.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
