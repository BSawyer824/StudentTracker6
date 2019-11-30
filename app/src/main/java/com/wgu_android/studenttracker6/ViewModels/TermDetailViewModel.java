package com.wgu_android.studenttracker6.ViewModels;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wgu_android.studenttracker6.Database.AppRepository;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermCourseAssociationEntity;
import com.wgu_android.studenttracker6.Entities.TermCourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TermDetailViewModel extends AndroidViewModel {

    public MutableLiveData<TermEntity> mLiveTerm = new MutableLiveData<>();
    public LiveData<List<CourseEntity>> mCourse;
    public LiveData<List<CourseEntity>> mAssociatedCourses;
    public LiveData<List<TermCourseAssociationEntity>> mTermCourses;
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();
    private int termId;


    public TermDetailViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(getApplication());
        mCourse = mRepository.mCourses;
        mTermCourses = mRepository.mTermCourses;
    }


    public void loadData(final int termId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                TermEntity term = mRepository.getTermById(termId);
                mLiveTerm.postValue(term);
                mAssociatedCourses = mRepository.getAssociatedCourses(termId);

            }
        });
    }

    public void saveTerm(String termName, Date start, Date end) {
        TermEntity term = mLiveTerm.getValue();

        if (term == null) {
            //New Note
            if (TextUtils.isEmpty(termName.trim())) {
                return;
            } else {
                term = new TermEntity(termName.trim(), start, end);
            }

        } else {
            //Existing Note
            term.setTermName(termName);
            term.setTermStart(start);
            term.setTermEnd(end);
        }

        //Save to Repository
        mRepository.insertTerm(term);
    }

    public void deleteTerm() {
        mRepository.deleteTerm(mLiveTerm.getValue());
    }


    public LiveData<List<CourseEntity>> getAllCourses() {
        return mCourse;
    }

    public LiveData<List<TermCourseAssociationEntity>> getAllTermCourses() {
        return mTermCourses;
    }

    public List<TermCourseAssociationEntity> getListTermCourseAssociations() {
        return mRepository.getListTermCourseAssociations();
    }
}
