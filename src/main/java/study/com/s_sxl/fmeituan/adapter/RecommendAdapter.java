package study.com.s_sxl.fmeituan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import study.com.s_sxl.carelib.adapter.BaseAdapterHelper;
import study.com.s_sxl.carelib.adapter.QuickAdapter;
import study.com.s_sxl.carelib.utils.ToastMgr;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.bean.NewsBean;

public class RecommendAdapter extends QuickAdapter<NewsBean> {

    public RecommendAdapter(Context context) {
        super(context, R.layout.coustom_lv_item);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, NewsBean item) {
        if(item != null){
            helper.setText(R.id.item_tv,item.newsTitle);
            helper.setText(R.id.tv_name,item.author);
            helper.setText(R.id.tv_time,item.newsTime);
            helper.setText(R.id.tv_comment,item.commentNumber);
            helper.setOnClickListener(R.id.iv_delete, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastMgr.show("暂时先不处理");
                }
            });

            LinearLayout llPic = (LinearLayout) helper.getView().findViewById(R.id.ll_iv);
            int childCount = llPic.getChildCount();
            for(int i = 0; i < childCount; i++){
                llPic.getChildAt(i).setId(i);
                helper.setImageUrl(llPic.getChildAt(i).getId(),item.newsImgList.get(i));
            }
        }
    }
}
