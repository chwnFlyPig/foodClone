package chwn.com.boohee.food;

import android.app.Application;
import android.content.Context;

public class FoodApplication extends Application {
	private static Context a;
	private boolean b;

	public static Context a() {
		return a;
	}

	public void a(boolean paramBoolean) {
		this.b = paramBoolean;
	}

	public boolean b() {
		return this.b;
	}

	public void onCreate() {
		super.onCreate();
		a = getApplicationContext();
	}
}
