package com.wgu_android.studenttracker6.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName="term_course_association_table", indices = {@Index("term_id"), @Index("course_id")})
public class TermCourseAssociationEntity {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="term_course_id")
    private int termCourseId;

    @ColumnInfo(name="term_id")
    private int termId;

    @ColumnInfo(name="course_id")
    private int courseId;

    public TermCourseAssociationEntity(int termId, int courseId) {
        this.termId = termId;
        this.courseId = courseId;
    }

    @Ignore
    public TermCourseAssociationEntity() {
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
