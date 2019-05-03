package com.example.sawek.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuestionViewModel extends AndroidViewModel {
    private QuestionRepository mRepository;
    private LiveData<List<Question>> mAllQuestions;

    public QuestionViewModel (Application application) {
        super(application);
        mRepository = new QuestionRepository(application);
        mAllQuestions = mRepository.getAllQuestions();
    }

    LiveData<List<Question>> getAllQuestions() { return mAllQuestions; }

    public void insert(Question question) { mRepository.insert(question); }

    public void search(String query){
        mRepository.search(query);
    }

    public void update(Question question){
        mRepository.update(question);
    }

    public void delete(int pos){
        mRepository.delete(pos);
    }
}
