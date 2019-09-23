package com.example.usercwq.day02work2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.usercwq.day02work2.adapters.MainAdapter;
import com.example.usercwq.day02work2.bean.EventBean;
import com.example.usercwq.day02work2.bean.FuLiBean;
import com.example.usercwq.day02work2.presenter.MainPresenter;
import com.example.usercwq.day02work2.view.MainView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.rclv)
    RecyclerView rclv;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.iv_main_img)
    ImageView ivMainImg;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    int page=1;
    private ArrayList<FuLiBean.ResultsBean> resultsBeans;
    private MainAdapter mainAdapter;
    private VpFragment vpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ivMainImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .hide(vpFragment)
                        .commit();
            }
        });
    }

    private void initView() {
        initRclv();
        final MainPresenter mainPresenter = new MainPresenter(this);
        mainPresenter.loadData(page);
        smart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smart.finishLoadMore(true);

                page++;
                mainPresenter.loadData(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart.finishRefresh(true);
                resultsBeans.clear();
                mainAdapter.notifyDataSetChanged();
                page=1;
                mainPresenter.loadData(page);

            }
        });
    }

    private void initRclv() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        rclv.setLayoutManager(manager);
        //帮证图片不错乱
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        rclv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        resultsBeans = new ArrayList<>();
        mainAdapter = new MainAdapter(resultsBeans, this);
        rclv.setAdapter(mainAdapter);
        mainAdapter.setOnItemClick(new MainAdapter.OnItemClick() {
            @Override
            public void onItem(View v, int position) {
                go2MvVpAdapter(position);
            }
        });
    }

    private void go2MvVpAdapter(int position) {
        vpFragment = new VpFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl, vpFragment)
                .show(vpFragment)
                .commit();
        EventBus.getDefault().postSticky(new EventBean(resultsBeans, position));
    }
    @Override
    public void setData(ArrayList<FuLiBean.ResultsBean> list) {

        mainAdapter.setData(list);
    }
}
