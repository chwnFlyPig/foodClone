package chwn.com.boohee.food;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import chwn.com.boohee.food.fragment.LoadingFragment;
import chwn.com.boohee.food.volley.RequestManager;

public abstract class BaseActivity extends FragmentActivity {
	protected Context mContext;
	private LoadingFragment mLoadingFragment;

	private void a() {
		ActionBar localActionBar = getActionBar();
		if (localActionBar != null) {
			localActionBar.setDisplayHomeAsUpEnabled(true);
			localActionBar.setDisplayShowHomeEnabled(true);
			localActionBar.setIcon(17170445);
			localActionBar.setHomeButtonEnabled(true);
		}
	}

	public void a(Class paramClass) {
		Intent localIntent = new Intent(this, paramClass);
		startActivity(localIntent);
	}

	public void showLoading() {
		try {
			if (mLoadingFragment == null) {
				mLoadingFragment = LoadingFragment.newInstance();
			}
			if (!mLoadingFragment.isAdded()) {
				FragmentTransaction ft = getSupportFragmentManager()
						.beginTransaction();
				ft.remove(mLoadingFragment).commitAllowingStateLoss();
				FragmentManager localFragmentManager = getSupportFragmentManager();
				mLoadingFragment.show(localFragmentManager, "loading");
			}
			return;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public void dismissLoading() {
		try {
			if ((mLoadingFragment != null) && (mLoadingFragment.isAdded())) {
				mLoadingFragment.dismiss();
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	@Override
	public void onBackPressed() {
		if (!((FoodApplication) getApplication()).b()) {
			Intent localIntent1 = new Intent(this, MainActivity.class);
			localIntent1.setFlags(67108864);
			startActivity(localIntent1);
			finish();
			return;
		}
		super.onBackPressed();
	}

	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		mContext = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		a();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		RequestManager.cancelAll(this);
	}
}
