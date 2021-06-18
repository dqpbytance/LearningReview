package com.example.learingreview.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.learingreview.R;
import com.example.learingreview.SuccessLogin;

public class SaveFragment extends Fragment {
    private EditText et_name,et_passwd;
    private Button btn_login;
    private CheckBox checkBox;
    private View view;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_save,container,false);
        et_name=view.findViewById(R.id.et_name);
        et_passwd=view.findViewById(R.id.et_passwd);
        btn_login=view.findViewById(R.id.login_btn);
        checkBox=view.findViewById(R.id.remember);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor=sharedPreferences.edit();

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        boolean isChencked=sharedPreferences.getBoolean("check",false);
        if (isChencked){
            et_name.setText(sharedPreferences.getString("name",""));
            et_passwd.setText(sharedPreferences.getString("passwd",""));
            checkBox.setChecked(true);
        }
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("admin".equals(et_name.getText().toString())&&"123456".equals(et_passwd.getText().toString())){
                    if(checkBox.isChecked()){
                        editor.putString("name","admin");
                        editor.putString("passwd","123456");
                        editor.putBoolean("check",true);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent=new Intent(getActivity(), SuccessLogin.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(),"账号密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
        System.out.println(getClass().toString());
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
