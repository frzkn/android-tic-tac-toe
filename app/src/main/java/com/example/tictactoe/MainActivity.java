package com.example.tictactoe;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    TableLayout tableLayout;
    Button reset;
    Boolean won;
    View view;
    List<String> playerOne;
    List<String> playerTwo;
    int playerTurn;
    int turnCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playerOne = new ArrayList<String>();
        playerTwo = new ArrayList<String>();

        button1 = findViewById(R.id.button_11);
        button2 = findViewById(R.id.button_12);
        button3 = findViewById(R.id.button_13);
        button4 = findViewById(R.id.button_21);
        button5 = findViewById(R.id.button_22);
        button6 = findViewById(R.id.button_23);
        button7 = findViewById(R.id.button_31);
        button8 = findViewById(R.id.button_32);
        button9 = findViewById(R.id.button_33);
        reset = findViewById(R.id.button_reset);
        playerTurn = 0;
        turnCounter = 1;
        won = false;

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

    }


    void checkWinner(List<String> list) {
        if (list.contains("11") && list.contains("12") && list.contains("13"))
            callWinner(ifPlayerOne());
        else if (list.contains("21") && list.contains("22") && list.contains("23"))
            callWinner(ifPlayerOne());
        else if (list.contains("31") && list.contains("32") && list.contains("33"))
            callWinner(ifPlayerOne());
        else if (list.contains("11") && list.contains("22") && list.contains("33"))
            callWinner(ifPlayerOne());
        else if (list.contains("13") && list.contains("22") && list.contains("31"))
            callWinner(ifPlayerOne());
        else if (list.contains("11") && list.contains("21") && list.contains("31"))
            callWinner(ifPlayerOne());
        else if (list.contains("12") && list.contains("22") && list.contains("32"))
            callWinner(ifPlayerOne());
        else if (list.contains("13") && list.contains("23") && list.contains("33"))
            callWinner(ifPlayerOne());
        if (turnCounter == 9 && !won) {
            Toast.makeText(MainActivity.this, "Game draw", Toast.LENGTH_SHORT).show();
            disableAndReload(view);
        }
        turnCounter++;
    }

    void callWinner(boolean ifPlayerOne) {

        if (ifPlayerOne) {
            Toast.makeText(MainActivity.this, "Player X is the winner.", Toast.LENGTH_SHORT).show();
            won = true;
            disableAndReload(view);

        } else if (!ifPlayerOne) {
            Toast.makeText(MainActivity.this, "Player O is the winner.", Toast.LENGTH_SHORT).show();
            won = true;
            disableAndReload(view);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_11:
                playGame(button1, "11");
                break;
            case R.id.button_12:
                playGame(button2, "12");
                break;
            case R.id.button_13:
                playGame(button3, "13");
                break;
            case R.id.button_21:
                playGame(button4, "21");
                break;
            case R.id.button_22:
                playGame(button5, "22");
                break;
            case R.id.button_23:
                playGame(button6, "23");
                break;
            case R.id.button_31:
                playGame(button7, "31");
                break;
            case R.id.button_32:
                playGame(button8, "32");
                break;
            case R.id.button_33:
                playGame(button9, "33");
                break;
            default:
                throw new RuntimeException("Cannot find button ID");


        }


    }

    boolean ifPlayerOne() {
        if (playerTurn == 0)
            return true;
        else
            return false;
    }

    void playGame(Button button, String position) {

        if (ifPlayerOne()) {
            button.setBackgroundColor(Color.parseColor("#E91E63"));
            button.setText("X");
            button.setTextColor(getResources().getColor(android.R.color.white));
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            playerOne.add(position);
            checkWinner(playerOne);
            playerTurn = 1;
        } else if (!ifPlayerOne()) {
            button.setBackgroundColor(Color.parseColor("#F8BBD0"));
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            button.setTextColor(getResources().getColor(android.R.color.black));
            button.setText("O");
            playerTwo.add(position);
            checkWinner(playerTwo);
            playerTurn = 0;
        }
        button.setEnabled(false);
    }


    void disableAndReload(View view) {

        tableLayout = findViewById(R.id.table_layout);
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            View child = tableLayout.getChildAt(i);
            child.setEnabled(false);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }


}
