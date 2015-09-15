package chwn.com.boohee.food.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import chwn.com.boohee.food.FoodApplication;

public class RequestManager {
	public static RequestQueue sRequestQueue = Volley.newRequestQueue(FoodApplication.a());

	public static void add(Request<?> request, Object tag)
  {
        if (tag != null) {
            request.setTag(tag);
        }
    sRequestQueue.add(request);
  }

    /**
     * 移除请求
     *
     * @param tag
     */
    public static void cancelAll(Object tag) {
    	sRequestQueue.cancelAll(tag);
    }
}
