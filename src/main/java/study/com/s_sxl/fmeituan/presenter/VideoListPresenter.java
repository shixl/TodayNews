package study.com.s_sxl.fmeituan.presenter;

import java.util.List;

import study.com.s_sxl.fmeituan.bean.VideoData;
import study.com.s_sxl.fmeituan.constant.DataSimulation;
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

    }

    /**
     * 获取视频列表请求

     * @param startPage
     */
    @Override
    public void getVideosListDataRequest(int startPage) {
       /* mRxManage.add(mModel.getVideosListData(type,startPage).subscribe(new RxSubscriber<List<VideoData>>(mContext,false) {
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
        }));*/
        List<VideoData> videoDatas = DataSimulation.getInstance().getVideos(startPage + 10);
        mView.returnVideosListData(videoDatas);
    }
}
