package com.example.supersudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MedActivity extends AppCompatActivity {
    Button puz1, puz2, puz3, puz4;

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediummenu);

        Toolbar toolbar = findViewById(R.id.gametoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Super Sudoku");

        puz1 = (Button) findViewById(R.id.button);
        puz2 = (Button) findViewById(R.id.button2);
        puz3 = (Button) findViewById(R.id.button3);
        puz4 = (Button) findViewById(R.id.button4);

        puz1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MedActivity.this, GameActivity.class);
                intent.putExtra("game", 4);
                startActivity(intent);
            }
        });

        puz2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MedActivity.this, GameActivity.class);
                intent.putExtra("game", 5);
                startActivity(intent);
            }
        });

        puz3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MedActivity.this, GameActivity.class);
                intent.putExtra("game", 6);
                startActivity(intent);
            }
        });

        puz4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MedActivity.this, GameActivity.class);
                intent.putExtra("game", 7);
                startActivity(intent);
            }
        });
    }
}
