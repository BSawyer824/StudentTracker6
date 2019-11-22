package com.wgu_android.studenttracker6;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.wgu_android.studenttracker6.Adapters.AssessmentsAdapter;
import com.wgu_android.studenttracker6.Adapters.CourseAdapter;
import com.wgu_android.studenttracker6.Adapters.TermAdapter;
import com.wgu_android.studenttracker6.Database.SampleData;
import com.wgu_android.studenttracker6.Entities.AssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;
import com.wgu_android.studenttracker6.ViewModels.TermSummaryViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermSummaryActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView_Terms)
    RecyclerView mRecyclerViewTerms;

    @BindView(R.id.recyclerView_Courses)
    RecyclerView mRecyclerViewCourses;

    @BindView(R.id.recyclerView_Assessments)
    RecyclerView mRecyclerViewAssessments;

    public static final int NEW_TERM_ACTIVITY_REQUEST_CODE = 1;
    private List<TermEntity> termData = new ArrayList<>();
    private TermAdapter mTermAdapter;
    private List<CourseEntity> courseData = new ArrayList<>();
    private CourseAdapter mCourseAdapter;
    private List<AssessmentEntity> assessmentData = new ArrayList<>();
    private AssessmentsAdapter mAssessmentAdapter;
    private TermSummaryViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_summary);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initViewModel();
        initRecyclerView();

        //Open Term Detail Screen
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermSummaryActivity.this, TermDetailActivity.class);
                startActivityForResult(intent, NEW_TERM_ACTIVITY_REQUEST_CODE);
            }
        });

        //Add Sample Data
        termData.addAll(mViewModel.mTerms);
        courseData.addAll(mViewModel.mCourses);
        assessmentData.addAll(mViewModel.mAssessments);
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(TermSummaryViewModel.class);
    }

    private void initRecyclerView() {


        mRecyclerViewTerms.setHasFixedSize(true);
        LinearLayoutManager layoutManagerTerms = new LinearLayoutManager(this);
        mRecyclerViewTerms.setLayoutManager(layoutManagerTerms);


        mRecyclerViewCourses.setHasFixedSize(true);
        LinearLayoutManager layoutManagerCourses = new LinearLayoutManager(this);
        mRecyclerViewCourses.setLayoutManager(layoutManagerCourses);

        mRecyclerViewAssessments.setHasFixedSize(true);
        LinearLayoutManager layoutManagerAssessments = new LinearLayoutManager(this);
        mRecyclerViewAssessments.setLayoutManager(layoutManagerAssessments);

        mTermAdapter = new TermAdapter(termData, this);
        mRecyclerViewTerms.setAdapter(mTermAdapter);

        mCourseAdapter = new CourseAdapter(courseData, this);
        mRecyclerViewCourses.setAdapter(mCourseAdapter);

        mAssessmentAdapter = new AssessmentsAdapter(assessmentData, this);
        mRecyclerViewAssessments.setAdapter(mAssessmentAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {
            addSampleData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}
