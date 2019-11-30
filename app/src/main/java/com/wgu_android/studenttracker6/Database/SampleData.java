package com.wgu_android.studenttracker6.Database;

import com.wgu_android.studenttracker6.Entities.AssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseAssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermCourseAssociationEntity;
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
    public static final String SAMPLE_TEXT_8 = "Course C";
    public static final String SAMPLE_TEXT_6 = "Assessment Objective";
    public static final String SAMPLE_TEXT_7 = "Assessment Performance";

    public static List<TermEntity> term = new ArrayList<>();
    public static List<CourseEntity> course = new ArrayList<>();
    public static List<AssessmentEntity> assessment = new ArrayList<>();
    public static List<TermCourseAssociationEntity> termCourse = new ArrayList<>();


    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MINUTE, diff);
        return cal.getTime();
    }

    public static List<TermEntity> getTerm() {

        term.add(new TermEntity(SAMPLE_TEXT_1, getDate(500), getDate(2500)));
        term.add(new TermEntity(SAMPLE_TEXT_2, getDate(5000), getDate(25000)));
        term.add(new TermEntity(SAMPLE_TEXT_3, getDate(15000), getDate(250000)));
        return term;
    }

    public static List<CourseEntity> getCourse() {
        course.add(new CourseEntity(SAMPLE_TEXT_4, getDate(500), getDate(2500)));
        course.add(new CourseEntity(SAMPLE_TEXT_5, getDate(5000), getDate(25000)));
        course.add(new CourseEntity(SAMPLE_TEXT_8, getDate(5000), getDate(25000)));
        return course;
    }

    public static List<TermCourseAssociationEntity> getTermCourseAssociation() {
        termCourse.add(new TermCourseAssociationEntity(1, 1));
        termCourse.add(new TermCourseAssociationEntity(1, 3));
        termCourse.add(new TermCourseAssociationEntity(2, 2));
        termCourse.add(new TermCourseAssociationEntity(3, 1));
        return termCourse;
    }

    public static List<AssessmentEntity> getAssessment() {

        assessment.add(new AssessmentEntity(SAMPLE_TEXT_6, getDate(750), getDate(1500)));
        assessment.add(new AssessmentEntity(SAMPLE_TEXT_7, getDate(2500), getDate(5000)));
        return assessment;
    }



    
}
