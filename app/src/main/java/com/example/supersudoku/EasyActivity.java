package com.example.supersudoku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EasyActivity extends AppCompatActivity {
    Button puz1, puz2, puz3, puz4;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easymenu);

        puz1 = (Button)findViewById(R.id.button);

        puz1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(EasyActivity.this, GameActivity.class));
            }
        });
    }
}
