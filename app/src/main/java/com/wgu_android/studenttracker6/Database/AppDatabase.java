package com.wgu_android.studenttracker6.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.wgu_android.studenttracker6.DAO.AssessmentDAO;
import com.wgu_android.studenttracker6.DAO.CourseAssessmentDAO;
import com.wgu_android.studenttracker6.DAO.CourseDAO;
import com.wgu_android.studenttracker6.DAO.TermCourseDAO;
import com.wgu_android.studenttracker6.DAO.TermDAO;
import com.wgu_android.studenttracker6.Entities.AssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseAssessmentEntity;
import com.wgu_android.studenttracker6.Entities.CourseEntity;
import com.wgu_android.studenttracker6.Entities.TermCourseEntity;
import com.wgu_android.studenttracker6.Entities.TermEntity;
import com.wgu_android.studenttracker6.Utilities.ConvertTypes;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {TermEntity.class, CourseEntity.class, AssessmentEntity.class, TermCourseEntity.class, CourseAssessmentEntity.class}, version = 1, exportSchema = false)
@TypeConverters({ConvertTypes.class})
public abstract class AppDatabase extends RoomDatabase {

    //***************************************************************
    //Variable Declaration
    private static final String DATABASE_NAME = "StudentTrackerDB.db";
    private static volatile AppDatabase INSTANCE;
    private static final Object LOCK = new Object();

    public abstract TermDAO termDao();
    public abstract CourseDAO courseDao();
    public abstract AssessmentDAO assessmentDao();
    public abstract TermCourseDAO termCourseDao();
    public abstract CourseAssessmentDAO courseAssessmentDao();


    //***************************************************************
    //Constructor
    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    //***************************************************************
    //This callback method will run when the DB is opened
    //it runs the method to populate the db asynchronously
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    //***************************************************************
    //This method populates the database asynchronously
    //also currently deletes all data at start up and populates a default value
    //will need to change once more screens are built
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TermDAO mTermDao;
        private final CourseDAO mCourseDao;
        private final AssessmentDAO mAssessmentDao;
        private final TermCourseDAO mTermCourseDao;
        private final CourseAssessmentDAO mCourseAssessmentDao;


        PopulateDbAsync(AppDatabase db) {
            mTermDao = db.termDao();
            mCourseDao = db.courseDao();
            mAssessmentDao = db.assessmentDao();
            mTermCourseDao = db.termCourseDao();
            mCourseAssessmentDao = db.courseAssessmentDao();

        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
//            mTermDao.deleteAll();
//
//            //Add Sample Data
//            List<TermsEntity> termsData = new ArrayList<>(SampleDataEntities.getTerms());
//            mTermDao.insertAll(SampleDataEntities.getTerms());

            return null;
        }
    }


}
