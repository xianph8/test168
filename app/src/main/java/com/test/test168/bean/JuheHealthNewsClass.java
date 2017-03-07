package com.test.test168.bean;

import java.io.Serializable;

/**
 * Created by King on 2017/3/6.
 */

public class JuheHealthNewsClass implements Serializable {


    /**
     * description : 社会热点，健康资讯，综合健康资讯,生活热点新闻,社会热点新闻,社会热点查询,提供最新的健康资讯,社会热点新闻网
     * id : 6
     * keywords : 社会热点
     * name : 社会热点
     * seq : 1
     * title : 社会热点
     */

    private String description;
    private String id;
    private String keywords;
    private String name;
    private int seq;
    private String title;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "JuheHealthNewsClass{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", keywords='" + keywords + '\'' +
                ", name='" + name + '\'' +
                ", seq=" + seq +
                ", title='" + title + '\'' +
                '}';
    }
}
