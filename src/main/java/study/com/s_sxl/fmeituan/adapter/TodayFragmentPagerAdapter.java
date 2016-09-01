package study.com.s_sxl.fmeituan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TodayFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    private static final String[] names = new String[] {"推荐", "热点", "本地", "视频","头条号","社会","图片" };

    public TodayFragmentPagerAdapter(List<Fragment> pagerFragment, FragmentManager fm) {
        super(fm);
        this.mFragments = pagerFragment;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        if(position < mFragments.size()){
            fragment = mFragments.get(position);
        }else {
            fragment = mFragments.get(0);
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names[position%names.length];
    }
}
