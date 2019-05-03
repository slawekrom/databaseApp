package com.example.sawek.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Question.class},version = 1,exportSchema = false)
public abstract class QuestionRoomDatabase extends RoomDatabase {
    public abstract QuestionDAO questionDAO();

    private static volatile QuestionRoomDatabase INSTANCE;

    static QuestionRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QuestionRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuestionRoomDatabase.class, "question_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final QuestionDAO mDao;

        PopulateDbAsync(QuestionRoomDatabase db) {
            mDao = db.questionDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Question question = new Question("Wynik dzielenia to:","Suma","Roznica","iloczyn","Iloraz","matematyka","iloraz");
            mDao.insert(question);
            question = new Question("2+3*2=","10","8","12","18","fizyka","8");
            mDao.insert(question);
            return null;
        }
    }
}
