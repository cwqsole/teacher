package com.example.usercwq.day02work.presenter;

import com.example.usercwq.day02work.TeacherActivity;
import com.example.usercwq.day02work.bean.ActourBean;
import com.example.usercwq.day02work.bean.TeacherBean;
import com.example.usercwq.day02work.model.TeacherModel;
import com.example.usercwq.day02work.net.ResultCallBack;
import com.example.usercwq.day02work.view.TeacherView;

/**
 * Created by usercwq on 2019/9/22.
 */

public class TeacherPreasenter implements ResultCallBack{

    private final TeacherModel teacherModel;
    private final TeacherView teacherView;

    public TeacherPreasenter(TeacherView teacherView) {
        this.teacherView =teacherView;
        teacherModel = new TeacherModel();
    }

    public void loadData(int id) {

        teacherModel.loadData(id, this);
    }



    @Override
    public void setDataTeacher(TeacherBean teacherBean) {
        if (teacherView!=null){
            teacherView.setData(teacherBean);
        }
    }
}
