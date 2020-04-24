package com.example.supersudoku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    Button preEasy, preMed, preHard, preExtreme, genEasy, genMed, genHard, genExtreme, options;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.options) {
            Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.gametoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Super Sudoku");

        //initialize buttons here
        preEasy = (Button)findViewById(R.id.button);
        preMed = (Button)findViewById(R.id.button2);
        preHard = (Button)findViewById(R.id.button3);
        preExtreme = (Button)findViewById(R.id.button4);
        genEasy = (Button)findViewById(R.id.button7);
        genMed = (Button)findViewById(R.id.button6);
        genHard = (Button)findViewById(R.id.button5);
        genExtreme = (Button)findViewById(R.id.button8);

        //button listeners here
        preEasy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, EasyActivity.class));
            }
        });

        genEasy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, EasyActivity.class));
            }
        });

        preMed.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MedActivity.class));
            }
        });

        genMed.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, MedActivity.class));
            }
        });

        preHard.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, HardActivity.class));
            }
        });

        genHard.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, HardActivity.class));
            }
        });

        preExtreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ExtremeActivity.class));
            }
        });

        genExtreme.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ExtremeActivity.class));
            }
        });
    }


}


