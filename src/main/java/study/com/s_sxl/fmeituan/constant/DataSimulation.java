package study.com.s_sxl.fmeituan.constant;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import study.com.s_sxl.fmeituan.bean.NewsBean;
import study.com.s_sxl.fmeituan.bean.VideoBean;
import study.com.s_sxl.fmeituan.fragment.homeFragments.HeadLineFragment;
import study.com.s_sxl.fmeituan.fragment.homeFragments.HotspotFragment;
import study.com.s_sxl.fmeituan.fragment.homeFragments.LocalFragment;
import study.com.s_sxl.fmeituan.fragment.homeFragments.PicFragment;
import study.com.s_sxl.fmeituan.fragment.homeFragments.RecommendFragment;
import study.com.s_sxl.fmeituan.fragment.homeFragments.SociologyFragment;
import study.com.s_sxl.fmeituan.fragment.homeFragments.VideoFragment;

public class DataSimulation {

    //私有构造方法，防止被实例化
    private DataSimulation() {

    }

    //使用一个内部类来维持单例
    private static final class SingletonHelper {
        private static final DataSimulation INSTANCE = new DataSimulation();
    }

    //获取实例
    public static DataSimulation getInstance() {
        return SingletonHelper.INSTANCE;
    }


    /**
     * 首页中的Fragment
     * @return
     */
    public List<Fragment> getFragment(){
        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecommendFragment());
        fragments.add(new HotspotFragment());
        fragments.add(new LocalFragment());
        fragments.add(new VideoFragment());
        fragments.add(new HeadLineFragment());
        fragments.add(new SociologyFragment());
        fragments.add(new PicFragment());

        return fragments;
    }

    /**
     * 首页中ListView的条目数据
     *
     */
    public List<NewsBean> getNews(int number){

        List<NewsBean> list = new ArrayList<>(number);

        List<String> urls = new ArrayList<>();
        urls.add("http://img5.imgtn.bdimg.com/it/u=3584830634,1222557427&fm=21&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=2894838897,3395831603&fm=21&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=475333573,2921291647&fm=21&gp=0.jpg");

        for(int i =0; i < number; i++){
            NewsBean bean = new NewsBean();
            bean.newsTitle = "聪明外露者德薄，词华太盛者福浅。" + i;
            bean.author = "傲娇小胖子";
            bean.commentNumber = "999评论";
            bean.newsTime = "25小时前";
            bean.newsImgList = urls;
            list.add(bean);
        }

        return list;
    }

    /**
     *  获取播放视频的个数
     * @param number
     * @return
     */
    public List<VideoBean> getVideos(int number){
        List<VideoBean> list = new ArrayList<>(number);

        for(int i = 0; i < number;i++){
            VideoBean bean = new VideoBean();
            bean.headImgUrl = "http://img4.imgtn.bdimg.com/it/u=2894838897,3395831603&fm=21&gp=0.jpg";
            bean.videoPcUrl = "http://img2.imgtn.bdimg.com/it/u=475333573,2921291647&fm=21&gp=0.jpg";
            bean.playTimes = "999万次评论";
            bean.comment = "999";
            bean.userName = "小胖子";
            list.add(bean);
        }
        return list;
    }

  /*  public List<T> getRecords(Class<T> clz, int number) {

        try {
            for (int i = 0; i < number; i++) {
                T t = clz.newInstance();
                mDataList.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mDataList;
    }*/
}
