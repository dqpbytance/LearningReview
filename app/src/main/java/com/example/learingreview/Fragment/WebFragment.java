package com.example.learingreview.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learingreview.R;

public class WebFragment extends Fragment {
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_web,container,false);
        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Log.d("TAG", "onResume:WebFragment ");
//    }
}
