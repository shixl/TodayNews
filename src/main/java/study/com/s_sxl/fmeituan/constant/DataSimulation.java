package study.com.s_sxl.fmeituan.constant;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import study.com.s_sxl.fmeituan.App;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.bean.NewsBean;
import study.com.s_sxl.fmeituan.bean.VideoChannelTable;
import study.com.s_sxl.fmeituan.bean.VideoData;
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
    public ArrayList<NewsBean> getNews(int number){

        ArrayList<NewsBean> list = new ArrayList<>(number);

        List<String> urls = new ArrayList<>();
        urls.add("http://img5.imgtn.bdimg.com/it/u=3584830634,1222557427&fm=21&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=2894838897,3395831603&fm=21&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=475333573,2921291647&fm=21&gp=0.jpg");

        for(int i =0; i < number; i++){
            NewsBean bean = new NewsBean();
            bean.newsTitle = "聪明外露者德薄，词华太盛者福浅。聪明外露者德薄，词华太盛者福浅。" + i;
            bean.author = "傲娇小胖子";
            bean.commentNumber = "999评论";
            bean.newsTime = "25小时前";
            bean.newsImgList = urls;
            if(i%2==0){
                bean.isChange = true;
            }else {
                bean.isChange = false;
            }
            list.add(bean);
        }

        return list;
    }

    /**
     *  获取播放视频的个数
     * @param number
     * @return
     */
    public List<VideoData> getVideos(int number){
        List<VideoData> list = new ArrayList<>(number);

        for(int i = 0; i < number;i++){
            VideoData bean = new VideoData();
            VideoData.VideoTopicBean videoTopicBean = new VideoData.VideoTopicBean();
            bean.setTopicImg("http://vimg1.ws.126.net/image/snapshot/2016/6/B/7/VBOLSG6B7.jpg");
            bean.setVideosource("新媒体");
            bean.setMp4Hd_url("");
            bean.setTopicDesc("把最好的电影分享给你！");
            bean.setCover("http://vimg2.ws.126.net/image/snapshot/2016/12/C/L/VC7VEAQCL.jpg");
            bean.setDescription("");
            bean.setLength(162);
            bean.setM3u8Hd_url("");
            bean.setM3u8_url("http://flv2.bn.netease.com/tvmrepo/2016/12/Q/M/EC7VDI6QM/SD/movie_index.m3u8");
            bean.setMp4_url("http://flv2.bn.netease.com/tvmrepo/2016/12/Q/M/EC7VDI6QM/SD/EC7VDI6QM-mobile.mp4");
            bean.setPlayCount(998);
            bean.setPlayersize(1);
            bean.setPtime("12-22 14:34");
            bean.setReplyBoard("video_bbs");
            bean.setReplyid("C7VDJB91008535RB");
            bean.setSectiontitle("");
            bean.setTitle("为解救午马和村民，郑少秋、梁朝伟等几人不得不退缩");
            bean.setTopicName("放映厅");
            bean.setTopicSid("VBOLSG6B5");
            bean.setVid("VC7VDJB91");
            videoTopicBean.setAlias("听财哥讲财经");
            videoTopicBean.setEname("30秒懂财经");
            videoTopicBean.setTid("T1472544642251");
            videoTopicBean.setTname("T1472544642251");
            bean.setVideoTopic(videoTopicBean);
            list.add(bean);
        }
        return list;
    }

    public List<VideoChannelTable> getVideosChannels(){
        List<String> channelName = Arrays.asList(App.getAppResources().getStringArray(R.array.video_channel_name));
        List<String> channelId = Arrays.asList(App.getAppResources().getStringArray(R.array.video_channel_id));

        ArrayList<VideoChannelTable>newsChannelTables=new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            VideoChannelTable entity = new VideoChannelTable(channelId.get(i), channelName.get(i));
            newsChannelTables.add(entity);
        }
        return newsChannelTables;
    }
}
