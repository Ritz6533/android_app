package com.example.as1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
//creating the database for the list of user
    public static  final  String DBNAME = "Userlist.db";

    public DbHelper(Context context) {
        super(context, "Userlist.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (username TEXT primary Key, password TEXT)");
        Log.d("database" ,"added table");

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
    public void defaultUser() {
        //default user id/psw
        String[] usernames = {"admin", "Auser200", "Auser300", "Auser400", "Auser500","Auser600", "Auser700", "Auser800", "Auser900", "root"};
        String[] passwords = {"admin", "password200", "password300", "password400", "password500","password600", "password700", "password800", "password900", "root"};

        for (int i = 0; i < 10; i++) {
            Boolean checkuser = checkusername(usernames[i]);
            Log.d("logins" ,"added defaults = "+usernames[i]+ " "+ passwords[i]+checkuser );
            //check if the user has been already registered and break the loop
            //since it gets executed everytime and we only need it once
            if (checkuser == false) {
                boolean success = insertData(usernames[i], passwords[i]);
                Log.d("database" ,"added defaults"+usernames[i]+ " "+ passwords[i]);
                if (!success) {
                    //get out of loop and prevent crash.
                    break;
                }
            }
            else
            { break;}
        }

        close();
    }

}


