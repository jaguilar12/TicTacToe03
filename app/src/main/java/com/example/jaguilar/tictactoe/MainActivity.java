package com.example.jaguilar.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    public Spinner spin1;
    public Spinner spin2;
    public ArrayAdapter <String> adapter;
    public ImageView gp1;
    public ImageView gp2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gp1 = (ImageView)findViewById(R.id.PlayerOneGamePiece);
        gp2 = (ImageView)findViewById(R.id.playerTwoGamePiece);

        String[] gamePieces = {"Circle", "Square", "Triangle", "Pentagon"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gamePieces);
        spin1 = (Spinner)findViewById(R.id.spinnerPlayerOne);
        spin1.setAdapter(adapter);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                ChooseGamePiece(i, gp1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
        spin2 = (Spinner)findViewById(R.id.spinnerPlayerTwo);
        spin2.setAdapter(adapter);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                ChooseGamePiece(i, gp2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    public void ChooseGamePiece(int i, ImageView gp)
    {
        switch (i)
        {
            case 0:
                gp.setImageResource(R.drawable.circle);
                break;
            case 1:
                gp.setImageResource(R.drawable.square);
                break;
            case 2:
                gp.setImageResource(R.drawable.triangle);
                break;
            case 3:
                gp.setImageResource(R.drawable.pentagon);
                break;
        }
    }

    public void CreateSharedPref()
    {
        SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.example.datastorageexample",
                        Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("one", ((TextView)findViewById(R.id.playerOneName)).getText().toString()).apply();
        sharedPreferences.edit().putString("two", ((TextView)findViewById(R.id.playerTwoName)).getText().toString()).apply();
    }

    public void playGameOnClick(View view)
    {
        CreateSharedPref();
        Intent intent = new Intent(this, GameBoard.class);
        startActivity(intent);
    }
}
