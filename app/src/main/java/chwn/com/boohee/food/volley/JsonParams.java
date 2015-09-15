package chwn.com.boohee.food.volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParams {
	protected final JSONObject a;

	public JsonParams() {
		JSONObject localJSONObject = new JSONObject();
		this.a = localJSONObject;
	}

	public JSONObject a() {
		return this.a;
	}

	public void a(String paramString, int paramInt) {
		if (paramString == null) {
			return;
		}
		try {
			JSONObject localJSONObject = this.a.put(paramString, paramInt);
			return;
		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
	}

	public void a(String paramString, JsonParams paramJsonParams) {
		if ((paramString == null) || (paramJsonParams == null)) {
			return;
		}
		try {
			JSONObject localJSONObject1 = this.a;
			JSONObject localJSONObject2 = paramJsonParams.a();
			JSONObject localJSONObject3 = localJSONObject1.put(paramString, localJSONObject2);
			return;
		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
	}

	public void a(String paramString1, String paramString2) {
		if ((paramString1 == null) || (paramString2 == null)) {
			return;
		}
		try {
			JSONObject localJSONObject = this.a.put(paramString1, paramString2);
			return;
		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
	}

	public void a(String paramString, JSONArray paramJSONArray) {
		if ((paramString == null) || (paramJSONArray == null)) {
			return;
		}
		try {
			JSONObject localJSONObject = this.a.put(paramString, paramJSONArray);
			return;
		} catch (JSONException localJSONException) {
			localJSONException.printStackTrace();
		}
	}

	public String toString() {
		return this.a.toString();
	}
}
