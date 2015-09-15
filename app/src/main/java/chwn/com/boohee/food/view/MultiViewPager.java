package chwn.com.boohee.food.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import chwn.com.boohee.food.R;

public class MultiViewPager extends ViewPager {
	private int mMaxWidth = -1;
	private int mMaxHeight = -1;
	private int mMatchChildWidth;
	private boolean d;

	public MultiViewPager(Context paramContext) {
		super(paramContext);
	}

	public MultiViewPager(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		a(paramContext, paramAttributeSet);
	}

	private void a(Context paramContext, AttributeSet paramAttributeSet) {
		setClipChildren(false);
		int[] arrayOfInt = R.styleable.MultiViewPager;
		TypedArray localTypedArray = paramContext.obtainStyledAttributes(
				paramAttributeSet, arrayOfInt);
		int i = localTypedArray.getDimensionPixelSize(0, -1);
		setMaxWidth(i);
		int j = localTypedArray.getDimensionPixelSize(1, -1);
		setMaxHeight(j);
		int k = localTypedArray.getResourceId(2, 0);
		setMatchChildWidth(k);
		localTypedArray.recycle();
	}

	private static void a(Point paramPoint1, Point paramPoint2) {
		if (paramPoint2.x >= 0) {
			int i = paramPoint1.x;
			int j = paramPoint2.x;
			if (i > j) {
				int k = paramPoint2.x;
				paramPoint1.x = k;
			}
		}
		if (paramPoint2.y < 0)
			return;
		int l = paramPoint1.y;
		int i1 = paramPoint2.y;
		if (l <= i1)
			return;
		int i2 = paramPoint2.y;
		paramPoint1.y = i2;
	}

	protected void a(int paramInt1, int paramInt2) {
		if (!this.d) {
			return;
		}

		if (mMatchChildWidth == 0) {
			this.d = false;
		}
		if (getChildCount() <= 0) {
			return;
		}
		View localView1 = getChildAt(0);
		localView1.measure(paramInt1, paramInt2);
		int i = localView1.getMeasuredWidth();
		int j = mMatchChildWidth;
		View localView2 = localView1.findViewById(j);
		if (localView2 == null) {
			throw new NullPointerException(
					"MatchWithChildResId did not find that ID in the first fragment of the ViewPager; is that view defined in the child view's layout? Note that MultiViewPager only measures the child for index 0.");
		}
		int k = localView2.getMeasuredWidth();
		if (k <= 0) {
			return;
		}
		this.d = false;
		int l = -(i - k);
		setPageMargin(l);
		float f1 = i;
		float f2 = k;
		int i1 = (int) Math.ceil(f1 / f2) + 1;
		setOffscreenPageLimit(i1);
		requestLayout();
	}

	@Override
	protected void onMeasure(int paramInt1, int paramInt2) {
		int i = View.MeasureSpec.getSize(paramInt1);
		int j = View.MeasureSpec.getSize(paramInt2);
		Point localPoint1 = new Point(i, j);
		if ((mMaxWidth >= 0) || (mMaxHeight >= 0)) {
			int k = mMaxWidth;
			int l = mMaxHeight;
			Point localPoint2 = new Point(k, l);
			a(localPoint1, localPoint2);
			paramInt1 = View.MeasureSpec.makeMeasureSpec(localPoint1.x,
					1073741824);
			paramInt2 = View.MeasureSpec.makeMeasureSpec(localPoint1.y,
					1073741824);
		}
		super.onMeasure(paramInt1, paramInt2);
		a(paramInt1, paramInt2);
	}

	@Override
	protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3,
			int paramInt4) {
		super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
		this.d = true;
	}

	public void setMatchChildWidth(int paramInt) {
		if (mMatchChildWidth == paramInt)
			return;
		mMatchChildWidth = paramInt;
		this.d = true;
	}

	public void setMaxHeight(int paramInt) {
		mMaxHeight = paramInt;
	}

	public void setMaxWidth(int paramInt) {
		mMaxWidth = paramInt;
	}
}
