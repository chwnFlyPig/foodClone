package chwn.com.boohee.food.model;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

public class Banner {
	public static String KEY = "banner";
	public static String KEY_OTHERS = "others";
	public String food_code;
	public int food_id;
	public String food_name;
	public String image_url;

	public static Banner parse(String paramString) {
		Banner localBanner;
		try {
			localBanner = (Banner) JSONObject.parseObject(paramString,
					Banner.class);

		} catch (Exception localException) {
			localException.printStackTrace();
			localBanner = null;
		}
		return localBanner;
	}

	public static List<Banner> parseOthers(String paramString) {
		List localList2;
		try {
			List localList1 = JSONObject.parseArray(paramString, Banner.class);
			localList2 = localList1;

		} catch (Exception localException) {
			localException.printStackTrace();
			localList2 = null;
		}
		return localList2;
	}

	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
