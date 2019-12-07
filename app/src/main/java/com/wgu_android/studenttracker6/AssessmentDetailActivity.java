package com.wgu_android.studenttracker6;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.wgu_android.studenttracker6.Entities.AssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.ViewModels.AssessmentDetailViewModel;
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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wgu_android.studenttracker6.Utilities.Constants.ASSESMENT_KEY_ID;
import static com.wgu_android.studenttracker6.Utilities.Constants.TERM_KEY_ID;

public class AssessmentDetailActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener  {


    //**************************************************
    //Assessment Variables
    @BindView(R.id.spinnerAssessType)
    Spinner spinnerAssessment;

    @BindView(R.id.editTextAssessmentName)
    EditText mEditTextAssessmentName;

    @BindView(R.id.editTextGoalDate)
    EditText mEditTextGoalDate;

    @BindView(R.id.editTextDueDate)
    EditText mEditTextDueDate;

    @BindView(R.id.textViewCourseId_Assessments)
    TextView mTextViewCourseId;

    final Calendar myCalendarGoal = Calendar.getInstance();
    final Calendar myCalendarDue = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener goalDate;
    DatePickerDialog.OnDateSetListener dueDate;
    private String spinnerSelectedItem;
    private AssessmentDetailViewModel mViewModel;
    private Boolean mNewAssessment;

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
        mViewModel = ViewModelProviders.of(this).get(AssessmentDetailViewModel.class);

        mViewModel.mLiveAssessment.observe(this, new Observer<AssessmentEntity>() {
            @Override
            public void onChanged(AssessmentEntity assessmentEntity) {
                mEditTextAssessmentName.setText(assessmentEntity.getAssessmentName());
                setLabelGoal(assessmentEntity);
                setLabelDue(assessmentEntity);
            }
        });


        //Check to see if a term was passed, and if so, display it
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Assessment");
            mNewAssessment = true;
        } else {
            setTitle("Edit Assessment");
            int assessmentId = extras.getInt(ASSESMENT_KEY_ID);
            mViewModel.loadData(assessmentId);
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
        } else if (item.getItemId() == R.id.action_delete_assessment) {
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

        spinnerSelectedItem = spinnerAssessment.getSelectedItem().toString();

        //TODO replace 1 with lookup of course id
        mViewModel.saveAssessment(mEditTextAssessmentName.getText().toString(), myCalendarGoal.getTime(),
                myCalendarDue.getTime(), spinnerSelectedItem, 1);

        finish();
    }

    //**********************************************************************
    //Spinner Methods
    private void initSpinner() {
        ArrayAdapter<CharSequence> mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_types, android.R.layout.simple_spinner_item);
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAssessment.setAdapter(mSpinnerAdapter);
        spinnerAssessment.setOnItemSelectedListener(this);

        //TODO not correctly displaying the status in the spinner from the DB
        if (spinnerSelectedItem != null) {
            int spinnerPosition = mSpinnerAdapter.getPosition(spinnerSelectedItem);
            spinnerAssessment.setSelection(spinnerPosition);
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
