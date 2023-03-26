package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuizScoreBoard extends AppCompatActivity {


    private Button btnexits;
    private TextView userTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_score_board);

        String USER_ID = getIntent().getStringExtra("USER_ID");

        btnexits= findViewById(R.id.button2);
        userTitle=findViewById(R.id.textUserScoreTitle);

        userTitle.setText("User- "+ USER_ID.toUpperCase());




        //extracting string of scores from the saved file based on user id

        try {
            FileInputStream fileInputStream = openFileInput("score.csv");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            ArrayList<String> scoreList = new ArrayList<>();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                String userId = fields[0];
                String score = fields[1];
                if (userId.equals(USER_ID)) {
                scoreList.add(score);}
            }

            bufferedReader.close();
            fileInputStream.close();

            String[] scoreArray = scoreList.toArray(new String[0]);
            scoreArray[0]="0";
            // Sort the scoreArray in descending order
            Arrays.sort(scoreArray, Collections.reverseOrder());

            // Create a new array with the 10 highest scores
            String[] top10Scores = Arrays.copyOfRange(scoreArray, 0, Math.min(scoreArray.length, 10));

            Log.d("score", " 1st score "+ top10Scores[0]);
            RecyclerView NameList = findViewById(R.id.recycleViewScores);
            RecycleViewScoreList recycleviewAdapterClass = new RecycleViewScoreList(top10Scores);
            NameList.setLayoutManager(new LinearLayoutManager(QuizScoreBoard.this));
            NameList.setAdapter(recycleviewAdapterClass);


        } catch (IOException e) {
            e.printStackTrace();
        }


        RecyclerView NameList = findViewById(R.id.recycleViewScores);





        btnexits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                intent.putExtra("USER_ID", USER_ID);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onBackPressed() {
        String USER_ID = getIntent().getStringExtra("USER_ID");

        Intent intent = new Intent(getApplicationContext(),Dashboard.class);
        intent.putExtra("USER_ID", USER_ID);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



}