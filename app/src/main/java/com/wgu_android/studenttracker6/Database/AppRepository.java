package com.wgu_android.studenttracker6.Database;

import android.content.Context;

import com.wgu_android.studenttracker6.Entities.AssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;

    public List<TermEntity> mTerms;
    public List<CourseEntity> mCourses;
    public List<AssessmentEntity> mAssessments;
    private AppDatabase mDB;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {

        mTerms = SampleData.getTerm();
        mCourses = SampleData.getCourse();
        mAssessments = SampleData.getAssessment();

        mDB = AppDatabase.getDatabase(context);

    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDB.termDao().insertAll(SampleData.getTerm());
                mDB.courseDao().insertAll(SampleData.getCourse());
                mDB.assessmentDao().insertAll(SampleData.getAssessment());
            }
        });

    }
}
