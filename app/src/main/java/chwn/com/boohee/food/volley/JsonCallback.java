package chwn.com.boohee.food.volley;

import org.json.JSONObject;

import com.android.volley.NetworkResponse;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import android.app.Activity;
import android.widget.Toast;
import chwn.com.boohee.food.model.ApiError;

public class JsonCallback implements ErrorListener, Listener<JSONObject> {
	private Activity mActivity;

	public JsonCallback(Activity paramActivity) {
		mActivity = paramActivity;
	}

	@Override
	public void onResponse(JSONObject response) {
		if (!response.has("errors")) {
			successResponse(response);
		} else {
			String str = ApiError.getErrorMessage(response);
		}
	}

	@Override
	public void onErrorResponse(VolleyError error) {

		String str = "网络异常，请检查网络稍后重试~";
		NetworkResponse localNetworkResponse = error.networkResponse;
		int statusCode;
		if (error.networkResponse != null) {
			statusCode = error.networkResponse.statusCode;
			str = "请求不合法~ 错误代码:" + statusCode;
		}
		Toast.makeText(mActivity, str, Toast.LENGTH_LONG).show();
	}

	public void successResponse(JSONObject paramJSONObject) {
	}
}
