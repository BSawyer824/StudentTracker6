package com.wgu_android.studenttracker6.Database;

import com.wgu_android.studenttracker6.Entities.AssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseAssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermCourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {


    private static final String SAMPLE_TEXT_1 = "Term 1";
    private static final String SAMPLE_TEXT_2 = "Term 2";
    private static final String SAMPLE_TEXT_3 = "Possible Term 3";
    private static final String SAMPLE_TEXT_4 = "Course A";
    public static final String SAMPLE_TEXT_5 = "Course B";
    public static final String SAMPLE_TEXT_6 = "Assessment Objective";
    public static final String SAMPLE_TEXT_7 = "Assessment Performance";

    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MINUTE, diff);
        return cal.getTime();
    }

    public static List<TermEntity> getTerm() {
        List<TermEntity> term = new ArrayList<>();
        term.add(new TermEntity(SAMPLE_TEXT_1, getDate(500), getDate(2500)));
        term.add(new TermEntity(SAMPLE_TEXT_2, getDate(5000), getDate(25000)));
        term.add(new TermEntity(SAMPLE_TEXT_3, getDate(15000), getDate(250000)));
        return term;
    }

    public static List<CourseEntity> getCourse() {
        List<CourseEntity> course = new ArrayList<>();
        course.add(new CourseEntity(SAMPLE_TEXT_4, getDate(500), getDate(2500)));
        course.add(new CourseEntity(SAMPLE_TEXT_5, getDate(5000), getDate(25000)));
        return course;
    }

    public static List<TermCourseEntity> getTermCourseAssociation() {
        List<TermCourseEntity> termCourse = new ArrayList<>();
        termCourse.add(new TermCourseEntity(1, 1));
        termCourse.add(new TermCourseEntity(1, 2));
    return termCourse;
    }

    public static List<AssessmentEntity> getAssessments() {
        List<AssessmentEntity> assessment = new ArrayList<>();
        assessment.add(new AssessmentEntity(SAMPLE_TEXT_6, getDate(750)));
        assessment.add(new AssessmentEntity(SAMPLE_TEXT_7, getDate(2500)));
        return assessment;
    }

    public static List<CourseAssessmentEntity> getCourseAssessmentAssociation() {
        List<CourseAssessmentEntity> courseAssessment = new ArrayList<>();
        courseAssessment.add(new CourseAssessmentEntity(1, 1));
        courseAssessment.add(new CourseAssessmentEntity(2, 1));
        return courseAssessment;
    }


    
}
