package com.derskeal.tictactoe;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SThreeActivity extends AppCompatActivity {

    public boolean valtouse = true;
    public int taptimes = 0;
    public String[] tapinfotext = new String[9];
    public int[] tapinfotags = new int[9];
    public String[] asv = new String[10]; //asv - all squares value
    public boolean gamewon = false;
    public String playsym;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sthree);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                reset_game();
            }
        });

        avatar_select();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mthree, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_main_menu) {
            //finish();
            //System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO: 28/03/2018 add single player mode/AI

    public void avatar_select() {
        Toast.makeText(this, "chai", Toast.LENGTH_SHORT).show();
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Please select your Player Symbol")
                .setTitle("Player");

        builder.setPositiveButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the player symbol to X
                playsym = "X";
                valtouse = true;
                TextView v = (TextView) findViewById(R.id.player_turn_id);
                v.setText("X");
            }
        });

        builder.setNegativeButton("O", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the player symbol to O
                playsym = "O";
                valtouse = false;
                TextView v = (TextView) findViewById(R.id.player_turn_id);
                v.setText("O");
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SThreeActivity.this, "Player Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();

    }


    public void cell_clicked(View vv) {
        if (gamewon) {

        } else if (taptimes == 9 && !gamewon) {
            Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();
        }

        else {

            //check if the cell is filled
        /*
        * if filled, do nothing
        * if empty, fill it with the appropriate player symbol and,
        *
        *
        * check if there is a winner
        *
        *
        * */

            TextView v = (TextView) vv;

            if (taptimes < 10 && TextUtils.isEmpty(v.getText().toString())) {
                if (valtouse) {
                    playsym = "X";
                } else {
                    playsym = "O";
                }

                int tag = Integer.parseInt(v.getTag().toString());
                asv[tag] = playsym;

                v.setText(playsym);
                valtouse = !valtouse;

                TextView pti = (TextView) findViewById(R.id.player_turn_id);
                String pt = valtouse ? "X" : "O";
                pti.setText(pt);
            } else if (taptimes >= 9) {
                Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();
            }

            else {
                Toast.makeText(this, "Already played cell", Toast.LENGTH_SHORT).show();
            }

            taptimes++;

            //Array e =
            //Object e = new Object();
            int[][] wi = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9},
                    {1, 4, 7},
                    {2, 5, 8},
                    {3, 6, 9},
                    {1, 5, 9},
                    {3, 5, 7},
            };


            for (int[] i : wi) {
                int a, b, c;
                a = i[0];
                b = i[1];
                c = i[2];

                if (!TextUtils.isEmpty(asv[a]) && asv[a] == asv[b] && asv[a] == asv[c]) {
                    String winner = "Winner: " + asv[a];
                    Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                    gamewon = true;

                    TextView win = (TextView)findViewById(R.id.winner_status);
                    win.setText(winner);

                    break;
                }

                /*if (!TextUtils.isEmpty(asv[a]) && asv[a] != asv[b] && asv[a] != asv[c]) {

                }*/

            }

            if (taptimes == 9 && !gamewon) {
                Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();
            }


        }
    }

    public void reset_game() {
        gamewon = false;
        TextView win = (TextView)findViewById(R.id.winner_status);
        win.setText("");
        taptimes = 0;

        valtouse = true;
        TextView pti = (TextView) findViewById(R.id.player_turn_id);
        String pt = valtouse ? "X" : "O";
        pti.setText(pt);

        asv = new String[10];

        TextView tv = (TextView) findViewById(R.id.c1);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c2);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c3);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c4);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c5);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c6);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c7);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c8);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c9);
        tv.setText("");

    }
}
