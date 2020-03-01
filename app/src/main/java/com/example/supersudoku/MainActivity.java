package com.example.supersudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    TextView[] Cell;
    int cellSelector;
    int[] workingBoard = {0, 0, -1, 0, -1, 0, -1, 0, -1,
            0, 0, -1, 0, -1, -1, -1, -1, 0,
            0, 0, 0, -1, 0, 0, 0, -1, 0,
            -1, 0, 0, 0, 0, -1, -1, 0, 0,
            -1, 0, 0, -1, -1, -1, 0, 0, -1,
            0, 0, -1, -1, 0, 0, 0, 0, -1,
            0, -1, 0, 0, 0, -1, 0, 0, 0,
            0, -1, -1, -1, -1, 0, -1, 0, 0,
            -1, 0, -1, 0, -1, 0, -1, 0, 0};
    int[] solvedBoard = {1, 9, 5, 6, 7, 3, 8, 2, 4,
            7, 2, 8, 9, 4, 1, 3, 6, 5,
            3, 4, 6, 2, 5, 8, 9, 7, 1,
            2, 7, 3, 4, 8, 6, 1, 5, 9,
            6, 5, 1, 3, 2, 9, 4, 8, 7,
            9, 8, 4, 5, 1, 7, 6, 3, 2,
            5, 3, 2, 8, 9, 4, 7, 1, 6,
            8, 1, 9, 7, 6, 2, 5, 4, 3,
            4, 6, 7, 1, 3, 5, 2, 9, 8};
    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);
        cellSelector = -1;
        //Initialize all buttons
        btn1 = (Button)findViewById(R.id.keypad1);
        btn2 = (Button)findViewById(R.id.keypad2);
        btn3 = (Button)findViewById(R.id.keypad3);
        btn4 = (Button)findViewById(R.id.keypad4);
        btn5 = (Button)findViewById(R.id.keypad5);
        btn6 = (Button)findViewById(R.id.keypad6);
        btn7 = (Button)findViewById(R.id.keypad7);
        btn8 = (Button)findViewById(R.id.keypad8);
        btn9 = (Button)findViewById(R.id.keypad9);

        //Initialize all Cells (so far)
        Cell = new TextView[81];
        Cell[0] = (TextView)findViewById(R.id.Cell0);
        Cell[1] = (TextView)findViewById(R.id.Cell1);
        Cell[2] = (TextView)findViewById(R.id.Cell2);
        Cell[3] = (TextView)findViewById(R.id.Cell3);
        Cell[4] = (TextView)findViewById(R.id.Cell4);
        Cell[5] = (TextView)findViewById(R.id.Cell5);
        Cell[6] = (TextView)findViewById(R.id.Cell6);
        Cell[7] = (TextView)findViewById(R.id.Cell7);
        Cell[8] = (TextView)findViewById(R.id.Cell8);
        Cell[9] = (TextView)findViewById(R.id.Cell9);
        Cell[10] = (TextView)findViewById(R.id.Cell10);
        Cell[11] = (TextView)findViewById(R.id.Cell11);
        Cell[12] = (TextView)findViewById(R.id.Cell12);
        Cell[13] = (TextView)findViewById(R.id.Cell13);
        Cell[14] = (TextView)findViewById(R.id.Cell14);
        Cell[15] = (TextView)findViewById(R.id.Cell15);
        Cell[16] = (TextView)findViewById(R.id.Cell16);
        Cell[17] = (TextView)findViewById(R.id.Cell17);
        Cell[18] = (TextView)findViewById(R.id.Cell18);
        Cell[19] = (TextView)findViewById(R.id.Cell19);
        Cell[20] = (TextView)findViewById(R.id.Cell20);
        Cell[21] = (TextView)findViewById(R.id.Cell21);
        Cell[22] = (TextView)findViewById(R.id.Cell22);
        Cell[23] = (TextView)findViewById(R.id.Cell23);
        Cell[24] = (TextView)findViewById(R.id.Cell24);
        Cell[25] = (TextView)findViewById(R.id.Cell25);
        Cell[26] = (TextView)findViewById(R.id.Cell26);

        //Initialize the game board data
        board = new Board(9, workingBoard, solvedBoard);


        //setup button listeners
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cell[cellSelector].isSelected())
                    Cell[cellSelector].setText("1");
            }
        });

        //Setup Cell listeners

        Cell[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[0].setSelected(true);
                Cell[0].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 0;
            }
        });

        Cell[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[1].setSelected(true);
                Cell[1].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 1;
            }
        });

        Cell[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[2].setSelected(true);
                Cell[2].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 2;
            }
        });

        Cell[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[9].setSelected(true);
                Cell[9].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 9;
            }
        });

        Cell[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[10].setSelected(true);
                Cell[10].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 10;
            }
        });

        Cell[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[11].setSelected(true);
                Cell[11].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 11;
            }
        });

        Cell[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[18].setSelected(true);
                Cell[18].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 18;
            }
        });

        Cell[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[19].setSelected(true);
                Cell[19].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 19;
            }
        });

        Cell[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[20].setSelected(true);
                Cell[20].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 20;
            }
        });


    }

    public void ClearSelected(){
        for(int i = 0; i < 27; ++i) {
            Cell[i].setSelected(false);
            Cell[i].setBackgroundColor(getResources().getColor(R.color.colorPrimBack));
        }
    }




}
