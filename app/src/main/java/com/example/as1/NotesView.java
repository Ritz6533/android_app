package com.example.as1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class NotesView extends AppCompatActivity {


    private TextView userTag;
    private EditText notes, title;
    private Button save, delete, back;
    String newDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);

        String titleIs = getIntent().getStringExtra("title");
        String USER_ID = getIntent().getStringExtra("USER_ID");
        String date = getIntent().getStringExtra("date");
        Boolean newNotepad = getIntent().getBooleanExtra("newNotepad",false );

        userTag= findViewById(R.id.textUsernameis);
        title = findViewById(R.id.textViewtitle);
        notes = findViewById(R.id.textViewNotes);
        save = findViewById(R.id.btnSave1);
        delete = findViewById(R.id.btndelete1);
        back = findViewById(R.id.btnback1);


        userTag.setText("User- "+ USER_ID.toUpperCase());


        //to read inside the files


        if (newNotepad == true) {
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
             newDate = df.format(c);

        }
        else {
            title.setText(titleIs);
            StringBuilder sb = new StringBuilder();
            try {
                FileInputStream getFile = openFileInput(USER_ID+"_"+date+"_"+titleIs); // open the file input stream
                InputStreamReader getInput = new InputStreamReader(getFile);
                BufferedReader br = new BufferedReader(getInput);
                String line;
                while ((line = br.readLine()) != null) {
                    Log.d("MSG", line); // display each line of the file in the logcat
                    sb.append(line); // add++ each line to the StringBuilder
                }
                br.close(); // close the buffered reader
                getInput.close(); // close the input stream reader
                getFile.close(); // close the file input stream
            } catch (IOException e) {
                e.printStackTrace();

            }
            String allText = sb.toString(); // get the final string containing all the lines of text
            notes.setText(allText);
            Log.d("MSG", "file notes read "+allText);
            newDate= date;

        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //saving a new file and saving old file while deleting old file.


                Log.d("MSG", "today's date is " + newDate);
                String newtitlex = title.getText().toString();
                String filename = USER_ID + "_" + newDate + "_" + newtitlex;
                Log.d("MSG", "file name is " + filename);
                String string = notes.getText().toString();


                boolean filesaved = false;
                if (newtitlex.contains("_")) {
                    // If there is _ in the file name it will create dispute in logic of file view sys inorder to remove that dispute
                    Toast.makeText(getApplicationContext(), "Error Do Not Use '_' ", Toast.LENGTH_SHORT).show();


                } else {
                    FileOutputStream outputStream;
                    try {
                        outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                        outputStream.write(string.getBytes());
                        outputStream.close();
                        Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
                        Log.d("SAVED", "filesaved name=  " + filename);
                        filesaved = true;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (!newNotepad && !newtitlex.equals(titleIs) && !filesaved) {
                    String oldfile = USER_ID + "_" + newDate + "_" + titleIs;
                    Log.d("MSG", "old file name  " + oldfile);

                    File file = new File(getFilesDir(), oldfile);
                    file.delete();
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filename = USER_ID+"_"+newDate+"_"+titleIs;
                File file = new File(getFilesDir(), filename);
                if (file.delete()) {
                    Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_SHORT).show();
                }
                //
                else {
                    Toast.makeText(getApplicationContext(), "Failed to delete note", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                intent.putExtra("USER_ID", USER_ID);//one way of doing it is starting a new activity by calling intent and a better way is just going back with calling the method.
                startActivity(intent);
            }
        });


    }

}