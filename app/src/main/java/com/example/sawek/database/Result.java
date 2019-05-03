package com.example.sawek.database;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {

    private  QuestionListAdapter adapter;
    private List<Question> mQuestion;
    private String query;
    private QuestionRepository mRepository;

    public Result(){
        super();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        adapter = new QuestionListAdapter(this);
        query=getIntent().getExtras().getString("query");
        mQuestion=new ArrayList<>();

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        mRepository=new QuestionRepository(getApplication());
        mRepository.search(query);

        do{
            mQuestion=mRepository.getmQuestions();

        }while (mQuestion==null);
        Log.i("result list size", String.valueOf(mQuestion.size()));

        adapter.setQuestions(mQuestion);
        recyclerView.setAdapter(adapter);
    }
}
