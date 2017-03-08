package com.test.test168.bean;

import java.io.Serializable;

/**
 * Created by King on 2017/3/8.
 */

public class JuheHealthNewsClassListItem implements Serializable {

    /**
     * count : 165
     * description : 北京市控烟执法3个月 罚款总计超37万元 3D演示“史上最严”控烟令用3种劝阻吸烟手势时长：1'43''播放：18874来源：QQ播客3D演示“史上最严”控烟令用3种劝阻吸烟手势关闭自动播放相关专辑推荐视频：收起视频正在播放据统计，北京市控烟执法三个月以来，群众对违法吸烟共举报7986件，全市共出动卫生监督执法人员31804人次，共监督检查20566户次；发现不合格单位3777户次，责令整改3128户次，卫生监督行政处罚共144户，罚款35
     * fcount : 0
     * id : 6088
     * img : http://tnfs.tngou.net/image/info/150912/6f1f81106d337d769882e34f481cc191.jpg
     * infoclass : 6
     * keywords : 北京市 吸烟 执法人员 监督 卫生
     * rcount : 0
     * time : 1442027530000
     * title : 8月是北京市控烟集中行政处罚第三个月
     */

    private int count;
    private String description;
    private int fcount;
    private String id;
    private String img;
    private int infoclass;
    private String keywords;
    private int rcount;
    private long time;
    private String title;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getInfoclass() {
        return infoclass;
    }

    public void setInfoclass(int infoclass) {
        this.infoclass = infoclass;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "JuheHealthNewsClassListItem{" +
                "count=" + count +
                ", description='" + description + '\'' +
                ", fcount=" + fcount +
                ", id=" + id +
                ", img='" + img + '\'' +
                ", infoclass=" + infoclass +
                ", keywords='" + keywords + '\'' +
                ", rcount=" + rcount +
                ", time=" + time +
                ", title='" + title + '\'' +
                '}';
    }
}
