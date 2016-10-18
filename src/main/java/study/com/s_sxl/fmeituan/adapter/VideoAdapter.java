package study.com.s_sxl.fmeituan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

import study.com.s_sxl.carelib.viewUtils.roundedimageview.RoundedTransformationBuilder;
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
    public void onBindViewHolder(VideoViewHolder holder, final int position) {
        VideoBean videoBean = mList.get(position);

        Picasso.with(mContext).load(videoBean.videoPcUrl).into(holder.videoPc);
        holder.videoControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnClickListener != null){
                    mOnClickListener.OnClick(position);
                }
            }
        });
        holder.videoTimes.setText(videoBean.videoTime);
        RequestCreator creator = Picasso.with(mContext).load(videoBean.headImgUrl).resize(45, 45)
                .centerCrop().transform(new RoundedTransformationBuilder().oval(true).build());
        creator.into(holder.userHead);
        holder.userName.setText(videoBean.userName);
        holder.playTimes.setText(videoBean.playTimes);
        holder.comments.setText(videoBean.comment);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {

        ImageView videoControl;
        TextView videoTimes;
        ImageView userHead;
        TextView userName;
        TextView playTimes;
        TextView comments;
        ImageView videoPc;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoPc =  (ImageView) itemView.findViewById(R.id.surface_view);
            videoControl = (ImageView) itemView.findViewById(R.id.iv_control);
            videoTimes =  (TextView) itemView.findViewById(R.id.video_times);
            userHead = (ImageView) itemView.findViewById(R.id.video_head);
            userName = (TextView) itemView.findViewById(R.id.tv_userName);
            playTimes = (TextView) itemView.findViewById(R.id.tv_playTimes);
            comments = (TextView) itemView.findViewById(R.id.tv_comment);

        }
    }

    private OnClickItemListener mOnClickListener;

    public interface OnClickItemListener {
        void OnClick(int position);
    }

    public void setOnClicItemkListener(OnClickItemListener onClickItemListener){
        this.mOnClickListener = onClickItemListener;
    }
}
