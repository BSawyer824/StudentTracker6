package com.wgu_android.studenttracker6.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wgu_android.studenttracker6.Entities.CourseAssessmentEntity;
import com.wgu_android.studenttracker6.Entities.TermCourseEntity;

import java.util.List;

@Dao
public interface CourseAssessmentDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseAssessmentEntity courseAssessment);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CourseAssessmentEntity> courseAssessment);

    @Query("DELETE FROM course_assessment_table")
    void deleteAll();

    @Delete
    void deleteCourseAssessment(CourseAssessmentEntity courseAssessmentEntity);

    @Query("SELECT * FROM course_assessment_table ORDER BY course_assessment_id ASC")
    LiveData<List<CourseAssessmentEntity>> getAllCourseAssessment();

//    @Query("SELECT * FROM course_assessment_table WHERE course_assessment_id = :id")
//    CourseAssessmentEntity getCourseAssessmentById(int id);


}
