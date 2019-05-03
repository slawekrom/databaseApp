package com.example.sawek.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.List;


public class QuestionRepository {

    private QuestionDAO mQuestionDao;
    private LiveData<List<Question>> mAllQuestions;
    private List<Question> mQuestions;

    QuestionRepository(Application application) {
        QuestionRoomDatabase db = QuestionRoomDatabase.getDatabase(application);
        mQuestionDao = db.questionDAO();
        mAllQuestions = mQuestionDao.getAllQuestions();
    }

    LiveData<List<Question>> getAllQuestions() {
        return mAllQuestions;
    }

    public void insert (Question question) {
        new insertAsyncTask(mQuestionDao).execute(question);
    }


    public void search(final String q){
        new searchAsyncTask(mQuestionDao).execute(q);

    }

    public List<Question> getmQuestions() {
        return mQuestions;
    }

    public void delete(int pos) {
        Question question=mAllQuestions.getValue().get(pos);
        new deleteAsyncTask(mQuestionDao).execute(question);
    }
    public void update(Question question){
        new updateAsyncTask(mQuestionDao).execute(question);
    }

    private static class insertAsyncTask extends AsyncTask<Question, Void, Void> {

        private QuestionDAO mAsyncTaskDao;

        insertAsyncTask(QuestionDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Question... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDAO mAsyncTaskDao;
        deleteAsyncTask(QuestionDAO dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Question... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
    private static class updateAsyncTask extends AsyncTask<Question, Void, Void> {
        private QuestionDAO mAsyncTaskDao;
        updateAsyncTask(QuestionDAO dao){
            mAsyncTaskDao=dao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            mAsyncTaskDao.update(questions[0]);
            return null;
        }
    }

    private class searchAsyncTask extends AsyncTask<String, Void, Void>{
        private QuestionDAO mAsyncDao;
        searchAsyncTask(QuestionDAO dao){
            mAsyncDao=dao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            mQuestions=mAsyncDao.searchQuestion(strings[0]);

          return null;
        }
    }
}
