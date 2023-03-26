package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Notes extends AppCompatActivity {

    private FloatingActionButton addNote;
    private TextView useridAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        useridAdd = findViewById(R.id.textUsername3);
        addNote = findViewById(R.id.addnotesbtn);

        String USER_ID = getIntent().getStringExtra("USER_ID");
        useridAdd.setText("User- "+ USER_ID.toUpperCase());

        RecyclerView NameList = findViewById(R.id.recycleviewNotepad1);
        ArrayList<String> fileNames = new ArrayList<>();
        ArrayList<String> titleNotes = new ArrayList<>();
        ArrayList<String> dateis = new ArrayList<>();
        fileNames.add("hey");
        fileNames.add("hello");


        //default and cannot be removed to avoid system crash
        if (titleNotes.isEmpty()) {

            String filenameis = USER_ID + "_" + "20202" + "_" + "Defaulttext";
            Log.d("TAG", "file name is " + filenameis);
            String string2 = "write a note here This is a default notes and cannot be removed";

            FileOutputStream outputStream;
            try {
                outputStream = openFileOutput(filenameis, Context.MODE_PRIVATE);
                outputStream.write(string2.getBytes());
                outputStream.close();
                Log.d("TAG", "default file is saved == " + filenameis);

            } catch (Exception e) {
                e.printStackTrace();
            }}
        /// read the dir and files

        File dir = getFilesDir(); // get the directory where the files are stored
        File[] files = dir.listFiles(); // get the list of files in the directory

            for (File file : files) {
                try {
                    String[] parts = file.getName().split("_");
                    Log.d("RUN ","has been starte ");
                    // Check if the file name has at least 3 parts
                    if (parts.length == 3) {
                        String username = parts[0];
                        String date = parts[1];
                        String titleIs = parts[2];


                        // check if the username is equal to file username
                        if (username.equals(USER_ID)) {


                            // add the last part of filename as title of the notes
                            titleNotes.add(titleIs);

                            // saving date of the files
                            dateis.add(date);
                        }
                    } else {}
                        // Ignore the file if it does not have at least 3 parts
                        continue;

                } catch (Exception e) {
                    // Handle the exception and continue to the next file
                    continue;
                }
            }

            RecycleViewAdaptorClass recycleviewAdapterClass = new RecycleViewAdaptorClass(titleNotes,USER_ID,dateis);
            NameList.setLayoutManager(new LinearLayoutManager(Notes.this));

            NameList.setAdapter(recycleviewAdapterClass);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NotesView.class);
                intent.putExtra("title", "");
                intent.putExtra("USER_ID", USER_ID);
                intent.putExtra("newNotepad", true);
                v.getContext().startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        String USER_ID = getIntent().getStringExtra("USER_ID");
        // Refresh the data in the activity get the new data
        RecyclerView NameList = findViewById(R.id.recycleviewNotepad1);
        ArrayList<String> fileNames = new ArrayList<>();
        ArrayList<String> titleNotes = new ArrayList<>();
        ArrayList<String> dateis = new ArrayList<>();

        // Clear the existing data from the arrays
        fileNames.clear();
        titleNotes.clear();
        dateis.clear();

        // Read the updated file list and populate the arrays
        File dir = getFilesDir();
        File[] files = dir.listFiles();
        for (File file : files) {
            try {
                String[] parts = file.getName().split("_");

                if (parts.length == 3) {
                    String username = parts[0];
                    String date = parts[1];
                    String titleIs = parts[2];

                    if (username.equals(USER_ID)) {
                        titleNotes.add(titleIs);
                        dateis.add(date);
                    }
                } else {
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
        }

        // Update the RecyclerView adapter with the new data
        RecycleViewAdaptorClass recycleviewAdapterClass = new RecycleViewAdaptorClass(titleNotes, USER_ID, dateis);
        NameList.setLayoutManager(new LinearLayoutManager(Notes.this));
        NameList.setAdapter(recycleviewAdapterClass);
    }


}


