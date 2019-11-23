package com.wgu_android.studenttracker6.Entities;


//This Entity will create the table that holds the term_id and course_id association

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

//@Entity(tableName="term_course_table", primaryKeys = {"term_id", "course_id"}, foreignKeys = {
//        @ForeignKey(entity = TermEntity.class, parentColumns = "term_id", childColumns = "term_id", onDelete = CASCADE),
//        @ForeignKey(entity = CourseEntity.class, parentColumns = "course_id", childColumns = "course_id", onDelete = CASCADE)
//        })
@Entity(tableName="term_course_table")
public class TermCourseEntity {

    @PrimaryKey

    @ColumnInfo(name="term_course_id")
    private int term_course_id;

    @ColumnInfo(name="term_id")
    private int term_id;

    @ColumnInfo(name="course_id")
    private int course_id;

    public TermCourseEntity(int term_id, int course_id) {
        this.term_id = term_id;
        this.course_id = course_id;
    }

    @Ignore
    public TermCourseEntity() {
    }

    public int getTerm_course_id() {
        return term_course_id;
    }

    public void setTerm_course_id(int term_course_id) {
        this.term_course_id = term_course_id;
    }

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "TermCourseEntity{" +
                "term_course_id=" + term_course_id +
                ", term_id=" + term_id +
                ", course_id=" + course_id +
                '}';
    }
}
