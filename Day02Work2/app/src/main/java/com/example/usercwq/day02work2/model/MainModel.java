package com.example.usercwq.day02work2.model;

import com.example.usercwq.day02work2.bean.FuLiBean;
import com.example.usercwq.day02work2.net.ApiService;
import com.example.usercwq.day02work2.net.ResultCallBack;

import java.util.ArrayList;

import javax.xml.transform.Result;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by usercwq on 2019/9/22.
 */

public class MainModel {
    public void loadData(int page, final ResultCallBack resultCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.url)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<FuLiBean> data = apiService.getData(page);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FuLiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FuLiBean fuLiBean) {
                            if (fuLiBean!=null){
                                resultCallBack.onSeccuss((ArrayList<FuLiBean.ResultsBean>) fuLiBean.getResults());
                            }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
