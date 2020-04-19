package com.example.supersudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EasyActivity extends AppCompatActivity {
    Button puz1, puz2, puz3, puz4;

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easymenu);

        Toolbar toolbar = findViewById(R.id.gametoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Super Sudoku");

        puz1 = (Button)findViewById(R.id.button);
        puz2 = (Button)findViewById(R.id.button2);
        puz3 = (Button)findViewById(R.id.button3);
        puz4 = (Button)findViewById(R.id.button4);

        puz1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(EasyActivity.this, GameActivity.class);
                intent.putExtra("game", 0);
                intent.putExtra("difficulty", 0);
                startActivity(intent);
            }
        });

        puz2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(EasyActivity.this, GameActivity.class);
                intent.putExtra("game", 1);
                startActivity(intent);
            }
        });

        puz3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(EasyActivity.this, GameActivity.class);
                intent.putExtra("game", 2);
                startActivity(intent);
            }
        });

        puz4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(EasyActivity.this, GameActivity.class);
                intent.putExtra("game", 3);
                startActivity(intent);
            }
        });
    }
}
