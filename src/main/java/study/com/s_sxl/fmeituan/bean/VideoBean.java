package study.com.s_sxl.fmeituan.bean;

import java.io.Serializable;

@Deprecated
public class VideoBean implements Serializable {
    //用户头像
    public String headImgUrl;
    //用户昵称
    public String userName;
    //视频封面链接
    public String videoPcUrl;
    //播放次数
    public String playTimes;
    //评论次数
    public String comment;
}
