package com.example.supersudoku;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

public class GameActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, modeBtn, nextBtn, exitBtn;
    TextView[] Cell;
    int cellSelector;
    int buttonSelector;
    boolean inputMode;

    //board 33 from book
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

    View victory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int game = extras.getInt("game");
        final int difficulty = extras.getInt("difficulty");

        cellSelector = -1;
        buttonSelector = 0;
        inputMode = false;
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
        modeBtn = (Button)findViewById(R.id.keypadmodebutton);
        nextBtn = (Button)findViewById(R.id.Next);
        exitBtn = (Button)findViewById(R.id.Exit);
        victory = findViewById(R.id.Victory);

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
        if(game == 0)
            board = new Board(9, workingBoard, solvedBoard);
        else if(game == 1)
            board = new Board(9,workingBoard2, solvedBoard2);
        else
            board = new Board(9, workingBoard3, solvedBoard3);

        for(int i = 0; i < 81; ++i){
            Cell[i].setText(Character.toString(board.getSquare(i)));
        }

        //set background colors
        ClearSelected();
        ClearSelectedBtn();
        modeBtn.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                intent.putExtra("game", (game + 1) % 3);
                intent.putExtra("difficulty", difficulty);
                startActivity(intent);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //setup button listeners
        modeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(inputMode) {
                    modeBtn.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
                    inputMode = false;
                }
                else {
                    modeBtn.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                    inputMode = true;
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(inputMode){
                    buttonSelector = 1;
                    btn1.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                }
                else {
                    board.setSquare(cellSelector, 1);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                    CheckConflict(cellSelector);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(inputMode){
                    buttonSelector = 2;
                    btn2.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                }
                else {
                    board.setSquare(cellSelector, 2);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                    CheckConflict(cellSelector);
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(inputMode){
                    buttonSelector = 3;
                    btn3.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                }
                else {
                    board.setSquare(cellSelector, 3);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                    CheckConflict(cellSelector);
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(inputMode){
                    buttonSelector = 4;
                    btn4.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                }
                else {
                    board.setSquare(cellSelector, 4);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                    CheckConflict(cellSelector);
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(inputMode){
                    buttonSelector = 5;
                    btn5.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                }
                else {
                    board.setSquare(cellSelector, 5);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                    CheckConflict(cellSelector);
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(inputMode){
                    buttonSelector = 6;
                    btn6.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                }
                else {
                    board.setSquare(cellSelector, 6);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                    CheckConflict(cellSelector);
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(inputMode){
                    buttonSelector = 7;
                    btn7.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                }
                else {
                    board.setSquare(cellSelector, 7);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                    CheckConflict(cellSelector);
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(inputMode){
                    buttonSelector = 8;
                    btn8.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                }
                else {
                    board.setSquare(cellSelector, 8);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                    CheckConflict(cellSelector);
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(inputMode){
                    buttonSelector = 9;
                    btn9.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                }
                else {
                    board.setSquare(cellSelector, 9);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                    CheckConflict(cellSelector);
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
                if(inputMode){
                    board.setSquare(0,buttonSelector);
                    Cell[0].setText(Character.toString(board.getSquare(0)));
                    CheckConflict(0);
                }
            }
        });

        Cell[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[1].setSelected(true);
                Cell[1].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 1;
                if(inputMode){
                    board.setSquare(1,buttonSelector);
                    Cell[1].setText(Character.toString(board.getSquare(1)));
                    CheckConflict(1);
                }
            }
        });

        Cell[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[2].setSelected(true);
                Cell[2].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 2;
                if(inputMode){
                    board.setSquare(2,buttonSelector);
                    Cell[2].setText(Character.toString(board.getSquare(2)));
                    CheckConflict(2);
                }
            }
        });

        Cell[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[3].setSelected(true);
                Cell[3].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 3;
                if(inputMode){
                    board.setSquare(3,buttonSelector);
                    Cell[3].setText(Character.toString(board.getSquare(3)));
                    CheckConflict(3);
                }
            }
        });

        Cell[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[4].setSelected(true);
                Cell[4].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 4;
                if(inputMode){
                    board.setSquare(4,buttonSelector);
                    Cell[4].setText(Character.toString(board.getSquare(4)));
                    CheckConflict(4);
                }
            }
        });

        Cell[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[5].setSelected(true);
                Cell[5].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 5;
                if(inputMode){
                    board.setSquare(5,buttonSelector);
                    Cell[5].setText(Character.toString(board.getSquare(5)));
                    CheckConflict(5);
                }
            }
        });

        Cell[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[6].setSelected(true);
                Cell[6].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 6;
                if(inputMode){
                    board.setSquare(6,buttonSelector);
                    Cell[6].setText(Character.toString(board.getSquare(6)));
                    CheckConflict(6);
                }
            }
        });

        Cell[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[7].setSelected(true);
                Cell[7].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 7;
                if(inputMode){
                    board.setSquare(7,buttonSelector);
                    Cell[7].setText(Character.toString(board.getSquare(7)));
                    CheckConflict(7);
                }
            }
        });

        Cell[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[8].setSelected(true);
                Cell[8].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 8;
                if(inputMode){
                    board.setSquare(8,buttonSelector);
                    Cell[8].setText(Character.toString(board.getSquare(8)));
                    CheckConflict(8);
                }
            }
        });

        Cell[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[9].setSelected(true);
                Cell[9].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 9;
                if(inputMode){
                    board.setSquare(9,buttonSelector);
                    Cell[9].setText(Character.toString(board.getSquare(9)));
                    CheckConflict(9);
                }
            }
        });

        Cell[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[10].setSelected(true);
                Cell[10].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 10;
                if(inputMode){
                    board.setSquare(10,buttonSelector);
                    Cell[10].setText(Character.toString(board.getSquare(10)));
                    CheckConflict(10);
                }
            }
        });

        Cell[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[11].setSelected(true);
                Cell[11].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 11;
                if(inputMode){
                    board.setSquare(11,buttonSelector);
                    Cell[11].setText(Character.toString(board.getSquare(11)));
                    CheckConflict(11);
                }
            }
        });

        Cell[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[12].setSelected(true);
                Cell[12].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 12;
                if(inputMode){
                    board.setSquare(12,buttonSelector);
                    Cell[12].setText(Character.toString(board.getSquare(12)));
                    CheckConflict(12);
                }
            }
        });

        Cell[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[13].setSelected(true);
                Cell[13].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 13;
                if(inputMode){
                    board.setSquare(13,buttonSelector);
                    Cell[13].setText(Character.toString(board.getSquare(13)));
                    CheckConflict(13);
                }
            }
        });

        Cell[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[14].setSelected(true);
                Cell[14].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 14;
                if(inputMode){
                    board.setSquare(14,buttonSelector);
                    Cell[14].setText(Character.toString(board.getSquare(14)));
                    CheckConflict(14);
                }
            }
        });

        Cell[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[15].setSelected(true);
                Cell[15].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 15;
                if(inputMode){
                    board.setSquare(15,buttonSelector);
                    Cell[15].setText(Character.toString(board.getSquare(15)));
                    CheckConflict(15);
                }
            }
        });

        Cell[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[16].setSelected(true);
                Cell[16].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 16;
                if(inputMode){
                    board.setSquare(16,buttonSelector);
                    Cell[16].setText(Character.toString(board.getSquare(16)));
                    CheckConflict(16);
                }
            }
        });

        Cell[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[17].setSelected(true);
                Cell[17].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 17;
                if(inputMode){
                    board.setSquare(17,buttonSelector);
                    Cell[17].setText(Character.toString(board.getSquare(17)));
                    CheckConflict(17);
                }
            }
        });

        Cell[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[18].setSelected(true);
                Cell[18].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 18;
                if(inputMode){
                    board.setSquare(18,buttonSelector);
                    Cell[18].setText(Character.toString(board.getSquare(18)));
                    CheckConflict(18);
                }
            }
        });

        Cell[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[19].setSelected(true);
                Cell[19].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 19;
                if(inputMode){
                    board.setSquare(19,buttonSelector);
                    Cell[19].setText(Character.toString(board.getSquare(19)));
                    CheckConflict(19);
                }
            }
        });

        Cell[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[20].setSelected(true);
                Cell[20].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 20;
                if(inputMode){
                    board.setSquare(20,buttonSelector);
                    Cell[20].setText(Character.toString(board.getSquare(20)));
                    CheckConflict(20);
                }
            }
        });

        Cell[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[21].setSelected(true);
                Cell[21].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 21;
                if(inputMode){
                    board.setSquare(21,buttonSelector);
                    Cell[21].setText(Character.toString(board.getSquare(21)));
                    CheckConflict(21);
                }
            }
        });

        Cell[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[22].setSelected(true);
                Cell[22].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 22;
                if(inputMode){
                    board.setSquare(22,buttonSelector);
                    Cell[22].setText(Character.toString(board.getSquare(22)));
                    CheckConflict(22);
                }
            }
        });

        Cell[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[23].setSelected(true);
                Cell[23].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 23;
                if(inputMode){
                    board.setSquare(23,buttonSelector);
                    Cell[23].setText(Character.toString(board.getSquare(23)));
                    CheckConflict(23);
                }
            }
        });

        Cell[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[24].setSelected(true);
                Cell[24].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 24;
                if(inputMode){
                    board.setSquare(24,buttonSelector);
                    Cell[24].setText(Character.toString(board.getSquare(24)));
                    CheckConflict(24);
                }
            }
        });

        Cell[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[25].setSelected(true);
                Cell[25].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 25;
                if(inputMode){
                    board.setSquare(25,buttonSelector);
                    Cell[25].setText(Character.toString(board.getSquare(25)));
                    CheckConflict(25);
                }
            }
        });

        Cell[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[26].setSelected(true);
                Cell[26].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 26;
                if(inputMode){
                    board.setSquare(26,buttonSelector);
                    Cell[26].setText(Character.toString(board.getSquare(26)));
                    CheckConflict(26);
                }
            }
        });

        Cell[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[27].setSelected(true);
                Cell[27].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 27;
                if(inputMode){
                    board.setSquare(27,buttonSelector);
                    Cell[27].setText(Character.toString(board.getSquare(27)));
                    CheckConflict(27);
                }
            }
        });

        Cell[28].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[28].setSelected(true);
                Cell[28].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 28;
                if(inputMode){
                    board.setSquare(28,buttonSelector);
                    Cell[28].setText(Character.toString(board.getSquare(28)));
                    CheckConflict(28);
                }
            }
        });

        Cell[29].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[29].setSelected(true);
                Cell[29].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 29;
                if(inputMode){
                    board.setSquare(29,buttonSelector);
                    Cell[29].setText(Character.toString(board.getSquare(29)));
                    CheckConflict(29);
                }
            }
        });

        Cell[30].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[30].setSelected(true);
                Cell[30].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 30;
                if(inputMode){
                    board.setSquare(30,buttonSelector);
                    Cell[30].setText(Character.toString(board.getSquare(30)));
                    CheckConflict(30);
                }
            }
        });

        Cell[31].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[31].setSelected(true);
                Cell[31].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 31;
                if(inputMode){
                    board.setSquare(31,buttonSelector);
                    Cell[31].setText(Character.toString(board.getSquare(31)));
                    CheckConflict(31);
                }
            }
        });

        Cell[32].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[32].setSelected(true);
                Cell[32].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 32;
                if(inputMode){
                    board.setSquare(32,buttonSelector);
                    Cell[32].setText(Character.toString(board.getSquare(32)));
                    CheckConflict(32);
                }
            }
        });

        Cell[33].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[33].setSelected(true);
                Cell[33].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 33;
                if(inputMode){
                    board.setSquare(33,buttonSelector);
                    Cell[33].setText(Character.toString(board.getSquare(33)));
                    CheckConflict(33);
                }
            }
        });

        Cell[34].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[34].setSelected(true);
                Cell[34].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 34;
                if(inputMode){
                    board.setSquare(34,buttonSelector);
                    Cell[34].setText(Character.toString(board.getSquare(34)));
                    CheckConflict(34);
                }
            }
        });

        Cell[35].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[35].setSelected(true);
                Cell[35].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 35;
                if(inputMode){
                    board.setSquare(35,buttonSelector);
                    Cell[35].setText(Character.toString(board.getSquare(35)));
                    CheckConflict(35);
                }
            }
        });

        Cell[36].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[36].setSelected(true);
                Cell[36].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 36;
                if(inputMode){
                    board.setSquare(36,buttonSelector);
                    Cell[36].setText(Character.toString(board.getSquare(36)));
                    CheckConflict(36);
                }
            }
        });

        Cell[37].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[37].setSelected(true);
                Cell[37].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 37;
                if(inputMode){
                    board.setSquare(37,buttonSelector);
                    Cell[37].setText(Character.toString(board.getSquare(37)));
                    CheckConflict(37);
                }
            }
        });

        Cell[38].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[38].setSelected(true);
                Cell[38].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 38;
                if(inputMode){
                    board.setSquare(38,buttonSelector);
                    Cell[38].setText(Character.toString(board.getSquare(38)));
                    CheckConflict(38);
                }
            }
        });

        Cell[39].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[39].setSelected(true);
                Cell[39].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 39;
                if(inputMode){
                    board.setSquare(39,buttonSelector);
                    Cell[39].setText(Character.toString(board.getSquare(39)));
                    CheckConflict(39);
                }
            }
        });

        Cell[40].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[40].setSelected(true);
                Cell[40].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 40;
                if(inputMode){
                    board.setSquare(40,buttonSelector);
                    Cell[40].setText(Character.toString(board.getSquare(40)));
                    CheckConflict(40);
                }
            }
        });

        Cell[41].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[41].setSelected(true);
                Cell[41].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 41;
                if(inputMode){
                    board.setSquare(41,buttonSelector);
                    Cell[41].setText(Character.toString(board.getSquare(41)));
                    CheckConflict(41);
                }
            }
        });

        Cell[42].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[42].setSelected(true);
                Cell[42].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 42;
                if(inputMode){
                    board.setSquare(42,buttonSelector);
                    Cell[42].setText(Character.toString(board.getSquare(42)));
                    CheckConflict(42);
                }
            }
        });

        Cell[43].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[43].setSelected(true);
                Cell[43].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 43;
                if(inputMode){
                    board.setSquare(43,buttonSelector);
                    Cell[43].setText(Character.toString(board.getSquare(43)));
                    CheckConflict(43);
                }
            }
        });

        Cell[44].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[44].setSelected(true);
                Cell[44].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 44;
                if(inputMode){
                    board.setSquare(44,buttonSelector);
                    Cell[44].setText(Character.toString(board.getSquare(44)));
                    CheckConflict(44);
                }
            }
        });

        Cell[45].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[45].setSelected(true);
                Cell[45].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 45;
                if(inputMode){
                    board.setSquare(45,buttonSelector);
                    Cell[45].setText(Character.toString(board.getSquare(45)));
                    CheckConflict(45);
                }
            }
        });

        Cell[46].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[46].setSelected(true);
                Cell[46].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 46;
                if(inputMode){
                    board.setSquare(46,buttonSelector);
                    Cell[46].setText(Character.toString(board.getSquare(46)));
                    CheckConflict(46);
                }
            }
        });

        Cell[47].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[47].setSelected(true);
                Cell[47].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 47;
                if(inputMode){
                    board.setSquare(47,buttonSelector);
                    Cell[47].setText(Character.toString(board.getSquare(47)));
                    CheckConflict(47);
                }
            }
        });

        Cell[48].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[48].setSelected(true);
                Cell[48].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 48;
                if(inputMode){
                    board.setSquare(48,buttonSelector);
                    Cell[48].setText(Character.toString(board.getSquare(48)));
                    CheckConflict(48);
                }
            }
        });

        Cell[49].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[49].setSelected(true);
                Cell[49].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 49;
                if(inputMode){
                    board.setSquare(49,buttonSelector);
                    Cell[49].setText(Character.toString(board.getSquare(49)));
                    CheckConflict(49);
                }
            }
        });

        Cell[50].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[50].setSelected(true);
                Cell[50].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 50;
                if(inputMode){
                    board.setSquare(50,buttonSelector);
                    Cell[50].setText(Character.toString(board.getSquare(50)));
                    CheckConflict(50);
                }
            }
        });

        Cell[51].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[51].setSelected(true);
                Cell[51].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 51;
                if(inputMode){
                    board.setSquare(51,buttonSelector);
                    Cell[51].setText(Character.toString(board.getSquare(51)));
                    CheckConflict(51);
                }
            }
        });

        Cell[52].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[52].setSelected(true);
                Cell[52].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 52;
                if(inputMode){
                    board.setSquare(52,buttonSelector);
                    Cell[52].setText(Character.toString(board.getSquare(52)));
                    CheckConflict(52);
                }
            }
        });

        Cell[53].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[53].setSelected(true);
                Cell[53].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 53;
                if(inputMode){
                    board.setSquare(53,buttonSelector);
                    Cell[53].setText(Character.toString(board.getSquare(53)));
                    CheckConflict(53);
                }
            }
        });

        Cell[54].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[54].setSelected(true);
                Cell[54].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 54;
                if(inputMode){
                    board.setSquare(54,buttonSelector);
                    Cell[54].setText(Character.toString(board.getSquare(54)));
                    CheckConflict(54);
                }
            }
        });

        Cell[55].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[55].setSelected(true);
                Cell[55].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 55;
                if(inputMode){
                    board.setSquare(55,buttonSelector);
                    Cell[55].setText(Character.toString(board.getSquare(55)));
                    CheckConflict(55);
                }
            }
        });

        Cell[56].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[56].setSelected(true);
                Cell[56].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 56;
                if(inputMode){
                    board.setSquare(56,buttonSelector);
                    Cell[56].setText(Character.toString(board.getSquare(56)));
                    CheckConflict(56);
                }
            }
        });

        Cell[57].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[57].setSelected(true);
                Cell[57].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 57;
                if(inputMode){
                    board.setSquare(57,buttonSelector);
                    Cell[57].setText(Character.toString(board.getSquare(57)));
                    CheckConflict(57);
                }
            }
        });

        Cell[58].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[58].setSelected(true);
                Cell[58].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 58;
                if(inputMode){
                    board.setSquare(58,buttonSelector);
                    Cell[58].setText(Character.toString(board.getSquare(58)));
                    CheckConflict(58);
                }
            }
        });

        Cell[59].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[59].setSelected(true);
                Cell[59].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 59;
                if(inputMode){
                    board.setSquare(59,buttonSelector);
                    Cell[59].setText(Character.toString(board.getSquare(59)));
                    CheckConflict(59);
                }
            }
        });

        Cell[60].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[60].setSelected(true);
                Cell[60].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 60;
                if(inputMode){
                    board.setSquare(60,buttonSelector);
                    Cell[60].setText(Character.toString(board.getSquare(60)));
                    CheckConflict(60);
                }
            }
        });

        Cell[61].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[61].setSelected(true);
                Cell[61].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 61;
                if(inputMode){
                    board.setSquare(61,buttonSelector);
                    Cell[61].setText(Character.toString(board.getSquare(61)));
                    CheckConflict(61);
                }
            }
        });

        Cell[62].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[62].setSelected(true);
                Cell[62].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 62;
                if(inputMode){
                    board.setSquare(62,buttonSelector);
                    Cell[62].setText(Character.toString(board.getSquare(62)));
                    CheckConflict(62);
                }
            }
        });

        Cell[63].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[63].setSelected(true);
                Cell[63].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 63;
                if(inputMode){
                    board.setSquare(63,buttonSelector);
                    Cell[63].setText(Character.toString(board.getSquare(63)));
                    CheckConflict(63);
                }
            }
        });

        Cell[64].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[64].setSelected(true);
                Cell[64].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 64;
                if(inputMode){
                    board.setSquare(64,buttonSelector);
                    Cell[64].setText(Character.toString(board.getSquare(64)));
                    CheckConflict(64);
                }
            }
        });

        Cell[65].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[65].setSelected(true);
                Cell[65].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 65;
                if(inputMode){
                    board.setSquare(65,buttonSelector);
                    Cell[65].setText(Character.toString(board.getSquare(65)));
                    CheckConflict(65);
                }
            }
        });

        Cell[66].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[66].setSelected(true);
                Cell[66].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 66;
                if(inputMode){
                    board.setSquare(66,buttonSelector);
                    Cell[66].setText(Character.toString(board.getSquare(66)));
                    CheckConflict(66);
                }
            }
        });

        Cell[67].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[67].setSelected(true);
                Cell[67].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 67;
                if(inputMode){
                    board.setSquare(67,buttonSelector);
                    Cell[67].setText(Character.toString(board.getSquare(67)));
                    CheckConflict(67);
                }
            }
        });

        Cell[68].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[68].setSelected(true);
                Cell[68].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 68;
                if(inputMode){
                    board.setSquare(68,buttonSelector);
                    Cell[68].setText(Character.toString(board.getSquare(68)));
                    CheckConflict(68);
                }
            }
        });

        Cell[69].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[69].setSelected(true);
                Cell[69].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 69;
                if(inputMode){
                    board.setSquare(69,buttonSelector);
                    Cell[69].setText(Character.toString(board.getSquare(69)));
                    CheckConflict(69);
                }
            }
        });

        Cell[70].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[70].setSelected(true);
                Cell[70].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 70;
                if(inputMode){
                    board.setSquare(70,buttonSelector);
                    Cell[70].setText(Character.toString(board.getSquare(70)));
                    CheckConflict(70);
                }
            }
        });

        Cell[71].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[71].setSelected(true);
                Cell[71].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 71;
                if(inputMode){
                    board.setSquare(71,buttonSelector);
                    Cell[71].setText(Character.toString(board.getSquare(71)));
                    CheckConflict(71);
                }
            }
        });

        Cell[72].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[72].setSelected(true);
                Cell[72].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 72;
                if(inputMode){
                    board.setSquare(72,buttonSelector);
                    Cell[72].setText(Character.toString(board.getSquare(72)));
                    CheckConflict(72);
                }
            }
        });

        Cell[73].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[73].setSelected(true);
                Cell[73].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 73;
                if(inputMode){
                    board.setSquare(73,buttonSelector);
                    Cell[73].setText(Character.toString(board.getSquare(73)));
                    CheckConflict(73);
                }
            }
        });

        Cell[74].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[74].setSelected(true);
                Cell[74].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 74;
                if(inputMode){
                    board.setSquare(74,buttonSelector);
                    Cell[74].setText(Character.toString(board.getSquare(74)));
                    CheckConflict(74);
                }
            }
        });

        Cell[75].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[75].setSelected(true);
                Cell[75].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 75;
                if(inputMode){
                    board.setSquare(75,buttonSelector);
                    Cell[75].setText(Character.toString(board.getSquare(75)));
                    CheckConflict(75);
                }
            }
        });

        Cell[76].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[76].setSelected(true);
                Cell[76].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 76;
                if(inputMode){
                    board.setSquare(76,buttonSelector);
                    Cell[76].setText(Character.toString(board.getSquare(76)));
                    CheckConflict(76);
                }
            }
        });

        Cell[77].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[77].setSelected(true);
                Cell[77].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 77;
                if(inputMode){
                    board.setSquare(77,buttonSelector);
                    Cell[77].setText(Character.toString(board.getSquare(77)));
                    CheckConflict(77);
                }
            }
        });

        Cell[78].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[78].setSelected(true);
                Cell[78].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 78;
                if(inputMode){
                    board.setSquare(78,buttonSelector);
                    Cell[78].setText(Character.toString(board.getSquare(78)));
                    CheckConflict(78);
                }
            }
        });

        Cell[79].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[79].setSelected(true);
                Cell[79].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 79;
                if(inputMode){
                    board.setSquare(79,buttonSelector);
                    Cell[79].setText(Character.toString(board.getSquare(79)));
                    CheckConflict(79);
                }
            }
        });

        Cell[80].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelected();
                Cell[80].setSelected(true);
                Cell[80].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                cellSelector = 80;
                if(inputMode){
                    board.setSquare(80,buttonSelector);
                    Cell[80].setText(Character.toString(board.getSquare(80)));
                    CheckConflict(80);
                }
            }
        });


    }

    public void ClearSelected(){
        for(int i = 0; i < 81; ++i) {
            Cell[i].setSelected(false);
            Cell[i].setBackgroundColor(getResources().getColor(R.color.colorPrimBack));
        }
    }

    public void ClearSelectedBtn(){
        btn1.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        btn2.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        btn3.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        btn4.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        btn5.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        btn6.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        btn7.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        btn8.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        btn9.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
    }

    public void CheckConflict(int index){
        ClearSelected();
        if(board.rowConflict(index)){
            int row = index / 9;
            for(int i = 0; i < 9; ++i){
                Cell[row * 9 + i].setBackgroundColor(getResources().getColor(R.color.colorConflict));
            }
        }
        if(board.colConflict(index)){
            int col = index % 9;
            for(int i = 0; i < 9; ++i){
                Cell[col + (i * 9)].setBackgroundColor(getResources().getColor(R.color.colorConflict));
            }

        }
        if(board.quadConflict(index)){
            int rowQuad = (index / 9) / 3;
            int colQuad = (index % 9) / 3;
            int dest = colQuad * 3 + (9 * (rowQuad * 3));
            for(int i = 0; i < 3; ++i){
                for(int j = 0; j < 3; ++j){
                    Cell[dest + j + (9 * i)].setBackgroundColor(getResources().getColor(R.color.colorConflict));
                }
            }
        }
        if(board.isValid(index))
            if(board.isSolved())
                victoryPop();
    }

    public void victoryPop(){
        victory.setVisibility(View.VISIBLE);
        victory.setClickable(true);
    }

}
