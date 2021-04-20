package com.jrsarath.SimpleQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("quzinator", Context.MODE_PRIVATE);
        String user = sharedpreferences.getString("user-email", "");
        if (!user.matches("")) {
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    public void login(View view) {
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }
    public void signup(View view) {
        Intent intent = new Intent(getBaseContext(), SignupActivity.class);
        startActivity(intent);
    }
}