package com.wgu_android.studenttracker6.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wgu_android.studenttracker6.CourseDetailActivity;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.wgu_android.studenttracker6.Utilities.Constants.COURSE_KEY_ID;
import static com.wgu_android.studenttracker6.Utilities.Constants.COURSE_NAME;
import static com.wgu_android.studenttracker6.Utilities.Constants.COURSE_NEW;
import static com.wgu_android.studenttracker6.Utilities.Constants.COURSE_STATUS;
import static com.wgu_android.studenttracker6.Utilities.Constants.COURSE_TERM_ID;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<CourseEntity> mCourses;
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

        //assign start and end dates to a string
        Date startDate = course.getCourseStart();
        Date endDate = course.getCourseEnd();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String strDate = dateFormat.format(startDate);
        String strDateEnd = dateFormat.format(endDate);
        String label = strDate + "  to  " + strDateEnd;

        holder.mTextViewCourseName.setText(course.getCourseName());
        holder.mTextViewCourseDates.setText(label);


        //When a Course is clicked in the recycler view, send selected course to the next activity
        holder.mTextViewCourseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CourseDetailActivity.class);
                intent.putExtra(COURSE_KEY_ID, course.getCourseID());
                intent.putExtra(COURSE_NAME, course.getCourseName());
                intent.putExtra(COURSE_STATUS, course.getCourseStatus());
                intent.putExtra(COURSE_TERM_ID, course.getFkTermId());
                intent.putExtra(COURSE_NEW, false);
                mContext.startActivity(intent);
            }
        });
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

    public void setCourses(List<CourseEntity> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

    public CourseEntity getCourseById(int courseId) {
        for (CourseEntity c: mCourses)
            if(c.getCourseID() == courseId)
                return c;
        return null;
    }


}
