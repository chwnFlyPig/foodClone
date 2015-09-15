package chwn.com.boohee.food.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import chwn.com.boohee.food.R;

public class LoadingFragment extends DialogFragment {
	public static LoadingFragment newInstance() {
		LoadingFragment localLoadingFragment = new LoadingFragment();
		localLoadingFragment.setStyle(2, 0);
		return localLoadingFragment;
	}

	@Override
	public View onCreateView(LayoutInflater paramLayoutInflater,
			ViewGroup paramViewGroup, Bundle paramBundle) {
		return paramLayoutInflater.inflate(R.layout.fragment_loading,
				paramViewGroup, false);
	}
}
