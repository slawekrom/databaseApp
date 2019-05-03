package com.example.sawek.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    private String the_query;
    EditText query;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        query=findViewById(R.id.query);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result();
            }
        });
    }
    public void result(){
        Intent intent = new Intent(this, Result.class);
        the_query=query.getText().toString();
        intent.putExtra("query",the_query);
        startActivity(intent);
    }
}
