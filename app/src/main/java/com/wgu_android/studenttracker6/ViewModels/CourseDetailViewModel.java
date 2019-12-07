package com.wgu_android.studenttracker6.ViewModels;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wgu_android.studenttracker6.Database.AppRepository;
import com.wgu_android.studenttracker6.Entities.AssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CourseDetailViewModel extends AndroidViewModel {

    public MutableLiveData<CourseEntity> mLiveCourse = new MutableLiveData<>();
    public LiveData<List<AssessmentEntity>> mAssessment;
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public CourseDetailViewModel(@NonNull Application application) {

        super(application);

        mRepository = AppRepository.getInstance((getApplication()));
        mAssessment = mRepository.mAssessments;
    }

    public void loadData(final int courseId) {
        //displays a course in the course detaail activity
        executor.execute(new Runnable() {
            @Override
            public void run() {
                CourseEntity course = mRepository.getCourseById(courseId);
                mLiveCourse.postValue(course);
            }
        });
    }

    public void deleteCourse() {
        mRepository.deleteCourse(mLiveCourse.getValue());
    }

    public void saveCourse(String courseName, Date courseStart, Date courseEnd, String mentorName,
                           String mentorPhone, String mentorEmail, String courseNotes, String courseStatus, int termId) {

        CourseEntity course = mLiveCourse.getValue();
        if (course == null) {
            //New Course
            if (TextUtils.isEmpty(courseName.trim())) {
                return;
            } else {
                course = new CourseEntity(courseName.trim(), courseStart, courseEnd, courseStatus,
                        mentorName, mentorPhone, mentorEmail, courseNotes, termId);
            }
        } else {
            //Existing Course
            course.setCourseName(courseName);
            course.setCourseStart(courseStart);
            course.setCourseEnd(courseEnd);
            course.setCourseStatus(courseStatus);
            course.setCourseMentorName(mentorName);
            course.setCourseMentorPhone(mentorPhone);
            course.setCourseMentorEmail(mentorEmail);
            course.setCourseNotes(courseNotes);
            course.setFkTermId(termId);
        }

        //Save to Repository
        mRepository.insertCourse(course);
    }
}
