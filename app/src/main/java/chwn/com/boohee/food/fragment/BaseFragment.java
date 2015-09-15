package chwn.com.boohee.food.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import chwn.com.boohee.food.BaseActivity;

public abstract class BaseFragment extends Fragment {

	public void showLoading() {
		FragmentActivity localFragmentActivity = getActivity();
		if ((localFragmentActivity == null) || (!(localFragmentActivity instanceof BaseActivity)))
			return;
		((BaseActivity) localFragmentActivity).showLoading();
	}

	public void dismissLoading() {
		FragmentActivity localFragmentActivity = getActivity();
		if ((localFragmentActivity == null) || (!(localFragmentActivity instanceof BaseActivity)))
			return;
		((BaseActivity) localFragmentActivity).dismissLoading();
	}
}
