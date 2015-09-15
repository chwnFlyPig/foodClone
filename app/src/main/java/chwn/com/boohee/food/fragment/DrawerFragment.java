package chwn.com.boohee.food.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import chwn.com.boohee.food.R;

public class DrawerFragment extends BaseFragment {
	TextView b;
	private ActionBarDrawerToggle mActionBarDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private View mDrawerView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View localView = inflater.inflate(R.layout.fragment_drawer, container,
				false);
		return localView;
	}

	@Override
	public void onConfigurationChanged(Configuration paramConfiguration) {
		super.onConfigurationChanged(paramConfiguration);
		mActionBarDrawerToggle.onConfigurationChanged(paramConfiguration);
	}

	public void a(int paramInt, DrawerLayout paramDrawerLayout) {
		mDrawerView = getActivity().findViewById(paramInt);
		mDrawerLayout = paramDrawerLayout;
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.CENTER);
		FragmentActivity localFragmentActivity = getActivity();
		mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),
				mDrawerLayout, R.drawable.ic_drawer,
				R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
		mDrawerLayout.post(new DrawerRunnable());
		mDrawerLayout.setScrimColor(0);
		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
	}

	class DrawerRunnable implements Runnable {
		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			mActionBarDrawerToggle.syncState();
		}
	}

}
