package com.example.learingreview.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;
    public ScreenSlidePagerAdapter( FragmentActivity fragmentActivity,List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment createFragment(int position) {
        return fragmentList==null?null:fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList==null?0:fragmentList.size();
    }
}
