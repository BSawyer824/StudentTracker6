package com.wgu_android.studenttracker6.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;
import com.wgu_android.studenttracker6.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private final List<CourseEntity> mCourses;
    private final Context mContext;

    public CourseAdapter(List<CourseEntity> mCourses, Context mContext) {
        this.mCourses = mCourses;
        this.mContext = mContext;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.course_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CourseEntity course = mCourses.get(position);
        holder.mTextViewCourseName.setText(course.getCourseName());
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_Course_Name)
        TextView mTextViewCourseName;

        @BindView(R.id.textView_Course_Dates)
        TextView mTextViewCourseDates;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
