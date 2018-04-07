package com.derskeal.tictactoe;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
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

import java.util.Random;

public class SFiveActivity extends AppCompatActivity {

    public boolean valtouse = true;
    public int taptimes = 0;

    public String[] asv = new String[26]; //asv - all squares value
    public boolean gamewon = false;

    //the above two perform the same function as the below, therefore:
    public String defplaysym;

    public int r1;
    public boolean player_played;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mfive);

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


    public void avatar_select() {
        //Toast.makeText(this, "chai", Toast.LENGTH_SHORT).show();
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Please select your Player Symbol")
                .setTitle("Player");

        builder.setPositiveButton("X", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the player symbol to X
                //player = "X";
                //playsym = "X";
                defplaysym = "X";
                valtouse = true;
                /*TextView v = (TextView) findViewById(R.id.player_turn_id);
                v.setText("");*/
            }
        });

        builder.setNegativeButton("O", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //set the player symbol to O
                // player = "O";
                //playsym = "O";
                defplaysym = "O";
                valtouse = false;
                /*TextView v = (TextView) findViewById(R.id.player_turn_id);
                v.setText("O");*/
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SFiveActivity.this, "Player Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void cell_clicked(View v) {
        cell_clicked2(v);

        TextView k = (TextView)findViewById(R.id.player_turn_id);
        if(!gamewon && taptimes < 25) {
            k.setText("Computer is playing");
        } else if (gamewon && taptimes >= 25) {
            k.setText("Game Over");
        } else if (!gamewon && taptimes >= 25) {
            /*Toast.makeText(this, "Gamez Ova", Toast.LENGTH_SHORT).show();
            k.setText("Game Over");*/
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                ai_play();
            }
        }, 1000);

    }


    public void cell_clicked2(View vv) {

        SharedPreferences sharedPref = getSharedPreferences( "ttts4", this.MODE_PRIVATE);
        SharedPreferences.Editor storage = sharedPref.edit();

        if (gamewon) {

        } else if (taptimes == 25 && !gamewon) {
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
            TextView pti = (TextView) findViewById(R.id.player_turn_id);

            if (taptimes < 25 && TextUtils.isEmpty(v.getText().toString())) {
                String playsym;
                if (valtouse) {
                    playsym = "X";
                } else {
                    playsym = "O";
                }

                int tag = Integer.parseInt(v.getTag().toString());
                asv[tag] = playsym;

                v.setText(playsym);
                valtouse = !valtouse;

                //TextView pti = (TextView) findViewById(R.id.player_turn_id);
                String pt = valtouse ? "Player 1" : "Computer";
                pti.setText(pt);
                taptimes++;

            } else if (taptimes >= 16) {
                Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();
            }

            else {
                Toast.makeText(this, "Already played cell", Toast.LENGTH_SHORT).show();
            }



            //Array e =
            //Object e = new Object();
            int[][] wi = {
                    {1, 2, 3, 4, 5},
                    {6, 7, 8, 9, 10},
                    { 11, 12, 13, 14, 15},
                    {16, 17, 18, 19, 20},
                    {21, 22, 23, 24, 25},
                    {1, 6, 11, 16, 21},
                    {2, 7, 12, 17, 22},
                    {3, 8, 13, 18, 23},
                    {4, 9, 14, 19, 24},
                    {5, 10, 15, 20, 25},
                    {1, 7, 13, 19, 25},
                    {5, 9, 13, 17, 21},
            };


            for (int[] i : wi) {
                int a, b, c, d, e;
                a = i[0];
                b = i[1];
                c = i[2];
                d = i[3];
                e = i[4];

                if (!TextUtils.isEmpty(asv[a]) && asv[a] == asv[b] && asv[a] == asv[c] && asv[a] == asv[d] && asv[a] == asv[e]) {

                    String p = asv[a] == defplaysym ? "Player 1" : "Computer";
                    String winner = "Winner: " + p;

                    Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                    gamewon = true;

                    TextView win = (TextView)findViewById(R.id.winner_status);
                    win.setText(winner);

                    pti.setText("Game Over");

                    String pp = p == "Player 1" ? "player1" : "computer";
                    String op = p == "Player 1" ? "computer" : "player1";

                    int vp = sharedPref.getInt(pp+"wins", 0);
                    int vpl = sharedPref.getInt(op+"losses", 0);
                    vp++;
                    vpl++;


                    storage.putInt(pp+"wins",vp);
                    storage.putInt(op+"losses",vpl);
                    storage.apply();



                    break;
                }

                /*if (!TextUtils.isEmpty(asv[a]) && asv[a] != asv[b] && asv[a] != asv[c]) {

                }*/

            }

            if (!gamewon && taptimes >= 16) {
                //Toast.makeText(this, "Gamez Ova", Toast.LENGTH_SHORT).show();
                //TextView k = (TextView)findViewById(R.id.player_turn_id);
                pti.setText("Game Over");

                int vp1 = sharedPref.getInt("player1draws", 0);
                int vp2 = sharedPref.getInt("computerdraws", 0);
                vp1++;
                vp2++;
                storage.putInt("player1draws",vp1);
                storage.putInt("computerdraws",vp2);
                storage.apply();
            }

            /*if (taptimes == 16 && !gamewon) {
                Toast.makeText(this, "Game Over. It's a draw", Toast.LENGTH_SHORT).show();
            }*/


        }
    }

    public void reset_game() {
        gamewon = false;
        TextView win = (TextView)findViewById(R.id.winner_status);
        win.setText("");
        taptimes = 0;



        valtouse = true;
        TextView pti = (TextView) findViewById(R.id.player_turn_id);
        String pt = valtouse ? "Player 1" : "Computer";
        pti.setText(pt);

        asv = new String[26];


        int[] allcells = get_cells_in_array();

        for (int c :
                allcells) {
            try {
                TextView tv = (TextView) findViewById(c);
                tv.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        /*TextView tv = (TextView) findViewById(R.id.c1);
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

        tv = (TextView) findViewById(R.id.c17);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c18);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c19);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c20);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c21);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c22);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c23);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c24);
        tv.setText("");

        tv = (TextView) findViewById(R.id.c25);
        tv.setText("");        */

    }

    public int[] get_cells_in_array() {

        int c1 = R.id.c1;
        int c2 = R.id.c2;
        int c3 = R.id.c3;
        int c4 = R.id.c4;
        int c5 = R.id.c5;
        int c6 = R.id.c6;
        int c7 = R.id.c7;
        int c8 = R.id.c8;
        int c9 = R.id.c9;
        int c10 = R.id.c10;
        int c11 = R.id.c11;
        int c12 = R.id.c12;
        int c13 = R.id.c13;
        int c14 = R.id.c14;
        int c15 = R.id.c15;
        int c16 = R.id.c16;
        int c17 = R.id.c17;
        int c18 = R.id.c18;
        int c19 = R.id.c19;
        int c20 = R.id.c20;
        int c21 = R.id.c21;
        int c22 = R.id.c22;
        int c23 = R.id.c23;
        int c24 = R.id.c24;
        int c25 = R.id.c25;


        //int[] cs = {0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25};


        //int[] a = {2,3};
        return new int[] {0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25};
    }

    public void ai_play() {

        int[] cs = get_cells_in_array(); //{0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25};


        Random rand = new Random();


        boolean akpos = false;
        while (!akpos) {
            r1 = rand.nextInt(25);
            if (r1 != 0 && TextUtils.isEmpty(asv[r1])) {
                akpos = true;
            } else if(!TextUtils.isEmpty(asv[1]) && !TextUtils.isEmpty(asv[2]) && !TextUtils.isEmpty(asv[3]) && !TextUtils.isEmpty(asv[4])
                    && !TextUtils.isEmpty(asv[5]) && !TextUtils.isEmpty(asv[6]) && !TextUtils.isEmpty(asv[7]) && !TextUtils.isEmpty(asv[8])
                    && !TextUtils.isEmpty(asv[9]) && !TextUtils.isEmpty(asv[10]) && !TextUtils.isEmpty(asv[11]) && !TextUtils.isEmpty(asv[12])
                    && !TextUtils.isEmpty(asv[13]) && !TextUtils.isEmpty(asv[14]) && !TextUtils.isEmpty(asv[15]) && !TextUtils.isEmpty(asv[16])
                    && !TextUtils.isEmpty(asv[17]) && !TextUtils.isEmpty(asv[18]) && !TextUtils.isEmpty(asv[19]) && !TextUtils.isEmpty(asv[20])
                    && !TextUtils.isEmpty(asv[21]) && !TextUtils.isEmpty(asv[22]) && !TextUtils.isEmpty(asv[23]) && !TextUtils.isEmpty(asv[24])
                    && !TextUtils.isEmpty(asv[25]) )

            {
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
