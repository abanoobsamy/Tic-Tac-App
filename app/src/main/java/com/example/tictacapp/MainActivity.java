package com.bob.tictacapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvPlayer;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    int player = 1;

    int buttonState [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        addButtonClickListener();

        buttonState = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    private void initViews() {

        tvPlayer = findViewById(R.id.tvPlayer);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
    }

    public void addButtonClickListener() {

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.button1:

                checkWinner(0, view);
                break;

            case R.id.button2:

                checkWinner(1, view);
                break;

            case R.id.button3:

                checkWinner(2, view);
                break;

            case R.id.button4:

                checkWinner(3, view);
                break;

            case R.id.button5:

                checkWinner(4, view);
                break;

            case R.id.button6:

                checkWinner(5, view);
                break;

            case R.id.button7:

                checkWinner(6, view);
                break;

            case R.id.button8:

                checkWinner(7, view);
                break;

            case R.id.button9:

                checkWinner(8, view);
                break;
        }
    }

    private void checkWinner(int i, View view) {

        changeText(view, i);

        if (buttonState[i] == 0)
        {
            //set player value
            buttonState[i] = player;
        }
        else if (buttonState[i] == 1)
        {
            recreate();
            Toast.makeText(this, "Start New Game", Toast.LENGTH_SHORT).show();
        }

        // Winner state
        if (buttonState[0] == player && buttonState[1] == player && buttonState[2] == player
                || buttonState[0] == player && buttonState[3] == player && buttonState[6] == player
                || buttonState[0] == player && buttonState[4] == player && buttonState[8] == player
                || buttonState[1] == player && buttonState[4] == player && buttonState[7] == player
                || buttonState[2] == player && buttonState[5] == player && buttonState[8] == player
                || buttonState[2] == player && buttonState[4] == player && buttonState[6] == player
                || buttonState[3] == player && buttonState[4] == player && buttonState[5] == player
                || buttonState[6] == player && buttonState[7] == player && buttonState[8] == player)
        {
            if (player == 1)
            {
                dialogWinner(2);
                Toast.makeText(this, "The Winner is Player 2", Toast.LENGTH_SHORT).show();
            }
            else if (player == 2)
            {
                dialogWinner(1);
                Toast.makeText(this, "The Winner is Player 1", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // @Overloading
    public void dialogWinner(int player) {

        if (player == 1)
        {
            dialogWinner("First");

            tvPlayer.setText("Winner Winner.");
        }

        else if (player == 2)
        {
            dialogWinner("Second");

            tvPlayer.setText("Winner Winner.");
        }

    }

    // @Overloading
    private void dialogWinner(String winner) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Winner is " + winner + " Player")
                .setMessage("Do you want to replay?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        recreate();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                    }
                }).create();

        builder.show();
    }

    private void changeText(View view, int i) {

        Button btn = (Button) view;
        
        if (buttonState[i] == 0)
        {
            // هنا في ال if دي حاططله player = 2 في نهاية الشرط وكدا انا لما دوست بقيت player 2
            if (player == 1)
            {
                btn.setText("X");
                btn.setTextColor(Color.parseColor("#FF0000"));

                player = 2;
            }
            else if (player == 2)
            {
                btn.setText("O");
                btn.setTextColor(Color.parseColor("#0000FF"));

                player = 1;
            }
        }

        if (player == 1)
        {
            tvPlayer.setText("First Player");
        }
        else if (player == 2)
        {
            tvPlayer.setText("Second Player");
        }

//        tvPlayer.setText("Player = " + player);
    }
}