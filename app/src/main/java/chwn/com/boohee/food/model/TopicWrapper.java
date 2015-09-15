package chwn.com.boohee.food.model;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import java.util.List;

public class TopicWrapper implements Serializable {
	public int topic_count;
	public List<Topic> topics;

	public static TopicWrapper parse(String paramString) {
		TopicWrapper localTopicWrapper;
		try {
			localTopicWrapper = (TopicWrapper) JSONObject.parseObject(
					paramString, TopicWrapper.class);
		} catch (Exception localException) {
			localException.printStackTrace();
			localTopicWrapper = null;
		}
		return localTopicWrapper;
	}

	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> paramList) {
		this.topics = paramList;
	}
}
