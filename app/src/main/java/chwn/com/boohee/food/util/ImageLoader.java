package chwn.com.boohee.food.util;

import java.io.File;

import com.squareup.picasso.Picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import chwn.com.boohee.food.FoodApplication;
import chwn.com.boohee.food.R;

public class ImageLoader {
	public static Picasso a() {
		Picasso.with(FoodApplication.a()).setIndicatorsEnabled(false);
		return Picasso.with(FoodApplication.a());
	}

	public static void a(ImageView paramImageView) {
		a(paramImageView, "", R.drawable.img_default_avatar, R.drawable.img_default_avatar);
	}

	public static void a(ImageView paramImageView, int paramInt1, int paramInt2) {
		a(paramImageView, paramInt1, paramInt2, paramInt2);
	}

	public static void a(ImageView paramImageView, int paramInt1, int paramInt2, int paramInt3) {
		if ((paramImageView == null) || (paramInt1 == 0) || (paramInt2 == 0) || (paramInt3 == 0)) {
			return;
		}
		a().load(paramInt1).placeholder(paramInt2).error(paramInt3).into(paramImageView);
	}

	public static void a(ImageView paramImageView, Uri paramUri, int placeResId, int errorResId) {
		if ((paramImageView == null) || (placeResId == 0) || (errorResId == 0)) {
			return;
		}
		if (paramUri == null) {
			a().load(placeResId).into(paramImageView);
		} else {
			a().load(paramUri).placeholder(placeResId).error(errorResId).into(paramImageView);
		}
	}

	public static void a(ImageView paramImageView, String url) {
		a(paramImageView, url, R.drawable.img_default_avatar, R.drawable.img_default_avatar);
	}

	public static void a(ImageView paramImageView, String paramString, int paramInt) {
		a(paramImageView, paramString, paramInt, paramInt);
	}

	public static void a(ImageView paramImageView, String paramString, int paramInt1, int paramInt2) {
		if ((paramImageView == null) || (paramInt1 == 0) || (paramInt2 == 0)) {
			return;
		}
		if (TextUtils.isEmpty(paramString)) {
			a().load(paramInt1).into(paramImageView);
		} else {
			a().load(paramString).placeholder(paramInt1).error(paramInt2).into(paramImageView);
		}
	}

	public static void a(ImageView paramImageView, String paramString, int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) {
		if ((paramImageView == null) || (paramInt1 == 0) || (paramInt2 == 0)) {
			return;
		}

		if (TextUtils.isEmpty(paramString)) {
			a().load(paramInt1).into(paramImageView);
			return;
		}
		try {
			if (!paramString.startsWith("http")) {
				Uri localUri = Uri.fromFile(new File(paramString));
				a().load(localUri).resize(paramInt3, paramInt4).placeholder(paramInt1).error(paramInt2)
						.into(paramImageView);
			} else {
				a().load(paramString).placeholder(paramInt1).error(paramInt2).into(paramImageView);
			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}

	}

	public static void a(ImageView paramImageView, String paramString, int paramInt1, int paramInt2,
			boolean paramBoolean) {
		if (paramString.startsWith("http")) {
			ImageView localImageView1 = paramImageView;
			String str1 = paramString;
			int l = 0;
			a(localImageView1, str1, paramInt1, paramInt2, 0, l);
			return;
		}

		BitmapFactory.Options localOptions = new BitmapFactory.Options();
		localOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(paramString, localOptions);
		int outHeight = localOptions.outHeight;
		int outWidth = localOptions.outWidth;
		if (paramBoolean)
			;
		for (int i2 = 180; Math.max(outWidth, outHeight) > i2; i2 = 480) {
			outWidth /= 2;
			outHeight /= 2;
		}
		a(paramImageView, paramString, paramInt1, paramInt2, outWidth, outHeight);
	}

	public static void b(ImageView paramImageView, String paramString) {
		a(paramImageView, paramString, R.drawable.img_default_topic_banner, R.drawable.img_default_topic_banner);
	}

	public static void c(ImageView paramImageView, String paramString) {
		a(paramImageView, paramString, R.drawable.img_default_topic_banner, R.drawable.img_default_topic_banner);
	}

	public static void d(ImageView paramImageView, String paramString) {
		Animation localAnimation1 = AnimationUtils.loadAnimation(FoodApplication.a(), R.anim.fade_out);
		paramImageView.startAnimation(localAnimation1);
		a(paramImageView, paramString, R.drawable.img_default_home_cover, R.drawable.img_default_home_cover);
		Animation localAnimation2 = AnimationUtils.loadAnimation(FoodApplication.a(), R.anim.fade_in);
		paramImageView.startAnimation(localAnimation2);
	}

	public static void e(ImageView paramImageView, String paramString) {
		if (!TextUtils.isEmpty(paramString)) {
			if (!paramString.startsWith("http")) {
				Uri localUri = Uri.fromFile(new File(paramString));
				a(paramImageView, localUri, R.drawable.img_default_food_thumbnail, R.drawable.img_error_food_thumbnail);
			} else {
				a(paramImageView, paramString, R.drawable.img_default_food_thumbnail, R.drawable.img_error_food_thumbnail);
			}
		}
	}

	public static void f(ImageView paramImageView, String paramString) {
		a(paramImageView, paramString, R.drawable.img_default_food_category, R.drawable.img_default_food_category);
	}

	public static void g(ImageView paramImageView, String paramString) {
		a(paramImageView, paramString, R.drawable.img_default_compared_food, R.drawable.img_default_compared_food);
	}

	public static void h(ImageView paramImageView, String paramString) {
		a(paramImageView, paramString, R.drawable.img_default_food_thumbnail, R.drawable.img_error_food_thumbnail, true);
	}

	public static void i(ImageView paramImageView, String paramString) {
		a(paramImageView, paramString, R.drawable.img_default_uploaded, R.drawable.img_default_uploaded, false);
	}
}
