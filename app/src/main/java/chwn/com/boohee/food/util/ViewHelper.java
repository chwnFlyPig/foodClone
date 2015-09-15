package chwn.com.boohee.food.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import chwn.com.boohee.food.R;

public class ViewHelper
{
  public static int a(Context paramContext, int paramInt1, int paramInt2)
  {
    int i = paramContext.getResources().getDimensionPixelSize(paramInt1);
    int j = paramContext.getResources().getDimensionPixelSize(paramInt2);
    int k = paramContext.getResources().getDisplayMetrics().widthPixels;
    return j * k / i;
  }

  public static int a(Context paramContext, String paramString)
  {
    int j = 0;
    try
    {
      int i = R.drawable.class.getDeclaredField(paramString).getInt(null);
      j = i;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      j = R.drawable.img_default_home_cover;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      localNoSuchFieldException.printStackTrace();
      j = R.drawable.img_default_home_cover;
    }
    return j;
  }

  public static void a(Activity paramActivity)
  {
    if ((paramActivity == null) || (Build.VERSION.SDK_INT < 19))
      return;
    WindowManager.LayoutParams localLayoutParams = paramActivity.getWindow().getAttributes();
    int i = localLayoutParams.flags;
    int j = 0x4000000 | i;
    localLayoutParams.flags = j;
  }
}
