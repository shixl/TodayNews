package study.com.s_sxl.fmeituan.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsBean implements Serializable {

    public String newsTitle;
    public List<String> newsImgList = new ArrayList<>();
    public String author;
    public String commentNumber;
    public String newsTime;

}
