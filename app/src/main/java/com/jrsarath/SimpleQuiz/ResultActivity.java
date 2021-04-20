package com.jrsarath.SimpleQuiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);
    int score = 0;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        if (getIntent().getStringExtra("question1_answer").equals(getString(R.string.question1_correct_answer))) {
            score++;
        }
        if (getIntent().getStringExtra("question3_answer").equals(getString(R.string.question3_correct_answer))) {
            score++;
        }
        if (getIntent().getStringExtra("question4_answer").equals(getString(R.string.question4_correct_answer))) {
            score++;
        }
        if (getIntent().getStringExtra("question5_answer").equals(getString(R.string.question5_correct_answer))) {
            score++;
        }

        Intent intent = getIntent();
        HashMap<String, Boolean> hashMap = (HashMap<String, Boolean>)intent.getSerializableExtra("question2_answer");
        Boolean isValid = false;
        String list = null;

        for (Map.Entry me : hashMap.entrySet()) {
            if (list == null) list = (String) me.getKey(); else list = list + ", "+ (String) me.getKey();
            if (!me.getKey().equals(getString(R.string.question2_answer4))) {
                Log.d("app", (String) me.getKey());
                if (me.getValue().toString().equals("true")) {
                    isValid = true;
                } else {
                    isValid = false;
                    break;
                }
            } else {
                if (me.getValue().toString().equals("false")) {
                    isValid = true;
                } else {
                    isValid = false;
                    break;
                }
            }
        }
        if (isValid) {
            score++;
        }

        TextView scoreView = (TextView) findViewById(R.id.score);
        scoreView.setText(String.valueOf(score)+"/5");

        TextView answer1 = (TextView) findViewById(R.id.answer1);
        answer1.setText("Your Answer: "+getIntent().getStringExtra("question1_answer"));
        TextView answer2 = (TextView) findViewById(R.id.answer2);
        answer2.setText("Your Answer: "+list);
        TextView answer3 = (TextView) findViewById(R.id.answer3);
        answer3.setText("Your Answer: "+getIntent().getStringExtra("question3_answer"));
        TextView answer4 = (TextView) findViewById(R.id.answer4);
        answer4.setText("Your Answer: "+getIntent().getStringExtra("question4_answer"));
        TextView answer5 = (TextView) findViewById(R.id.answer5);
        answer5.setText("Your Answer: "+getIntent().getStringExtra("question5_answer"));

        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("quzinator", Context.MODE_PRIVATE);
        String user = sharedpreferences.getString("user-email", "");
        db.addScore(user,score);
    }
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Going back is prohibited", Toast.LENGTH_SHORT).show();
    }
    public void submit(View view) {
        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}