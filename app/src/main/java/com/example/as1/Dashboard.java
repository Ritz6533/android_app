package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    private TextView welcome;
    private ImageButton notes, calculator, logout, quiz, dice, language;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        welcome = findViewById(R.id.welcomeview);
        notes =findViewById(R.id.imageNotes);
        calculator =findViewById(R.id.imageCalc);
        logout =findViewById(R.id.imageLogout);
        quiz =findViewById(R.id.imageQuiz);
        dice = findViewById(R.id.imageDice);
        language = findViewById(R.id.imageLanguage);

        //getting the user id through login
        String USER_ID = getIntent().getStringExtra("USER_ID");
        Log.d("USER ID ","has been passed "+USER_ID);

        welcome.setText("Hi! "+ USER_ID.toUpperCase());
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Notes.class);
                intent.putExtra("USER_ID", USER_ID);

                startActivity(intent);
                Log.d("RUN ","Activity Started "+USER_ID);

            }
        });

        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Calculator.class);
                intent.putExtra("USER_ID", USER_ID);
                startActivity(intent);

            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Quiz.class);
                intent.putExtra("USER_ID", USER_ID);
                startActivity(intent);

            }
        });


        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Dice.class);
                intent.putExtra("USER_ID", USER_ID);
                startActivity(intent);

            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LearnLanguage.class);
                intent.putExtra("USER_ID", USER_ID);
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Dashboard.this, "LOGGED OUT", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("login", false);


                startActivity(intent);
                //destroy last activities and
                finishAffinity();
                System.exit(0);

            }

        });

}
    @Override
    public void onBackPressed() {

        moveTaskToBack(true);
        }
    }
