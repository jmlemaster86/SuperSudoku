package com.example.supersudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MedActivity extends AppCompatActivity {
    Button puz1, puz2, puz3, puz4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediummenu);

        puz1 = (Button) findViewById(R.id.button);
        puz2 = (Button) findViewById(R.id.button2);
        puz3 = (Button) findViewById(R.id.button3);
        puz4 = (Button) findViewById(R.id.button4);

        puz1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MedActivity.this, GameActivity.class));
            }
        });

        puz2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MedActivity.this, GameActivity.class));
            }
        });

        puz3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MedActivity.this, GameActivity.class));
            }
        });

        puz4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MedActivity.this, GameActivity.class));
            }
        });
    }
}
