package com.wgu_android.studenttracker6;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.wgu_android.studenttracker6.Entities.AssessmentEntity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssessmentDetailActivity extends AppCompatActivity {


    //**************************************************
    //Assessment Variables
    @BindView(R.id.spinnerAssessType)
    Spinner spinner;

    @BindView(R.id.editTextAssessmentName)
    EditText mEditTextAssessmentName;

    @BindView(R.id.editTextGoalDate)
    EditText mEditTextGoalDate;

    @BindView(R.id.editTextDueDate)
    EditText mEditTextDueDate;

    final Calendar myCalendarGoal = Calendar.getInstance();
    final Calendar myCalendarDue = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener goalDate;
    DatePickerDialog.OnDateSetListener dueDate;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_save);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ButterKnife.bind(this);
        initSpinner();
        initRecyclerView();
        initViewModel();



        //*****************************************************
        //Make Goal and Due Date fields have a calendar date picker

        //Goal Date - Date Picker
        goalDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendarGoal.set(Calendar.YEAR, year);
                myCalendarGoal.set(Calendar.MONTH, monthOfYear);
                myCalendarGoal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelGoal();
            }
        };

        mEditTextGoalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AssessmentDetailActivity.this, goalDate, myCalendarGoal
                        .get(Calendar.YEAR), myCalendarGoal.get(Calendar.MONTH),
                        myCalendarGoal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Due Date - Date Picker
        dueDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendarDue.set(Calendar.YEAR, year);
                myCalendarDue.set(Calendar.MONTH, monthOfYear);
                myCalendarDue.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelDue();
            }
        };

        mEditTextDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AssessmentDetailActivity.this, dueDate, myCalendarDue
                        .get(Calendar.YEAR), myCalendarDue.get(Calendar.MONTH),
                        myCalendarDue.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        
    }

    //*****************************************************************************************
    //recycler view methods    
    
    private void initViewModel() {
    }

    private void initRecyclerView() {
    }




    //*****************************************************************************************
    //menu methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_coursedetail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Save Button
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete_term) {
            //mViewModel.deleteCourse();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        //Back Button
        saveAndReturn();
    }

    private void saveAndReturn() {

//        spinnerSelectedItem = spinner.getSelectedItem().toString();
//
//        mViewModel.saveCourse(mEditTextCourseName.getText().toString(), myCalendarGoal.getTime(),
//                myCalendarDue.getTime(), mEditTextMentor.getText().toString(), mEditTextPhone.getText().toString(),
//                mEditTextEmail.getText().toString(), mEditTextNotes.getText().toString(), spinnerSelectedItem);

        finish();
    }

    //**********************************************************************
    //Spinner Methods
    private void initSpinner() {
    }


    //**************************************************************************************
    //Date Picker Methods
    private void updateLabelGoal() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEditTextGoalDate.setText(sdf.format(myCalendarGoal.getTime()));
    }

    private void setLabelGoal(AssessmentEntity assessmentEntity) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEditTextGoalDate.setText(sdf.format(assessmentEntity.getAssessmentGoalDate()));
    }

    private void updateLabelDue() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEditTextDueDate.setText(sdf.format(myCalendarDue.getTime()));
    }

    private void setLabelDue(AssessmentEntity assessmentEntity) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEditTextDueDate.setText(sdf.format(assessmentEntity.getAssessmentDueDate()));
    }
    
}
