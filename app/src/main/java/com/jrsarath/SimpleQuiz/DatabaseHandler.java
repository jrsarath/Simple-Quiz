package com.jrsarath.SimpleQuiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MainDB";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_SCORES = "scores";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_NAME = "last_name";
    private static final String KEY_DOB = "dob";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_SCORE = "score";
    private static final String KEY_TIME = "time";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT,"
                + KEY_DOB + " TEXT,"
                + KEY_EMAIL + " TEXT PRIMARY KEY,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
        String CREATE_SCORES_TABLE = "CREATE TABLE " + TABLE_SCORES + "("
                + KEY_EMAIL + " TEXT,"
                + KEY_SCORE + " TEXT,"
                + KEY_TIME + " INT" + ")";
        db.execSQL(CREATE_SCORES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(db);
    }

    boolean addUser(String first_name, String last_name, String dob, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("INSERT INTO "+TABLE_USERS+" VALUES('"+first_name+"','"+last_name+"','"+dob+"','"+email+"','"+password+"');");
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void addScore(String user,int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        Calendar cal = Calendar.getInstance();
        Date currentLocalTime = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY  hh:mm a");
        String date = dateFormat.format(currentLocalTime);
        db.execSQL("INSERT INTO "+TABLE_SCORES+" VALUES('"+user+"','"+score+"','"+date+"');");
        db.close();
    }
    boolean login(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_USERS + " WHERE "+KEY_EMAIL+"='"+email+"' AND " + KEY_PASSWORD + "='"+password+"'", null);
            int count = cursor.getCount();
            cursor.close();
            return count == 1;
        } catch (Exception e) {
            Log.e("App","exception", e);
            return false;
        }
    }

    public List<HashMap> getScores(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_SCORES + " WHERE "+KEY_EMAIL+"='"+user+"'", null);
        int length = cursor.getCount();
        List<HashMap> scores = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                HashMap<String,String> data = new HashMap<String,String>();
                data.put("score",cursor.getString(1));
                data.put("date",cursor.getString(2));
                scores.add(data);
            } while (cursor.moveToNext());
        }
        return scores;
    }


}
