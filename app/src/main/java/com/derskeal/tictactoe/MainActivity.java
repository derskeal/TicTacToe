package com.derskeal.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //launch the activities
    public void play_3x3(View v) {
        Intent intent = new Intent(this, MThreeActivity.class);
        startActivity(intent);
    }

    public void play_4x4(View v) {
        Intent intent = new Intent(this, MFourActivity.class);
        startActivity(intent);
    }

    public void play_5x5(View v) {
        Intent intent = new Intent(this, MFiveActivity.class);
        startActivity(intent);
    }

}
