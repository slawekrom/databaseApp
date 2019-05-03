package com.example.sawek.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddQuestion extends AppCompatActivity {

    private EditText question;
    private EditText answer1;
    private EditText answer2;
    private EditText answer3;
    private EditText answer4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_question);
       question = findViewById(R.id.add_q);
       answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(question.getText()) ||TextUtils.isEmpty(answer1.getText())
                        || TextUtils.isEmpty(answer2.getText()) || TextUtils.isEmpty(answer3.getText())
                        || TextUtils.isEmpty(answer4.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String ques = question.getText().toString();
                    replyIntent.putExtra("question", ques);
                    String a1 = answer1.getText().toString();
                    replyIntent.putExtra("answer1", a1);
                    String a2 = answer2.getText().toString();
                    replyIntent.putExtra("answer2", a2);
                    String a3 = answer3.getText().toString();
                    replyIntent.putExtra("answer3", a3);
                    String a4 = answer4.getText().toString();
                    replyIntent.putExtra("answer4", a4);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}