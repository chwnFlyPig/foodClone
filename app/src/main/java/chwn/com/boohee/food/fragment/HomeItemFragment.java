package chwn.com.boohee.food.fragment;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import chwn.com.boohee.food.R;
import chwn.com.boohee.food.model.Topic;
import chwn.com.boohee.food.util.ImageLoader;
import chwn.com.boohee.food.util.ViewHelper;

public class HomeItemFragment extends BaseFragment {
	private static AnimatorSet mAnimatorSet;
	private static int d;
	@ViewInject(R.id.iv)
	ImageView iv;
	@ViewInject(R.id.fl_cover)
	FrameLayout fl_cover;
	private Topic mTopic;

	public static HomeItemFragment newInstance(Topic paramTopic) {
		if (paramTopic == null) {
			return null;
		}
		HomeItemFragment localHomeItemFragment = new HomeItemFragment();
		Bundle localBundle = new Bundle();
		String str = paramTopic.toString();
		localBundle.putString("KEY_TOPIC_DATA", str);
		localHomeItemFragment.setArguments(localBundle);
		return localHomeItemFragment;
	}

	private void a(View paramView) {
		float[] arrayOfFloat = { 0, 1135869952 };
		Animator[] arrayOfAnimator = new Animator[1];
		arrayOfAnimator[0] = ObjectAnimator.ofFloat(paramView, "rotationY", arrayOfFloat);
		mAnimatorSet.removeAllListeners();
		mAnimatorSet.setTarget(paramView);
		mAnimatorSet.playTogether(arrayOfAnimator);
		mAnimatorSet.addListener(new AnimatorListenerAdapter() {
			public void onAnimationEnd(Animator paramAnimator) {
				super.onAnimationEnd(paramAnimator);
				if (iv == null)
					return;
//				String str = String.valueOf(HomeItemFragment.a(this.a).id);
//				TopicActivity.a(getActivity(), str);
			}
		});
		mAnimatorSet.start();
	}

	private void c() {
		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
//				a(v);

			}
		});
	}

	private void d() {
		if (mAnimatorSet != null)
			return;
		mAnimatorSet = new AnimatorSet();
		mAnimatorSet.setDuration(300L);
		mAnimatorSet.setInterpolator(new AccelerateInterpolator());
	}

	private void e() {
		if (mTopic == null) {
			return;
		}
		String str1 = mTopic.image_url;
		if (!str1.contains("http")) {
			int j = ViewHelper.a(getActivity(), str1);
			ImageLoader.a(iv, j, R.drawable.img_default_topic_cover);
			return;
		}
		ViewGroup.LayoutParams localLayoutParams = fl_cover.getLayoutParams();
		localLayoutParams.width = d;
		ImageLoader.a(iv, str1, R.drawable.img_default_topic_cover);
	}

	@Override
	public void onActivityCreated(Bundle paramBundle) {
		super.onActivityCreated(paramBundle);
		d = getResources().getDisplayMetrics().widthPixels / 3;
		e();
	}

	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		if (getArguments() == null)
			return;
		mTopic = Topic.parse(getArguments().getString("KEY_TOPIC_DATA"));
		d();
	}

	@Override
	public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
		return paramLayoutInflater.inflate(R.layout.fragment_image, paramViewGroup, false);
	}

	@Override
	public void onViewCreated(View paramView, Bundle paramBundle) {
		super.onViewCreated(paramView, paramBundle);
		ViewUtils.inject(this, paramView);
		c();
	}
}
