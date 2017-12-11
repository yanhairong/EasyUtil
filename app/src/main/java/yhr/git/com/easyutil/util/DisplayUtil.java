package yhr.git.com.easyutil.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description: 显示换算相关工具 <br>
 * @author Zhang Fangtao
 * @version 2.0
 * @since 2016/1/6
 */
public class DisplayUtil {
	private static final String TAG = DisplayUtil.class.getSimpleName();

	/**
	 * 获取 显示信息
	 */
	public static DisplayMetrics getDisplayMetrics() {
		return Resources.getSystem().getDisplayMetrics();
	}

	/**
	 * 打印 显示信息
	 */
	public static String printDisplayInfo() {
		DisplayMetrics dm = getDisplayMetrics();
		StringBuilder sb = new StringBuilder();
		sb.append("_______  显示信息:  ");
		sb.append("\ndensity         :").append(dm.density);
		sb.append("\ndensityDpi      :").append(dm.densityDpi);
		sb.append("\nheightPixels    :").append(dm.heightPixels);
		sb.append("\nwidthPixels     :").append(dm.widthPixels);
		sb.append("\nscaledDensity   :").append(dm.scaledDensity);
		sb.append("\nxdpi            :").append(dm.xdpi);
		sb.append("\nydpi            :").append(dm.ydpi);
		return sb.toString();
	}

    /**
     * 获取手机屏幕宽度
     * @return 屏幕宽度
     */
    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    /**
     * 获取手机屏幕高度
     * @return 屏幕高度
     */
    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }
	
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        if (result > 0) {
            return result;
        }
        return dp2px(25);
    }

	/**
	 * px转换为dp
	 * @param value px值
	 * @return dp值
	 */
	public static float px2dp(int value) {
		float density = getDensity();
		return value / density;
	}

	/**
	 * dp转换为px
	 * @param value dp值
	 * @return px值
	 */
	public static int dp2px(float value) {
		float density = getDensity();
		return (int) (density * value + 0.5);
	}

	/**
	 * px转换为sp
	 * @param value px值
	 * @return sp值
	 */
	public static float px2sp(int value) {
		float scaledDensity = getScaledDensity();
		return value / scaledDensity;
	}

	/**
	 * sp转换为px
	 * @param value sp值
	 * @return
	 */
	public static int sp2px(float value) {
		float scaledDensity = getScaledDensity();
		return (int) (scaledDensity * value + 0.5);
	}

	private static float getDensity() {
		return getDisplayMetrics().density;
	}

	private static float getScaledDensity() {
		return getDisplayMetrics().scaledDensity;
	}

	/*** 适配Flyme OS 有SmartBar设备 Start ***/

    //判断设备是否具有SmartBar
    public static boolean hasSmartBar() {
        try {
            Method method = Class.forName("android.os.Build").getMethod("hasSmartBar");
            return (Boolean) method.invoke(null);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取SmartBar高度
     * @return 高度值
     */
    public static int getSmartBarHeight() {
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            Field field = clazz.getField("mz_action_button_min_height");
            int height = Integer.parseInt(field.get(obj).toString());
            return Resources.getSystem().getDimensionPixelSize(height);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return 0;
    }
    /*** 适配Flyme OS 有SmartBar设备 End ***/


	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 *
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

}
