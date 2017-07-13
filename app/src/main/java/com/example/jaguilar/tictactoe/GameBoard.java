package com.example.jaguilar.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
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
        String string1 = sharedPreferences.getString("imgOne", "");
        Bitmap bit1 = DecodeBase64(string1);
        ImageView test03 = (ImageView)findViewById(R.id.centerLeft);
        TextView test01 = (TextView)findViewById(R.id.oneName);
        TextView test02 = (TextView)findViewById(R.id.twoName);
        test03.setImageBitmap(bit1);
        test01.setText(usernameOne);
        test02.setText(usernameTwo);
    }

    public Bitmap DecodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
