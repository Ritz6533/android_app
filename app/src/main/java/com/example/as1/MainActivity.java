package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnReg;
    private EditText username, password;

//initialize database
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        username = findViewById(R.id.usernamex);
        password = findViewById(R.id.passwordx);
        btnReg = findViewById(R.id.btnReg);


        DB = new DbHelper(this);

        DB.defaultUser();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String psw = password.getText().toString();
                Log.d("USER-", "ID/PSW " + user + " " + psw);

                if (user.equals("") || psw.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, psw);
                    if (checkuserpass == true) {
                        Toast.makeText(MainActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        intent.putExtra("USER_ID", user);
                        intent.putExtra("login", true);
                        //clear any activity infront of login ex. register so that it cannot come back from dashboard on back click
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        Log.d("user id is passed", "the user id is "+ user);

                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


}