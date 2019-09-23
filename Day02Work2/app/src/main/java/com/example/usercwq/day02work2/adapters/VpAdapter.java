package com.example.usercwq.day02work2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.usercwq.day02work2.R;
import com.example.usercwq.day02work2.bean.FuLiBean;

import java.util.ArrayList;

/**
 * Created by usercwq on 2019/9/22.
 */

public class VpAdapter extends PagerAdapter {
    private ArrayList<FuLiBean.ResultsBean> list;
    private Context context;

    public VpAdapter(ArrayList<FuLiBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.vp_item, null, false);
        ImageView iv = inflate.findViewById(R.id.iv);
        Glide.with(context).load(list.get(position).getUrl()).into(iv);
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      //  super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
