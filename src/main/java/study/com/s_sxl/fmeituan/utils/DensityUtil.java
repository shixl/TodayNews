package study.com.s_sxl.fmeituan.utils;

import android.content.Context;

public class DensityUtil {

	/**
	 * dip转px
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context , int dipValue){
		float density = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue*density+0.5f);
	}
	
	/**
	 * px转dip
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context,int pxValue){
		float density = context.getResources().getDisplayMetrics().density;
		return (int)(pxValue/density+0.5f);
	}

}
