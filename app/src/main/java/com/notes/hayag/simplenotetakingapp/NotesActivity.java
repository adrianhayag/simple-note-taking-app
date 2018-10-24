package com.notes.hayag.simplenotetakingapp;

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


    public static final String TAG = "MainActivity";
    private DbHelper dbHelper = null;
    private SQLiteDatabase db = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        dbHelper = new DbHelper(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        EditText title_note = findViewById(R.id.title_note);
        EditText body_note = findViewById(R.id.body_note);
        String title = title_note.getText().toString();
        String body = body_note.getText().toString();

       SQLiteDatabase db = dbHelper.getWritableDatabase();
       long noteID = dbHelper.insertNote(db, title, body);

       if (noteID > 0){
           Toast.makeText(this, "Showed", Toast.LENGTH_SHORT).show();
       }


        return true;
    }
}
