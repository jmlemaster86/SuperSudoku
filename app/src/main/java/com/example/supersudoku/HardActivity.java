package com.example.supersudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HardActivity extends AppCompatActivity {
    Button puz1, puz2, puz3, puz4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardmenu);

        puz1 = (Button) findViewById(R.id.button);
        puz2 = (Button) findViewById(R.id.button2);
        puz3 = (Button) findViewById(R.id.button3);
        puz4 = (Button) findViewById(R.id.button4);

        puz1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HardActivity.this, GameActivity.class);
                intent.putExtra("game", 0);
                intent.putExtra("difficulty", 2);
                startActivity(intent);
            }
        });

        puz2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HardActivity.this, GameActivity.class);
                intent.putExtra("game", 1);
                intent.putExtra("difficulty", 2);
                startActivity(intent);
            }
        });

        puz3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HardActivity.this, GameActivity.class);
                intent.putExtra("game", 2);
                intent.putExtra("difficulty", 2);
                startActivity(intent);
            }
        });

        puz4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HardActivity.this, GameActivity.class);
                intent.putExtra("game", 3);
                intent.putExtra("difficulty", 2);
                startActivity(intent);
            }
        });
    }
}
