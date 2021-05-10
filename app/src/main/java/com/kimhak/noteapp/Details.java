package com.kimhak.noteapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    TextView mDetails;
    NoteDatabase db;
    Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDetails = findViewById(R.id.detailsOfNote);

        Intent i = getIntent();
        Long id = i.getLongExtra("ID", 0);

        db = new NoteDatabase(this);
        note = db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDetails.setText(note.getContent());
        mDetails.setMovementMethod(new ScrollingMovementMethod());

        //Toast.makeText(this, note.getTitle(), Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               db.deleteNote(note.getID());
                Toast.makeText(getApplicationContext(), "The note is deleleted!", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.editNote){
         //redirect user to edit activity
            Toast.makeText(this, "edit note", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,Edit.class);
            i.putExtra("ID", note.getID());
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


}