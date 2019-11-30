package com.wgu_android.studenttracker6.DAO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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

    @Query("SELECT term_table.term_id, term_name, term_start, term_end FROM term_table INNER JOIN term_course_table ON term_table.term_id=term_course_table.term_id " +
            "WHERE term_course_table.course_id = :courseId")
    LiveData<List<TermEntity>> getTermById_Courses(int courseId);

    @Query("SELECT course_table.course_id, course_name, course_start, course_end, course_status, course_mentor, mentor_phone, mentor_email, course_notes FROM course_table INNER JOIN term_course_table ON course_table.course_id=term_course_table.course_id " +
            "WHERE term_course_table.term_id = :termId")
    LiveData<List<CourseEntity>> getCourseById_Terms(int termId);

    @Query("SELECT * FROM term_course_table")
    LiveData<List<TermCourseEntity>> getAllTermCourses();

    @Query("DELETE FROM term_course_table")
    void deleteAll();
}
