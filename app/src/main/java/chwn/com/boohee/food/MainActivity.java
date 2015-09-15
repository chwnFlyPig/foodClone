package chwn.com.boohee.food;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import chwn.com.boohee.food.fragment.DrawerFragment;
import chwn.com.boohee.food.fragment.HomeFragment;
import chwn.com.boohee.food.util.ViewHelper;

public class MainActivity extends BaseActivity {

	private DrawerFragment mDrawerFragment;
	private DrawerLayout mDrawerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ViewHelper.a(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void b() {
		ActionBar abar = getActionBar();
		if (abar != null) {
			abar.hide();
		}
		e();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		HomeFragment homeFragment = new HomeFragment();
		ft.replace(R.id.container, homeFragment).commit();
	}

	private void e() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.drawer);
		mDrawerFragment.a(R.id.drawer, mDrawerLayout);
	}

	public void a() {
		if (mDrawerLayout == null)
			return;
		mDrawerLayout.openDrawer(8388611);
	}
}
