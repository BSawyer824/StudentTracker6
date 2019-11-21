package com.wgu_android.studenttracker6.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu_android.studenttracker6.Entities.AssessmentEntity;
import com.wgu_android.studenttracker6.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssessmentsAdapter extends RecyclerView.Adapter<AssessmentsAdapter.ViewHolder> {

    private final List<AssessmentEntity> mAssessments;
    private final Context mContext;

    public AssessmentsAdapter(List<AssessmentEntity> mAssessments, Context mContext) {
        this.mAssessments = mAssessments;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.assessment_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AssessmentEntity assessment = mAssessments.get(position);
        holder.mTextViewAssessmentName.setText(assessment.getAssessmentName());

    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_Assessment_Name)
        TextView mTextViewAssessmentName;

        @BindView(R.id.textView_Assessment_Dates)
        TextView mTextViewAssessmentDates;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
