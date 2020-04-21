package com.example.supersudoku;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Layout;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MotionEventCompat;

public class GameActivity extends AppCompatActivity {
    //Interface variables
    Button modeBtn, nextBtn, exitBtn, smallBtn, eraserBtn;
    Button[] btn;
    TextView[] Cell;
    TextView[][] MiniCell;
    ConstraintLayout tableLayout;
    //Game function variables
    int cellSelector;
    int buttonSelector;
    boolean inputMode;
    boolean annotateMode;
    //Zoom/Pan function variables
    float zoomFact;
    float touchX;
    float touchY;
    float curOffsetX;
    float curOffsetY;
    float maxOffset;
    float tablePosX;
    float tablePosY;
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
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        Toolbar toolbar = findViewById(R.id.gametoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Super Sudoku");

        zoomFact = 1.0f;
        zoomDetect = new ScaleGestureDetector(this, new ZoomListener());
        curOffsetX = 0.0f;
        curOffsetY= 0.0f;

        //fetch intent data
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int game = extras.getInt("game");



        initializeInterface();
        initializeGameData(game);

        for(int i = 0; i < 81; ++i){
            Cell[i].setText(Character.toString(board.getSquare(i)));
            for(int c = 0; c < 9; ++c) {
                MiniCell[i][c].setText(Character.toString(board.getAnnotationValue(i, c)));
                MiniCell[i][c].setVisibility(View.INVISIBLE);
            }
        }


        //set background colors
        ClearSelected();
        ClearSelectedBtn();
        modeBtn.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        eraserBtn.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        smallBtn.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));

        //setup button listeners
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
        smallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if(annotateMode) {
                    smallBtn.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
                    annotateMode = false;
                }
                else {
                    smallBtn.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                    annotateMode = true;
                }
            }
        });
        eraserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearSelectedBtn();
                if (inputMode && !annotateMode) {
                    buttonSelector = 0;
                    eraserBtn.setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                } else{
                    board.setSquare(cellSelector, 0);
                    Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                    if(board.getSquare(cellSelector) == ' ')
                        for(int i = 0; i < 9; ++i) {
                            MiniCell[cellSelector][i].setVisibility(View.VISIBLE);
                            MiniCell[cellSelector][i].bringToFront();
                        }
                }
            }
        });

        for(int i = 1; i < 10; ++i) {
            final int j = i;
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClearSelectedBtn();
                    if (inputMode) {
                        buttonSelector = j;
                        btn[j].setBackgroundColor(getResources().getColor(R.color.colorBtnOn));
                    } else {
                        if(annotateMode){
                            board.setAnnotationValue(cellSelector, j);
                            if(board.getSquare(cellSelector) == ' ') {
                                MiniCell[cellSelector][j - 1].setText(Character.toString(board.getAnnotationValue(cellSelector, j)));
                                MiniCell[cellSelector][j - 1].setVisibility(View.VISIBLE);
                                MiniCell[cellSelector][j - 1].bringToFront();
                            }
                        }
                        else {
                            board.setSquare(cellSelector, j);
                            Cell[cellSelector].setText(Character.toString(board.getSquare(cellSelector)));
                            for(int c = 0; c < 9; ++c)
                                MiniCell[cellSelector][c].setVisibility(View.INVISIBLE);
                            CheckConflict(cellSelector);
                        }
                    }
                }
            });
        }



        //Setup Cell listeners
        for(int i = 0; i < 81; ++i) {
            final int j = i;
            Cell[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ClearSelected();
                    Cell[j].setSelected(true);
                    Cell[j].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                    for(int p = 0; p < 9; ++p)
                        MiniCell[j][p].setBackgroundColor(getResources().getColor(R.color.colorSecBack));
                    cellSelector = j;
                    if (inputMode) {
                        if(annotateMode && buttonSelector > 0){
                            board.setAnnotationValue(j,buttonSelector);
                            if(board.getSquare(j) == ' ') {
                                MiniCell[j][buttonSelector - 1].setText(Character.toString(board.getAnnotationValue(j, buttonSelector)));
                                MiniCell[j][buttonSelector - 1].setVisibility(View.VISIBLE);
                                MiniCell[j][buttonSelector - 1].bringToFront();
                            }
                        }
                        else {
                            board.setSquare(j, buttonSelector);
                            Cell[j].setText(Character.toString(board.getSquare(j)));
                            if(buttonSelector != 0){
                                for (int c = 0; c < 9; ++c)
                                    MiniCell[j][c].setVisibility(View.INVISIBLE);
                                CheckConflict(j);
                            }
                            else if(board.getSquare(j) == ' ')
                                for (int c = 0; c < 9; ++c) {
                                    MiniCell[j][c].setVisibility(View.VISIBLE);
                                    MiniCell[j][c].bringToFront();
                                }
                        }
                    }
                }
            });
        }
    }//End of OnCreate

    public void ClearSelected(){
        for(int i = 0; i < 81; ++i) {
            Cell[i].setSelected(false);
            Cell[i].setBackgroundColor(getResources().getColor(R.color.colorPrimBack));
            for(int c = 0; c < 9; ++c)
                MiniCell[i][c].setBackgroundColor(getResources().getColor(R.color.colorPrimBack));
        }
    }

    public void ClearSelectedBtn(){
        for(int i = 1; i < 10; ++i) {
            btn[i].setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
        }
        eraserBtn.setBackgroundColor(getResources().getColor(R.color.colorBtnOff));
    }

    public void CheckConflict(int index){
        ClearSelected();
        if(board.rowConflict(index)){
            int row = index / 9;
            for(int i = 0; i < 9; ++i){
                Cell[row * 9 + i].setBackgroundColor(getResources().getColor(R.color.colorConflict));
                for(int c = 0; c < 9; ++c)
                    MiniCell[row * 9 + i][c].setBackgroundColor(getResources().getColor(R.color.colorConflict));
            }
        }
        if(board.colConflict(index)){
            int col = index % 9;
            for(int i = 0; i < 9; ++i){
                Cell[col + (i * 9)].setBackgroundColor(getResources().getColor(R.color.colorConflict));
                for(int c = 0; c < 9; ++c)
                    MiniCell[col + (i * 9)][c].setBackgroundColor(getResources().getColor(R.color.colorConflict));
            }

        }
        if(board.quadConflict(index)){
            int rowQuad = (index / 9) / 3;
            int colQuad = (index % 9) / 3;
            int dest = colQuad * 3 + (9 * (rowQuad * 3));
            for(int i = 0; i < 3; ++i){
                for(int j = 0; j < 3; ++j){
                    Cell[dest + j + (9 * i)].setBackgroundColor(getResources().getColor(R.color.colorConflict));
                    for(int c = 0; c < 9; ++c)
                        MiniCell[dest + j + (9 * i)][c].setBackgroundColor(getResources().getColor(R.color.colorConflict));
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
        MotionEvent copy = MotionEvent.obtain(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touchX = event.getX();
            touchY = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            float distX = event.getX() - touchX;
            float distY = event.getY() - touchY;
            moveTable(distX, distY);
            touchX = event.getX();
            touchY = event.getY();

        }

        return zoomDetect.onTouchEvent(copy);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        MotionEvent copy = MotionEvent.obtain(event);
        super.dispatchTouchEvent(copy);
        return onTouchEvent(event);
    }

    private class ZoomListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            zoomFact *= scaleGestureDetector.getScaleFactor();
            zoomFact = Math.max(1.0f, Math.min(zoomFact, 3.0f));
            tableLayout.setScaleX(zoomFact);
            tableLayout.setScaleY(zoomFact);
            maxOffset =  600.0f * (zoomFact - 1.0f);
            return true;
        }

    }

    public void moveTable(float X, float Y){
        float curX = tableLayout.getX();
        float curY = tableLayout.getY();

        //code to move table in X direction
        if(curX + X >= tablePosX + maxOffset){
            tableLayout.setX(tablePosX + maxOffset);
        }
        else if(curX + X <= tablePosX - maxOffset){
            tableLayout.setX(tablePosX - maxOffset);
        }
        else{
            tableLayout.setX(curX + X);
        }

        //code to move table in Y direction
        if(curY + Y >= tablePosY + maxOffset){
            tableLayout.setY(tablePosY + maxOffset);
        }
        else if(curY + Y <= tablePosY - maxOffset){
            tableLayout.setY(tablePosY - maxOffset);
        }
        else{
            tableLayout.setY(curY + Y);
        }
    }

    public void initializeInterface(){
        //Initialize table containing cells
        tableLayout = findViewById(R.id.tableLayout);
        tablePosX = tableLayout.getX() + 20;
        tablePosY = tableLayout.getY() + 165;

        //Initialize all buttons
        btn = new Button[10];
        btn[1] = (Button)findViewById(R.id.keypad1);
        btn[2] = (Button)findViewById(R.id.keypad2);
        btn[3] = (Button)findViewById(R.id.keypad3);
        btn[4] = (Button)findViewById(R.id.keypad4);
        btn[5] = (Button)findViewById(R.id.keypad5);
        btn[6] = (Button)findViewById(R.id.keypad6);
        btn[7] = (Button)findViewById(R.id.keypad7);
        btn[8] = (Button)findViewById(R.id.keypad8);
        btn[9] = (Button)findViewById(R.id.keypad9);
        modeBtn = (Button)findViewById(R.id.keypadmodebutton);
        smallBtn = (Button)findViewById(R.id.keypadsmallbig);
        eraserBtn = (Button)findViewById(R.id.keypaderaser);
        nextBtn = (Button)findViewById(R.id.Next);
        exitBtn = (Button)findViewById(R.id.Exit);
        victory = findViewById(R.id.Victory);

        //Initialize all Cells
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

        //Initialize mini cells
        MiniCell = new TextView[81][9];
        MiniCell[0][0] = findViewById(R.id.MiniCell0a0);
        MiniCell[0][1] = findViewById(R.id.MiniCell0a1);
        MiniCell[0][2] = findViewById(R.id.MiniCell0a2);
        MiniCell[0][3] = findViewById(R.id.MiniCell0a3);
        MiniCell[0][4] = findViewById(R.id.MiniCell0a4);
        MiniCell[0][5] = findViewById(R.id.MiniCell0a5);
        MiniCell[0][6] = findViewById(R.id.MiniCell0a6);
        MiniCell[0][7] = findViewById(R.id.MiniCell0a7);
        MiniCell[0][8] = findViewById(R.id.MiniCell0a8);
        MiniCell[1][0] = findViewById(R.id.MiniCell1a0);
        MiniCell[1][1] = findViewById(R.id.MiniCell1a1);
        MiniCell[1][2] = findViewById(R.id.MiniCell1a2);
        MiniCell[1][3] = findViewById(R.id.MiniCell1a3);
        MiniCell[1][4] = findViewById(R.id.MiniCell1a4);
        MiniCell[1][5] = findViewById(R.id.MiniCell1a5);
        MiniCell[1][6] = findViewById(R.id.MiniCell1a6);
        MiniCell[1][7] = findViewById(R.id.MiniCell1a7);
        MiniCell[1][8] = findViewById(R.id.MiniCell1a8);
        MiniCell[2][0] = findViewById(R.id.MiniCell2a0);
        MiniCell[2][1] = findViewById(R.id.MiniCell2a1);
        MiniCell[2][2] = findViewById(R.id.MiniCell2a2);
        MiniCell[2][3] = findViewById(R.id.MiniCell2a3);
        MiniCell[2][4] = findViewById(R.id.MiniCell2a4);
        MiniCell[2][5] = findViewById(R.id.MiniCell2a5);
        MiniCell[2][6] = findViewById(R.id.MiniCell2a6);
        MiniCell[2][7] = findViewById(R.id.MiniCell2a7);
        MiniCell[2][8] = findViewById(R.id.MiniCell2a8);
        MiniCell[3][0] = findViewById(R.id.MiniCell3a0);
        MiniCell[3][1] = findViewById(R.id.MiniCell3a1);
        MiniCell[3][2] = findViewById(R.id.MiniCell3a2);
        MiniCell[3][3] = findViewById(R.id.MiniCell3a3);
        MiniCell[3][4] = findViewById(R.id.MiniCell3a4);
        MiniCell[3][5] = findViewById(R.id.MiniCell3a5);
        MiniCell[3][6] = findViewById(R.id.MiniCell3a6);
        MiniCell[3][7] = findViewById(R.id.MiniCell3a7);
        MiniCell[3][8] = findViewById(R.id.MiniCell3a8);
        MiniCell[4][0] = findViewById(R.id.MiniCell4a0);
        MiniCell[4][1] = findViewById(R.id.MiniCell4a1);
        MiniCell[4][2] = findViewById(R.id.MiniCell4a2);
        MiniCell[4][3] = findViewById(R.id.MiniCell4a3);
        MiniCell[4][4] = findViewById(R.id.MiniCell4a4);
        MiniCell[4][5] = findViewById(R.id.MiniCell4a5);
        MiniCell[4][6] = findViewById(R.id.MiniCell4a6);
        MiniCell[4][7] = findViewById(R.id.MiniCell4a7);
        MiniCell[4][8] = findViewById(R.id.MiniCell4a8);
        MiniCell[5][0] = findViewById(R.id.MiniCell5a0);
        MiniCell[5][1] = findViewById(R.id.MiniCell5a1);
        MiniCell[5][2] = findViewById(R.id.MiniCell5a2);
        MiniCell[5][3] = findViewById(R.id.MiniCell5a3);
        MiniCell[5][4] = findViewById(R.id.MiniCell5a4);
        MiniCell[5][5] = findViewById(R.id.MiniCell5a5);
        MiniCell[5][6] = findViewById(R.id.MiniCell5a6);
        MiniCell[5][7] = findViewById(R.id.MiniCell5a7);
        MiniCell[5][8] = findViewById(R.id.MiniCell5a8);
        MiniCell[6][0] = findViewById(R.id.MiniCell6a0);
        MiniCell[6][1] = findViewById(R.id.MiniCell6a1);
        MiniCell[6][2] = findViewById(R.id.MiniCell6a2);
        MiniCell[6][3] = findViewById(R.id.MiniCell6a3);
        MiniCell[6][4] = findViewById(R.id.MiniCell6a4);
        MiniCell[6][5] = findViewById(R.id.MiniCell6a5);
        MiniCell[6][6] = findViewById(R.id.MiniCell6a6);
        MiniCell[6][7] = findViewById(R.id.MiniCell6a7);
        MiniCell[6][8] = findViewById(R.id.MiniCell6a8);
        MiniCell[7][0] = findViewById(R.id.MiniCell7a0);
        MiniCell[7][1] = findViewById(R.id.MiniCell7a1);
        MiniCell[7][2] = findViewById(R.id.MiniCell7a2);
        MiniCell[7][3] = findViewById(R.id.MiniCell7a3);
        MiniCell[7][4] = findViewById(R.id.MiniCell7a4);
        MiniCell[7][5] = findViewById(R.id.MiniCell7a5);
        MiniCell[7][6] = findViewById(R.id.MiniCell7a6);
        MiniCell[7][7] = findViewById(R.id.MiniCell7a7);
        MiniCell[7][8] = findViewById(R.id.MiniCell7a8);
        MiniCell[8][0] = findViewById(R.id.MiniCell8a0);
        MiniCell[8][1] = findViewById(R.id.MiniCell8a1);
        MiniCell[8][2] = findViewById(R.id.MiniCell8a2);
        MiniCell[8][3] = findViewById(R.id.MiniCell8a3);
        MiniCell[8][4] = findViewById(R.id.MiniCell8a4);
        MiniCell[8][5] = findViewById(R.id.MiniCell8a5);
        MiniCell[8][6] = findViewById(R.id.MiniCell8a6);
        MiniCell[8][7] = findViewById(R.id.MiniCell8a7);
        MiniCell[8][8] = findViewById(R.id.MiniCell8a8);
        MiniCell[9][0] = findViewById(R.id.MiniCell9a0);
        MiniCell[9][1] = findViewById(R.id.MiniCell9a1);
        MiniCell[9][2] = findViewById(R.id.MiniCell9a2);
        MiniCell[9][3] = findViewById(R.id.MiniCell9a3);
        MiniCell[9][4] = findViewById(R.id.MiniCell9a4);
        MiniCell[9][5] = findViewById(R.id.MiniCell9a5);
        MiniCell[9][6] = findViewById(R.id.MiniCell9a6);
        MiniCell[9][7] = findViewById(R.id.MiniCell9a7);
        MiniCell[9][8] = findViewById(R.id.MiniCell9a8);
        MiniCell[10][0] = findViewById(R.id.MiniCell10a0);
        MiniCell[10][1] = findViewById(R.id.MiniCell10a1);
        MiniCell[10][2] = findViewById(R.id.MiniCell10a2);
        MiniCell[10][3] = findViewById(R.id.MiniCell10a3);
        MiniCell[10][4] = findViewById(R.id.MiniCell10a4);
        MiniCell[10][5] = findViewById(R.id.MiniCell10a5);
        MiniCell[10][6] = findViewById(R.id.MiniCell10a6);
        MiniCell[10][7] = findViewById(R.id.MiniCell10a7);
        MiniCell[10][8] = findViewById(R.id.MiniCell10a8);
        MiniCell[11][0] = findViewById(R.id.MiniCell11a0);
        MiniCell[11][1] = findViewById(R.id.MiniCell11a1);
        MiniCell[11][2] = findViewById(R.id.MiniCell11a2);
        MiniCell[11][3] = findViewById(R.id.MiniCell11a3);
        MiniCell[11][4] = findViewById(R.id.MiniCell11a4);
        MiniCell[11][5] = findViewById(R.id.MiniCell11a5);
        MiniCell[11][6] = findViewById(R.id.MiniCell11a6);
        MiniCell[11][7] = findViewById(R.id.MiniCell11a7);
        MiniCell[11][8] = findViewById(R.id.MiniCell11a8);
        MiniCell[12][0] = findViewById(R.id.MiniCell12a0);
        MiniCell[12][1] = findViewById(R.id.MiniCell12a1);
        MiniCell[12][2] = findViewById(R.id.MiniCell12a2);
        MiniCell[12][3] = findViewById(R.id.MiniCell12a3);
        MiniCell[12][4] = findViewById(R.id.MiniCell12a4);
        MiniCell[12][5] = findViewById(R.id.MiniCell12a5);
        MiniCell[12][6] = findViewById(R.id.MiniCell12a6);
        MiniCell[12][7] = findViewById(R.id.MiniCell12a7);
        MiniCell[12][8] = findViewById(R.id.MiniCell12a8);
        MiniCell[13][0] = findViewById(R.id.MiniCell13a0);
        MiniCell[13][1] = findViewById(R.id.MiniCell13a1);
        MiniCell[13][2] = findViewById(R.id.MiniCell13a2);
        MiniCell[13][3] = findViewById(R.id.MiniCell13a3);
        MiniCell[13][4] = findViewById(R.id.MiniCell13a4);
        MiniCell[13][5] = findViewById(R.id.MiniCell13a5);
        MiniCell[13][6] = findViewById(R.id.MiniCell13a6);
        MiniCell[13][7] = findViewById(R.id.MiniCell13a7);
        MiniCell[13][8] = findViewById(R.id.MiniCell13a8);
        MiniCell[14][0] = findViewById(R.id.MiniCell14a0);
        MiniCell[14][1] = findViewById(R.id.MiniCell14a1);
        MiniCell[14][2] = findViewById(R.id.MiniCell14a2);
        MiniCell[14][3] = findViewById(R.id.MiniCell14a3);
        MiniCell[14][4] = findViewById(R.id.MiniCell14a4);
        MiniCell[14][5] = findViewById(R.id.MiniCell14a5);
        MiniCell[14][6] = findViewById(R.id.MiniCell14a6);
        MiniCell[14][7] = findViewById(R.id.MiniCell14a7);
        MiniCell[14][8] = findViewById(R.id.MiniCell14a8);
        MiniCell[15][0] = findViewById(R.id.MiniCell15a0);
        MiniCell[15][1] = findViewById(R.id.MiniCell15a1);
        MiniCell[15][2] = findViewById(R.id.MiniCell15a2);
        MiniCell[15][3] = findViewById(R.id.MiniCell15a3);
        MiniCell[15][4] = findViewById(R.id.MiniCell15a4);
        MiniCell[15][5] = findViewById(R.id.MiniCell15a5);
        MiniCell[15][6] = findViewById(R.id.MiniCell15a6);
        MiniCell[15][7] = findViewById(R.id.MiniCell15a7);
        MiniCell[15][8] = findViewById(R.id.MiniCell15a8);
        MiniCell[16][0] = findViewById(R.id.MiniCell16a0);
        MiniCell[16][1] = findViewById(R.id.MiniCell16a1);
        MiniCell[16][2] = findViewById(R.id.MiniCell16a2);
        MiniCell[16][3] = findViewById(R.id.MiniCell16a3);
        MiniCell[16][4] = findViewById(R.id.MiniCell16a4);
        MiniCell[16][5] = findViewById(R.id.MiniCell16a5);
        MiniCell[16][6] = findViewById(R.id.MiniCell16a6);
        MiniCell[16][7] = findViewById(R.id.MiniCell16a7);
        MiniCell[16][8] = findViewById(R.id.MiniCell16a8);
        MiniCell[17][0] = findViewById(R.id.MiniCell17a0);
        MiniCell[17][1] = findViewById(R.id.MiniCell17a1);
        MiniCell[17][2] = findViewById(R.id.MiniCell17a2);
        MiniCell[17][3] = findViewById(R.id.MiniCell17a3);
        MiniCell[17][4] = findViewById(R.id.MiniCell17a4);
        MiniCell[17][5] = findViewById(R.id.MiniCell17a5);
        MiniCell[17][6] = findViewById(R.id.MiniCell17a6);
        MiniCell[17][7] = findViewById(R.id.MiniCell17a7);
        MiniCell[17][8] = findViewById(R.id.MiniCell17a8);
        MiniCell[18][0] = findViewById(R.id.MiniCell18a0);
        MiniCell[18][1] = findViewById(R.id.MiniCell18a1);
        MiniCell[18][2] = findViewById(R.id.MiniCell18a2);
        MiniCell[18][3] = findViewById(R.id.MiniCell18a3);
        MiniCell[18][4] = findViewById(R.id.MiniCell18a4);
        MiniCell[18][5] = findViewById(R.id.MiniCell18a5);
        MiniCell[18][6] = findViewById(R.id.MiniCell18a6);
        MiniCell[18][7] = findViewById(R.id.MiniCell18a7);
        MiniCell[18][8] = findViewById(R.id.MiniCell18a8);
        MiniCell[19][0] = findViewById(R.id.MiniCell19a0);
        MiniCell[19][1] = findViewById(R.id.MiniCell19a1);
        MiniCell[19][2] = findViewById(R.id.MiniCell19a2);
        MiniCell[19][3] = findViewById(R.id.MiniCell19a3);
        MiniCell[19][4] = findViewById(R.id.MiniCell19a4);
        MiniCell[19][5] = findViewById(R.id.MiniCell19a5);
        MiniCell[19][6] = findViewById(R.id.MiniCell19a6);
        MiniCell[19][7] = findViewById(R.id.MiniCell19a7);
        MiniCell[19][8] = findViewById(R.id.MiniCell19a8);
        MiniCell[20][0] = findViewById(R.id.MiniCell20a0);
        MiniCell[20][1] = findViewById(R.id.MiniCell20a1);
        MiniCell[20][2] = findViewById(R.id.MiniCell20a2);
        MiniCell[20][3] = findViewById(R.id.MiniCell20a3);
        MiniCell[20][4] = findViewById(R.id.MiniCell20a4);
        MiniCell[20][5] = findViewById(R.id.MiniCell20a5);
        MiniCell[20][6] = findViewById(R.id.MiniCell20a6);
        MiniCell[20][7] = findViewById(R.id.MiniCell20a7);
        MiniCell[20][8] = findViewById(R.id.MiniCell20a8);
        MiniCell[21][0] = findViewById(R.id.MiniCell21a0);
        MiniCell[21][1] = findViewById(R.id.MiniCell21a1);
        MiniCell[21][2] = findViewById(R.id.MiniCell21a2);
        MiniCell[21][3] = findViewById(R.id.MiniCell21a3);
        MiniCell[21][4] = findViewById(R.id.MiniCell21a4);
        MiniCell[21][5] = findViewById(R.id.MiniCell21a5);
        MiniCell[21][6] = findViewById(R.id.MiniCell21a6);
        MiniCell[21][7] = findViewById(R.id.MiniCell21a7);
        MiniCell[21][8] = findViewById(R.id.MiniCell21a8);
        MiniCell[22][0] = findViewById(R.id.MiniCell22a0);
        MiniCell[22][1] = findViewById(R.id.MiniCell22a1);
        MiniCell[22][2] = findViewById(R.id.MiniCell22a2);
        MiniCell[22][3] = findViewById(R.id.MiniCell22a3);
        MiniCell[22][4] = findViewById(R.id.MiniCell22a4);
        MiniCell[22][5] = findViewById(R.id.MiniCell22a5);
        MiniCell[22][6] = findViewById(R.id.MiniCell22a6);
        MiniCell[22][7] = findViewById(R.id.MiniCell22a7);
        MiniCell[22][8] = findViewById(R.id.MiniCell22a8);
        MiniCell[23][0] = findViewById(R.id.MiniCell23a0);
        MiniCell[23][1] = findViewById(R.id.MiniCell23a1);
        MiniCell[23][2] = findViewById(R.id.MiniCell23a2);
        MiniCell[23][3] = findViewById(R.id.MiniCell23a3);
        MiniCell[23][4] = findViewById(R.id.MiniCell23a4);
        MiniCell[23][5] = findViewById(R.id.MiniCell23a5);
        MiniCell[23][6] = findViewById(R.id.MiniCell23a6);
        MiniCell[23][7] = findViewById(R.id.MiniCell23a7);
        MiniCell[23][8] = findViewById(R.id.MiniCell23a8);
        MiniCell[24][0] = findViewById(R.id.MiniCell24a0);
        MiniCell[24][1] = findViewById(R.id.MiniCell24a1);
        MiniCell[24][2] = findViewById(R.id.MiniCell24a2);
        MiniCell[24][3] = findViewById(R.id.MiniCell24a3);
        MiniCell[24][4] = findViewById(R.id.MiniCell24a4);
        MiniCell[24][5] = findViewById(R.id.MiniCell24a5);
        MiniCell[24][6] = findViewById(R.id.MiniCell24a6);
        MiniCell[24][7] = findViewById(R.id.MiniCell24a7);
        MiniCell[24][8] = findViewById(R.id.MiniCell24a8);
        MiniCell[25][0] = findViewById(R.id.MiniCell25a0);
        MiniCell[25][1] = findViewById(R.id.MiniCell25a1);
        MiniCell[25][2] = findViewById(R.id.MiniCell25a2);
        MiniCell[25][3] = findViewById(R.id.MiniCell25a3);
        MiniCell[25][4] = findViewById(R.id.MiniCell25a4);
        MiniCell[25][5] = findViewById(R.id.MiniCell25a5);
        MiniCell[25][6] = findViewById(R.id.MiniCell25a6);
        MiniCell[25][7] = findViewById(R.id.MiniCell25a7);
        MiniCell[25][8] = findViewById(R.id.MiniCell25a8);
        MiniCell[26][0] = findViewById(R.id.MiniCell26a0);
        MiniCell[26][1] = findViewById(R.id.MiniCell26a1);
        MiniCell[26][2] = findViewById(R.id.MiniCell26a2);
        MiniCell[26][3] = findViewById(R.id.MiniCell26a3);
        MiniCell[26][4] = findViewById(R.id.MiniCell26a4);
        MiniCell[26][5] = findViewById(R.id.MiniCell26a5);
        MiniCell[26][6] = findViewById(R.id.MiniCell26a6);
        MiniCell[26][7] = findViewById(R.id.MiniCell26a7);
        MiniCell[26][8] = findViewById(R.id.MiniCell26a8);
        MiniCell[27][0] = findViewById(R.id.MiniCell27a0);
        MiniCell[27][1] = findViewById(R.id.MiniCell27a1);
        MiniCell[27][2] = findViewById(R.id.MiniCell27a2);
        MiniCell[27][3] = findViewById(R.id.MiniCell27a3);
        MiniCell[27][4] = findViewById(R.id.MiniCell27a4);
        MiniCell[27][5] = findViewById(R.id.MiniCell27a5);
        MiniCell[27][6] = findViewById(R.id.MiniCell27a6);
        MiniCell[27][7] = findViewById(R.id.MiniCell27a7);
        MiniCell[27][8] = findViewById(R.id.MiniCell27a8);
        MiniCell[28][0] = findViewById(R.id.MiniCell28a0);
        MiniCell[28][1] = findViewById(R.id.MiniCell28a1);
        MiniCell[28][2] = findViewById(R.id.MiniCell28a2);
        MiniCell[28][3] = findViewById(R.id.MiniCell28a3);
        MiniCell[28][4] = findViewById(R.id.MiniCell28a4);
        MiniCell[28][5] = findViewById(R.id.MiniCell28a5);
        MiniCell[28][6] = findViewById(R.id.MiniCell28a6);
        MiniCell[28][7] = findViewById(R.id.MiniCell28a7);
        MiniCell[28][8] = findViewById(R.id.MiniCell28a8);
        MiniCell[29][0] = findViewById(R.id.MiniCell29a0);
        MiniCell[29][1] = findViewById(R.id.MiniCell29a1);
        MiniCell[29][2] = findViewById(R.id.MiniCell29a2);
        MiniCell[29][3] = findViewById(R.id.MiniCell29a3);
        MiniCell[29][4] = findViewById(R.id.MiniCell29a4);
        MiniCell[29][5] = findViewById(R.id.MiniCell29a5);
        MiniCell[29][6] = findViewById(R.id.MiniCell29a6);
        MiniCell[29][7] = findViewById(R.id.MiniCell29a7);
        MiniCell[29][8] = findViewById(R.id.MiniCell29a8);
        MiniCell[30][0] = findViewById(R.id.MiniCell30a0);
        MiniCell[30][1] = findViewById(R.id.MiniCell30a1);
        MiniCell[30][2] = findViewById(R.id.MiniCell30a2);
        MiniCell[30][3] = findViewById(R.id.MiniCell30a3);
        MiniCell[30][4] = findViewById(R.id.MiniCell30a4);
        MiniCell[30][5] = findViewById(R.id.MiniCell30a5);
        MiniCell[30][6] = findViewById(R.id.MiniCell30a6);
        MiniCell[30][7] = findViewById(R.id.MiniCell30a7);
        MiniCell[30][8] = findViewById(R.id.MiniCell30a8);
        MiniCell[31][0] = findViewById(R.id.MiniCell31a0);
        MiniCell[31][1] = findViewById(R.id.MiniCell31a1);
        MiniCell[31][2] = findViewById(R.id.MiniCell31a2);
        MiniCell[31][3] = findViewById(R.id.MiniCell31a3);
        MiniCell[31][4] = findViewById(R.id.MiniCell31a4);
        MiniCell[31][5] = findViewById(R.id.MiniCell31a5);
        MiniCell[31][6] = findViewById(R.id.MiniCell31a6);
        MiniCell[31][7] = findViewById(R.id.MiniCell31a7);
        MiniCell[31][8] = findViewById(R.id.MiniCell31a8);
        MiniCell[32][0] = findViewById(R.id.MiniCell32a0);
        MiniCell[32][1] = findViewById(R.id.MiniCell32a1);
        MiniCell[32][2] = findViewById(R.id.MiniCell32a2);
        MiniCell[32][3] = findViewById(R.id.MiniCell32a3);
        MiniCell[32][4] = findViewById(R.id.MiniCell32a4);
        MiniCell[32][5] = findViewById(R.id.MiniCell32a5);
        MiniCell[32][6] = findViewById(R.id.MiniCell32a6);
        MiniCell[32][7] = findViewById(R.id.MiniCell32a7);
        MiniCell[32][8] = findViewById(R.id.MiniCell32a8);
        MiniCell[33][0] = findViewById(R.id.MiniCell33a0);
        MiniCell[33][1] = findViewById(R.id.MiniCell33a1);
        MiniCell[33][2] = findViewById(R.id.MiniCell33a2);
        MiniCell[33][3] = findViewById(R.id.MiniCell33a3);
        MiniCell[33][4] = findViewById(R.id.MiniCell33a4);
        MiniCell[33][5] = findViewById(R.id.MiniCell33a5);
        MiniCell[33][6] = findViewById(R.id.MiniCell33a6);
        MiniCell[33][7] = findViewById(R.id.MiniCell33a7);
        MiniCell[33][8] = findViewById(R.id.MiniCell33a8);
        MiniCell[34][0] = findViewById(R.id.MiniCell34a0);
        MiniCell[34][1] = findViewById(R.id.MiniCell34a1);
        MiniCell[34][2] = findViewById(R.id.MiniCell34a2);
        MiniCell[34][3] = findViewById(R.id.MiniCell34a3);
        MiniCell[34][4] = findViewById(R.id.MiniCell34a4);
        MiniCell[34][5] = findViewById(R.id.MiniCell34a5);
        MiniCell[34][6] = findViewById(R.id.MiniCell34a6);
        MiniCell[34][7] = findViewById(R.id.MiniCell34a7);
        MiniCell[34][8] = findViewById(R.id.MiniCell34a8);
        MiniCell[35][0] = findViewById(R.id.MiniCell35a0);
        MiniCell[35][1] = findViewById(R.id.MiniCell35a1);
        MiniCell[35][2] = findViewById(R.id.MiniCell35a2);
        MiniCell[35][3] = findViewById(R.id.MiniCell35a3);
        MiniCell[35][4] = findViewById(R.id.MiniCell35a4);
        MiniCell[35][5] = findViewById(R.id.MiniCell35a5);
        MiniCell[35][6] = findViewById(R.id.MiniCell35a6);
        MiniCell[35][7] = findViewById(R.id.MiniCell35a7);
        MiniCell[35][8] = findViewById(R.id.MiniCell35a8);
        MiniCell[36][0] = findViewById(R.id.MiniCell36a0);
        MiniCell[36][1] = findViewById(R.id.MiniCell36a1);
        MiniCell[36][2] = findViewById(R.id.MiniCell36a2);
        MiniCell[36][3] = findViewById(R.id.MiniCell36a3);
        MiniCell[36][4] = findViewById(R.id.MiniCell36a4);
        MiniCell[36][5] = findViewById(R.id.MiniCell36a5);
        MiniCell[36][6] = findViewById(R.id.MiniCell36a6);
        MiniCell[36][7] = findViewById(R.id.MiniCell36a7);
        MiniCell[36][8] = findViewById(R.id.MiniCell36a8);
        MiniCell[37][0] = findViewById(R.id.MiniCell37a0);
        MiniCell[37][1] = findViewById(R.id.MiniCell37a1);
        MiniCell[37][2] = findViewById(R.id.MiniCell37a2);
        MiniCell[37][3] = findViewById(R.id.MiniCell37a3);
        MiniCell[37][4] = findViewById(R.id.MiniCell37a4);
        MiniCell[37][5] = findViewById(R.id.MiniCell37a5);
        MiniCell[37][6] = findViewById(R.id.MiniCell37a6);
        MiniCell[37][7] = findViewById(R.id.MiniCell37a7);
        MiniCell[37][8] = findViewById(R.id.MiniCell37a8);
        MiniCell[38][0] = findViewById(R.id.MiniCell38a0);
        MiniCell[38][1] = findViewById(R.id.MiniCell38a1);
        MiniCell[38][2] = findViewById(R.id.MiniCell38a2);
        MiniCell[38][3] = findViewById(R.id.MiniCell38a3);
        MiniCell[38][4] = findViewById(R.id.MiniCell38a4);
        MiniCell[38][5] = findViewById(R.id.MiniCell38a5);
        MiniCell[38][6] = findViewById(R.id.MiniCell38a6);
        MiniCell[38][7] = findViewById(R.id.MiniCell38a7);
        MiniCell[38][8] = findViewById(R.id.MiniCell38a8);
        MiniCell[39][0] = findViewById(R.id.MiniCell39a0);
        MiniCell[39][1] = findViewById(R.id.MiniCell39a1);
        MiniCell[39][2] = findViewById(R.id.MiniCell39a2);
        MiniCell[39][3] = findViewById(R.id.MiniCell39a3);
        MiniCell[39][4] = findViewById(R.id.MiniCell39a4);
        MiniCell[39][5] = findViewById(R.id.MiniCell39a5);
        MiniCell[39][6] = findViewById(R.id.MiniCell39a6);
        MiniCell[39][7] = findViewById(R.id.MiniCell39a7);
        MiniCell[39][8] = findViewById(R.id.MiniCell39a8);
        MiniCell[40][0] = findViewById(R.id.MiniCell40a0);
        MiniCell[40][1] = findViewById(R.id.MiniCell40a1);
        MiniCell[40][2] = findViewById(R.id.MiniCell40a2);
        MiniCell[40][3] = findViewById(R.id.MiniCell40a3);
        MiniCell[40][4] = findViewById(R.id.MiniCell40a4);
        MiniCell[40][5] = findViewById(R.id.MiniCell40a5);
        MiniCell[40][6] = findViewById(R.id.MiniCell40a6);
        MiniCell[40][7] = findViewById(R.id.MiniCell40a7);
        MiniCell[40][8] = findViewById(R.id.MiniCell40a8);
        MiniCell[41][0] = findViewById(R.id.MiniCell41a0);
        MiniCell[41][1] = findViewById(R.id.MiniCell41a1);
        MiniCell[41][2] = findViewById(R.id.MiniCell41a2);
        MiniCell[41][3] = findViewById(R.id.MiniCell41a3);
        MiniCell[41][4] = findViewById(R.id.MiniCell41a4);
        MiniCell[41][5] = findViewById(R.id.MiniCell41a5);
        MiniCell[41][6] = findViewById(R.id.MiniCell41a6);
        MiniCell[41][7] = findViewById(R.id.MiniCell41a7);
        MiniCell[41][8] = findViewById(R.id.MiniCell41a8);
        MiniCell[42][0] = findViewById(R.id.MiniCell42a0);
        MiniCell[42][1] = findViewById(R.id.MiniCell42a1);
        MiniCell[42][2] = findViewById(R.id.MiniCell42a2);
        MiniCell[42][3] = findViewById(R.id.MiniCell42a3);
        MiniCell[42][4] = findViewById(R.id.MiniCell42a4);
        MiniCell[42][5] = findViewById(R.id.MiniCell42a5);
        MiniCell[42][6] = findViewById(R.id.MiniCell42a6);
        MiniCell[42][7] = findViewById(R.id.MiniCell42a7);
        MiniCell[42][8] = findViewById(R.id.MiniCell42a8);
        MiniCell[43][0] = findViewById(R.id.MiniCell43a0);
        MiniCell[43][1] = findViewById(R.id.MiniCell43a1);
        MiniCell[43][2] = findViewById(R.id.MiniCell43a2);
        MiniCell[43][3] = findViewById(R.id.MiniCell43a3);
        MiniCell[43][4] = findViewById(R.id.MiniCell43a4);
        MiniCell[43][5] = findViewById(R.id.MiniCell43a5);
        MiniCell[43][6] = findViewById(R.id.MiniCell43a6);
        MiniCell[43][7] = findViewById(R.id.MiniCell43a7);
        MiniCell[43][8] = findViewById(R.id.MiniCell43a8);
        MiniCell[44][0] = findViewById(R.id.MiniCell44a0);
        MiniCell[44][1] = findViewById(R.id.MiniCell44a1);
        MiniCell[44][2] = findViewById(R.id.MiniCell44a2);
        MiniCell[44][3] = findViewById(R.id.MiniCell44a3);
        MiniCell[44][4] = findViewById(R.id.MiniCell44a4);
        MiniCell[44][5] = findViewById(R.id.MiniCell44a5);
        MiniCell[44][6] = findViewById(R.id.MiniCell44a6);
        MiniCell[44][7] = findViewById(R.id.MiniCell44a7);
        MiniCell[44][8] = findViewById(R.id.MiniCell44a8);
        MiniCell[45][0] = findViewById(R.id.MiniCell45a0);
        MiniCell[45][1] = findViewById(R.id.MiniCell45a1);
        MiniCell[45][2] = findViewById(R.id.MiniCell45a2);
        MiniCell[45][3] = findViewById(R.id.MiniCell45a3);
        MiniCell[45][4] = findViewById(R.id.MiniCell45a4);
        MiniCell[45][5] = findViewById(R.id.MiniCell45a5);
        MiniCell[45][6] = findViewById(R.id.MiniCell45a6);
        MiniCell[45][7] = findViewById(R.id.MiniCell45a7);
        MiniCell[45][8] = findViewById(R.id.MiniCell45a8);
        MiniCell[46][0] = findViewById(R.id.MiniCell46a0);
        MiniCell[46][1] = findViewById(R.id.MiniCell46a1);
        MiniCell[46][2] = findViewById(R.id.MiniCell46a2);
        MiniCell[46][3] = findViewById(R.id.MiniCell46a3);
        MiniCell[46][4] = findViewById(R.id.MiniCell46a4);
        MiniCell[46][5] = findViewById(R.id.MiniCell46a5);
        MiniCell[46][6] = findViewById(R.id.MiniCell46a6);
        MiniCell[46][7] = findViewById(R.id.MiniCell46a7);
        MiniCell[46][8] = findViewById(R.id.MiniCell46a8);
        MiniCell[47][0] = findViewById(R.id.MiniCell47a0);
        MiniCell[47][1] = findViewById(R.id.MiniCell47a1);
        MiniCell[47][2] = findViewById(R.id.MiniCell47a2);
        MiniCell[47][3] = findViewById(R.id.MiniCell47a3);
        MiniCell[47][4] = findViewById(R.id.MiniCell47a4);
        MiniCell[47][5] = findViewById(R.id.MiniCell47a5);
        MiniCell[47][6] = findViewById(R.id.MiniCell47a6);
        MiniCell[47][7] = findViewById(R.id.MiniCell47a7);
        MiniCell[47][8] = findViewById(R.id.MiniCell47a8);
        MiniCell[48][0] = findViewById(R.id.MiniCell48a0);
        MiniCell[48][1] = findViewById(R.id.MiniCell48a1);
        MiniCell[48][2] = findViewById(R.id.MiniCell48a2);
        MiniCell[48][3] = findViewById(R.id.MiniCell48a3);
        MiniCell[48][4] = findViewById(R.id.MiniCell48a4);
        MiniCell[48][5] = findViewById(R.id.MiniCell48a5);
        MiniCell[48][6] = findViewById(R.id.MiniCell48a6);
        MiniCell[48][7] = findViewById(R.id.MiniCell48a7);
        MiniCell[48][8] = findViewById(R.id.MiniCell48a8);
        MiniCell[49][0] = findViewById(R.id.MiniCell49a0);
        MiniCell[49][1] = findViewById(R.id.MiniCell49a1);
        MiniCell[49][2] = findViewById(R.id.MiniCell49a2);
        MiniCell[49][3] = findViewById(R.id.MiniCell49a3);
        MiniCell[49][4] = findViewById(R.id.MiniCell49a4);
        MiniCell[49][5] = findViewById(R.id.MiniCell49a5);
        MiniCell[49][6] = findViewById(R.id.MiniCell49a6);
        MiniCell[49][7] = findViewById(R.id.MiniCell49a7);
        MiniCell[49][8] = findViewById(R.id.MiniCell49a8);
        MiniCell[50][0] = findViewById(R.id.MiniCell50a0);
        MiniCell[50][1] = findViewById(R.id.MiniCell50a1);
        MiniCell[50][2] = findViewById(R.id.MiniCell50a2);
        MiniCell[50][3] = findViewById(R.id.MiniCell50a3);
        MiniCell[50][4] = findViewById(R.id.MiniCell50a4);
        MiniCell[50][5] = findViewById(R.id.MiniCell50a5);
        MiniCell[50][6] = findViewById(R.id.MiniCell50a6);
        MiniCell[50][7] = findViewById(R.id.MiniCell50a7);
        MiniCell[50][8] = findViewById(R.id.MiniCell50a8);
        MiniCell[51][0] = findViewById(R.id.MiniCell51a0);
        MiniCell[51][1] = findViewById(R.id.MiniCell51a1);
        MiniCell[51][2] = findViewById(R.id.MiniCell51a2);
        MiniCell[51][3] = findViewById(R.id.MiniCell51a3);
        MiniCell[51][4] = findViewById(R.id.MiniCell51a4);
        MiniCell[51][5] = findViewById(R.id.MiniCell51a5);
        MiniCell[51][6] = findViewById(R.id.MiniCell51a6);
        MiniCell[51][7] = findViewById(R.id.MiniCell51a7);
        MiniCell[51][8] = findViewById(R.id.MiniCell51a8);
        MiniCell[52][0] = findViewById(R.id.MiniCell52a0);
        MiniCell[52][1] = findViewById(R.id.MiniCell52a1);
        MiniCell[52][2] = findViewById(R.id.MiniCell52a2);
        MiniCell[52][3] = findViewById(R.id.MiniCell52a3);
        MiniCell[52][4] = findViewById(R.id.MiniCell52a4);
        MiniCell[52][5] = findViewById(R.id.MiniCell52a5);
        MiniCell[52][6] = findViewById(R.id.MiniCell52a6);
        MiniCell[52][7] = findViewById(R.id.MiniCell52a7);
        MiniCell[52][8] = findViewById(R.id.MiniCell52a8);
        MiniCell[53][0] = findViewById(R.id.MiniCell53a0);
        MiniCell[53][1] = findViewById(R.id.MiniCell53a1);
        MiniCell[53][2] = findViewById(R.id.MiniCell53a2);
        MiniCell[53][3] = findViewById(R.id.MiniCell53a3);
        MiniCell[53][4] = findViewById(R.id.MiniCell53a4);
        MiniCell[53][5] = findViewById(R.id.MiniCell53a5);
        MiniCell[53][6] = findViewById(R.id.MiniCell53a6);
        MiniCell[53][7] = findViewById(R.id.MiniCell53a7);
        MiniCell[53][8] = findViewById(R.id.MiniCell53a8);
        MiniCell[54][0] = findViewById(R.id.MiniCell54a0);
        MiniCell[54][1] = findViewById(R.id.MiniCell54a1);
        MiniCell[54][2] = findViewById(R.id.MiniCell54a2);
        MiniCell[54][3] = findViewById(R.id.MiniCell54a3);
        MiniCell[54][4] = findViewById(R.id.MiniCell54a4);
        MiniCell[54][5] = findViewById(R.id.MiniCell54a5);
        MiniCell[54][6] = findViewById(R.id.MiniCell54a6);
        MiniCell[54][7] = findViewById(R.id.MiniCell54a7);
        MiniCell[54][8] = findViewById(R.id.MiniCell54a8);
        MiniCell[55][0] = findViewById(R.id.MiniCell55a0);
        MiniCell[55][1] = findViewById(R.id.MiniCell55a1);
        MiniCell[55][2] = findViewById(R.id.MiniCell55a2);
        MiniCell[55][3] = findViewById(R.id.MiniCell55a3);
        MiniCell[55][4] = findViewById(R.id.MiniCell55a4);
        MiniCell[55][5] = findViewById(R.id.MiniCell55a5);
        MiniCell[55][6] = findViewById(R.id.MiniCell55a6);
        MiniCell[55][7] = findViewById(R.id.MiniCell55a7);
        MiniCell[55][8] = findViewById(R.id.MiniCell55a8);
        MiniCell[56][0] = findViewById(R.id.MiniCell56a0);
        MiniCell[56][1] = findViewById(R.id.MiniCell56a1);
        MiniCell[56][2] = findViewById(R.id.MiniCell56a2);
        MiniCell[56][3] = findViewById(R.id.MiniCell56a3);
        MiniCell[56][4] = findViewById(R.id.MiniCell56a4);
        MiniCell[56][5] = findViewById(R.id.MiniCell56a5);
        MiniCell[56][6] = findViewById(R.id.MiniCell56a6);
        MiniCell[56][7] = findViewById(R.id.MiniCell56a7);
        MiniCell[56][8] = findViewById(R.id.MiniCell56a8);
        MiniCell[57][0] = findViewById(R.id.MiniCell57a0);
        MiniCell[57][1] = findViewById(R.id.MiniCell57a1);
        MiniCell[57][2] = findViewById(R.id.MiniCell57a2);
        MiniCell[57][3] = findViewById(R.id.MiniCell57a3);
        MiniCell[57][4] = findViewById(R.id.MiniCell57a4);
        MiniCell[57][5] = findViewById(R.id.MiniCell57a5);
        MiniCell[57][6] = findViewById(R.id.MiniCell57a6);
        MiniCell[57][7] = findViewById(R.id.MiniCell57a7);
        MiniCell[57][8] = findViewById(R.id.MiniCell57a8);
        MiniCell[58][0] = findViewById(R.id.MiniCell58a0);
        MiniCell[58][1] = findViewById(R.id.MiniCell58a1);
        MiniCell[58][2] = findViewById(R.id.MiniCell58a2);
        MiniCell[58][3] = findViewById(R.id.MiniCell58a3);
        MiniCell[58][4] = findViewById(R.id.MiniCell58a4);
        MiniCell[58][5] = findViewById(R.id.MiniCell58a5);
        MiniCell[58][6] = findViewById(R.id.MiniCell58a6);
        MiniCell[58][7] = findViewById(R.id.MiniCell58a7);
        MiniCell[58][8] = findViewById(R.id.MiniCell58a8);
        MiniCell[59][0] = findViewById(R.id.MiniCell59a0);
        MiniCell[59][1] = findViewById(R.id.MiniCell59a1);
        MiniCell[59][2] = findViewById(R.id.MiniCell59a2);
        MiniCell[59][3] = findViewById(R.id.MiniCell59a3);
        MiniCell[59][4] = findViewById(R.id.MiniCell59a4);
        MiniCell[59][5] = findViewById(R.id.MiniCell59a5);
        MiniCell[59][6] = findViewById(R.id.MiniCell59a6);
        MiniCell[59][7] = findViewById(R.id.MiniCell59a7);
        MiniCell[59][8] = findViewById(R.id.MiniCell59a8);
        MiniCell[60][0] = findViewById(R.id.MiniCell60a0);
        MiniCell[60][1] = findViewById(R.id.MiniCell60a1);
        MiniCell[60][2] = findViewById(R.id.MiniCell60a2);
        MiniCell[60][3] = findViewById(R.id.MiniCell60a3);
        MiniCell[60][4] = findViewById(R.id.MiniCell60a4);
        MiniCell[60][5] = findViewById(R.id.MiniCell60a5);
        MiniCell[60][6] = findViewById(R.id.MiniCell60a6);
        MiniCell[60][7] = findViewById(R.id.MiniCell60a7);
        MiniCell[60][8] = findViewById(R.id.MiniCell60a8);
        MiniCell[61][0] = findViewById(R.id.MiniCell61a0);
        MiniCell[61][1] = findViewById(R.id.MiniCell61a1);
        MiniCell[61][2] = findViewById(R.id.MiniCell61a2);
        MiniCell[61][3] = findViewById(R.id.MiniCell61a3);
        MiniCell[61][4] = findViewById(R.id.MiniCell61a4);
        MiniCell[61][5] = findViewById(R.id.MiniCell61a5);
        MiniCell[61][6] = findViewById(R.id.MiniCell61a6);
        MiniCell[61][7] = findViewById(R.id.MiniCell61a7);
        MiniCell[61][8] = findViewById(R.id.MiniCell61a8);
        MiniCell[62][0] = findViewById(R.id.MiniCell62a0);
        MiniCell[62][1] = findViewById(R.id.MiniCell62a1);
        MiniCell[62][2] = findViewById(R.id.MiniCell62a2);
        MiniCell[62][3] = findViewById(R.id.MiniCell62a3);
        MiniCell[62][4] = findViewById(R.id.MiniCell62a4);
        MiniCell[62][5] = findViewById(R.id.MiniCell62a5);
        MiniCell[62][6] = findViewById(R.id.MiniCell62a6);
        MiniCell[62][7] = findViewById(R.id.MiniCell62a7);
        MiniCell[62][8] = findViewById(R.id.MiniCell62a8);
        MiniCell[63][0] = findViewById(R.id.MiniCell63a0);
        MiniCell[63][1] = findViewById(R.id.MiniCell63a1);
        MiniCell[63][2] = findViewById(R.id.MiniCell63a2);
        MiniCell[63][3] = findViewById(R.id.MiniCell63a3);
        MiniCell[63][4] = findViewById(R.id.MiniCell63a4);
        MiniCell[63][5] = findViewById(R.id.MiniCell63a5);
        MiniCell[63][6] = findViewById(R.id.MiniCell63a6);
        MiniCell[63][7] = findViewById(R.id.MiniCell63a7);
        MiniCell[63][8] = findViewById(R.id.MiniCell63a8);
        MiniCell[64][0] = findViewById(R.id.MiniCell64a0);
        MiniCell[64][1] = findViewById(R.id.MiniCell64a1);
        MiniCell[64][2] = findViewById(R.id.MiniCell64a2);
        MiniCell[64][3] = findViewById(R.id.MiniCell64a3);
        MiniCell[64][4] = findViewById(R.id.MiniCell64a4);
        MiniCell[64][5] = findViewById(R.id.MiniCell64a5);
        MiniCell[64][6] = findViewById(R.id.MiniCell64a6);
        MiniCell[64][7] = findViewById(R.id.MiniCell64a7);
        MiniCell[64][8] = findViewById(R.id.MiniCell64a8);
        MiniCell[65][0] = findViewById(R.id.MiniCell65a0);
        MiniCell[65][1] = findViewById(R.id.MiniCell65a1);
        MiniCell[65][2] = findViewById(R.id.MiniCell65a2);
        MiniCell[65][3] = findViewById(R.id.MiniCell65a3);
        MiniCell[65][4] = findViewById(R.id.MiniCell65a4);
        MiniCell[65][5] = findViewById(R.id.MiniCell65a5);
        MiniCell[65][6] = findViewById(R.id.MiniCell65a6);
        MiniCell[65][7] = findViewById(R.id.MiniCell65a7);
        MiniCell[65][8] = findViewById(R.id.MiniCell65a8);
        MiniCell[66][0] = findViewById(R.id.MiniCell66a0);
        MiniCell[66][1] = findViewById(R.id.MiniCell66a1);
        MiniCell[66][2] = findViewById(R.id.MiniCell66a2);
        MiniCell[66][3] = findViewById(R.id.MiniCell66a3);
        MiniCell[66][4] = findViewById(R.id.MiniCell66a4);
        MiniCell[66][5] = findViewById(R.id.MiniCell66a5);
        MiniCell[66][6] = findViewById(R.id.MiniCell66a6);
        MiniCell[66][7] = findViewById(R.id.MiniCell66a7);
        MiniCell[66][8] = findViewById(R.id.MiniCell66a8);
        MiniCell[67][0] = findViewById(R.id.MiniCell67a0);
        MiniCell[67][1] = findViewById(R.id.MiniCell67a1);
        MiniCell[67][2] = findViewById(R.id.MiniCell67a2);
        MiniCell[67][3] = findViewById(R.id.MiniCell67a3);
        MiniCell[67][4] = findViewById(R.id.MiniCell67a4);
        MiniCell[67][5] = findViewById(R.id.MiniCell67a5);
        MiniCell[67][6] = findViewById(R.id.MiniCell67a6);
        MiniCell[67][7] = findViewById(R.id.MiniCell67a7);
        MiniCell[67][8] = findViewById(R.id.MiniCell67a8);
        MiniCell[68][0] = findViewById(R.id.MiniCell68a0);
        MiniCell[68][1] = findViewById(R.id.MiniCell68a1);
        MiniCell[68][2] = findViewById(R.id.MiniCell68a2);
        MiniCell[68][3] = findViewById(R.id.MiniCell68a3);
        MiniCell[68][4] = findViewById(R.id.MiniCell68a4);
        MiniCell[68][5] = findViewById(R.id.MiniCell68a5);
        MiniCell[68][6] = findViewById(R.id.MiniCell68a6);
        MiniCell[68][7] = findViewById(R.id.MiniCell68a7);
        MiniCell[68][8] = findViewById(R.id.MiniCell68a8);
        MiniCell[69][0] = findViewById(R.id.MiniCell69a0);
        MiniCell[69][1] = findViewById(R.id.MiniCell69a1);
        MiniCell[69][2] = findViewById(R.id.MiniCell69a2);
        MiniCell[69][3] = findViewById(R.id.MiniCell69a3);
        MiniCell[69][4] = findViewById(R.id.MiniCell69a4);
        MiniCell[69][5] = findViewById(R.id.MiniCell69a5);
        MiniCell[69][6] = findViewById(R.id.MiniCell69a6);
        MiniCell[69][7] = findViewById(R.id.MiniCell69a7);
        MiniCell[69][8] = findViewById(R.id.MiniCell69a8);
        MiniCell[70][0] = findViewById(R.id.MiniCell70a0);
        MiniCell[70][1] = findViewById(R.id.MiniCell70a1);
        MiniCell[70][2] = findViewById(R.id.MiniCell70a2);
        MiniCell[70][3] = findViewById(R.id.MiniCell70a3);
        MiniCell[70][4] = findViewById(R.id.MiniCell70a4);
        MiniCell[70][5] = findViewById(R.id.MiniCell70a5);
        MiniCell[70][6] = findViewById(R.id.MiniCell70a6);
        MiniCell[70][7] = findViewById(R.id.MiniCell70a7);
        MiniCell[70][8] = findViewById(R.id.MiniCell70a8);
        MiniCell[71][0] = findViewById(R.id.MiniCell71a0);
        MiniCell[71][1] = findViewById(R.id.MiniCell71a1);
        MiniCell[71][2] = findViewById(R.id.MiniCell71a2);
        MiniCell[71][3] = findViewById(R.id.MiniCell71a3);
        MiniCell[71][4] = findViewById(R.id.MiniCell71a4);
        MiniCell[71][5] = findViewById(R.id.MiniCell71a5);
        MiniCell[71][6] = findViewById(R.id.MiniCell71a6);
        MiniCell[71][7] = findViewById(R.id.MiniCell71a7);
        MiniCell[71][8] = findViewById(R.id.MiniCell71a8);
        MiniCell[72][0] = findViewById(R.id.MiniCell72a0);
        MiniCell[72][1] = findViewById(R.id.MiniCell72a1);
        MiniCell[72][2] = findViewById(R.id.MiniCell72a2);
        MiniCell[72][3] = findViewById(R.id.MiniCell72a3);
        MiniCell[72][4] = findViewById(R.id.MiniCell72a4);
        MiniCell[72][5] = findViewById(R.id.MiniCell72a5);
        MiniCell[72][6] = findViewById(R.id.MiniCell72a6);
        MiniCell[72][7] = findViewById(R.id.MiniCell72a7);
        MiniCell[72][8] = findViewById(R.id.MiniCell72a8);
        MiniCell[73][0] = findViewById(R.id.MiniCell73a0);
        MiniCell[73][1] = findViewById(R.id.MiniCell73a1);
        MiniCell[73][2] = findViewById(R.id.MiniCell73a2);
        MiniCell[73][3] = findViewById(R.id.MiniCell73a3);
        MiniCell[73][4] = findViewById(R.id.MiniCell73a4);
        MiniCell[73][5] = findViewById(R.id.MiniCell73a5);
        MiniCell[73][6] = findViewById(R.id.MiniCell73a6);
        MiniCell[73][7] = findViewById(R.id.MiniCell73a7);
        MiniCell[73][8] = findViewById(R.id.MiniCell73a8);
        MiniCell[74][0] = findViewById(R.id.MiniCell74a0);
        MiniCell[74][1] = findViewById(R.id.MiniCell74a1);
        MiniCell[74][2] = findViewById(R.id.MiniCell74a2);
        MiniCell[74][3] = findViewById(R.id.MiniCell74a3);
        MiniCell[74][4] = findViewById(R.id.MiniCell74a4);
        MiniCell[74][5] = findViewById(R.id.MiniCell74a5);
        MiniCell[74][6] = findViewById(R.id.MiniCell74a6);
        MiniCell[74][7] = findViewById(R.id.MiniCell74a7);
        MiniCell[74][8] = findViewById(R.id.MiniCell74a8);
        MiniCell[75][0] = findViewById(R.id.MiniCell75a0);
        MiniCell[75][1] = findViewById(R.id.MiniCell75a1);
        MiniCell[75][2] = findViewById(R.id.MiniCell75a2);
        MiniCell[75][3] = findViewById(R.id.MiniCell75a3);
        MiniCell[75][4] = findViewById(R.id.MiniCell75a4);
        MiniCell[75][5] = findViewById(R.id.MiniCell75a5);
        MiniCell[75][6] = findViewById(R.id.MiniCell75a6);
        MiniCell[75][7] = findViewById(R.id.MiniCell75a7);
        MiniCell[75][8] = findViewById(R.id.MiniCell75a8);
        MiniCell[76][0] = findViewById(R.id.MiniCell76a0);
        MiniCell[76][1] = findViewById(R.id.MiniCell76a1);
        MiniCell[76][2] = findViewById(R.id.MiniCell76a2);
        MiniCell[76][3] = findViewById(R.id.MiniCell76a3);
        MiniCell[76][4] = findViewById(R.id.MiniCell76a4);
        MiniCell[76][5] = findViewById(R.id.MiniCell76a5);
        MiniCell[76][6] = findViewById(R.id.MiniCell76a6);
        MiniCell[76][7] = findViewById(R.id.MiniCell76a7);
        MiniCell[76][8] = findViewById(R.id.MiniCell76a8);
        MiniCell[77][0] = findViewById(R.id.MiniCell77a0);
        MiniCell[77][1] = findViewById(R.id.MiniCell77a1);
        MiniCell[77][2] = findViewById(R.id.MiniCell77a2);
        MiniCell[77][3] = findViewById(R.id.MiniCell77a3);
        MiniCell[77][4] = findViewById(R.id.MiniCell77a4);
        MiniCell[77][5] = findViewById(R.id.MiniCell77a5);
        MiniCell[77][6] = findViewById(R.id.MiniCell77a6);
        MiniCell[77][7] = findViewById(R.id.MiniCell77a7);
        MiniCell[77][8] = findViewById(R.id.MiniCell77a8);
        MiniCell[78][0] = findViewById(R.id.MiniCell78a0);
        MiniCell[78][1] = findViewById(R.id.MiniCell78a1);
        MiniCell[78][2] = findViewById(R.id.MiniCell78a2);
        MiniCell[78][3] = findViewById(R.id.MiniCell78a3);
        MiniCell[78][4] = findViewById(R.id.MiniCell78a4);
        MiniCell[78][5] = findViewById(R.id.MiniCell78a5);
        MiniCell[78][6] = findViewById(R.id.MiniCell78a6);
        MiniCell[78][7] = findViewById(R.id.MiniCell78a7);
        MiniCell[78][8] = findViewById(R.id.MiniCell78a8);
        MiniCell[79][0] = findViewById(R.id.MiniCell79a0);
        MiniCell[79][1] = findViewById(R.id.MiniCell79a1);
        MiniCell[79][2] = findViewById(R.id.MiniCell79a2);
        MiniCell[79][3] = findViewById(R.id.MiniCell79a3);
        MiniCell[79][4] = findViewById(R.id.MiniCell79a4);
        MiniCell[79][5] = findViewById(R.id.MiniCell79a5);
        MiniCell[79][6] = findViewById(R.id.MiniCell79a6);
        MiniCell[79][7] = findViewById(R.id.MiniCell79a7);
        MiniCell[79][8] = findViewById(R.id.MiniCell79a8);
        MiniCell[80][0] = findViewById(R.id.MiniCell80a0);
        MiniCell[80][1] = findViewById(R.id.MiniCell80a1);
        MiniCell[80][2] = findViewById(R.id.MiniCell80a2);
        MiniCell[80][3] = findViewById(R.id.MiniCell80a3);
        MiniCell[80][4] = findViewById(R.id.MiniCell80a4);
        MiniCell[80][5] = findViewById(R.id.MiniCell80a5);
        MiniCell[80][6] = findViewById(R.id.MiniCell80a6);
        MiniCell[80][7] = findViewById(R.id.MiniCell80a7);
        MiniCell[80][8] = findViewById(R.id.MiniCell80a8);
    }

    public void initializeGameData(int game){
        cellSelector = 0;
        buttonSelector = 0;
        inputMode = false;
        annotateMode = false;

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

    }
}
