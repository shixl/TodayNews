package study.com.s_sxl.fmeituan.contract;


import java.util.List;

import rx.Observable;
import study.com.s_sxl.carelib.base.BaseModel;
import study.com.s_sxl.carelib.base.BasePresenter;
import study.com.s_sxl.carelib.base.BaseView;
import study.com.s_sxl.fmeituan.bean.VideoData;

/**
 * des:视频列表contract
 * Created by xsf
 * on 2016.09.14:38
 */
public interface VideosListContract {
    interface Model extends BaseModel {
        //请求获取视频
        Observable<List<VideoData>> getVideosListData(String type, int startPage);
    }

    interface View extends BaseView {
        //返回获取的视频
        void returnVideosListData(List<VideoData> videoDataList);
//        //返回顶部
//        void scrolltoTop();
    }
    abstract static class Presenter extends BasePresenter<View, Model> {
        //发起获取视频请求
        public abstract void getVideosListDataRequest(int startPage);
    }
}
