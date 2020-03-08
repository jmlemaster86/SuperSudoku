package com.example.supersudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    Button preEasy, preMed, preHard, preExtreme, genEasy, genMed, genHard, genExtreme;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


