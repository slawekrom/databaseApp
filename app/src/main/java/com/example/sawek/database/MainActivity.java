package com.example.sawek.database;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener{

    private QuestionViewModel mQuestionViewModel;
    private  QuestionListAdapter adapter;
    private Question question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuestionListAdapter(this);
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mQuestionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);

        mQuestionViewModel.getAllQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(@Nullable final List<Question> questions) {
                // Update the cached copy of the words in the adapter.
                adapter.setQuestions(questions);
            }
        });

        registerForContextMenu(recyclerView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddQuestion.class);
                startActivityForResult(intent, 1);
            }
        });

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("requestCode",String.valueOf(requestCode));
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Question question = new Question(data.getStringExtra(AddQuestion.EXTRA_REPLY));
            String ques = data.getStringExtra("question");
            String a1 = data.getStringExtra("answer1");
            String a2 = data.getStringExtra("answer2");
            String a3 = data.getStringExtra("answer3");
            String a4 = data.getStringExtra("answer4");
            //Question question = new Question(ques,a1,a2,a3,a4);
            mQuestionViewModel.insert(question);
        } else if(resultCode!=RESULT_OK){
            Toast.makeText(getApplicationContext(), "Enter all fields", Toast.LENGTH_LONG).show();
        }
        if(requestCode ==2 ){
            Log.i("enter update","enter update");
            String ques = data.getStringExtra("question");
            String a1 = data.getStringExtra("answer1");
            String a2 = data.getStringExtra("answer2");
            String a3 = data.getStringExtra("answer3");
            String a4 = data.getStringExtra("answer4");
            Log.i("answer1",a4);
            question.setQuestion(ques);
            question.setAnswer1(a1);
            question.setAnswer2(a2);
            question.setAnswer3(a3);
            question.setAnswer4(a4);
            mQuestionViewModel.update(question);
            Log.i("update logged","update logged");
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int position=adapter.getPosition();
        switch (item.getItemId()){
            case 1:
                Log.i("update","update clicked at"+String.valueOf(position));
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                question=mQuestionViewModel.getAllQuestions().getValue().get(position);
                intent.putExtra("question",question.getQuestion());
                intent.putExtra("a1",question.getAnswer1());
                intent.putExtra("a2",question.getAnswer2());
                intent.putExtra("a3",question.getAnswer3());
                intent.putExtra("a4",question.getAnswer4());

                startActivityForResult(intent, 2);
                break;
            case 2:
                Log.i("delete","delete clicked at"+String .valueOf(position));
                mQuestionViewModel.delete(position);
                break;
            case 3:
                Intent search = new Intent(this, SearchActivity.class);
                startActivity(search);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
