package com.example.usercwq.day02work2.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.usercwq.day02work2.R;
import com.example.usercwq.day02work2.bean.BaseApp;
import com.example.usercwq.day02work2.bean.FuLiBean;
import com.example.usercwq.day02work2.util.SystemUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by usercwq on 2019/9/22.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyItem> {
    private ArrayList<FuLiBean.ResultsBean> list;
    private Context context;

    public MainAdapter(ArrayList<FuLiBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public MainAdapter() {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_item_adapter,
                parent, false);
        MyItem myItem = new MyItem(inflate);
        return myItem;
    }

    @Override
    public void onBindViewHolder(@NonNull MyItem holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItem(v,position);
            }
        });
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.ivAdapterImg.getLayoutParams();
        //宽 = 屏幕的一半
        layoutParams.width = BaseApp.widthPixels/2 - SystemUtil.dp2px(8);
        //左的边距
        layoutParams.leftMargin = SystemUtil.dp2px(4);
        //上边距
        layoutParams.topMargin = SystemUtil.dp2px(6);
        if(this.list.get(position).getScale()!=0){
            layoutParams.height= (int) (layoutParams.width/this.list.get(position).getScale());
        }
        holder.ivAdapterImg.setLayoutParams(layoutParams);
        Glide.with(context).load(this.list.get(position).getUrl()).into(holder.ivAdapterImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<FuLiBean.ResultsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
        setImageWhScale();
    }

    private void setImageWhScale() {
        for (final FuLiBean.ResultsBean bean:list){
            if (bean.getScale()==0){
                Glide.with(context).load(bean.getUrl())
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable
                                    Transition<? super Drawable> transition) {
                                int intrinsicHeight = resource.getIntrinsicHeight();
                                int intrinsicWidth = resource.getIntrinsicWidth();
                                //计算宽高比
                                bean.setScale(intrinsicWidth *1.0f/intrinsicHeight);
                                notifyDataSetChanged();
                            }
                        });

            }else{
                notifyDataSetChanged();
            }
        }
    }

    public class MyItem extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_adapter_img)
        ImageView ivAdapterImg;
        public MyItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public  interface OnItemClick{
        void onItem(View v, int position);
    }
}
