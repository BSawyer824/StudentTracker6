package com.wgu_android.studenttracker6.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wgu_android.studenttracker6.Entities.TermCourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;

import java.util.List;

@Dao
public interface TermCourseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TermCourseEntity termCourse);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TermCourseEntity> termCourse);

    @Query("DELETE FROM term_course_table")
    void deleteAll();

    @Delete
    void deleteTermCourse(TermCourseEntity termCourseEntity);

    @Query("SELECT * FROM term_course_table ORDER BY term_course_id ASC")
    LiveData<List<TermCourseEntity>> getAllTermCourse();

//    @Query("SELECT * FROM term_course_table WHERE term_course_id = :id")
//    TermCourseEntity getTermCourseById(int id);

    
}
