package study.com.s_sxl.fmeituan.presenter;

import java.util.List;

import study.com.s_sxl.carelib.baseRx.RxSubscriber;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.bean.VideoData;
import study.com.s_sxl.fmeituan.contract.VideosListContract;

/**
 * des:
 * Created by xsf
 * on 2016.09.14:53
 */
public class VideoListPresenter extends VideosListContract.Presenter {

    @Override
    public void onStart() {
        super.onStart();
//        //监听返回顶部动作
//       mRxManage.on(AppConstant.NEWS_LIST_TO_TOP, new Action1<Object>() {
//           @Override
//           public void call(Object o) {
//            mView.scrolltoTop();
//           }
//       });
    }

    /**
     * 获取视频列表请求
     * @param type
     * @param startPage
     */
    @Override
    public void getVideosListDataRequest(String type, int startPage) {
        mRxManage.add(mModel.getVideosListData(type,startPage).subscribe(new RxSubscriber<List<VideoData>>(mContext,false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }
            @Override
            protected void _onNext(List<VideoData> videoDatas) {
                mView.returnVideosListData(videoDatas);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
