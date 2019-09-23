package com.example.usercwq.day02work2.presenter;

import com.example.usercwq.day02work2.MainActivity;
import com.example.usercwq.day02work2.bean.FuLiBean;
import com.example.usercwq.day02work2.model.MainModel;
import com.example.usercwq.day02work2.net.ResultCallBack;
import com.example.usercwq.day02work2.view.MainView;

import java.util.ArrayList;

/**
 * Created by usercwq on 2019/9/22.
 */

public class MainPresenter implements ResultCallBack{
    private final MainView mainView;
    private final MainModel mainModel;

    public MainPresenter(MainView mainView) {
        this.mainView=mainView;
        mainModel = new MainModel();

    }

    public void loadData(int page) {
        mainModel.loadData(page,this);
    }

    @Override
    public void onSeccuss(ArrayList<FuLiBean.ResultsBean> list) {
        if (mainView!=null){
            mainView.setData(list);
        }
    }
}
