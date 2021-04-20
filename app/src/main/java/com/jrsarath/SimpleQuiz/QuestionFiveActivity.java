package com.jrsarath.SimpleQuiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.app.AlertDialog;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class QuestionFiveActivity extends AppCompatActivity {
    String answer = "";
    AlertDialog alert = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_five_activity);
        alert = new AlertDialog.Builder(this)
                .setTitle("Attention Please")
                .setMessage("Are you sure that u want to submit this answer ?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                        Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                        intent.putExtra("question1_answer", getIntent().getStringExtra("question1_answer"));
                        intent.putExtra("question2_answer", getIntent().getSerializableExtra("question2_answer"));
                        intent.putExtra("question3_answer", getIntent().getStringExtra("question3_answer"));
                        intent.putExtra("question4_answer", getIntent().getStringExtra("question4_answer"));
                        intent.putExtra("question5_answer", answer);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create();
    }
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Going back is prohibited", Toast.LENGTH_SHORT).show();
    }
    public void submit(View view) {
        if (answer.matches(""))
            Toast.makeText(getApplicationContext(),"Please choose an answer first", Toast.LENGTH_SHORT).show();
        else
            alert.show();
    }
    public void toggleBg(int ID) {
        TextView text1 = findViewById(R.id.answer1);
        text1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbg));
        TextView text2 = findViewById(R.id.answer2);
        text2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbg));
        TextView text3 = findViewById(R.id.answer3);
        text3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbg));
        TextView text4 = findViewById(R.id.answer4);
        text4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbg));
        TextView MainView = findViewById(ID);
        MainView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answerbgactive));
    }
    public void answer1(View view) {
        toggleBg(R.id.answer1);
        answer = getString(R.string.question5_answer1);
    }
    public void answer2(View view) {
        toggleBg(R.id.answer2);
        answer = getString(R.string.question5_answer2);
    }
    public void answer3(View view) {
        toggleBg(R.id.answer3);
        answer = getString(R.string.question5_answer3);
    }
    public void answer4(View view) {
        toggleBg(R.id.answer4);
        answer = getString(R.string.question5_answer4);
    }
}
