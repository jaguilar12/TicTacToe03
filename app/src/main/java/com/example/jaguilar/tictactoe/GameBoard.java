package com.example.jaguilar.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameBoard extends AppCompatActivity
{
    public boolean playerTurn = true;
    public GamePiece[] gps = new GamePiece[9];
    public GamePiece test;
    public int drawCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        SetUpGame();
        CreateListeners();

        TextView p = (TextView)findViewById(R.id.winnerTextView);
        p.setText("Player 1's Turn");
        TextView b = (TextView)findViewById(R.id.twoName);
        b.setVisibility(View.INVISIBLE);
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

        for(int j = 0; j < 9; j++)
        {
            gps[j] = new GamePiece();
        }
    }

    public Bitmap DecodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public void CreateListeners()
    {
        gps[0].img = (ImageView) findViewById(R.id.topLeft);
        gps[1].img = (ImageView) findViewById(R.id.topCenter);
        gps[2].img = (ImageView) findViewById(R.id.topRight);
        gps[3].img = (ImageView) findViewById(R.id.centerLeft);
        gps[4].img = (ImageView) findViewById(R.id.centerCenter);
        gps[5].img = (ImageView) findViewById(R.id.centerRight);
        gps[6].img = (ImageView) findViewById(R.id.bottomLeft);
        gps[7].img = (ImageView) findViewById(R.id.bottomCenter);
        gps[8].img = (ImageView) findViewById(R.id.bottomRight);
        for(int j = 0; j < 9; j++)
        {
            SetListener(gps[j]);
        }
    }

    public void SetListener(final GamePiece gp)
    {
        gp.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetImage(gp);
            }
        });
    }

    public void SetImage(GamePiece gp)
    {
        if(!gp.isSet)
        {
            SharedPreferences sharedPreferences =
                    this.getSharedPreferences("com.example.datastorageexample",
                            Context.MODE_PRIVATE);

            if(playerTurn)
            {
                String string1 = sharedPreferences.getString("imgOne", "");
                Bitmap bit1 = DecodeBase64(string1);
                ImageView test03 = gp.img;
                test03.setImageBitmap(bit1);

                gp.gp = 1;
                gp.isSet = true;

                playerTurn = !playerTurn;
                TextView p = (TextView)findViewById(R.id.winnerTextView);
                TextView a = (TextView)findViewById(R.id.twoName);
                a.setVisibility(View.VISIBLE);
                TextView b = (TextView)findViewById(R.id.oneName);
                b.setVisibility(View.INVISIBLE);
                p.setText("Player 2's Turn");
            }else if(!playerTurn)
            {
                String string2 = sharedPreferences.getString("imgTwo", "");
                Bitmap bit1 = DecodeBase64(string2);
                ImageView test04 = gp.img;
                test04.setImageBitmap(bit1);

                gp.gp = 2;
                gp.isSet = true;

                playerTurn = !playerTurn;
                TextView p = (TextView)findViewById(R.id.winnerTextView);
                TextView a = (TextView)findViewById(R.id.oneName);
                a.setVisibility(View.VISIBLE);
                TextView b = (TextView)findViewById(R.id.twoName);
                b.setVisibility(View.INVISIBLE);
                p.setText("Player 1's Turn");
            }
            Win();
            drawCount += 1;
        }
    }

    public boolean CheckForWin(int j)
    {
        boolean win = false;
        if(gps[0].gp == j && gps[1].gp == j && gps[2].gp == j)
        {
            win = true;
        }else if(gps[3].gp == j && gps[4].gp == j && gps[5].gp == j)
        {
            win = true;
        }else if(gps[6].gp == j && gps[7].gp == j && gps[8].gp == j)
        {
            win = true;
        }else if(gps[0].gp == j && gps[3].gp == j && gps[6].gp == j)
        {
            win = true;
        }else if(gps[1].gp == j && gps[4].gp == j && gps[7].gp == j)
        {
            win = true;
        }else if(gps[2].gp == j && gps[5].gp == j && gps[8].gp == j)
        {
            win = true;
        }else if(gps[0].gp == j && gps[4].gp == j && gps[8].gp == j)
        {
            win = true;
        }else if(gps[2].gp == j && gps[4].gp == j && gps[6].gp == j)
        {
            win = true;
        }
        return win;
    }

    public void Win()
    {
        TextView winner = (TextView)findViewById(R.id.winnerTextView);
        if(drawCount < 8)
        {
            if(CheckForWin(1))
            {
                winner.setText("Winner \nPlayer 1");
                SetPlayAgainButton();
            }else if(CheckForWin(2))
            {
                winner.setText("Winner\nPlayer 2");
                SetPlayAgainButton();
            }
        }else if(drawCount >= 8)
        {
            winner.setText("Draw");
            SetPlayAgainButton();
        }
    }

    public void SetPlayAgainButton()
    {
        Button butt = (Button)findViewById(R.id.playAgainButton);
        butt.setVisibility(View.VISIBLE);
    }

    public void playAgainOnClick(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
