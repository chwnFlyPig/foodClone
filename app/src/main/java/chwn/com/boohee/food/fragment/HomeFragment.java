package chwn.com.boohee.food.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import chwn.com.boohee.food.MainActivity;
import chwn.com.boohee.food.R;
import chwn.com.boohee.food.model.Banner;
import chwn.com.boohee.food.model.Topic;
import chwn.com.boohee.food.model.TopicWrapper;
import chwn.com.boohee.food.util.AssetUtils;
import chwn.com.boohee.food.util.FileCache;
import chwn.com.boohee.food.util.ImageLoader;
import chwn.com.boohee.food.util.ViewHelper;
import chwn.com.boohee.food.view.MultiViewPager;
import chwn.com.boohee.food.volley.FoodRequest;
import chwn.com.boohee.food.volley.JsonCallback;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
	private static int g = 1;
	@ViewInject(R.id.viewpager)
	MultiViewPager mMultiViewPager;

	@ViewInject(R.id.tv_title)
	TextView tv_title;

	@ViewInject(R.id.tv_sub_title)
	TextView tv_sub_title;

	@ViewInject(R.id.iv_bg)
	ImageView iv_banner_view;

	@ViewInject(R.id.view_swiperefresh)
	SwipeRefreshLayout mSwipeRefreshLayout;

	@ViewInject(R.id.btn_nav)
	Button btn_nav;

	private List<Banner> mBannerList;
	private Banner mCurBanner;
	private List<Topic> mTopicDataList;
	private int mOffscreenPageLimit;
	private int mTopicCount;
	private FileCache mFileCache;
	private int mCurbannerIndex = 0;
	private HomeViewPagerAdapter mHomeViewPagerAdapter;

	public HomeFragment() {
		mTopicDataList = new ArrayList();
	}

	@Override
	public void onActivityCreated(Bundle paramBundle) {
		super.onActivityCreated(paramBundle);
		i();
		j();
		f();
		showLoading();
		reqBannerData();
		reqTopicData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_nav:
			((MainActivity) getActivity()).a();
			break;
		case R.id.btn_search:
			// SearchActivity.a(getActivity());
			break;
		}
	}

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		mFileCache = FileCache.getInstance(getActivity());
		JSONObject localJSONObject1 = mFileCache.getCachedJson("CACHE_HOME_BANNER");
		parseBannerDatas(localJSONObject1);
		JSONObject localJSONObject2 = mFileCache.getCachedJson("CACHE_HOME_TOPICS");
		parseTopicDatas(localJSONObject2);
	}

	@Override
	public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
		View localView = paramLayoutInflater.inflate(R.layout.fragment_home, paramViewGroup, false);
		 ViewUtils.inject(this, localView);
		initView(localView);
		return localView;
	}

	private void parseTopicDatas(JSONObject paramJSONObject) {
		if (paramJSONObject == null) {
			return;
		}
		TopicWrapper localTopicWrapper = TopicWrapper.parse(paramJSONObject.toString());
		if ((localTopicWrapper == null) || (localTopicWrapper.topic_count <= 0)) {
			return;
		}
		mTopicCount = localTopicWrapper.topic_count;
		mTopicDataList.clear();
		mTopicDataList.addAll(localTopicWrapper.topics);
	}

	private void initTopicTitle(final int paramInt) {
		if ((mTopicDataList == null) || (mTopicDataList.size() <= 0))
			return;
		String str1 = ((Topic) mTopicDataList.get(paramInt)).title;
		tv_title.setText(str1);
		String str2 = ((Topic) mTopicDataList.get(paramInt)).sub_title;
		tv_sub_title.setText(str2);

	}

	private void parseBannerDatas(JSONObject paramJSONObject) {
		if (paramJSONObject == null) {
			return;
		}
		if (paramJSONObject.has(Banner.KEY)) {
			mCurBanner = Banner.parse(paramJSONObject.optString(Banner.KEY));
			mCurbannerIndex = 0;
		}
		if (paramJSONObject.has(Banner.KEY_OTHERS)) {
			mBannerList = Banner.parseOthers(paramJSONObject.optString(Banner.KEY_OTHERS));
			mBannerList.add(0, mCurBanner);
		}
	}

	private void initView(View rootView) {
		if (rootView == null) {
			return;
		}

		btn_nav.setOnClickListener(this);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mMultiViewPager.setOnPageChangeListener(new MPagerChangeL());
		mMultiViewPager.setOffscreenPageLimit(mOffscreenPageLimit);
		mMultiViewPager.setPageTransformer(true, new RotatePageTransformer());
	}

	private void f() {
		int i1 = getResources().getDisplayMetrics().widthPixels;
		int i2 = getResources().getDisplayMetrics().heightPixels;
		int i3 = Math.min(i1, i2);
		iv_banner_view.getLayoutParams().width = i3;
		int i4 = (int) (i3 * 0.75F);
		iv_banner_view.getLayoutParams().height = i4;
	}

	private void i() {
		if (mCurBanner != null) {
			String str1 = mCurBanner.image_url;
			ImageLoader.a(iv_banner_view, str1, R.drawable.img_default_home_cover, R.drawable.img_default_home_cover);
			return;
		}
		String str2 = AssetUtils.readFile(getActivity(), "json/home_banner.json");
		if (TextUtils.isEmpty(str2)) {
			return;
		}
		try {
			JSONObject localJSONObject = new JSONObject(str2);
			mCurBanner = (Banner) JSON.parseObject(localJSONObject.optString(Banner.KEY), Banner.class);
			if (mCurBanner != null) {
				int i1 = ViewHelper.a(getActivity(), mCurBanner.image_url);
				ImageLoader.a(iv_banner_view, i1, R.drawable.img_default_home_cover, R.drawable.img_default_home_cover);
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	private void j() {
		if ((mTopicDataList.size() == 0)) {
			String jsonStrs = AssetUtils.readFile(getActivity(), "json/home_topic.json");
			if (!TextUtils.isEmpty(jsonStrs)) {
				TopicWrapper topics = TopicWrapper.parse(jsonStrs);
				if (topics != null) {
					mTopicCount = topics.topic_count;
					mTopicDataList.addAll(topics.getTopics());
				}
			}
		}
		if (mHomeViewPagerAdapter == null) {
			mHomeViewPagerAdapter = new HomeViewPagerAdapter(this, getChildFragmentManager());
			mMultiViewPager.setAdapter(mHomeViewPagerAdapter);
			int i1 = mMultiViewPager.getCurrentItem();
			initTopicTitle(i1);
		}
		mHomeViewPagerAdapter.notifyDataSetChanged();
	}

	private void reqTopicData() {
		ReqTopicCallback callback = new ReqTopicCallback(getActivity());
		FoodRequest.get("/fb/v1/topics", callback, getActivity());
	}

	private void reqBannerData() {
		ReqBannerCallback callback = new ReqBannerCallback(getActivity());
		FoodRequest.get("/fb/v1/welcome", callback, getActivity());
	}

	@Override
	public void onRefresh() {
		mSwipeRefreshLayout.setRefreshing(true);
		if ((mBannerList == null) || (mBannerList.size() <= 0))
			return;
		if (mCurbannerIndex >= mBannerList.size()) {
			mCurbannerIndex = 0;
		}
		String str = mBannerList.get(mCurbannerIndex).image_url;
		ImageLoader.b(iv_banner_view, str);
		mCurbannerIndex += 1;
	}

	class MPagerChangeL extends ViewPager.SimpleOnPageChangeListener {

		@Override
		public void onPageSelected(int position) {
			super.onPageSelected(position);
			initTopicTitle(position);
		}

	}

	public class RotatePageTransformer implements ViewPager.PageTransformer {
		private float mMaxTranslationY = 50.0F;
		private int mMaxRotationDegree = 15;

		@Override
		public void transformPage(View paramView, float paramFloat) {
			float f1 = 0;
			float f2;
			if (paramFloat <= 1.0F) {
				f1 = mMaxRotationDegree * paramFloat;
				f2 = mMaxTranslationY * paramFloat;
				if (paramFloat <= 0.0F) {
					paramView.setTranslationY(-f2);
				} else {
					paramView.setTranslationY(f2);
				}
			}
			paramView.setRotation(f1);
		}
	}

	class ReqTopicCallback extends JsonCallback {
		public ReqTopicCallback(Activity paramActivity) {
			super(paramActivity);
		}

		@Override
		public void onErrorResponse(VolleyError error) {
			dismissLoading();
			super.onErrorResponse(error);
		}

		public void successResponse(JSONObject paramJSONObject) {
			dismissLoading();
			parseTopicDatas(paramJSONObject);
			j();
			mFileCache.saveJson("CACHE_HOME_TOPICS", paramJSONObject);
		}
	}

	class ReqBannerCallback extends JsonCallback {

		public ReqBannerCallback(Activity paramActivity) {
			super(paramActivity);
		}

		@Override
		public void onErrorResponse(VolleyError error) {
			dismissLoading();
			super.onErrorResponse(error);
		}

		public void successResponse(JSONObject paramJSONObject) {
			dismissLoading();
			if (paramJSONObject == null)
				return;
			parseBannerDatas(paramJSONObject);
			i();
			mFileCache.saveJson("CACHE_HOME_BANNER", paramJSONObject);
		}
	}

	class HomeViewPagerAdapter extends FragmentPagerAdapter {
		public HomeViewPagerAdapter(HomeFragment paramHomeFragment, FragmentManager paramFragmentManager) {
			super(paramFragmentManager);
		}

		public int getCount() {
			if (mTopicDataList == null || mTopicDataList.isEmpty()) {
				return 0;
			}
			return mTopicDataList.size();
		}

		public Fragment getItem(int paramInt) {
			return HomeItemFragment.newInstance(mTopicDataList.get(paramInt));
		}
	}

}
