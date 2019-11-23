package com.wgu_android.studenttracker6.Database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.wgu_android.studenttracker6.Entities.AssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;

    public LiveData<List<TermEntity>> mTerms;
    public List<CourseEntity> mCourses;
    public List<AssessmentEntity> mAssessments;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {



        mDb = AppDatabase.getDatabase(context);
        mTerms = getAllTerms();
        mCourses = SampleData.getCourse();
        mAssessments = SampleData.getAssessment();

    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.termDao().insertAll(SampleData.getTerm());
                mDb.courseDao().insertAll(SampleData.getCourse());
                mDb.assessmentDao().insertAll(SampleData.getAssessment());
            }
        });

    }

    //*************************************************************
    //Term Methods
    private LiveData<List<TermEntity>> getAllTerms() {
        return mDb.termDao().getAllTerm();
    }

    public void deleteAllTerms() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.termDao().deleteAll();
            }
        });
    }
}
