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

        //button listeners here
        preEasy.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, EasyActivity.class));
            }
        });
    }


}


