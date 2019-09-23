package com.example.usercwq.day02work2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usercwq.day02work2.adapters.VpAdapter;
import com.example.usercwq.day02work2.bean.EventBean;
import com.example.usercwq.day02work2.bean.FuLiBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class VpFragment extends Fragment {

    protected boolean isVisible;
    @BindView(R.id.Vp)
    ViewPager vp;
    Unbinder unbinder;
    @BindView(R.id.tv_count)
    TextView tvCount;

    public VpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vp, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);

    }

    @Subscribe(sticky = true)
    public void getEventBean(EventBean eventBean){
        final ArrayList<FuLiBean.ResultsBean> list = eventBean.getList();
        int page = eventBean.getPage();
        VpAdapter vpAdapter = new VpAdapter(list,getContext());
        vp.setAdapter(vpAdapter);
        vp.setCurrentItem(page);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tvCount.setText((position+1)+"/"+list.size());
            }

            @Override
            public void onPageSelected(int position) {
                tvCount.setText((position+1)+"/"+list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
