package study.com.s_sxl.fmeituan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import study.com.s_sxl.carelib.adapter.BaseAdapterHelper;
import study.com.s_sxl.carelib.adapter.MultiItemTypeSupport;
import study.com.s_sxl.carelib.adapter.QuickAdapter;
import study.com.s_sxl.carelib.utils.ToastMgr;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.bean.NewsBean;

public class RecommendAdapter extends QuickAdapter<NewsBean> {

    public RecommendAdapter(Context context, ArrayList<NewsBean> mData, MultiItemTypeSupport<NewsBean> multiItemTypeSupport) {
        super(context, mData, multiItemTypeSupport);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, NewsBean item) {

        switch (helper.layoutId) {

            case R.layout.coustom_lv_item:
                if (item != null) {
                    helper.setText(R.id.item_tv, item.newsTitle);
                    helper.setText(R.id.tv_name, item.author);
                    helper.setText(R.id.tv_time, item.newsTime);
                    helper.setText(R.id.tv_comment, item.commentNumber);
                    helper.setOnClickListener(R.id.iv_delete, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastMgr.show("暂时先不处理");
                        }
                    });

                    LinearLayout llPic = (LinearLayout) helper.getView().findViewById(R.id.ll_iv);
                    int childCount = llPic.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        llPic.getChildAt(i).setId(i);
                        helper.setImageUrl(llPic.getChildAt(i).getId(), item.newsImgList.get(i));
                    }
                }
                break;
            case R.layout.coustom_lv_item_two:
                helper.setText(R.id.item_tv,item.newsTitle);
                helper.setText(R.id.tv_name, item.author);
                helper.setText(R.id.tv_time, item.newsTime);
                helper.setText(R.id.tv_comment, item.commentNumber);
                helper.setOnClickListener(R.id.iv_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastMgr.show("暂时先不处理");
                    }
                });

                helper.setImageUrl(R.id.iv_news,item.newsImgList.get(0));
                break;
        }


    }
}
