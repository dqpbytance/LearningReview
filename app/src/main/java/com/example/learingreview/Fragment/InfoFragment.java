package com.example.learingreview.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learingreview.Adapter.StudentInfoAdapter;
import com.example.learingreview.R;
import com.example.learingreview.Student;
import com.example.learingreview.Util.WebUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class InfoFragment extends Fragment {
    private View view;
    private Button btn_send;
    private RecyclerView recyclerView;
    private List<Student> studentList;
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_studentinfo,container,false);
        btn_send=view.findViewById(R.id.send_request);
        recyclerView=view.findViewById(R.id.rv_student_info);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebUtil.OkhttpConnection("http://10.95.46.192:8080/testjson/test.json", new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        parseWithGSON(response.body().string());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                Log.d("TAG", "run: "+studentList.size());
                                recyclerView.setAdapter(new StudentInfoAdapter(studentList));
                            }
                        });

                    }
                });
            }
        });
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        System.out.println(getClass().toString());
    }

    private void parseWithGSON(String respnoseData){
        Gson gson=new Gson();
        studentList= gson.fromJson(respnoseData,new TypeToken<List<Student>>(){}.getType());
    }
}
