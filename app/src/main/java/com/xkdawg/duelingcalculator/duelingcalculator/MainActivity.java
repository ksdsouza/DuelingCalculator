package com.xkdawg.duelingcalculator.duelingcalculator;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
/**
 * This file was created by <xkdawg>.
 * File created on 2015-12-28.
 *
 * This file holds all methods related to the main menu of the app.
 */
public class MainActivity extends AppCompatActivity {
    private String mathNum = "";
    private String buttonID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow(); //Used to change status bar color to match app theme
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
    }

    //  Allows user to enter LP changes
    //
    public void doMath(View view) {
        buttonID = getResources().getResourceEntryName(view.getId()); //Get the player whose LP is being changed
        Intent intent = new Intent(this, Math.class);   //Create a new intent for the math activity
        TextView textView;   //create a textview to get player's current LP

        if (buttonID.equals("p1math")) { //check if we're doing math on p1 or p2
            textView = (TextView) findViewById(R.id.p1LP);
        } else {
            textView = (TextView) findViewById(R.id.p2LP);
        }

        String lp = textView.getText().toString(); //get player's lp from the textview
        intent.putExtra("Life Points", lp); //send the life points to the new intent under the tag 'Life Points'
        intent.putExtra("title", buttonID); //send the buttonID to the new intent under the tag 'title'

        startActivityForResult(intent, 1); //Start the new activity. When activity finishes, get the resulting value
        if (buttonID.equals("p1math")) { //Override default transition, replace it with custom sliding animation
            overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        } else {
            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) { //LP changes are request code 1. Currently only request code, but may add more in future.
            if (resultCode == Activity.RESULT_OK) { //Make sure we're getting the correct resultCode. Hackers are bad.
                mathNum = data.getStringExtra("result");    //get the result of math.
                TextView textView;                   // create a text view.
                if (buttonID.equals("p1math")) {            // to display result on correct player's LP
                    textView = (TextView) findViewById(R.id.p1LP);
                } else {
                    textView = (TextView) findViewById(R.id.p2LP);
                }
                textView.setText(mathNum); //Set the new LP on the player
            }
            if (resultCode == Activity.RESULT_CANCELED) { //debug only. Do not worry.
                Logger.info("MainActivity","Nothing Happened");
            }
        }
    }
    // Allow user to quickly halve the LP of a player
    //
    public void division(View view) {
        TextView textView;
        if (getResources().getResourceEntryName(view.getId()).equals("p1division")) { //Are we halving p1 or p2?
            textView = (TextView) findViewById(R.id.p1LP);
        } else {
            textView = (TextView) findViewById(R.id.p2LP);
        }
        int lp = Integer.parseInt(textView.getText().toString());   //Get player's LP
        lp /= 2;                                                    //and halve it
        MediaPlayer damagefx = MediaPlayer.create(this, R.raw.damage);//Play the damage sound fx
        damagefx.start();
        textView.setText("" + lp);  //Display new LP.
    }
    // Allow user to reset scoreboard
    //
    public void reset(View view) {  //Self explanatory
        TextView player1LP = (TextView) findViewById(R.id.p1LP);
        TextView player2LP = (TextView) findViewById(R.id.p2LP);

        player1LP.setText("8000");
        player2LP.setText("8000");

    }
    // Open dialog to flip a coin
    public void coinToss(View view) {
        Intent intent = new Intent(this, CoinToss.class);
        startActivity(intent);
    }
    // Create a notification, displaying the result of a simulated dice roll
    //
    public void diceRoll(View view) {
        Double random = java.lang.Math.random(); //Generate a random double, determine the value it corresponds to on a dice.

        if (random < (1 / 6f)) {
            Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
        } else if (random < (2 / 6f)) {
            Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
        } else if (random < (3 / 6f)) {
            Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
        } else if (random < (4 / 6f)) {
            Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
        } else if (random < (5 / 6f)) {
            Toast.makeText(MainActivity.this, "5", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "6", Toast.LENGTH_SHORT).show();
        }

    }

}
