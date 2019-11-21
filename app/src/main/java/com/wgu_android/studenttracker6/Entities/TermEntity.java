package com.wgu_android.studenttracker6.Entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

/**********************************************************
 Term Entity is the object class that will be used to hold all Term objects
 ********************************************************/

@Entity(tableName="term_table")
public class TermEntity {

    //*****************************************************************
    //Variable Declarations

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="term_id")
    private int termID;

    @NonNull
    @ColumnInfo(name="term_name")
    private String termName;

    @ColumnInfo(name="term_start")
    private Date termStart;

    @ColumnInfo(name="term_end")
    private Date termEnd;



    //*****************************************************************
    //Constructors
    @Ignore //this constructor is ignored when the database automatically generates a term
    public TermEntity(int termID, String termName, Date termStart, Date termEnd) {
        this.termID = termID;
        this.termName = termName;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }

    //@Ignore //this constructor is ignored when the database automatically generates a term
    public TermEntity(String termName, Date termStart, Date termEnd) {
        this.termName = termName;
        this.termStart = termStart;
        this.termEnd = termEnd;
    }

    @Ignore //this constructor is ignored when the database automatically generates a term
    public TermEntity(int termID, String termName) {
        this.termID = termID;
        this.termName = termName;
    }

    @Ignore
    public TermEntity(String termName) {
        this.termName = termName;
    }


    //*****************************************************************
    //Other Methods


    @Override
    public String toString() {
        return "TermEntity{" +
                "termID=" + termID +
                ", termName='" + termName + '\'' +
                ", termStart=" + termStart +
                ", termEnd=" + termEnd +
                '}';
    }


    public int getTermID() {
        return termID;
    }
    public String getTermName() {
        return termName;
    }
    public Date getTermStart() {
        return termStart;
    }
    public Date getTermEnd() {
        return termEnd;
    }



    public void setTermID(int termID) {
        this.termID = termID;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public void setTermStart(Date termStart) {
        this.termStart = termStart;
    }

    public void setTermEnd(Date termEnd) {
        this.termEnd = termEnd;
    }


}


