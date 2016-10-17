package study.com.s_sxl.fmeituan.bean;

import java.io.Serializable;

/**
 * Copyright  @2016 珞珈德毅. All rights reserved.
 * <p>Description: 视频描述类</p>
 * @ClassName VideoBean
 * @Package study.com.s_sxl.fmeituan.bean
 * @Author S_sxl
 * @Time 2016/10/12
 */
public class VideoBean implements Serializable {
    //用户头像
    public String headImgUrl;
    //用户昵称
    public String userName;
    //视频封面链接
    public String videoPcUrl;
    //视频标题
    public String videoTitle;
    //视频时长
    public String videoTime;
    //播放次数
    public String playTimes;
    //评论次数
    public String comment;
}
