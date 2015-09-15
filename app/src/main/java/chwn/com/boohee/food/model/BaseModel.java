package chwn.com.boohee.food.model;

import android.text.TextUtils;
import com.activeandroid.Model;
import com.alibaba.fastjson.JSONObject;

public class BaseModel<T> extends Model {
	public static <T> T parse(String paramString, Class<T> paramClass) {
		T localObject2 = null;
		if (TextUtils.isEmpty(paramString)) {
			return localObject2;
		}

		try {
			localObject2 = JSONObject.parseObject(paramString, paramClass);
		} catch (Exception localException) {
			localException.printStackTrace();
			localObject2 = null;
		}
		return localObject2;
	}

	public String toString() {
		String localObject = "";
		try {
			String str = JSONObject.toJSONString(this);
			localObject = str;

		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return localObject;
	}
}
