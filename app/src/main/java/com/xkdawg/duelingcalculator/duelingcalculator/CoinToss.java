package com.xkdawg.duelingcalculator.duelingcalculator;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * This file was created by <xkdawg>.
 * File created on 2016-02-17.
 *
 * This file holds all methods related to flipping a coin.
 */
public class CoinToss extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        // Generate random number, determine coin toss result.
        // Display result as picture dialog
        Double random = java.lang.Math.random();
        ImageView imgVw = (ImageView) findViewById(R.id.coin);
        if (random > .5f) {
            imgVw.setImageResource(R.drawable.heads);
            imgVw.setVisibility(View.VISIBLE);
        } else {
            imgVw.setImageResource(R.drawable.tails);
            imgVw.setVisibility(View.VISIBLE);
        }

        MediaPlayer coinfx = MediaPlayer.create(this, R.raw.coin); // play a sound effect when a coin is tossed.
        try {
            coinfx.start();
        } catch (Exception e) {
            Logger.error("CoinToss",e.toString());
        }

    }
}


