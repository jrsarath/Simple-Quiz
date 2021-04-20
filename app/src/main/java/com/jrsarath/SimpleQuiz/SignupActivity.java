package com.jrsarath.SimpleQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;


public class SignupActivity extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date Of Birth")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();
        EditText dob = findViewById(R.id.dobInput);
        dob.setOnClickListener(v -> {
            Log.d("App","clicked");
            datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
        });
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                dob.setText(datePicker.getHeaderText());
            }
        });
    }

    public String getInputValue(int id) {
        TextInputLayout text = findViewById(id);
        String textValue = text.getEditText().getText().toString();
        if (id == R.id.firstname) {
            if (textValue.matches("[A-Z][a-z]*")) {
                text.setError(null);
                return textValue;
            } else {
                text.setError("Enter a valid firstname");
            }
        } else if (id == R.id.lastname) {
            if (textValue.matches("[A-Z][a-z]*")) {
                text.setError(null);
                return textValue;
            } else {
                text.setError("Enter a valid lastname");
            }
        } else if (id == R.id.dob) {
            if (!textValue.matches("")) {
                text.setError(null);
                return textValue;
            } else {
                text.setError("Select a valid DOB");
            }
        } else if (id == R.id.email) {
            if (textValue.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
                text.setError(null);
                return textValue;
            } else {
                text.setError("Enter A Valid Email");
            }
        } else if (id == R.id.password) {
            if (textValue.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,40}$")) {
                text.setError(null);
                return textValue;
            } else {
                text.setError("Password should be at-least 6 digits and Must contain at-least 1 Caps & Small letter, 1 Digit");
            }
        }
        return null;
    }

    public void signup(View view) {
        String first_name = getInputValue(R.id.firstname);
        String last_name = getInputValue(R.id.lastname);
        String dob = getInputValue(R.id.dob);
        String email = getInputValue(R.id.email);
        String password = getInputValue(R.id.password);
        if (first_name != null && last_name != null && dob != null && email != null && password != null) {
            if (db.addUser(first_name,last_name,dob,email,password)) {
                Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(getApplicationContext(),"An user already exists with this Email",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void goBack(View view) {
        finish();
    }
}