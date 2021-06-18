package com.example.learingreview.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learingreview.R;
import com.example.learingreview.Student;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.MyViewHolder> {
    private List<Student> studentList;

    public StudentInfoAdapter(List<Student> studentList){
        this.studentList=studentList;
    }
    @Override
    public StudentInfoAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_studentinfo,parent,false));
    }

    @Override
    public void onBindViewHolder(StudentInfoAdapter.MyViewHolder holder, int position) {
        System.out.println(1);
        Student student=studentList.get(position);
        holder.tv_name.setText(student.getName());
        holder.tv_id.setText(student.getId());
        holder.tv_interest.setText(student.getInterest());
    }

    @Override
    public int getItemCount() {
        return this.studentList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_id,tv_interest;
        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.student_name);
            tv_id=itemView.findViewById(R.id.student_id);
            tv_interest=itemView.findViewById(R.id.student_interest);
        }
    }
}
