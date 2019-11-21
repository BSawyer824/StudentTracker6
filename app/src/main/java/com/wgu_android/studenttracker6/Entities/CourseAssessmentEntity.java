package com.wgu_android.studenttracker6.Entities;

//This Entity will create the table that holds the course_id and assessment_id association

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName="course_assessment_table")
public class CourseAssessmentEntity {

    @PrimaryKey
    @ColumnInfo(name="course_assessment_id")
    private int term_course_id;

    @ColumnInfo(name="course_id")
    private int course_id;

    @ColumnInfo(name="assessment_id")
    private int assessment_id;

    public CourseAssessmentEntity(int course_id, int assessment_id) {
        this.course_id = course_id;
        this.assessment_id = assessment_id;
    }

    @Ignore
    public CourseAssessmentEntity() {
    }

    public int getTerm_course_id() {
        return term_course_id;
    }

    public void setTerm_course_id(int term_course_id) {
        this.term_course_id = term_course_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getAssessment_id() {
        return assessment_id;
    }

    public void setAssessment_id(int assessment_id) {
        this.assessment_id = assessment_id;
    }

    @Override
    public String toString() {
        return "CourseAssessmentEntity{" +
                "term_course_id=" + term_course_id +
                ", course_id=" + course_id +
                ", assessment_id=" + assessment_id +
                '}';
    }
}
