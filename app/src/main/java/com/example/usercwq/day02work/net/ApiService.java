package com.example.usercwq.day02work.net;

import com.example.usercwq.day02work.bean.ActourBean;
import com.example.usercwq.day02work.bean.TeacherBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by usercwq on 2019/9/20.
 */

public interface ApiService {
     String url="https://api.yunxuekeji.cn/yunxue_app_api/";

    @GET("content/getData/30/66597/1/10")
    Observable<ActourBean> getDataActour();
    @GET("teacher/getTeacherPower/{id}")
    Observable<TeacherBean> getTeacher(@Path("id") int id);

}
