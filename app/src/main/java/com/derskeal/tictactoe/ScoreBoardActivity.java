package com.derskeal.tictactoe;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        setTheStage();
    }

    //@// TODO: 05/04/2018 revamp the below code to reflect the new scoreboard structure
    public void setTheStage() {

        //SINGLE PLAYER
        //3x3 box section

        SharedPreferences sp = getSharedPreferences("ttts3", this.MODE_PRIVATE);

        int p1w = sp.getInt("player1wins", 0);
        int p2w = sp.getInt("computerwins", 0);

        int p1d = sp.getInt("player1draws", 0);
        int p2d = sp.getInt("computerdraws", 0);

        int p1l = sp.getInt("player1losses", 0);
        int p2l = sp.getInt("computerlosses", 0);

        TextView tv = (TextView)findViewById(R.id.sthreewinsp1);//w1);
        tv.setText(""+p1w);

        tv = (TextView)findViewById(R.id.sthreewinsp2);//w2);
        tv.setText(""+p2w);

        tv = (TextView)findViewById(R.id.sthreedrawsp1);//d1);
        tv.setText(""+p1d);

        tv = (TextView)findViewById(R.id.sthreedrawsp2);//d2);
        tv.setText(""+p2d);

        tv = (TextView)findViewById(R.id.sthreelossesp1);//l1);
        tv.setText(""+p1l);

        tv = (TextView)findViewById(R.id.sthreelossesp2);//l2);
        tv.setText(""+p2l);



        //SINGLE LAYER
        //4X4 BOX SECTION

        sp = getSharedPreferences("ttts4", this.MODE_PRIVATE);

        p1w = sp.getInt("player1wins", 0);
        p2w = sp.getInt("computerwins", 0);

        p1d = sp.getInt("player1draws", 0);
        p2d = sp.getInt("computerdraws", 0);

        p1l = sp.getInt("player1losses", 0);
        p2l = sp.getInt("computerlosses", 0);

        tv = (TextView)findViewById(R.id.sfourwinsp1);//w1);
        tv.setText(""+p1w);

        tv = (TextView)findViewById(R.id.sfourwinsp2);//w2);
        tv.setText(""+p2w);

        tv = (TextView)findViewById(R.id.sfourdrawsp1);//d1);
        tv.setText(""+p1d);

        tv = (TextView)findViewById(R.id.sfourdrawsp2);//d2);
        tv.setText(""+p2d);

        tv = (TextView)findViewById(R.id.sfourlossesp1);//l1);
        tv.setText(""+p1l);

        tv = (TextView)findViewById(R.id.sfourlossesp2);//l2);
        tv.setText(""+p2l);


        //SINGLE LAYER
        //5x5 BOX SECTION

        sp = getSharedPreferences("ttts5", this.MODE_PRIVATE);

        p1w = sp.getInt("player1wins", 0);
        p2w = sp.getInt("computerwins", 0);

        p1d = sp.getInt("player1draws", 0);
        p2d = sp.getInt("computerdraws", 0);

        p1l = sp.getInt("player1losses", 0);
        p2l = sp.getInt("computerlosses", 0);

        tv = (TextView)findViewById(R.id.sfivewinsp1);//w1);
        tv.setText(""+p1w);

        tv = (TextView)findViewById(R.id.sfivewinsp2);//w2);
        tv.setText(""+p2w);

        tv = (TextView)findViewById(R.id.sfivedrawsp1);//d1);
        tv.setText(""+p1d);

        tv = (TextView)findViewById(R.id.sfivedrawsp2);//d2);
        tv.setText(""+p2d);

        tv = (TextView)findViewById(R.id.sfivelossesp1);//l1);
        tv.setText(""+p1l);

        tv = (TextView)findViewById(R.id.sfivelossesp2);//l2);
        tv.setText(""+p2l);


        //MULTIPLAYER
        //3x3 BOX SECTION

        sp = getSharedPreferences("tttm3", this.MODE_PRIVATE);

        p1w = sp.getInt("player1wins", 0);
        p2w = sp.getInt("player2wins", 0);

        p1d = sp.getInt("player1draws", 0);
        p2d = sp.getInt("player2draws", 0);

        p1l = sp.getInt("player1losses", 0);
        p2l = sp.getInt("player2losses", 0);

        tv = (TextView)findViewById(R.id.mthreewinsp1);
        tv.setText(""+p1w);

        tv = (TextView)findViewById(R.id.mthreewinsp2);
        tv.setText(""+p2w);

        tv = (TextView)findViewById(R.id.mthreedrawsp1);
        tv.setText(""+p1d);

        tv = (TextView)findViewById(R.id.mthreedrawsp2);
        tv.setText(""+p2d);

        tv = (TextView)findViewById(R.id.mthreelossesp1);
        tv.setText(""+p1l);

        tv = (TextView)findViewById(R.id.mthreelossesp2);
        tv.setText(""+p2l);


        //MULTIPLAYER
        //4x4 BOX SECTION

        sp = getSharedPreferences("tttm4", this.MODE_PRIVATE);

        p1w = sp.getInt("player1wins", 0);
        p2w = sp.getInt("player2wins", 0);

        p1d = sp.getInt("player1draws", 0);
        p2d = sp.getInt("player2draws", 0);

        p1l = sp.getInt("player1losses", 0);
        p2l = sp.getInt("player2losses", 0);

        tv = (TextView)findViewById(R.id.mfourwinsp1);
        tv.setText(""+p1w);

        tv = (TextView)findViewById(R.id.mfourwinsp2);
        tv.setText(""+p2w);

        tv = (TextView)findViewById(R.id.mfourdrawsp1);
        tv.setText(""+p1d);

        tv = (TextView)findViewById(R.id.mfourdrawsp2);
        tv.setText(""+p2d);

        tv = (TextView)findViewById(R.id.mfourlossesp1);
        tv.setText(""+p1l);

        tv = (TextView)findViewById(R.id.mfourlossesp2);
        tv.setText(""+p2l);


        //MULTIPLAYER
        //5x5 BOX SECTION

        sp = getSharedPreferences("tttm5", this.MODE_PRIVATE);

        p1w = sp.getInt("player1wins", 0);
        p2w = sp.getInt("player2wins", 0);

        p1d = sp.getInt("player1draws", 0);
        p2d = sp.getInt("player2draws", 0);

        p1l = sp.getInt("player1losses", 0);
        p2l = sp.getInt("player2losses", 0);

        tv = (TextView)findViewById(R.id.mfivewinsp1);
        tv.setText(""+p1w);

        tv = (TextView)findViewById(R.id.mfivewinsp2);
        tv.setText(""+p2w);

        tv = (TextView)findViewById(R.id.mfivedrawsp1);
        tv.setText(""+p1d);

        tv = (TextView)findViewById(R.id.mfivedrawsp2);
        tv.setText(""+p2d);

        tv = (TextView)findViewById(R.id.mfivelossesp1);
        tv.setText(""+p1l);

        tv = (TextView)findViewById(R.id.mfivelossesp2);
        tv.setText(""+p2l);
    }
}
