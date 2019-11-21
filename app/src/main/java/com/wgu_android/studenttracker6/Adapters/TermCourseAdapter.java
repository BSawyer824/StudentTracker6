package com.wgu_android.studenttracker6.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu_android.studenttracker6.Entities.TermCourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;

import java.util.List;

public class TermCourseAdapter extends RecyclerView.Adapter<TermCourseAdapter.ViewHolder> {

    private final List<TermCourseEntity> mTermCourses;
    private final Context mContext;

    public TermCourseAdapter(List<TermCourseEntity> mTermCourses, Context mContext) {
        this.mTermCourses = mTermCourses;
        this.mContext = mContext;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
