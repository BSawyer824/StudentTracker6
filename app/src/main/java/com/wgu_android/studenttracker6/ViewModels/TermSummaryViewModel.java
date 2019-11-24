package com.wgu_android.studenttracker6.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.wgu_android.studenttracker6.Database.AppRepository;
import com.wgu_android.studenttracker6.Database.SampleData;
import com.wgu_android.studenttracker6.Entities.AssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;

import java.util.List;

public class TermSummaryViewModel extends AndroidViewModel {

    public LiveData<List<TermEntity>> mTerms;
    public LiveData<List<CourseEntity>> mCourses;
    public List<AssessmentEntity> mAssessments;
    private AppRepository mRepository;

    public TermSummaryViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mTerms = mRepository.mTerms;
        mCourses = mRepository.mCourses;
        mAssessments = mRepository.mAssessments;

    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllData() {

        //deletes all data in all tables
        mRepository.deleteAllTerms();
        mRepository.deleteAllCourses();
    }
}
