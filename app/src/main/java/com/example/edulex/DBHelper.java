package com.example.edulex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";


    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {

        Mydb.execSQL("create Table users(Email VARCHAR, Username TEXT, Password VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int oldVersion, int newVersion) {

        Mydb.execSQL("drop Table if exists users");

    }

    public boolean insertData(String email,String username,String password)
    {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email",email);
        contentValues.put("Username",username);
        contentValues.put("Password",password);
        long results = Mydb.insert("users",null,contentValues);
        if(results==-1)
            return false;
        else
            return true;
    }


    public boolean validatemail(String email)
    {
        String regExpn =
                "\\A(?:[a-z0-9!#$%&'+/=?^_‘{|}~-]+(?:.[a-z0-9!#$%&'+/=?^_‘{|}~-]+)*\n" +
                        " |  \"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]\n" +
                        "      |  \\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])\")\n" +
                        "@ (?:(?:[a-z0-9](?:[a-z0-9-][a-z0-9])?.)+[a-z0-9](?:[a-z0-9-][a-z0-9])?\n" +
                        "  |  [(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}\n" +
                        "       (?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-][a-z0-9]:\n" +
                        "          (?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]\n" +
                        "          |  \\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\n" +
                        "     ])\\z";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;


    }
    public boolean checkemail(String email)
    {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from users where email = ?",new String[]{email});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkusername(String username)
    {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from users where username = ?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkusernamepasswordemail(String username,String password,String email)
    {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("Select * from users where email = ? and username = ? and password = ?",new String[]{email,username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}

