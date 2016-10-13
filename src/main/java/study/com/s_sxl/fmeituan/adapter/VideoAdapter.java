package study.com.s_sxl.fmeituan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.vov.vitamio.widget.VideoView;
import study.com.s_sxl.fmeituan.R;
import study.com.s_sxl.fmeituan.bean.VideoBean;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context mContext;
    private List<VideoBean> mList;

    public VideoAdapter(Context context, List<VideoBean> list){
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        VideoBean videoBean = mList.get(holder.getLayoutPosition());
        holder.mVideoView.setVideoPath(videoBean.videoUrl);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        VideoView mVideoView;

        public VideoViewHolder(View itemView) {
            super(itemView);

            mVideoView = (VideoView) itemView.findViewById(R.id.surface_view);
        }
    }
}
