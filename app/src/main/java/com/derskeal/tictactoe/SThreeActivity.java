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
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * This class handles the tictactoe box size 3 single player
 */
public class SThreeActivity extends AppCompatActivity {

    private boolean valtouse = true;
    private int taptimes = 0;

    private String[] asv = new String[10]; //asv - all squares value
    private boolean gamewon = false;
    private boolean gamedrawn = false;

    private String playsym;

    //anywhere you find defplaysym, it is performing the same function as the player variable
    private String defplaysym;

    private int r1;
    private boolean player_played;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mthree);

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


    private void avatar_select() {
        //Toast.makeText(this, "chai", Toast.LENGTH_SHORT).show();
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Please select your Player Symbol")
                .setTitle("Player");

        builder.setPositiveButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the player symbol to X
                playsym = "X";
                defplaysym = "X";
                valtouse = true;
                /*TextView v = (TextView) findViewById(R.id.player_turn_id);
                v.setText("X");*/
            }
        });

        builder.setNegativeButton("O", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the player symbol to O
                playsym = "O";
                defplaysym = "O";
                valtouse = false;
                /*TextView v = (TextView) findViewById(R.id.player_turn_id);
                v.setText("O");*/
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

    public void cell_clicked(View v) {
        if(!gamewon && !gamedrawn) {
            cell_clicked2(v);
        }


        TextView k = (TextView)findViewById(R.id.player_turn_id);
        if (!gamewon && !gamedrawn) {
            k.setText(R.string.comp_play_status);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ai_play();
                }
            }, 1000);
        }

        if(gamewon || gamedrawn) {
            k.setText(R.string.game_over);
        }
    }


    private void cell_clicked2(View vv) {
        SharedPreferences sharedPref = getSharedPreferences( "ttts3", Context.MODE_PRIVATE);
        SharedPreferences.Editor storage = sharedPref.edit();

        TextView pti = (TextView) findViewById(R.id.player_turn_id);

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


                String pt = valtouse ? "Player 1" : "Computer";
                pti.setText(pt);

                taptimes++;


            } else if (taptimes >= 9) {
                Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();

                //save the draw
                int vp1 = sharedPref.getInt("player1draws", 0);
                int vp2 = sharedPref.getInt("computerdraws", 0);
                vp1++;
                vp2++;
                storage.putInt("player1draws",vp1);
                storage.putInt("computerdraws",vp2);
                storage.apply();
                gamedrawn = true;

                pti.setText(R.string.game_over);
            }

            else {
                Toast.makeText(this, "Already played cell", Toast.LENGTH_SHORT).show();
            }

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
                    //String winner = "Winner: " + asv[a];
                    String p = asv[a] == defplaysym ? "Player 1" : "Computer";
                    String winner = "Winner: " + p;

                    String pp = p == "Player 1" ? "player1" : "computer";
                    String op = p == "Player 1" ? "computer" : "player1";

                    int vp = sharedPref.getInt(pp+"wins", 0);
                    int vpl = sharedPref.getInt(op+"losses", 0);
                    vp++;
                    vpl++;

                    storage.putInt(pp+"wins",vp);
                    storage.putInt(op+"losses",vpl);
                    storage.apply();

                    Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                    gamewon = true;
                    valtouse = true;

                    TextView win = (TextView)findViewById(R.id.winner_status);
                    win.setText(winner);

                    pti.setText(R.string.game_over);

                    break;
                }

                /*if (!TextUtils.isEmpty(asv[a]) && asv[a] != asv[b] && asv[a] != asv[c]) {

                }*/

            }

            /*if (taptimes == 9 && !gamewon) {
                Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();
                gamedrawn = true;
            }*/


        }
        player_played = true;


    }

    private void reset_game() {
        gamewon = false;
        TextView win = (TextView)findViewById(R.id.winner_status);
        win.setText("");
        taptimes = 0;
        gamedrawn = false;
        //valtouse = defplaysym == "X";
        TextView pti = (TextView) findViewById(R.id.player_turn_id);
        //String pt = valtouse ? "Player 1" : "Computer";
        String pt = "Player 1";

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


    private void ai_play() {
        int c1 = R.id.c1;
        int c2 = R.id.c2;
        int c3 = R.id.c3;
        int c4 = R.id.c4;
        int c5 = R.id.c5;
        int c6 = R.id.c6;
        int c7 = R.id.c7;
        int c8 = R.id.c8;
        int c9 = R.id.c9;

        int[] cs = {0, c1, c2, c3, c4, c5, c6, c7, c8, c9};


        Random rand = new Random();


        boolean akpos = false;
        while (!akpos) {
            r1 = rand.nextInt(9);
            if (r1 != 0 && TextUtils.isEmpty(asv[r1])) {
                akpos = true;
            } else if(!TextUtils.isEmpty(asv[1]) && !TextUtils.isEmpty(asv[2]) && !TextUtils.isEmpty(asv[3]) && !TextUtils.isEmpty(asv[4]) && !TextUtils.isEmpty(asv[5]) && !TextUtils.isEmpty(asv[6]) && !TextUtils.isEmpty(asv[7]) && !TextUtils.isEmpty(asv[8]) && !TextUtils.isEmpty(asv[9])){
                break;
            }
        }

        try {
            player_played = false;

            TextView v = (TextView) findViewById(cs[r1]);
            //v.performClick();
            cell_clicked2(v);
        } catch (Exception e) {
            //Toast.makeText(SThreeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
}
