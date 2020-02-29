package com.example.supersudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tView;
    Board

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);
        btn = (Button)findViewById(R.id.keypad1);
        tView = (TextView)findViewById(R.id.textView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tView.isSelected()){
                    tView.setText("1");
                }

            }
        });

        tView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setSelected(true);
            }
        });

    }




}
