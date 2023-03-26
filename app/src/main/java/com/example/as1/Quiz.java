package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class Quiz extends AppCompatActivity {

    private TextView txtUser,txtScore,txtQn;

    private ImageView imgQn;
    private Button btnSubmit;
    private RadioButton b1,b2,b3,b4;
    private RadioGroup r1;

    private int questionNumber = 0;
    private int imageQuestionNo = -1;
    private int score = 0;

    private String[] questions = {"What is the opposite of SAD ?",
            "Which country has the highest peak Mt.Everest ?",
            "What is the Capital city of Nepal ?",
            "What city is known as \"The Eternal City\" ?",
            "What is the acceleration of gravity of earth ?",
            "What is the currency of UK?"};


    private String[][] options = {{"Happy", "Guilt", "Angry", "Confused"},
            {"UK", "China", "USA", "Nepal"},
            {"London", "New Delhi", "Kathmandu", "Dang"},
            {"Paris", "Rome", "Cardiff", "Northampton"},
            {"10", "5.5", "9.8", "1024"},
            {"Pounds", "Yen", "Dollar", "Euro"}};
    private int[] correctAnswers = {0, 3, 2, 1, 2, 0};


    private final int[] imageQuestion = {R.drawable.cat, R.drawable.ukk, R.drawable.katana,R.drawable.statue};

    private String[][] imgQnOptions = {{"Monkey", "Rat", "Cat", "Pig"},
            {"Nepal", "China", "UK", "Vatican City"},
            {"Hammer", "Knife", "Gun", "katana Sword"},
            {"Statue of Liberty", "Christ the Redeemer", "Statue of America", "Statue of Europe"}
    };

    private int[] imgCorrectAnswers = {2, 2, 3, 0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        txtUser= findViewById(R.id.textUsername);
        txtQn= findViewById(R.id.textQuestion);
        txtScore= findViewById(R.id.textScore);
        btnSubmit= findViewById(R.id.buttonSubmitted);
        r1= findViewById(R.id.radioGroup1);


        b1= findViewById(R.id.radioButton1);
        b2= findViewById(R.id.radioButton2);
        b3= findViewById(R.id.radioButton3);
        b4= findViewById(R.id.radioButton4);

        //setting imageview and hiding the view
        imgQn= findViewById(R.id.imageQuestion);
        imgQn.setVisibility(View.GONE);


        String USER_ID = getIntent().getStringExtra("USER_ID");
        Log.d("USER ID ","has been passed "+USER_ID);

        txtUser.setText("User - "+USER_ID.toUpperCase() );
        txtScore.setText("Score - "+ score);



        displayQuestion(questionNumber);

        // Set onClickListener for submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if any button is clicked so that it can check the answer or display error
                if (r1.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select an option.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if answer is correct
                int submittedAnswer = r1.indexOfChild(findViewById(r1.getCheckedRadioButtonId()));
                //check if the array number i.e. 1/2/3 is being displayed
                Log.d("answer id", "is "+ submittedAnswer );
                if (imageQuestionNo<0) {
                    if (submittedAnswer == correctAnswers[questionNumber]) {
                        txtScore.setText("Score - " + ++score);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Answer!!.", Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    if (submittedAnswer == imgCorrectAnswers[imageQuestionNo]) {
                        txtScore.setText("Score - " + ++score);
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Answer!!.", Toast.LENGTH_SHORT).show();

                    }

                }

                // Move to next question or show score
                if (questionNumber < questions.length - 1) {
                  Log.d("qn", "is "+ questionNumber+ " "+questions.length);
                    questionNumber++;
                    displayQuestion(questionNumber);
                } else {
                    if (imageQuestionNo < imageQuestion.length - 1) {
                        //disable the TextView and add the image View
                        imgQn.setVisibility(View.VISIBLE);
                        txtQn.setVisibility(View.GONE);
                        Log.d("qn", "is " + imageQuestionNo + " " + imageQuestion.length);
                        imageQuestionNo++;
                        displayImg(imageQuestionNo);
                    } else {

                        //display the scores and save it like a leaderboard.
                        Intent intent = new Intent(getApplicationContext(),QuizScore.class);
                        intent.putExtra("USER_ID", USER_ID);
                        intent.putExtra("score", Integer.toString(score));
                        startActivity(intent);
                    }

                }
            }
        });
    }
    // Set the question and options for the given index
    private void displayQuestion(int index) {
        //image qn runner
        txtQn.setText(questions[index]);
        b1.setText(options[index][0]);
        b2.setText(options[index][1]);
        b3.setText(options[index][2]);
        b4.setText(options[index][3]);
       r1.clearCheck();
    }

    private void displayImg(int index) {
        imgQn.setImageResource(imageQuestion[index]);
        b1.setText(imgQnOptions[index][0]);
        b2.setText(imgQnOptions[index][1]);
        b3.setText(imgQnOptions[index][2]);
        b4.setText(imgQnOptions[index][3]);
        r1.clearCheck();
    }
    // Show the final score and write it to a file
    private void showScore() {
        Toast.makeText(getApplicationContext(), "Your score is " + score, Toast.LENGTH_SHORT).show();
        try {
            FileOutputStream fileOutputStream = openFileOutput("score.txt", MODE_PRIVATE);
            fileOutputStream.write(("Score: " + score).getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}