package com.example.as1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
//creating the database for the list of user
    public static  final  String DBNAME = "Userlist.db";

    public DbHelper(Context context) {
        super(context, "Userlist.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create  Table  users (username  TEXT  primary Key,  password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL( "drop  Table  if  exists users");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this. getWritableDatabase();
        ContentValues ContentValues= new ContentValues();
        ContentValues.put("username", username);
        ContentValues.put("password", password);
        long result = MyDB.insert("users", null, ContentValues);
        if(result==-1) return false;
        else
            return true;


    }
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            //if user exist return true
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)

            return true;
        else
            return false;
    }
}


