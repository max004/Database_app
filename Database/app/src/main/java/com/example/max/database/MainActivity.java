package com.example.max.database;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    FeedReaderDbHelper dbhelper;
    EditText id;
    EditText firstName;
    EditText lastName;
    Button saveButton;
    Button viewButton;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ArrayList<String> idsAl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText)findViewById(R.id.idEditText);
        firstName = (EditText)findViewById(R.id.lastNameEditText);
        lastName = (EditText)findViewById(R.id.firstNameEditText);
        saveButton = (Button) findViewById(R.id.saveButton);
        viewButton = (Button) findViewById(R.id.viewButton);
        dbhelper = new FeedReaderDbHelper(context);
    }

    public void onClickSave(View v){

            sqLiteDatabase = dbhelper.getWritableDatabase();
            dbhelper.insert(id.getText().toString(), lastName.getText().toString(), firstName.getText().toString(),sqLiteDatabase);
            Toast.makeText(getBaseContext(), "Data saved", Toast.LENGTH_LONG) .show();
            dbhelper.close();
    }

    public void onClickShow(View v){

        StringBuffer stringBuffer = new StringBuffer();

        sqLiteDatabase = dbhelper.getReadableDatabase();
        cursor = dbhelper.show(sqLiteDatabase);
        cursor.moveToFirst();

        while(cursor.moveToNext()){

            stringBuffer.append("ID: " + cursor.getString(0) + "\n");
            stringBuffer.append("last_name: " + cursor.getString(1) + "\n");
            stringBuffer.append("first_name: " + cursor.getString(2) + "\n\n");

        }

        showMessage("Data", stringBuffer.toString());

    }


    public void showMessage(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void onClickDelete(View v){

        sqLiteDatabase = dbhelper.getReadableDatabase();


        Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
        startActivity(intent);
        //startActivityForResult(intent, 1);

        Intent i = getIntent();

        idsAl = intent.getStringArrayListExtra("ids");


            String[] ids = new String[idsAl.size()];
            idsAl.toArray(ids);

            dbhelper.delete(ids, sqLiteDatabase);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { super.onActivityResult(requestCode, resultCode, data);, data);
    }
}
