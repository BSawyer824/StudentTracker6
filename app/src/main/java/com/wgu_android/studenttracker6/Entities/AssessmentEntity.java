package com.wgu_android.studenttracker6.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName="assessment_table")
public class AssessmentEntity {


    //*********************************************************
    //Variable Declarations

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="assessment_id")
    private int assessmentID;

    @ColumnInfo(name="assessment_name")
    private String assessmentName;

    @ColumnInfo(name="assessment_type")
    private String assessmentType;  //Objective or Performance

    @ColumnInfo(name="assessment_goal_date")
    private Date assessmentGoalDate;

    @ColumnInfo(name="assessment_due_date")
    private Date assessmentDueDate;

    //*********************************************************
    //Constructor
    @Ignore
    public AssessmentEntity(int assessmentID, String assessmentName, String assessmentType, Date assessmentGoalDate, Date assessmentDueDate) {
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentGoalDate = assessmentGoalDate;
        this.assessmentDueDate = assessmentDueDate;
    }

    public AssessmentEntity(String assessmentName, Date assessmentDueDate, Date assessmentGoalDate) {
        this.assessmentName = assessmentName;
        this.assessmentDueDate = assessmentDueDate;
        this.assessmentGoalDate = assessmentGoalDate;
    }

    @Ignore
    public AssessmentEntity(String assessmentName, String assessmentType, Date assessmentGoalDate, Date assessmentDueDate) {
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.assessmentGoalDate = assessmentGoalDate;
        this.assessmentDueDate = assessmentDueDate;
    }

    //*********************************************************
    //Accessors/Mutators
    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public Date getAssessmentGoalDate() {
        return assessmentGoalDate;
    }

    public void setAssessmentGoalDate(Date assessmentGoalDate) {
        this.assessmentGoalDate = assessmentGoalDate;
    }

    public Date getAssessmentDueDate() {
        return assessmentDueDate;
    }

    public void setAssessmentDueDate(Date assessmentDueDate) {
        this.assessmentDueDate = assessmentDueDate;
    }

    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "assessmentID=" + assessmentID +
                ", assessmentName='" + assessmentName + '\'' +
                ", assessmentType='" + assessmentType + '\'' +
                ", assessmentGoalDate=" + assessmentGoalDate +
                ", assessmentDueDate=" + assessmentDueDate +
                '}';
    }
}

