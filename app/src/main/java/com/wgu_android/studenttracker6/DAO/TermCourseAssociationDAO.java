package com.wgu_android.studenttracker6.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wgu_android.studenttracker6.Entities.TermCourseAssociationEntity;
import com.wgu_android.studenttracker6.Entities.TermCourseEntity;

import java.util.List;

@Dao
public interface TermCourseAssociationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TermCourseAssociationEntity termCourse);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<TermCourseAssociationEntity> termCourse);

    @Query("SELECT * FROM term_course_association_table")
    LiveData<List<TermCourseAssociationEntity>> getAllTermCourses();

    @Query("SELECT * FROM term_course_association_table")
    List<TermCourseAssociationEntity> getListTermCourses();


    @Query("DELETE FROM term_course_association_table")
    void deleteAll();


}
