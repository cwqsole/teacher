package com.example.usercwq.day02work2.net;

import com.example.usercwq.day02work2.bean.FuLiBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by usercwq on 2019/9/22.
 */

public interface ApiService {
    String url="http://gank.io/api/";
    @GET("data/%E7%A6%8F%E5%88%A9/20/{page}")
    Observable<FuLiBean> getData(@Path("page") int page);
}
