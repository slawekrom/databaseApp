package com.example.sawek.database;

import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    private EditText id;
    private EditText question;
    private EditText answer1;
    private EditText answer2;
    private EditText answer3;
    private EditText answer4;
    private String quest;
    private String answ1;
    private String answ2;
    private String answ3;
    private String answ4;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_view);
        question=findViewById(R.id.EditText_q);
        answer1=findViewById(R.id.EditText_a1);
        answer2=findViewById(R.id.EditText_a2);
        answer3=findViewById(R.id.EditText_a3);
        answer4=findViewById(R.id.EditText_a4);
        question.setText(getIntent().getExtras().getString("question"));
        answer1.setText(getIntent().getExtras().getString("a1"));
        answer2.setText(getIntent().getExtras().getString("a2"));
        answer3.setText(getIntent().getExtras().getString("a3"));
        answer4.setText(getIntent().getExtras().getString("a4"));

        Button update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                quest=question.getText().toString();
                answ1=answer1.getText().toString();
                answ2=answer2.getText().toString();
                answ3=answer3.getText().toString();
                answ4=answer4.getText().toString();
                        intent.putExtra("question",quest);
                        intent.putExtra("answer1",answ1);
                        intent.putExtra("answer2",answ2);
                        intent.putExtra("answer3",answ3);
                        intent.putExtra("answer4",answ4);
                        setResult(RESULT_OK, intent);
                        finish();
            }
        });
    }
}
