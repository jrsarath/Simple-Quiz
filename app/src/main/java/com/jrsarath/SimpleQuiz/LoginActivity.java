package com.jrsarath.SimpleQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }

    public String getInputValue(int id) {
        TextInputLayout text = findViewById(id);
        String textValue = text.getEditText().getText().toString();
        if (id == R.id.email) {
            if (textValue.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                text.setError(null);
                return textValue;
            } else {
                text.setError("Enter A Valid Email");
            }
        } else if (id == R.id.password) {
            if (!textValue.matches("")) {
                text.setError(null);
                return textValue;
            } else {
                text.setError("Password can't be empty");
            }
        }
        return null;
    }
    public void login(View view) {
        String email = getInputValue(R.id.email);
        String password = getInputValue(R.id.password);
        if (email != null && password != null) {
            if (db.login(email,password)) {
                Toast.makeText(getApplicationContext(),"Welcome back",Toast.LENGTH_SHORT).show();
                SharedPreferences sharedpreferences;
                sharedpreferences = getSharedPreferences("quzinator", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("user-email", email);
                editor.commit();
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(),"Incorrect email & password combination",Toast.LENGTH_SHORT).show();
            }
         }
    }
    public void goBack(View view) {
        finish();
    }
}