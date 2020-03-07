package com.example.supersudoku;

import androidx.appcompat.app.AppCompatActivity;

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

    // board 34 from book
    int[] workingBoard2 =
            {-1, -1, -1, 0, -1, 0, 0, 0, 0,
             -1, -1, 0, 0, 0, -1, -1, -1, 0,
             0, 0, 0, 0, -1, 0, 0, -1, -1,
             0, 0, 0, -1, 0, 0, -1, 0, -1,
             0, 0, -1, -1, -1, -1, -1, 0, 0,
             -1, 0, -1, 0, 0, -1, 0, 0, 0,
             -1, -1, 0, 0, -1, 0, 0, 0, 0,
              0, -1, -1, -1, 0, 0, 0, -1, -1,
              0, 0, 0, 0, -1, 0, -1, -1, -1};
    int[] solvedBoard2 =
            {2, 5, 3, 6, 4, 9, 7, 8, 1,
             7, 1, 6, 3, 5, 8, 2, 4, 9,
             8, 4, 9, 1, 2, 7, 5, 3, 6,
             3, 6, 1, 8, 9, 5, 4, 2, 7,
             5, 7, 2, 4, 3, 1, 9, 6, 8,
             4, 9, 8, 2, 7, 6, 1, 5, 3,
             9, 2, 5, 7, 8, 3, 6, 1, 4,
             1, 3, 7, 5, 6, 4, 8, 9, 2,
             6, 8, 4, 9, 1, 2, 3, 7, 5,};

    // #35 from book
    int[] workingBoard3 =
            {0, -1, 0, 0, 0, -1, 0, 0, 0,
             -1, 0, -1, -1, -1, -1, 0, 0, 0,
             -1, 0, 0, 0, 0, 0, -1, -1, 0,
              0, 0, -1, -1, -1, 0, -1, 0, -1,
             -1, -1, 0, 0, -1, 0, 0, -1, -1,
                    -1, 0, -1, 0, -1, -1, -1, 0, 0,
             0, -1, -1, 0, 0, 0, 0, 0, -1,
             0, 0, 0, -1, -1, -1, -1, 0, -1,
             0, 0, 0, -1, 0, 0, 0, -1, -1 };

    int[] solvedBoard3 =
            {2, 9, 7, 5, 8, 6, 3, 1, 4,
             8, 6, 1, 3, 2, 4, 7, 5, 9,
             5, 4, 3, 9, 1, 7, 2, 6, 8,
             6, 2, 9, 8, 7, 5, 4, 3, 1,
             3, 1, 5, 4, 6, 2, 8, 9, 7,
             7, 8, 4, 1, 3, 9, 5, 2, 6,
             9, 5, 2, 6, 4, 8, 1, 7, 3,
             4, 3, 6, 7, 5, 1, 9, 8, 2,
             1, 7, 8, 2, 9, 3, 6, 4, 5};
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
        Cell[27] = (TextView)findViewById(R.id.Cell27);
        Cell[28] = (TextView)findViewById(R.id.Cell28);
        Cell[29] = (TextView)findViewById(R.id.Cell29);
        Cell[30] = (TextView)findViewById(R.id.Cell30);
        Cell[31] = (TextView)findViewById(R.id.Cell31);
        Cell[32] = (TextView)findViewById(R.id.Cell32);
        Cell[33] = (TextView)findViewById(R.id.Cell33);
        Cell[34] = (TextView)findViewById(R.id.Cell34);
        Cell[35] = (TextView)findViewById(R.id.Cell35);
        Cell[36] = (TextView)findViewById(R.id.Cell36);
        Cell[37] = (TextView)findViewById(R.id.Cell37);
        Cell[38] = (TextView)findViewById(R.id.Cell38);
        Cell[39] = (TextView)findViewById(R.id.Cell39);
        Cell[40] = (TextView)findViewById(R.id.Cell40);
        Cell[41] = (TextView)findViewById(R.id.Cell41);
        Cell[42] = (TextView)findViewById(R.id.Cell42);
        Cell[43] = (TextView)findViewById(R.id.Cell43);
        Cell[44] = (TextView)findViewById(R.id.Cell44);
        Cell[45] = (TextView)findViewById(R.id.Cell45);
        Cell[46] = (TextView)findViewById(R.id.Cell46);
        Cell[47] = (TextView)findViewById(R.id.Cell47);
        Cell[48] = (TextView)findViewById(R.id.Cell48);
        Cell[49] = (TextView)findViewById(R.id.Cell49);
        Cell[50] = (TextView)findViewById(R.id.Cell50);
        Cell[51] = (TextView)findViewById(R.id.Cell51);
        Cell[52] = (TextView)findViewById(R.id.Cell52);
        Cell[53] = (TextView)findViewById(R.id.Cell53);
        Cell[54] = (TextView)findViewById(R.id.Cell54);
        Cell[55] = (TextView)findViewById(R.id.Cell55);
        Cell[56] = (TextView)findViewById(R.id.Cell56);
        Cell[57] = (TextView)findViewById(R.id.Cell57);
        Cell[58] = (TextView)findViewById(R.id.Cell58);
        Cell[59] = (TextView)findViewById(R.id.Cell59);
        Cell[60] = (TextView)findViewById(R.id.Cell60);
        Cell[61] = (TextView)findViewById(R.id.Cell61);
        Cell[62] = (TextView)findViewById(R.id.Cell62);
        Cell[63] = (TextView)findViewById(R.id.Cell63);
        Cell[64] = (TextView)findViewById(R.id.Cell64);
        Cell[65] = (TextView)findViewById(R.id.Cell65);
        Cell[66] = (TextView)findViewById(R.id.Cell66);
        Cell[67] = (TextView)findViewById(R.id.Cell67);
        Cell[68] = (TextView)findViewById(R.id.Cell68);
        Cell[69] = (TextView)findViewById(R.id.Cell69);
        Cell[70] = (TextView)findViewById(R.id.Cell70);
        Cell[71] = (TextView)findViewById(R.id.Cell71);
        Cell[72] = (TextView)findViewById(R.id.Cell72);
        Cell[73] = (TextView)findViewById(R.id.Cell73);
        Cell[74] = (TextView)findViewById(R.id.Cell74);
        Cell[75] = (TextView)findViewById(R.id.Cell75);
        Cell[76] = (TextView)findViewById(R.id.Cell76);
        Cell[77] = (TextView)findViewById(R.id.Cell77);
        Cell[78] = (TextView)findViewById(R.id.Cell78);
        Cell[79] = (TextView)findViewById(R.id.Cell79);
        Cell[80] = (TextView)findViewById(R.id.Cell80);



        //Initialize the game board data
        board = new Board(9, workingBoard, solvedBoard);

        for(int i = 0; i < 81; ++i){
            Cell[i].setText(Character.toString(board.getSquare(i)));
        }


        //setup button listeners
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cell[cellSelector].isSelected()) {
                    board.setSquare(cellSelector, 1);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cell[cellSelector].isSelected()) {
                    board.setSquare(cellSelector, 2);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cell[cellSelector].isSelected()) {
                    board.setSquare(cellSelector, 3);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cell[cellSelector].isSelected()) {
                    board.setSquare(cellSelector, 4);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cell[cellSelector].isSelected()) {
                    board.setSquare(cellSelector, 5);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cell[cellSelector].isSelected()) {
                    board.setSquare(cellSelector, 6);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cell[cellSelector].isSelected()) {
                    board.setSquare(cellSelector, 7);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cell[cellSelector].isSelected()) {
                    board.setSquare(cellSelector, 8);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cell[cellSelector].isSelected()) {
                    board.setSquare(cellSelector, 9);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                }
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

        Cell[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[3].setSelected(true);
                Cell[3].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 3;
            }
        });

        Cell[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[4].setSelected(true);
                Cell[4].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 4;
            }
        });

        Cell[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[5].setSelected(true);
                Cell[5].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 5;
            }
        });

        Cell[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[6].setSelected(true);
                Cell[6].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 6;
            }
        });

        Cell[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[7].setSelected(true);
                Cell[7].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 7;
            }
        });

        Cell[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[8].setSelected(true);
                Cell[8].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 8;
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

        Cell[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[12].setSelected(true);
                Cell[12].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 12;
            }
        });

        Cell[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[13].setSelected(true);
                Cell[13].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 13;
            }
        });

        Cell[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[14].setSelected(true);
                Cell[14].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 14;
            }
        });

        Cell[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[15].setSelected(true);
                Cell[15].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 15;
            }
        });

        Cell[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[16].setSelected(true);
                Cell[16].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 16;
            }
        });

        Cell[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[17].setSelected(true);
                Cell[17].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 17;
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

        Cell[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[21].setSelected(true);
                Cell[21].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 21;
            }
        });

        Cell[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[22].setSelected(true);
                Cell[22].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 22;
            }
        });

        Cell[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[23].setSelected(true);
                Cell[23].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 23;
            }
        });

        Cell[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[24].setSelected(true);
                Cell[24].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 24;
            }
        });

        Cell[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[25].setSelected(true);
                Cell[25].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 25;
            }
        });

        Cell[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[26].setSelected(true);
                Cell[26].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 26;
            }
        });

        Cell[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[27].setSelected(true);
                Cell[27].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 27;
            }
        });

        Cell[28].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[28].setSelected(true);
                Cell[28].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 28;
            }
        });

        Cell[29].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[29].setSelected(true);
                Cell[29].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 29;
            }
        });

        Cell[30].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[30].setSelected(true);
                Cell[30].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 30;
            }
        });

        Cell[31].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[31].setSelected(true);
                Cell[31].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 31;
            }
        });

        Cell[32].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[32].setSelected(true);
                Cell[32].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 32;
            }
        });

        Cell[33].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[33].setSelected(true);
                Cell[33].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 33;
            }
        });

        Cell[34].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[34].setSelected(true);
                Cell[34].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 34;
            }
        });

        Cell[35].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[35].setSelected(true);
                Cell[35].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 35;
            }
        });

        Cell[36].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[36].setSelected(true);
                Cell[36].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 36;
            }
        });

        Cell[37].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[37].setSelected(true);
                Cell[37].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 37;
            }
        });

        Cell[38].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[38].setSelected(true);
                Cell[38].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 38;
            }
        });

        Cell[39].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[39].setSelected(true);
                Cell[39].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 39;
            }
        });

        Cell[40].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[40].setSelected(true);
                Cell[40].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 40;
            }
        });

        Cell[41].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[41].setSelected(true);
                Cell[41].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 41;
            }
        });

        Cell[42].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[42].setSelected(true);
                Cell[42].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 42;
            }
        });

        Cell[43].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[43].setSelected(true);
                Cell[43].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 43;
            }
        });

        Cell[44].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[44].setSelected(true);
                Cell[44].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 44;
            }
        });

        Cell[45].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[45].setSelected(true);
                Cell[45].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 45;
            }
        });

        Cell[46].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[46].setSelected(true);
                Cell[46].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 46;
            }
        });

        Cell[47].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[47].setSelected(true);
                Cell[47].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 47;
            }
        });

        Cell[48].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[48].setSelected(true);
                Cell[48].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 48;
            }
        });

        Cell[49].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[49].setSelected(true);
                Cell[49].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 49;
            }
        });

        Cell[50].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[50].setSelected(true);
                Cell[50].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 50;
            }
        });

        Cell[51].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[51].setSelected(true);
                Cell[51].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 51;
            }
        });

        Cell[52].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[52].setSelected(true);
                Cell[52].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 52;
            }
        });

        Cell[53].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[53].setSelected(true);
                Cell[53].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 53;
            }
        });

        Cell[54].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[54].setSelected(true);
                Cell[54].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 54;
            }
        });

        Cell[55].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[55].setSelected(true);
                Cell[55].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 55;
            }
        });

        Cell[56].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[56].setSelected(true);
                Cell[56].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 56;
            }
        });

        Cell[57].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[57].setSelected(true);
                Cell[57].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 57;
            }
        });

        Cell[58].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[58].setSelected(true);
                Cell[58].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 58;
            }
        });

        Cell[59].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[59].setSelected(true);
                Cell[59].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 59;
            }
        });

        Cell[60].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[60].setSelected(true);
                Cell[60].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 60;
            }
        });

        Cell[61].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[61].setSelected(true);
                Cell[61].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 61;
            }
        });

        Cell[62].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[62].setSelected(true);
                Cell[62].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 62;
            }
        });

        Cell[63].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[63].setSelected(true);
                Cell[63].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 63;
            }
        });

        Cell[64].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[64].setSelected(true);
                Cell[64].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 64;
            }
        });

        Cell[65].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[65].setSelected(true);
                Cell[65].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 65;
            }
        });

        Cell[66].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[66].setSelected(true);
                Cell[66].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 66;
            }
        });

        Cell[67].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[67].setSelected(true);
                Cell[67].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 67;
            }
        });

        Cell[68].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[68].setSelected(true);
                Cell[68].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 68;
            }
        });

        Cell[69].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[69].setSelected(true);
                Cell[69].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 69;
            }
        });

        Cell[70].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[70].setSelected(true);
                Cell[70].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 70;
            }
        });

        Cell[71].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[71].setSelected(true);
                Cell[71].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 71;
            }
        });

        Cell[72].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[72].setSelected(true);
                Cell[72].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 72;
            }
        });

        Cell[73].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[73].setSelected(true);
                Cell[73].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 73;
            }
        });

        Cell[74].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[74].setSelected(true);
                Cell[74].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 74;
            }
        });

        Cell[75].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[75].setSelected(true);
                Cell[75].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 75;
            }
        });

        Cell[76].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[76].setSelected(true);
                Cell[76].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 76;
            }
        });

        Cell[77].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[77].setSelected(true);
                Cell[77].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 77;
            }
        });

        Cell[78].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[78].setSelected(true);
                Cell[78].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 78;
            }
        });

        Cell[79].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[79].setSelected(true);
                Cell[79].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 79;
            }
        });

        Cell[80].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[80].setSelected(true);
                Cell[80].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 80;
            }
        });


    }

    public void ClearSelected(){
        for(int i = 0; i < 81; ++i) {
            Cell[i].setSelected(false);
            Cell[i].setBackgroundColor(getResources().getColor(R.color.colorPrimBack));
        }
    }




}
