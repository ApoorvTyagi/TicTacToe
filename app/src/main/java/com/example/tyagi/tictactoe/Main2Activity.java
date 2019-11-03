package com.example.tyagi.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    public Button b1,b2;
    private TextView textp1,textp2;
    public void init() {

        b1 = findViewById(R.id.button_start);
        b2 = findViewById(R.id.button_rules);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy=new Intent(Main2Activity.this,MainActivity.class);
                textp1=findViewById(R.id.editText2);
                textp2=findViewById(R.id.editText);
                toy.putExtra("location", textp1.getText().toString());
                toy.putExtra("location2", textp2.getText().toString());
                startActivity(toy);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b_builder = new AlertDialog.Builder(Main2Activity.this);
                b_builder.setMessage("1.This is a 5 match game tournament.\n" +
                        "\n2.The player who wins the most number of matches out of 5 wins the tournament.\n" +
                        "\n3.The player who wins the previous match will go first in the next match.(In the 1st match player1 will go first)\n" +
                        "\n4.You have to press \"Next Game\" button whenever you want to start the next game of the tournament.\n" +
                        "\n5.After completing the 5 matches a fresh tournament starts again.\n"+
                        "\nENJOY THE GAME!!!").setCancelable(false).setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                }
                });
                AlertDialog alert=b_builder.create();
                alert.setTitle("RULES");
                alert.show();
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }
}
