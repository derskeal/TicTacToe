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

    public void setTheStage() {
        SharedPreferences sp = getSharedPreferences("ttts", this.MODE_PRIVATE);

        int p1w = sp.getInt("player1wins", 0);
        int p2w = sp.getInt("player2wins", 0);

        int p1d = sp.getInt("player1draws", 0);
        int p2d = sp.getInt("player2draws", 0);

        int p1l = sp.getInt("player1losses", 0);
        int p2l = sp.getInt("player2losses", 0);

        TextView tv = (TextView)findViewById(R.id.w1);
        tv.setText(""+p1w);

        tv = (TextView)findViewById(R.id.w2);
        tv.setText(""+p2w);

        tv = (TextView)findViewById(R.id.d1);
        tv.setText(""+p1d);

        tv = (TextView)findViewById(R.id.d2);
        tv.setText(""+p2d);

        tv = (TextView)findViewById(R.id.l1);
        tv.setText(""+p1l);

        tv = (TextView)findViewById(R.id.l2);
        tv.setText(""+p2l);
    }
}
