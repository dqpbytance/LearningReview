package com.example.learingreview;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.learingreview.Adapter.AppFragmentPageAdapter;
import com.example.learingreview.Adapter.ScreenSlidePagerAdapter;
import com.example.learingreview.Fragment.InfoFragment;
import com.example.learingreview.Fragment.NetStateFragment;
import com.example.learingreview.Fragment.SaveFragment;
import com.example.learingreview.Fragment.WebFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private List<Fragment> fragmentList;
    private ViewPager2 container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container=findViewById(R.id.fragment_container);
        fragmentList=new ArrayList<>();
        fragmentList.add(new WebFragment());
        fragmentList.add(new NetStateFragment());
        fragmentList.add(new SaveFragment());
        fragmentList.add(new InfoFragment());

        container.setAdapter(new ScreenSlidePagerAdapter(MainActivity.this,fragmentList));
    }
}