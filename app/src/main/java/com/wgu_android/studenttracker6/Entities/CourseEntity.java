package com.wgu_android.studenttracker6.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName="course_table", indices = {@Index(value = {"course_id"})})
public class CourseEntity {

    //*********************************************************
    //Variable Declarations
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="course_id")
    private int courseID;

    @ColumnInfo(name="course_name")
    private String courseName;

    @ColumnInfo(name="course_start")
    private Date courseStart;

    @ColumnInfo(name="course_end")
    private Date courseEnd;

    @ColumnInfo(name="course_status")
    private String courseStatus;

    @ColumnInfo(name="course_mentor")
    private String courseMentorName;

    @ColumnInfo(name="mentor_phone")
    private String courseMentorPhone;

    @ColumnInfo(name="mentor_email")
    private String courseMentorEmail;

    @ColumnInfo(name="course_notes")
    private String courseNotes;

    //*********************************************************
    //Constructor
    @Ignore
    public CourseEntity(String courseName, Date courseStart, Date courseEnd, String courseStatus,
                        String courseMentorName, String courseMentorPhone, String courseMentorEmail,
                        String courseNotes) {

        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.courseMentorName = courseMentorName;
        this.courseMentorPhone = courseMentorPhone;
        this.courseMentorEmail = courseMentorEmail;
        this.courseStatus = courseStatus;
        this.courseNotes = courseNotes;
    }


    @Ignore
    public CourseEntity(String courseName) {
        this.courseName= courseName;
    }

    public CourseEntity(String courseName, Date courseStart, Date courseEnd) {
        this.courseName = courseName;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
    }

    //*********************************************************
    //Accessors/Mutators
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(Date courseStart) {
        this.courseStart = courseStart;
    }

    public Date getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(Date courseEnd) {
        this.courseEnd = courseEnd;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseMentorName() {
        return courseMentorName;
    }

    public void setCourseMentorName(String courseMentorName) {
        this.courseMentorName = courseMentorName;
    }

    public String getCourseMentorPhone() {
        return courseMentorPhone;
    }

    public void setCourseMentorPhone(String courseMentorPhone) {
        this.courseMentorPhone = courseMentorPhone;
    }

    public String getCourseMentorEmail() {
        return courseMentorEmail;
    }

    public void setCourseMentorEmail(String courseMentorEmail) {
        this.courseMentorEmail = courseMentorEmail;
    }

    public String getCourseNotes() {
        return courseNotes;
    }

    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseID=" + courseID +
                ", courseName='" + courseName + '\'' +
                ", courseStart=" + courseStart +
                ", courseEnd=" + courseEnd +
                ", courseStatus='" + courseStatus + '\'' +
                ", courseMentorName='" + courseMentorName + '\'' +
                ", courseMentorPhone='" + courseMentorPhone + '\'' +
                ", courseMentorEmail='" + courseMentorEmail + '\'' +
                ", courseNotes='" + courseNotes + '\'' +
                '}';
    }
}


