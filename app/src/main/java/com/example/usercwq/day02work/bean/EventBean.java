package com.example.usercwq.day02work.bean;

import java.util.ArrayList;

/**
 * Created by usercwq on 2019/9/22.
 */

public class EventBean  {
    private ActourBean.BodyBean.ResultBean bean;
    private int page;

    public EventBean(ActourBean.BodyBean.ResultBean bean, int page) {
        this.bean = bean;
        this.page = page;
    }

    public ActourBean.BodyBean.ResultBean getBean() {
        return bean;
    }

    public void setBean(ActourBean.BodyBean.ResultBean bean) {
        this.bean = bean;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
