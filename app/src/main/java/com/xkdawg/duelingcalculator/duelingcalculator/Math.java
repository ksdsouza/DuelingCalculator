package com.xkdawg.duelingcalculator.duelingcalculator;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
/**
 * This file was created by <xkdawg>.
 * File created on 2015-12-28.
 *
 * This file holds all methods related to calculating life points and the math activity.
 */

public class Math extends AppCompatActivity {
    public String num2 = ""; //Display the second operand (first is player's current LP)
    public String operation = ""; // Store the operation we are performing (addition or subtraction)
    private String player;        // Store the player we're dealing with
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        TextView title = (TextView) findViewById(R.id.mathTitle);   //Create a text view to edit the title
        TextView textView = (TextView) findViewById(R.id.playerLP); //Create a text view for player's current LP

        Intent intent = getIntent();                                // Get all data passed into intent

        String buttonFrom = intent.getStringExtra("title");         // Get data under tag 'title'<Which player are we dealing with>
        String playerLP = intent.getStringExtra("Life Points");     // Get data under tag 'Life Points'<Player's current LP>

        if (buttonFrom.equals("p1math")) {
            title.setText("Player 1");
            player="1";
        } else {
            title.setText("Player 2");
            player="2";
        }
        textView.setText(playerLP);
    }
    // Perform addition instead of subtraction(default), and display the change on-screen
    //
    public void addOperation(View view) {
        TextView opTextView = (TextView) findViewById(R.id.operation);
        opTextView.setText(" + ");
        operation = "+";
    }
    // Perform subtraction, and display the change on-screen
    public void subtractOperation(View view) {
        TextView opTextView = (TextView) findViewById(R.id.operation);
        opTextView.setText(" - ");
        operation = "-";
    }

    // Create the second operand as the user punches numbers into the calculator
    //
    public void createNum(View view) {
        // Get the view id passed into the method, concatenate the corresponding number to a string containing the entire number
        //
        switch (view.getId()) {
            case R.id.math_1: {
                num2 += "1";
                break;
            }
            case R.id.math_2: {
                num2 += "2";
                break;
            }
            case R.id.math_3: {
                num2 += "3";
                break;
            }
            case R.id.math_4: {
                num2 += "4";
                break;
            }
            case R.id.math_5: {
                num2 += "5";
                break;
            }
            case R.id.math_6: {
                num2 += "6";
                break;
            }
            case R.id.math_7: {
                num2 += "7";
                break;
            }
            case R.id.math_8: {
                num2 += "8";
                break;
            }
            case R.id.math_9: {
                num2 += "9";
                break;
            }
            default:
                num2 += "0";
        }
        TextView textView = (TextView) findViewById(R.id.num2);
        textView.setText(num2); // Update second operand as numbers are punched in
    }
    // Clear second operand
    //
    public void clearNum(View view) {
        TextView textView = (TextView) findViewById(R.id.num2);
        textView.setText("");
        num2 = "";
    }
    // Perform math, return to the main activity.
    //
    public void solve(View view) throws InterruptedException {
        TextView textView = (TextView) findViewById(R.id.playerLP);
        int playerLP = Integer.parseInt(textView.getText().toString()); // Get player's current LP as integer
        MediaPlayer damagefx = MediaPlayer.create(this, R.raw.damage);  // Create 2 mediaplayers for a damage fx and a heal fx
        MediaPlayer gainfx = MediaPlayer.create(this, R.raw.gainlp);    // Inefficient memory usage. Fix later
        try {
            if (operation.equals("+")) { // Check operation, do math, play corresponding sound fx
                playerLP += Integer.parseInt(num2);
                gainfx.start();
            } else {
                playerLP -= Integer.parseInt(num2);
                damagefx.start();
            }
        } catch (Exception e) {
            finish();
        }
        if (playerLP <= 0) { // make sure LP does not go below 0
            playerLP = 0;
        }
        String total = "" + playerLP;
        clearNum(null);
        Intent resultIntent = new Intent(); //send the results as an intent back to the main activity
        resultIntent.putExtra("result", total); // send total under tag 'result'
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
    // Play animation when leaving this activity
    //
    @Override
    public void onPause() {
        super.onPause();
        finish();
        if(player.equals("1")){
            this.overridePendingTransition(R.anim.slide_in_from_right,R.anim.slide_out_to_left);
        }
        else{
            this.overridePendingTransition(R.anim.slide_in_from_left,R.anim.slide_out_to_right);
        }
    }

}
