package study.com.s_sxl.fmeituan.constant;

import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.fragment.FollowFragment;
import study.com.s_sxl.fmeituan.fragment.HomeFragment;
import study.com.s_sxl.fmeituan.fragment.UserFragment;
import study.com.s_sxl.fmeituan.fragment.VideoHomeFragment;

public class TabConstant {
	public static String[] getTabsTxt(){
		String[] tabs={"首页","视频","关注","我的"};
		return tabs;
	}
	public static int[] getTabsImg(){
		int[] ids={R.mipmap.tab_1,R.mipmap.tab_2,R.mipmap.tab_3,R.mipmap.tab_4};
		return ids;
	}
	public static int[] getTabsImgLight(){
		int[] ids={R.mipmap.tab_1_s,R.mipmap.tab_2_s,R.mipmap.tab_3_s,R.mipmap.tab_4_s};
		return ids;
	}
	public static Class[] getFragments(){
		Class[] clz={HomeFragment.class,VideoHomeFragment.class,FollowFragment.class,UserFragment.class};
		return clz;
	}
}
