package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class QuizScore extends AppCompatActivity {


    private TextView txtUser, txtScore, txtdefault, txtuserid;
    private Button btnSave, btnView, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_score);

        String USER_ID = getIntent().getStringExtra("USER_ID");

        String score = getIntent().getStringExtra("score");


        Log.d("USER ID ","has been passed "+USER_ID);

        txtuserid=findViewById(R.id.textuserid);
        txtUser=findViewById(R.id.textadmin);
        txtScore= findViewById(R.id.textScoreVal);
        txtdefault= findViewById(R.id.textView6);

        txtuserid.setText("User- "+ USER_ID.toUpperCase());

        txtUser.setText("Hello "+ USER_ID.toUpperCase()+" !! here is your final score.");
        txtScore.setText(score);
            Log.d("MSG", "Final score is"+ score);
        btnSave= findViewById(R.id.btnSave1);
        btnView= findViewById(R.id.btnView);
        btnExit= findViewById(R.id.btnExit);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {


                    FileOutputStream fileOutputStream = openFileOutput("score.csv", MODE_APPEND);
                    String line = USER_ID + "," + score + "\n";
                    fileOutputStream.write(line.getBytes());
                    fileOutputStream.close();

                    Toast.makeText(getApplicationContext(), "Score saved", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            }

        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),QuizScoreBoard.class);
                intent.putExtra("USER_ID", USER_ID);
                startActivity(intent);


            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
               intent.putExtra("USER_ID", USER_ID);
                startActivity(intent);
            }
        });

    }
    public void onBackPressed() {
        String USER_ID = getIntent().getStringExtra("USER_ID");

        Intent intent = new Intent(getApplicationContext(),Dashboard.class);
        intent.putExtra("USER_ID", USER_ID);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}