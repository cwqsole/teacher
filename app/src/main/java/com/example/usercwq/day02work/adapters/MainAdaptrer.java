package com.example.usercwq.day02work.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.usercwq.day02work.R;
import com.example.usercwq.day02work.bean.ActourBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by usercwq on 2019/9/20.
 */

public class MainAdaptrer extends RecyclerView.Adapter<MainAdaptrer.MyItem> {
    private ArrayList<ActourBean.BodyBean.ResultBean> list;
    private Context context;

    public MainAdaptrer(ArrayList<ActourBean.BodyBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_itemapdater,
                parent, false);
        MyItem myItem = new MyItem(inflate);
        return myItem;
    }

    @Override
    public void onBindViewHolder(@NonNull MyItem holder, final int position) {
        holder.tvActour.setText(list.get(position).getTeacherName());
        holder.tvTitle.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getTeacherPic()).apply(new RequestOptions().circleCrop()).into(holder.ivTemImg);
        holder.tv_rele.setText(list.get(position).getEnterpriseID()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick!=null){
                    onItemClick.onItem(v,position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyItem extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_tem_img)
        ImageView ivTemImg;
        @BindView(R.id.tv_actour)
        TextView tvActour;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_guanzhu)
        TextView tvGuanzhu;
        @BindView(R.id.tv_rele)
        TextView tv_rele;
        public MyItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void onItem(View v, int position);
    }

}
