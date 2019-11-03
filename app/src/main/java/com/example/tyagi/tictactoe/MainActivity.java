package com.example.tyagi.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int p1points,p2points,round,total;
    private Button[][] buttons=new Button[3][3];

    private TextView textViewp1,textViewp2,matchNum;
    private boolean player1turn=true;
    public  String str1,str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewp1=findViewById(R.id.text_view_p1);
        textViewp2=findViewById(R.id.text_view_p2);
        matchNum=findViewById(R.id.textView);
        Intent intent = getIntent();
        str1=intent.getStringExtra("location");
        if(str1.equals(""))
            str1="Player1";
        textViewp1.setText(str1+":0");
        str2 = intent.getStringExtra("location2");
        if(str2.equals(""))
            str2="Player2";
        textViewp2.setText(str2+":0");

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++){
            String buttonid="button_"+i+j;
            int resid=getResources().getIdentifier(buttonid,"id",getPackageName());
            buttons[i][j]=findViewById(resid);
            buttons[i][j].setOnClickListener(this);
            }
         Button reset=findViewById(R.id.button_reset);
         reset.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(checkforwin() || round==9) {
                     int x = total + 1;
                     matchNum.setText("Match:" + x);
                     resetBoard();
                 }
                 else {
                     AlertDialog.Builder c_builder = new AlertDialog.Builder(MainActivity.this);
                         c_builder.setMessage("You have to complete this match!!!.").setCancelable(false).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 dialog.cancel();
                             }
                         });
                         AlertDialog alert=c_builder.create();
                         alert.setTitle("Alert");
                         alert.show();
                 }
             }
         });
    }


    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals(""))
        {
            Toast.makeText(this, "This box is not empty", Toast.LENGTH_SHORT).show();
            round--;
            player1turn=!player1turn;
        }else if(player1turn) {
            ((Button) v).setText("X");
        }
        else {
            ((Button) v).setText("O");
        }

        round++;

        if(checkforwin()){
            if(player1turn)
                player1wins();
            else
                player2wins();
        }
        else if(round==9)
                draw();
        else
            player1turn=!player1turn;
    }

    private  boolean checkforwin(){
        String[][] field=new String[3][3];

        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                field[i][j]=buttons[i][j].getText().toString();

        for(int i=0;i<3;i++)
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                buttons[0][i].setBackgroundColor(Color.RED);
                buttons[1][i].setBackgroundColor(Color.RED);
                buttons[2][i].setBackgroundColor(Color.RED);
                return true;
            }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                buttons[i][0].setBackgroundColor(Color.RED);
                buttons[i][1].setBackgroundColor(Color.RED);
                buttons[i][2].setBackgroundColor(Color.RED);
                return true;
            }
        }
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            buttons[1][1].setBackgroundColor(Color.RED);
            buttons[2][2].setBackgroundColor(Color.RED);
            buttons[0][0].setBackgroundColor(Color.RED);
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            buttons[0][2].setBackgroundColor(Color.RED);
            buttons[1][1].setBackgroundColor(Color.RED);
            buttons[2][0].setBackgroundColor(Color.RED);
            return true;
        }
        return false;
    }
    private void player1wins() {
        p1points++;
        total++;
        Disable_Button();
        Toast.makeText(this, str1+" wins!", Toast.LENGTH_LONG).show();
        if(total==5)
            checkforcomp();
    }

    private void player2wins() {
        p2points++;
        total++;
        Disable_Button();
        Toast.makeText(this, str2+" wins!", Toast.LENGTH_SHORT).show();
        if(total==5)
            checkforcomp();
    }

    private void draw() {
        total++;
        Disable_Button();
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        if(total==5)
            checkforcomp();
    }

    private void Disable_Button()
    {
        Button buttonid = findViewById(R.id.button_00);
        buttonid.setEnabled(false);
        Button buttonid1 = findViewById(R.id.button_01);
        buttonid1.setEnabled(false);
        Button buttonid2 = findViewById(R.id.button_02);
        buttonid2.setEnabled(false);
        Button buttonid3 = findViewById(R.id.button_10);
        buttonid3.setEnabled(false);
        Button buttonid4 = findViewById(R.id.button_11);
        buttonid4.setEnabled(false);
        Button buttonid5 = findViewById(R.id.button_12);
        buttonid5.setEnabled(false);
        Button buttonid6 = findViewById(R.id.button_20);
        buttonid6.setEnabled(false);
        Button buttonid7 = findViewById(R.id.button_21);
        buttonid7.setEnabled(false);
        Button buttonid8 = findViewById(R.id.button_22);
        buttonid8.setEnabled(false);
    }
    private void Enable_Button()
    {
        Button buttonid = findViewById(R.id.button_00);
        buttonid.setEnabled(true);
        Button buttonid1 = findViewById(R.id.button_01);
        buttonid1.setEnabled(true);
        Button buttonid2 = findViewById(R.id.button_02);
        buttonid2.setEnabled(true);
        Button buttonid3 = findViewById(R.id.button_10);
        buttonid3.setEnabled(true);
        Button buttonid4 = findViewById(R.id.button_11);
        buttonid4.setEnabled(true);
        Button buttonid5 = findViewById(R.id.button_12);
        buttonid5.setEnabled(true);
        Button buttonid6 = findViewById(R.id.button_20);
        buttonid6.setEnabled(true);
        Button buttonid7 = findViewById(R.id.button_21);
        buttonid7.setEnabled(true);
        Button buttonid8 = findViewById(R.id.button_22);
        buttonid8.setEnabled(true);
    }


    private void updatePointsText() {
        textViewp1.setText(str1+": " + p1points);
        textViewp2.setText(str2+": " + p2points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackgroundResource(android.R.drawable.btn_default);
            }
        }
        Enable_Button();
        updatePointsText();
        round = 0;
    }
    private void checkforcomp(){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
        if(p1points>p2points && total==5)
        {
            a_builder.setMessage(str1+" wins the tournament by "+p1points+"-"+p2points+".").setCancelable(false).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    resetGame();
                }
            });
            AlertDialog alert=a_builder.create();
            alert.setTitle("Congratulation "+str1);
            alert.show();

        }
        else if(p2points>p1points && total==5)
        {
            a_builder.setMessage(str2+" wins the tournament by "+p1points+"-"+p2points+".").setCancelable(false).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    resetGame();
                }
            });
            AlertDialog alert=a_builder.create();
            alert.setTitle("Congratulation "+str2);
            alert.show();

        }
        else if(p1points==p2points && total==5)
        {
            a_builder.setMessage("You both played a Draw tournament").setCancelable(false).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    resetGame();
                }
            });
            AlertDialog alert=a_builder.create();
            alert.setTitle("DRAW!!!");
            alert.show();

        }
    }
    private void resetGame() {
        p1points = 0;
        p2points = 0;
        total=0;
        round=0;
        matchNum.setText("Match:1");
        updatePointsText();
        resetBoard();
    }
}
