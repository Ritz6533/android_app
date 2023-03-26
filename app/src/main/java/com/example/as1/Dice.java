package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Dice extends AppCompatActivity {


    private ImageView dice;
    private TextView diceIs, userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        String USER_ID = getIntent().getStringExtra("USER_ID");
       // Log.d("USER ID ","has been passed "+USER_ID);
        userid = findViewById(R.id.textUsername);
        userid.setText("User- "+ USER_ID.toUpperCase());

        dice = findViewById(R.id.diceImg);
        diceIs = findViewById(R.id.diceView);
        final int[] diceImages = {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };

        dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomNumber = new Random().nextInt(6);
                dice.setImageResource(diceImages[randomNumber]);
                Log.d("Dice Val", "is ="+ ++randomNumber);

                diceIs.setText("Congratulations!! you got " + randomNumber);
            }
        });


    }
}