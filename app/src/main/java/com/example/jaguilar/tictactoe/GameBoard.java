package com.example.jaguilar.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameBoard extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        SetUpGame();
    }

    public void SetUpGame()
    {
        SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.example.datastorageexample",
                        Context.MODE_PRIVATE);
        String usernameOne = sharedPreferences.getString("one", "");
        String usernameTwo = sharedPreferences.getString("two", "");
        TextView test01 = (TextView)findViewById(R.id.oneName);
        TextView test02 = (TextView)findViewById(R.id.twoName);
        test01.setText(usernameOne);
        test02.setText(usernameTwo);
    }
}
