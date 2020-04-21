package com.test.test168.arch.model;

import com.test.test168.bean.Response;

import java.util.List;

public class DetailsUI {

    public String title;
    public List<String> picList;
    public String content;

    public void setData(Response<String> response) {
        // covert response to ui data
        title = response.getData();
    }

}
