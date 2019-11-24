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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wgu_android.studenttracker6.Utilities.Constants.NEW_TERM_ACTIVITY_REQUEST_CODE;

public class TermSummaryActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView_Terms)
    RecyclerView mRecyclerViewTerms;


    @BindView(R.id.recyclerView_Assessments)
    RecyclerView mRecyclerViewAssessments;


    private List<TermEntity> termData = new ArrayList<>();
    private TermAdapter mTermAdapter;
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
        initRecyclerView();
        initViewModel();


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
        assessmentData.addAll(mViewModel.mAssessments);
    }

    private void initViewModel() {

        final Observer<List<TermEntity>> termObserver = new Observer<List<TermEntity>>() {
            @Override
            public void onChanged(List<TermEntity> termEntities) {
                termData.clear();
                termData.addAll(termEntities);

                if (mTermAdapter == null) {
                    mTermAdapter = new TermAdapter(termData, TermSummaryActivity.this);
                    mRecyclerViewTerms.setAdapter(mTermAdapter);
                } else {
                    //refreshes from adapter when data changes
                    mTermAdapter.notifyDataSetChanged();
                }
            }
        };

        mViewModel = ViewModelProviders.of(this)
                .get(TermSummaryViewModel.class);
        mViewModel.mTerms.observe(this, termObserver); //subscribed to the data
    }

    private void initRecyclerView() {


        mRecyclerViewTerms.setHasFixedSize(true);
        LinearLayoutManager layoutManagerTerms = new LinearLayoutManager(this);
        mRecyclerViewTerms.setLayoutManager(layoutManagerTerms);

        DividerItemDecoration divider = new DividerItemDecoration(mRecyclerViewTerms.getContext(), layoutManagerTerms.getOrientation());
        mRecyclerViewTerms.addItemDecoration(divider);

        mRecyclerViewAssessments.setHasFixedSize(true);
        LinearLayoutManager layoutManagerAssessments = new LinearLayoutManager(this);
        mRecyclerViewAssessments.setLayoutManager(layoutManagerAssessments);


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
        } else if (id == R.id.action_delete_all_data) {
            deleteAllTerm();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllTerm() {
        mViewModel.deleteAllTerms();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }
}
