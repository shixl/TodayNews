package study.com.s_sxl.fmeituan.fragment.homeFragments;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import butterknife.Bind;
import study.com.s_sxl.carelib.fragment.BaseFragment;
import study.com.s_sxl.carelib.pullRefreshView.layout.BaseFooterView;
import study.com.s_sxl.carelib.pullRefreshView.layout.BaseHeaderView;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.adapter.RecommendAdapter;
import study.com.s_sxl.fmeituan.bean.NewsBean;
import study.com.s_sxl.fmeituan.constant.DataSimulation;

public class RecommendFragment extends BaseFragment implements BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {

    @Bind(R.id.list)
    ListView mLv;

    @Bind(R.id.header)
    BaseHeaderView mHeader;

    @Bind(R.id.footer)
    BaseFooterView mFooter;

    private RecommendAdapter mRecommendAdapter;
    public int page = 1;
    private List<NewsBean> mNews;

    @Override
    protected void init(Bundle savedInstanceState) {
        init();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    private void init() {
        mNews = DataSimulation.getInstance().getNews(10);

        if(mRecommendAdapter == null){
            mRecommendAdapter = new RecommendAdapter(getContext());
        }
        mRecommendAdapter.addAll(mNews);
        mLv.setAdapter(mRecommendAdapter);

        mHeader.setOnRefreshListener(this);
        mFooter.setOnLoadListener(this);
    }


    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                List<NewsBean> news = DataSimulation.getInstance().getNews(5);
                mRecommendAdapter.replaceAll(news);
                mHeader.stopRefresh();
            }
        }, 3000);
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        baseFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                List<NewsBean> news = DataSimulation.getInstance().getNews(5);
                mRecommendAdapter.addAll(mNews);
                mFooter.stopLoad();
            }
        }, 3000);
    }


}
