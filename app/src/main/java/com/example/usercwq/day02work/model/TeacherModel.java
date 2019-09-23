package com.example.usercwq.day02work.model;

import com.example.usercwq.day02work.bean.TeacherBean;
import com.example.usercwq.day02work.net.ApiService;
import com.example.usercwq.day02work.net.ResultCallBack;
import com.example.usercwq.day02work.presenter.TeacherPreasenter;

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

public class TeacherModel {
    public void loadData(int id, final ResultCallBack resultCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<TeacherBean> teacher = apiService.getTeacher(id);
        teacher.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<TeacherBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeacherBean teacherBean) {
                        resultCallBack.setDataTeacher(teacherBean);
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
