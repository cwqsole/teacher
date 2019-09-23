package com.example.usercwq.day02work2.bean;

import java.util.ArrayList;

/**
 * Created by usercwq on 2019/9/22.
 */

public class EventBean {
    private ArrayList<FuLiBean.ResultsBean> list;
    private int page;

    public EventBean(ArrayList<FuLiBean.ResultsBean> list, int page) {
        this.list = list;
        this.page = page;
    }

    public ArrayList<FuLiBean.ResultsBean> getList() {
        return list;
    }

    public void setList(ArrayList<FuLiBean.ResultsBean> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
