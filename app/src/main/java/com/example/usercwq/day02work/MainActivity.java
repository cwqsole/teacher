package com.example.usercwq.day02work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.usercwq.day02work.adapters.MainAdaptrer;
import com.example.usercwq.day02work.bean.ActourBean;
import com.example.usercwq.day02work.bean.EventBean;
import com.example.usercwq.day02work.net.ApiService;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rclv)
    RecyclerView rclv;
    @BindView(R.id.iv_huitui)
    ImageView ivHuitui;
    private ArrayList<ActourBean.BodyBean.ResultBean> resultBeans;
    private MainAdaptrer mainAdaptrer;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initRclv();
        initData();
        ivHuitui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        Retrofit build = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiService.url)
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<ActourBean> data = apiService.getDataActour();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ActourBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ActourBean wanAndroidBean) {
                        Log.i(TAG, "onNext: " + wanAndroidBean.toString());
                        List<ActourBean.BodyBean.ResultBean> result = wanAndroidBean.getBody()
                                .getResult();
                        resultBeans.addAll(result);
                        mainAdaptrer.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onNext: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void initRclv() {
        rclv.setLayoutManager(new LinearLayoutManager(this));
        rclv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        resultBeans = new ArrayList<>();
        mainAdaptrer = new MainAdaptrer(resultBeans, this);
        rclv.setAdapter(mainAdaptrer);
        mainAdaptrer.setOnItemClick(new MainAdaptrer.OnItemClick() {
            @Override
            public void onItem(View v, int position) {
                go2TeacherActivity(position);
            }
        });
    }

    private void go2TeacherActivity(int position) {
        EventBus.getDefault().postSticky(new EventBean(resultBeans.get(position),position));
        startActivity(new Intent(this,TeacherActivity.class));
    }
}
