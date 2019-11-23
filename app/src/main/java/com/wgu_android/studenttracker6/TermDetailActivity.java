package com.wgu_android.studenttracker6;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.wgu_android.studenttracker6.Adapters.CourseAdapter;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;
import com.wgu_android.studenttracker6.ViewModels.TermDetailViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wgu_android.studenttracker6.Utilities.Constants.TERM_KEY_ID;

public class TermDetailActivity extends AppCompatActivity {

    //*************************************************
    //Term Variables
    @BindView(R.id.editTextTermName)
    EditText mTextViewTermName;

    @BindView(R.id.editTextStartDate)
    EditText mEditTextStartDate;

    @BindView(R.id.editTextEndDate)
    EditText mEditTextEndDate;

    final Calendar myCalendarStart = Calendar.getInstance();
    final Calendar myCalendarEnd = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener startDate;
    DatePickerDialog.OnDateSetListener endDate;
    private EditText mTermName;
    private Boolean mNewTerm;
    private TermDetailViewModel mViewModel;

    //*************************************************
    //Course Variables
    @BindView(R.id.recyclerViewTermDetail)
    RecyclerView mRecyclerView;

    private List<CourseEntity> courseData = new ArrayList<>();
    private CourseAdapter mAdapter;
    public static final int NEW_COURSE_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_save);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);
        initViewModel();

        //Add a new Assessment
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
        mViewModel = ViewModelProviders.of(this).get(TermDetailViewModel.class);

        mViewModel.mLiveTerm.observe(this, new Observer<TermEntity>() {
            @Override
            public void onChanged(TermEntity termEntity) {
                mTextViewTermName.setText(termEntity.getTermName());
            }
        });

        //Check to see if a term was passed, and if so, display it
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle("New Term");
            mNewTerm = true;
        } else {
            setTitle("Edit Term");
            int termId = extras.getInt(TERM_KEY_ID);
            mViewModel.loadData(termId);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_termdetail, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Save Button
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete_term) {
            //mViewModel.deleteTerm();
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
        mViewModel.saveTerm(mTextViewTermName.getText().toString());  //for testing just the Term Name
        //mViewModel.saveTerm(mTextViewTermName.getText().toString(), myCalendarStart.getTime(), myCalendarEnd.getTime()); //To send Name and Dates
        finish();
    }

}
