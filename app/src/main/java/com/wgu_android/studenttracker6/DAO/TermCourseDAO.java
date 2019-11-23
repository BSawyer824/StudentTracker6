package com.wgu_android.studenttracker6.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermCourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;

import java.util.List;

@Dao
public interface TermCourseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TermCourseEntity termCourse);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TermCourseEntity> termCourse);
//
//    @Query("SELECT * FROM term_table INNER JOIN term_course_table ON term_table.term_id=term_course_table.term_id " +
//            "WHERE term_table.term_id = :id")
//    LiveData<List<TermEntity>> getTermById_Courses(int id);
//
//    @Query("SELECT * FROM course_table INNER JOIN term_course_table ON course_table.course_id=term_course_table.course_id " +
//            "WHERE course_table.course_id = :id")
//    LiveData<List<CourseEntity>> getCourseById_Terms(int id);


    
}
