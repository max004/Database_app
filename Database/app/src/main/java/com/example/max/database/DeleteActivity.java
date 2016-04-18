package com.example.max.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    EditText idEditText;
    ArrayList<String> ids = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        idEditText = (EditText) findViewById(R.id.editText);

    }

    public void onClickAdd(View v){

        ids.add(idEditText.getText().toString());
        Toast.makeText(getBaseContext(), "Id added", Toast.LENGTH_SHORT).show();

    }

    public void onClickFinish(View v){
        Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
        intent.putStringArrayListExtra("ids", ids);
        setResult(1);
        startActivity(intent);
    }

}
