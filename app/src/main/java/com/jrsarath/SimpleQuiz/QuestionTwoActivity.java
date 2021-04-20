package com.jrsarath.SimpleQuiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.HashMap;

public class QuestionTwoActivity extends AppCompatActivity {
    HashMap<String, Boolean> answers = new HashMap<String, Boolean>( );
    AlertDialog alert = null;
    Boolean ansOne = false;
    Boolean ansTwo = false;
    Boolean ansThree = false;
    Boolean ansFour = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_two_activity);

        answers.put(getString(R.string.question2_answer1),false);
        answers.put(getString(R.string.question2_answer2),false);
        answers.put(getString(R.string.question2_answer3),false);
        answers.put(getString(R.string.question2_answer4),false);

        alert = new AlertDialog.Builder(this)
                .setTitle("Attention Please")
                .setMessage("Are you sure that u want to submit this answer ?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                        Intent intent = new Intent(getBaseContext(), QuestionThreeActivity.class);
                        intent.putExtra("question1_answer", getIntent().getStringExtra("question1_answer"));
                        intent.putExtra("question2_answer", answers);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create();
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Going back is prohibited", Toast.LENGTH_SHORT).show();
    }
    public void submit(View view) {
        if (answers.size() > 0)
            alert.show();
        else
            Toast.makeText(getApplicationContext(),"Please choose an answer first", Toast.LENGTH_SHORT).show();
    }

    public void answer1(View view) {
        TextView MainView = findViewById(R.id.answer1);
        if (answers.get(getString(R.string.question2_answer1))) {
            MainView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbg));
        } else {
            MainView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbgactive));
        }
        answers.put(getString(R.string.question2_answer1), !answers.get(getString(R.string.question2_answer1)));
    }
    public void answer2(View view) {
        TextView MainView = findViewById(R.id.answer2);
        if (answers.get(getString(R.string.question2_answer2))) {
            MainView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbg));
        } else {
            MainView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbgactive));
        }
        answers.put(getString(R.string.question2_answer2), !answers.get(getString(R.string.question2_answer2)));
    }
    public void answer3(View view) {
        TextView MainView = findViewById(R.id.answer3);
        if (answers.get(getString(R.string.question2_answer3))) {
            MainView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbg));
        } else {
            MainView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbgactive));
        }
        answers.put(getString(R.string.question2_answer3), !answers.get(getString(R.string.question2_answer3)));
    }
    public void answer4(View view) {
        TextView MainView = findViewById(R.id.answer4);
        if (answers.get(getString(R.string.question2_answer4))) {
            MainView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbg));
        } else {
            MainView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbgactive));
        }
        answers.put(getString(R.string.question2_answer4), !answers.get(getString(R.string.question2_answer4)));
    }
}
