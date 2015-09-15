package chwn.com.boohee.food.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiError {

	public int code;
	public String message;

	public static String getErrorMessage(JSONObject paramJSONObject) {
		int i = 0;
		String str = "";
		Object localObject = paramJSONObject.optJSONArray("errors");
		if ((localObject != null) && (((JSONArray) localObject).length() > 0)) {
			localObject = ((JSONArray) localObject).optJSONObject(0);
			if (localObject != null) {
				str = ((JSONObject) localObject).optString("message");
				i = ((JSONObject) localObject).optInt("code");
			}
		}
		return (String) (str + " �������: " + i);
	}

}
