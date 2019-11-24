package com.wgu_android.studenttracker6.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.wgu_android.studenttracker6.Database.AppRepository;
import com.wgu_android.studenttracker6.Entities.CourseEntity;

public class CourseDetailViewModel extends AndroidViewModel {

    public MutableLiveData<CourseEntity> mLiveCourse = new MutableLiveData<>();
    private AppRepository mRepository;

    public CourseDetailViewModel(@NonNull Application application) {

        super(application);

        mRepository = AppRepository.getInstance((getApplication()));
    }
}
