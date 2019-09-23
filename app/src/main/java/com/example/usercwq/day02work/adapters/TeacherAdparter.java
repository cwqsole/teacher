package com.example.usercwq.day02work.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by usercwq on 2019/9/22.
 */

public class TeacherAdparter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private Context context;
    private ArrayList<String> message;

    public TeacherAdparter(FragmentManager fm, ArrayList<Fragment> fragments, Context context,
                           ArrayList<String> message) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
        this.message=message;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return message.get(position);
    }
}
