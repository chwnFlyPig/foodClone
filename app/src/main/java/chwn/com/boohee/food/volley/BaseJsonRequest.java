package chwn.com.boohee.food.volley;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import chwn.com.boohee.food.util.SystemUtils;

public class BaseJsonRequest extends JsonObjectRequest {
	public BaseJsonRequest(int method, String url, JsonParams jsonParams, Response.Listener<JSONObject> listener,
			Response.ErrorListener errorListener) {
		super(method, url, jsonParams == null ? null : jsonParams.a(), listener, errorListener);
	}

	@Override
	public Map<String, String> getHeaders() {
		HashMap localHashMap = new HashMap();
		Object localObject1 = localHashMap.put("app_version", "1.6.1");
		Object localObject2 = localHashMap.put("app_key", "food");
		Object localObject3 = localHashMap.put("app_device", "Android");
		Object localObject4 = localHashMap.put("os_version", SystemUtils.getOsVersion());
		Object localObject5 = localHashMap.put("phone_model", SystemUtils.getPhoneModel());
		Object localObject6 = localHashMap.put("Accept", "application/json");
		Object localObject7 = localHashMap.put("User-Agent", "Android/Volley");
		return localHashMap;
	}
}
