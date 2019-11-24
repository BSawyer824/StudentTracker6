package com.wgu_android.studenttracker6;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;
import com.wgu_android.studenttracker6.ViewModels.CourseDetailViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wgu_android.studenttracker6.Utilities.Constants.COURSE_KEY_ID;
import static com.wgu_android.studenttracker6.Utilities.Constants.TERM_KEY_ID;

public class CourseDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //**************************************************
    //Course Variables
    @BindView(R.id.spinner_status)
    Spinner spinner;

    @BindView(R.id.editTextCourseName)
    EditText mEditTextCourseName;

    @BindView(R.id.editTextCourseStart)
    EditText mEditTextStartDate;

    @BindView(R.id.editTextCourseEnd)
    EditText mEditTextEndDate;

    @BindView(R.id.editTextMentor)
    EditText mEditTextMentor;

    @BindView(R.id.editTextPhone)
    EditText mEditTextPhone;

    @BindView(R.id.editTextEmail)
    EditText mEditTextEmail;

    @BindView(R.id.editTextNotes)
    EditText mEditTextNotes;

    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    private CourseDetailViewModel mViewModel;
    private String spinnerSelectedItem;
    private boolean mNewCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_save);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ButterKnife.bind(this);
        initSpinner();
        initViewModel();
        
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //*****************************************************
        //Make Start and End Date fields have a calendar date picker

        //Start Date - Date Picker
        startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendarStart.set(Calendar.YEAR, year);
                myCalendarStart.set(Calendar.MONTH, monthOfYear);
                myCalendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

        mEditTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CourseDetailActivity.this, startDate, myCalendarStart
                        .get(Calendar.YEAR), myCalendarStart.get(Calendar.MONTH),
                        myCalendarStart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //End Date - Date Picker
        endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        };

        mEditTextEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CourseDetailActivity.this, endDate, myCalendarEnd
                        .get(Calendar.YEAR), myCalendarEnd.get(Calendar.MONTH),
                        myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void initViewModel() {

        mViewModel = ViewModelProviders.of(this).get(CourseDetailViewModel.class);

        //Observe the Course being created/edited
        mViewModel.mLiveCourse.observe(this, new Observer<CourseEntity>() {
            @Override
            public void onChanged(CourseEntity courseEntity) {
                mEditTextCourseName.setText(courseEntity.getCourseName());
                setLabelStart(courseEntity);
                setLabelEnd(courseEntity);
                mEditTextMentor.setText(courseEntity.getCourseMentorName());
                mEditTextPhone.setText(courseEntity.getCourseMentorPhone());
                mEditTextEmail.setText(courseEntity.getCourseMentorEmail());
                mEditTextNotes.setText(courseEntity.getCourseNotes());
                spinnerSelectedItem = courseEntity.getCourseStatus();
                //TODO how do I pull in the course status and assign it to the spinner
            }
        });


        //Check to see if a course was passed, and if so, display it
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Course");
            mNewCourse = true;
        } else {
            setTitle("Edit Course");
            int courseId = extras.getInt(COURSE_KEY_ID);
            mViewModel.loadData(courseId);
        }
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
            mViewModel.deleteCourse();
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

        spinnerSelectedItem = spinner.getSelectedItem().toString();

        mViewModel.saveCourse(mEditTextCourseName.getText().toString(), myCalendarStart.getTime(),
                myCalendarEnd.getTime(), mEditTextMentor.getText().toString(), mEditTextPhone.getText().toString(),
                mEditTextEmail.getText().toString(), mEditTextNotes.getText().toString(), spinnerSelectedItem);

        finish();
    }



    //***************************************************************************************
    //spinner methods
    private void initSpinner() {

        ArrayAdapter<CharSequence> mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_status, android.R.layout.simple_spinner_item);
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mSpinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        //TODO not correctly displaying the status in the spinner from the DB
        if (spinnerSelectedItem != null) {
            int spinnerPosition = mSpinnerAdapter.getPosition(spinnerSelectedItem);
            spinner.setSelection(spinnerPosition);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String mSelectedItem = parent.getItemAtPosition(position).toString();
        spinnerSelectedItem = mSelectedItem;

        //Toast.makeText(parent.getContext(), spinnerSelectedItem, Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    
    //**************************************************************************************
    //Date Picker Methods
    private void updateLabelStart() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEditTextStartDate.setText(sdf.format(myCalendarStart.getTime()));
    }

    private void setLabelStart(CourseEntity coursesEntity) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEditTextStartDate.setText(sdf.format(coursesEntity.getCourseStart()));
    }

    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEditTextEndDate.setText(sdf.format(myCalendarEnd.getTime()));
    }

    private void setLabelEnd(CourseEntity coursesEntity) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mEditTextEndDate.setText(sdf.format(coursesEntity.getCourseEnd()));
    }
}
