package com.notes.hayag.simplenotetakingapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity {

    private EditText titleEditText = null;
    private EditText bodyEditText = null;
    private DbHelper dbHelper = null;
    private SQLiteDatabase db = null;
    private String title;
    private String body;
    private long noteId;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        titleEditText = findViewById(R.id.title_note);
        bodyEditText = findViewById(R.id.body_note);

        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        intent = getIntent();

        title = intent.getStringExtra("Title");
        body = intent.getStringExtra("Body");
        noteId = intent.getLongExtra("note_id", 1);
        titleEditText.setText(title);
        bodyEditText.setText(body);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.save_note){
            if(noteId == 1) {
                long save = dbHelper.insertNote(db, titleEditText.getText().toString(), bodyEditText.getText().toString());
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
                this.finish();
            } else {
                boolean update = dbHelper.updateNote(db, noteId ,titleEditText.getText().toString(), bodyEditText.getText().toString());
                if (update){
                    Toast.makeText(this, "Note is updated!", Toast.LENGTH_SHORT).show();
                    this.finish();
                }else{

                }
            }
        }
        return true;
    }
}
