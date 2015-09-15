package chwn.com.boohee.food.volley;

import android.content.Context;

public class FoodRequest extends BaseJsonRequest {

	public FoodRequest(int method, String url, JsonParams jsonParams, JsonCallback jsonCallback) {
		super(method, url, jsonParams, jsonCallback, jsonCallback);
	}

	public static void a(int paramInt, String paramString, JsonParams paramJsonParams, JsonCallback paramJsonCallback,
			Context paramContext) {
		String str1 = y();
		String str2 = str1 + paramString;
		RequestManager.add(new FoodRequest(paramInt, str2, paramJsonParams, paramJsonCallback), paramContext);
	}

	public static void get(String paramString, JsonCallback paramJsonCallback, Context paramContext) {
		a(Method.GET, paramString, null, paramJsonCallback, paramContext);
	}

	public static String y() {
		return "http://food.boohee.com";
	}

}
