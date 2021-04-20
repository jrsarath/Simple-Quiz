package com.jrsarath.SimpleQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("quzinator", Context.MODE_PRIVATE);
        String user = sharedpreferences.getString("user-email", "");

        LinearLayout view = findViewById(R.id.scores);

        List<HashMap> data = db.getScores(user);
        for (HashMap item : data) {
            LinearLayout container = new LinearLayout(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1.0f
            );
            container.setLayoutParams(param);
            container.setOrientation(LinearLayout.VERTICAL);
            container.setPadding(0,30,40,30);

            TextView score = new TextView(this);
            score.setText("Your score was: "+item.get("score").toString()+"/5");
            score.setTextAppearance(getApplicationContext(), R.style.scoreText);

            TextView date = new TextView(this);
            date.setTextAppearance(getApplicationContext(), R.style.scoreDate);
            date.setText("Played at: "+item.get("date").toString());

            container.addView(score);
            container.addView(date);
            view.addView(container);

            Log.d("app",item.toString());
        }
    }

    public void go(View view) {
        Intent intent = new Intent(getBaseContext(), QuestionOneActivity.class);
        startActivity(intent);
    }
}
