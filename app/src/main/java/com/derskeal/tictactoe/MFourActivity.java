/*
 * Copyright 2017 Derskeal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.derskeal.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

/**
 * This class handles the tictactoe box size 4 multiplayer
 */
public class MFourActivity extends AppCompatActivity {

    private boolean valtouse = true;
    private int taptimes = 0;
    private String[] asv = new String[17]; //asv - all squares value
    private boolean gamewon = false;
    private boolean gamedrawn = false;

    private String[] player = {"","",""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfour);

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

        /*TextView v = (TextView) findViewById(R.id.player_turn_id);
        v.setText("Player 1");*/
    }

    //Rules:
    /*
    *
     *
     * 4 player symbols in a row, column, or diagonal.
    */

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

    private void avatar_select() {
        //Toast.makeText(this, "chai", Toast.LENGTH_SHORT).show();
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Please select your Player Symbol")
                .setTitle("Player 1");

        builder.setPositiveButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the player symbol to X
                /*playsym = "X";
                defplaysym = "X";*/
                player[1] = "X";
                player[2] = "O";
                valtouse = true;
                /*TextView v = (TextView) findViewById(R.id.player_turn_id);
                v.setText("X");*/
            }
        });

        builder.setNegativeButton("O", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the player symbol to O
                /*playsym = "O";
                defplaysym = "O";*/
                player[1] = "O";
                player[2] = "X";
                valtouse = false;
                /*TextView v = (TextView) findViewById(R.id.player_turn_id);
                v.setText("O");*/
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MFourActivity.this, "Player Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void cell_clicked(View v) {
        cell_clicked2(v);

        TextView k = (TextView)findViewById(R.id.player_turn_id);
        if(gamewon || gamedrawn) {
            k.setText(R.string.game_over);
        }

    }


    private void cell_clicked2(View vv) {
        SharedPreferences sharedPref = getSharedPreferences( "tttm4", Context.MODE_PRIVATE);
        SharedPreferences.Editor storage = sharedPref.edit();

        if (gamewon) {

        } else if (taptimes == 16 && !gamewon) {
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

            if (taptimes < 16 && TextUtils.isEmpty(v.getText().toString())) {
                //store the played cell and set the next player
                String playsym;
                if (valtouse) {
                    playsym = player[1];
                    //playsym = "X";
                } else {
                    playsym = player[2];
                    //playsym = "O";
                }

                int tag = Integer.parseInt(v.getTag().toString());
                asv[tag] = playsym;

                v.setText(playsym);
                valtouse = !valtouse;

                TextView pti = (TextView) findViewById(R.id.player_turn_id);
                String pt = valtouse ? "Player 1" : "Player 2";
                pti.setText(pt);

                taptimes++;

            } else if (taptimes >= 16) {
                Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();

            }

            else {
                Toast.makeText(this, "Already played cell", Toast.LENGTH_SHORT).show();
            }

            TextView pti = (TextView) findViewById(R.id.player_turn_id);
            if(!gamedrawn) {
                String pt = valtouse ? "Player 1" : "Player 2";
                pti.setText(pt); //this guy sets the player turn
            } else {
                String pt = "Game Over";
                pti.setText(pt);
            }


            //Array e =
            //Object e = new Object();
            int[][] wi = {
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16},
                    {1, 5, 9, 13},
                    {2, 6, 10, 14},
                    {3, 7, 11, 15},
                    {4, 8, 12, 16},
                    {1, 6, 11, 16},
                    {4, 7, 10, 13}
            };


            for (int[] i : wi) {
                int a, b, c, d;
                a = i[0];
                b = i[1];
                c = i[2];
                d = i[3];

                if (!TextUtils.isEmpty(asv[a]) && asv[a] == asv[b] && asv[a] == asv[c] && asv[a] == asv[d]) {

                    String p = asv[a] == player[1] ? "Player 1" : "Player 2";
                    String winner = "Winner: " + p;

                    //String winner = "Winner: " + asv[a];
                    String pp = p == "Player 1" ? "player1" : "player2";
                    String op = p == "Player 1" ? "player2" : "player1";

                    int vp = sharedPref.getInt(pp+"wins", 0);
                    int vpl = sharedPref.getInt(op+"losses", 0);
                    vp++;
                    vpl++;


                    storage.putInt(pp+"wins",vp);
                    storage.putInt(op+"losses",vpl);
                    storage.apply();


                    Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                    gamewon = true;

                    TextView win = (TextView)findViewById(R.id.winner_status);
                    win.setText(winner);

                    break;
                }

                /*if (!TextUtils.isEmpty(asv[a]) && asv[a] != asv[b] && asv[a] != asv[c]) {

                }*/

            }

            if (taptimes == 16 && !gamewon) {
                Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();

                int vp1 = sharedPref.getInt("player1draws", 0);
                int vp2 = sharedPref.getInt("player2draws", 0);
                vp1++;
                vp2++;
                storage.putInt("player1draws",vp1);
                storage.putInt("player2draws",vp2);
                storage.apply();

                gamedrawn = true;
                String pt = "Game Over";
                pti.setText(pt);
            }


        }
    }

    private void reset_game() {
        gamewon = false;
        TextView win = (TextView)findViewById(R.id.winner_status);
        win.setText("");
        taptimes = 0;
        gamedrawn = false;

        valtouse = true;
        TextView pti = (TextView) findViewById(R.id.player_turn_id);
        String pt = valtouse ? "Player 1" : "Player 2";
        pti.setText(pt);

        asv = new String[17];

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

        tv = (TextView) findViewById(R.id.c10);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c11);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c12);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c13);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c14);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c15);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c16);
        tv.setText("");


    }
}
