package com.example.usercwq.day02work;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.usercwq.day02work.adapters.TeacherAdparter;
import com.example.usercwq.day02work.bean.ActourBean;
import com.example.usercwq.day02work.bean.EventBean;
import com.example.usercwq.day02work.bean.TeacherBean;
import com.example.usercwq.day02work.fragment.JieShaoFragment;
import com.example.usercwq.day02work.fragment.KeChengFragment;
import com.example.usercwq.day02work.fragment.ZhuanTiFragment;
import com.example.usercwq.day02work.presenter.TeacherPreasenter;
import com.example.usercwq.day02work.view.TeacherView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherActivity extends AppCompatActivity implements TeacherView {

    @BindView(R.id.teacher)
    TextView teacher;
    @BindView(R.id.iv_tem_img)
    ImageView ivTemImg;
    @BindView(R.id.tv_actour)
    TextView tvActour;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rele)
    TextView tvRele;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @BindView(R.id.tablayout_tezcher)
    TabLayout tablayoutTezcher;
    @BindView(R.id.vp_teacher)
    ViewPager vpTeacher;

    private TeacherAdparter teacherAdparter;
    private ArrayList<String>    message = new ArrayList<>();;
    private int id;
    private List<TeacherBean.BodyBean.ResultBean> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {


        TeacherPreasenter teacherPreasenter = new TeacherPreasenter(this);
        teacherPreasenter.loadData(id);

    }

    private void nitTab3() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new JieShaoFragment());
        fragments.add(new KeChengFragment());
        fragments.add(new ZhuanTiFragment());
        teacherAdparter = new TeacherAdparter(getSupportFragmentManager(),
                fragments, this,message);
        vpTeacher.setAdapter(teacherAdparter);
        tablayoutTezcher.setupWithViewPager(vpTeacher);
    }

    private void initTab2() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new JieShaoFragment());
        fragments.add(new KeChengFragment());
        teacherAdparter = new TeacherAdparter(getSupportFragmentManager(),
                fragments, this,message);
        vpTeacher.setAdapter(teacherAdparter);
        tablayoutTezcher.setupWithViewPager(vpTeacher);
    }

    private void initTab1() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new JieShaoFragment());
        fragments.add(new KeChengFragment());
        fragments.add(new ZhuanTiFragment());
        teacherAdparter = new TeacherAdparter(getSupportFragmentManager(),
                fragments, this,message);
        vpTeacher.setAdapter(teacherAdparter);
        tablayoutTezcher.setupWithViewPager(vpTeacher);
    }


    @Subscribe(sticky =true)
    public void getEvenBean(EventBean eventBean){
        ActourBean.BodyBean.ResultBean bean = eventBean.getBean();
        int page = eventBean.getPage();
        tvActour.setText(bean.getTeacherName());
        tvTitle.setText(bean.getTitle());
        Glide.with(this).load(bean.getTeacherPic()).apply(new RequestOptions().circleCrop()).into(ivTemImg);
        tvRele.setText(bean.getEnterpriseID()+"");
        id = bean.getID();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setData(TeacherBean teacherBean) {
        result = teacherBean.getBody().getResult();
        for (int i = 0; i< result.size(); i++){
            String description = result.get(i).getDescription();
            message.add(description);
        }
        if (message.size()==1){
            initTab1();
        }else if (message.size()==2){
            initTab2();
        }else{
            nitTab3();
        }
    }
}
