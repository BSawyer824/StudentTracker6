package com.wgu_android.studenttracker6.Entities;


//This Entity will create the table that holds the term_id and course_id association

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

//@Entity(tableName="term_course_table", primaryKeys = {"term_id", "course_id"}, foreignKeys = {
//        @ForeignKey(entity = TermEntity.class, parentColumns = "term_id", childColumns = "term_id", onDelete = CASCADE),
//        @ForeignKey(entity = CourseEntity.class, parentColumns = "course_id", childColumns = "course_id", onDelete = CASCADE)
//        }, indices = {@Index("term_id"), @Index("course_id")})

@Entity(tableName="term_course_table",  foreignKeys = {
        @ForeignKey(entity = TermEntity.class, parentColumns = "term_id", childColumns = "term_id", onDelete = CASCADE),
        @ForeignKey(entity = CourseEntity.class, parentColumns = "course_id", childColumns = "course_id", onDelete = CASCADE)
}, indices = {@Index("term_id"), @Index("course_id")})

public class TermCourseEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="term_course_id")
    private int termCourseId;

    @ColumnInfo(name="term_id")
    private int termId;

    @ColumnInfo(name="course_id")
    private int courseId;

    public TermCourseEntity(int termId, int courseId) {
        this.termId = termId;
        this.courseId = courseId;
    }

    @Ignore
    public TermCourseEntity() {
    }

    public int getTermCourseId() {
        return termCourseId;
    }

    public void setTermCourseId(int termCourseId) {
        this.termCourseId = termCourseId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "TermCourseEntity{" +
                "termCourseId=" + termCourseId +
                ", termId=" + termId +
                ", courseId=" + courseId +
                '}';
    }
}
