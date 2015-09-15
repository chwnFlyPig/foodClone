package chwn.com.boohee.food.model;

import com.alibaba.fastjson.JSONObject;

public class Topic {
	public int id;
	public String image_url;
	public int page_count;
	public String sub_title;
	public String title;

	public static Topic parse(String paramString) {
		Topic localTopic;
		try {
			localTopic = (Topic) JSONObject.parseObject(paramString,
					Topic.class);

		} catch (Exception localException) {
			localException.printStackTrace();
			localTopic = null;
		}
		return localTopic;
	}

	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
