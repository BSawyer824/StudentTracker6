package com.wgu_android.studenttracker6;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.ViewModels.CourseDetailViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseDetailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //**************************************************
    //Course Variables
    @BindView(R.id.spinner_status)
    Spinner spinner;

    @BindView(R.id.editTextCourseName)
    EditText mEditTextCourseName;

    private CourseDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
    }

    private void initViewModel() {

        mViewModel = ViewModelProviders.of(this).get(CourseDetailViewModel.class);

        //Observe the Course being created/edited
        mViewModel.mLiveCourse.observe(this, new Observer<CourseEntity>() {
            @Override
            public void onChanged(CourseEntity courseEntity) {
                mEditTextCourseName.setText(courseEntity.getCourseName());
            }
        });
    }

    //***************************************************************************************
    //spinner methods
    private void initSpinner() {

        ArrayAdapter<CharSequence> mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_status, android.R.layout.simple_spinner_item);
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(mSpinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String mSelectedItem = parent.getItemAtPosition(position).toString();

        //TODO change from toast to load to DB
        Toast.makeText(parent.getContext(), mSelectedItem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
