package com.example.supersudoku;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, modeBtn, nextBtn, exitBtn;
    TextView[] Cell;
    ConstraintLayout tableLayout;
    int cellSelector;
    int buttonSelector;
    boolean inputMode;
    float zoomFact;
    ScaleGestureDetector zoomDetect;

    //board 33 from book

    int[] board1 = {0, 0, 5, 0, 7, 0, 8, 0, 4,
            0, 0, 8, 0, 4, 1, 3, 6, 0,
            0, 0, 0, 2, 0, 0, 0, 7, 0,
            2, 0, 0, 0, 0, 6, 1, 0, 0,
            6, 0, 0, 3, 2, 9, 0, 0, 7,
            0, 0, 4, 5, 0, 0, 0, 0, 2,
            0, 3, 0, 0, 0, 4, 0, 0, 0,
            0, 1, 9, 7, 6, 0, 5, 0, 0,
            4, 0, 7, 0, 3, 0, 2, 0, 0};


    // board 34 from book
    int[] board2 =
            {2, 5, 3, 0, 4, 0, 0, 0, 0,
                    7, 1, 0, 0, 0, 8, 2, 4, 0,
                    0, 0, 0, 0, 2, 0, 0, 3, 6,
                    0, 0, 0, 8, 0, 0, 4, 0, 7,
                    0, 0, 2, 4, 3, 1, 9, 0, 0,
                    4, 0, 8, 0, 0, 6, 0, 0, 0,
                    9, 2, 0, 0, 8, 0, 0, 0, 0,
                    0, 3, 7, 5, 0, 0, 0, 9, 2,
                    0, 0, 0, 0, 1, 0, 3, 7, 5};

    // #35 from book
    int[] board3 =
            {0, 9, 0, 0, 0, 6, 0, 0, 0,
                    8, 0, 1, 3, 2, 4, 0, 0, 0,
                    5, 0, 0, 0, 0, 0, 2, 6, 0,
                    0, 0, 9, 8, 7, 0, 4, 0, 1,
                    3, 1, 0, 0, 6, 0, 0, 9, 7,
                    7, 0, 4, 0, 3, 9, 5, 0, 0,
                    0, 5, 2, 0, 0, 0, 0, 0, 3,
                    0, 0, 0, 7, 5, 1, 9, 0, 2,
                    0, 0, 0, 2, 0, 0, 0, 4, 5 };

    int[] board4 = {0,0,9,0,0,0,0,2,0,
            0,0,4,0,0,0,8,0,0,
            8,1,0,0,7,4,0,0,5,
            0,5,0,0,0,7,6,0,0,
            0,4,0,5,0,0,0,7,2,
            0,9,0,0,0,0,0,5,0,
            0,0,0,0,1,0,0,0,8,
            0,0,2,0,0,0,9,6,0,
            0,8,0,0,0,2,0,4,0};

    int[] board5 = {0,0,0,0,0,0,0,7,0,
            3,0,0,5,0,0,0,0,0,
            5,0,0,9,3,0,0,0,0,
            0,7,0,0,4,9,0,0,0,
            2,0,0,3,0,0,0,0,0,
            1,9,0,2,0,7,0,8,0,
            0,0,0,0,0,3,7,0,0,
            0,0,0,0,0,2,0,0,1,
            0,4,0,1,9,0,5,0,0};
    int[] board6 = {0,5,4,0,0,0,2,0,0,
            0,0,0,5,0,0,0,0,0,
            0,0,0,0,7,0,3,0,0,
            6,0,8,0,0,0,0,0,0,
            0,7,0,0,0,3,0,0,0,
            9,4,0,0,0,2,8,0,6,
            7,3,0,0,0,0,4,0,0,
            8,0,0,0,0,6,0,0,3,
            0,2,6,0,0,7,0,8,0};
    int[] board7 ={0,0,0,0,5,0,0,0,0,
            0,0,0,0,0,8,0,2,0,
            0,4,3,0,2,6,0,7,0,
            0,2,6,9,0,0,0,0,0,
            0,0,0,0,3,0,9,0,0,
            9,0,0,0,7,2,0,0,0,
            0,8,0,4,0,0,7,6,0,
            4,9,0,0,0,0,0,0,3,
            5,0,0,0,0,0,0,0,0};
    int[] board8 = {1,0,9,0,0,0,0,3,6,
            2,0,4,0,0,0,1,0,0,
            0,0,0,5,0,0,0,4,2,
            0,6,0,0,0,0,5,2,0,
            7,0,0,0,0,0,0,0,3,
            0,0,3,0,4,0,0,9,7,
            0,0,0,7,0,0,9,0,0,
            9,0,1,0,0,0,0,0,0,
            0,0,0,0,2,1,0,0,4};
    int[] board9 = {0,0,7,0,0,0,3,0,1,
            5,0,0,3,0,0,2,9,0,
            0,0,6,5,0,2,0,0,0,
            0,5,9,0,0,0,0,2,0,
            0,0,0,0,0,1,0,0,0,
            0,0,0,0,3,7,0,0,0,
            0,0,8,0,0,0,0,0,0,
            9,3,1,6,0,0,0,0,0,
            0,6,0,1,2,0,0,0,0};
    int[] board10 = {0,0,0,0,0,0,0,0,0,
            2,0,8,0,9,0,0,3,7,
            0,0,0,1,7,0,4,6,2,
            0,0,0,7,8,0,0,9,6,
            9,0,0,0,0,1,0,0,0,
            0,0,0,0,3,0,0,0,0,
            0,0,9,0,0,0,0,0,0,
            0,8,0,4,0,0,1,7,0,
            0,0,4,0,2,9,0,0,0};
    int[] board11 = {0,5,0,0,6,0,0,0,0,
            0,0,0,0,0,0,8,0,0,
            0,0,0,2,0,0,0,0,0,
            0,8,0,6,3,0,7,0,9,
            0,2,0,0,0,9,3,6,0,
            0,0,0,0,0,0,2,0,0,
            8,9,0,4,5,0,6,0,7,
            0,4,0,0,0,0,0,0,5,
            0,0,2,0,0,7,0,4,8};
    int[] board12 = {0,0,0,0,1,9,0,0,0,
            9,0,2,0,3,0,0,6,0,
            0,8,0,0,6,0,0,0,0,
            1,0,0,0,0,5,0,0,0,
            0,0,0,8,0,0,0,0,0,
            0,0,0,0,7,1,9,5,0,
            3,0,0,6,8,0,0,7,9,
            5,0,0,0,9,7,1,2,0,
            0,0,0,0,0,0,0,0,0};
    int[] board13 = {0,0,5,0,3,4,0,0,0,
            7,0,0,0,6,0,0,0,0,
            0,0,0,7,5,0,1,8,0,
            0,0,3,5,0,0,6,7,0,
            0,0,6,0,0,0,4,0,8,
            4,0,7,0,0,0,0,0,9,
            3,0,0,0,0,9,8,5,1,
            0,0,0,0,0,0,0,9,0,
            9,0,8,0,0,0,7,0,3};
    int[] board14 = {0,0,0,1,5,0,0,6,3,
            0,0,3,0,6,2,0,0,8,
            0,0,6,0,0,9,5,1,0,
            3,0,2,0,0,0,0,0,0,
            7,0,0,0,0,0,3,9,0,
            0,0,0,0,0,0,0,0,6,
            0,2,5,0,3,0,7,0,0,
            1,0,0,0,2,0,0,0,0,
            9,0,0,0,0,0,1,3,0};
    int[] board15 = {0,0,3,4,0,8,0,0,0,
            0,2,4,1,0,0,6,0,0,
            7,0,0,0,0,0,0,0,0,
            0,5,0,0,7,0,0,6,0,
            3,0,7,0,0,4,0,5,1,
            1,0,0,0,2,0,0,3,0,
            4,0,0,0,5,0,0,0,3,
            0,0,0,0,0,6,0,0,0,
            2,0,0,0,4,0,0,0,0};
    int[] board16 = {0,2,1,0,0,0,0,0,0,
            5,0,0,0,0,0,0,0,0,
            3,0,6,0,1,8,0,0,4,
            0,0,0,7,0,0,0,0,0,
            6,0,0,4,3,1,0,0,5,
            0,0,7,0,0,6,3,0,0,
            4,8,0,0,0,0,1,0,0,
            0,0,0,0,6,0,0,7,0,
            0,0,5,0,0,4,0,0,8};
    Board board;

    View victory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        zoomFact = 1.0f;
        zoomDetect = new ScaleGestureDetector(this, new ZoomListener());


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int game = extras.getInt("game");

        cellSelector = 0;
        buttonSelector = 0;
        inputMode = false;
        tableLayout = findViewById(R.id.tableLayout);
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
        switch(game){
            case 0:
                board = new Board(board1);
                break;
            case 1:
                board = new Board(board2);
                break;
            case 2:
                board = new Board(board3);
                break;
            case 3:
                board = new Board(board4);
                break;
            case 4:
                board = new Board(board5);
                break;
            case 5:
                board = new Board(board6);
                break;
            case 6:
                board = new Board(board7);
                break;
            case 7:
                board = new Board(board8);
                break;
            case 8:
                board = new Board(board9);
                break;
            case 9:
                board = new Board(board10);
                break;
            case 10:
                board = new Board(board11);
                break;
            case 11:
                board = new Board(board12);
                break;
            case 12:
                board = new Board(board13);
                break;
            case 13:
                board = new Board(board14);
                break;
            case 14:
                board = new Board(board15);
                break;
            case 15:
            default:
                board = new Board(board16);
        }

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
                intent.putExtra("game", (game + 1) % 16);
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
        for(int i = 0; i < 80; ++i) {
            final int j = i;
            Cell[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClearSelected();
                    Cell[j].setSelected(true);
                    Cell[j].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                    cellSelector = j;
                    if (inputMode) {
                        board.setSquare(j, buttonSelector);
                        Cell[j].setText(Character.toString(board.getSquare(j)));
                        CheckConflict(j);
                    }
                }
            });
        }



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

    public boolean onTouchEvent(MotionEvent event){
        zoomDetect.onTouchEvent(event);
        return true;
    }

    private class ZoomListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            zoomFact *= scaleGestureDetector.getScaleFactor();
            zoomFact = Math.max(0.6f, Math.min(zoomFact, 10.0f));
            tableLayout.setScaleX(zoomFact);
            tableLayout.setScaleY(zoomFact);
            return true;
        }
    }
}
