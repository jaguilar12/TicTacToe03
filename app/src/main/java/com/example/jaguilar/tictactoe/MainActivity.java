package com.example.jaguilar.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{
    public Spinner spin1;
    public Spinner spin2;
    public ArrayAdapter <String> adapter1;
    public ArrayAdapter <String> adapter2;
    public ImageView gp1;
    public ImageView gp2;
    public ArrayList<String> gamePieces1;
    public ArrayList<String> gamePieces2;
    //public String addThis;
    public String keepTrack1 = "Circle";
    public String keepTrack2 = "Square";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gp1 = (ImageView)findViewById(R.id.PlayerOneGamePiece);
        gp2 = (ImageView)findViewById(R.id.playerTwoGamePiece);

        //String[] gamePieces = {"Circle", "Square", "Triangle", "Pentagon"};
        gamePieces1 = new ArrayList<>(Arrays.asList("Circle", "Triangle", "Pentagon"));
        gamePieces2 = new ArrayList<>(Arrays.asList("Square", "Triangle", "Pentagon"));
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gamePieces1);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gamePieces2);
        spin1 = (Spinner)findViewById(R.id.spinnerPlayerOne);
        spin1.setAdapter(adapter1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                AddToList(adapter1.getItem(i), gamePieces2);
                RemoveFromList(gamePieces2, ChooseGamePiece(i, gp1, spin1));
                //AddToList(keepTrack1, gamePieces2);
                //keepTrack1 = spin1.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
        spin2 = (Spinner)findViewById(R.id.spinnerPlayerTwo);
        spin2.setAdapter(adapter2);
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                //ChooseGamePiece(i, gp2, spin2);
                AddToList(adapter2.getItem(i), gamePieces1);
                RemoveFromList(gamePieces1, ChooseGamePiece(i, gp2, spin2));
                //AddToList(keepTrack2, gamePieces1);
                //keepTrack2 = spin2.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    public String ChooseGamePiece(int i, ImageView gp, Spinner spin)
    {
        String test = "";
        switch (spin.getItemAtPosition(i).toString())
        {
            case "Circle":
                gp.setImageResource(R.drawable.circle);
                test = "Circle";
                break;
            case "Square":
                gp.setImageResource(R.drawable.square);
                test = "Square";
                break;
            case "Triangle":
                gp.setImageResource(R.drawable.triangle);
                test = "Triangle";
                break;
            case "Pentagon":
                gp.setImageResource(R.drawable.pentagon);
                test = "Pentagon";
                break;
        }
        return test;
    }

    public void RemoveFromList(ArrayList<String> listOfOptions, String removeThis)
    {
        for (int j = 0;  j < listOfOptions.size(); j++)
        {
            if(listOfOptions.get(j).equals(removeThis))
            {
                //addThis = listOfOptions.get(j);
                switch (listOfOptions.get(j))
                {
                    case "Circle":
                        listOfOptions.remove("Circle");
                        break;
                    case "Square":
                        listOfOptions.remove("Square");
                        break;
                    case "Triangle":
                        listOfOptions.remove("Triangle");
                        break;
                    case "Pentagon":
                        listOfOptions.remove("Pentagon");
                        break;
                }
            }
        }
    }

    public void AddToList(String exceptThis, ArrayList<String> listOfOptions)
    {
        boolean cir = false;
        boolean tri = false;
        boolean sqr = false;
        boolean pen = false;
        for (int j = 0;  j < listOfOptions.size(); j++)
        {
            if(!listOfOptions.get(j).equals(exceptThis))
            {
                if(listOfOptions.get(j).equals("Circle"))
                {
                    cir = true;
                }else if(listOfOptions.get(j).equals("Square"))
                {
                    sqr = true;
                }else if(listOfOptions.get(j).equals("Triangle"))
                {
                    tri = true;
                }else if(listOfOptions.get(j).equals("Pentagon"))
                {
                    pen = true;
                }
            }
        }
        if(!cir) listOfOptions.add("Circle");
        if(!tri) listOfOptions.add("Triangle");
        if(!sqr) listOfOptions.add("Square");
        if(!pen) listOfOptions.add("Pentagon");
    }

    public void CreateSharedPref()
    {
        ImageView img1 = (ImageView)findViewById(R.id.PlayerOneGamePiece);
        ImageView img2 = (ImageView)findViewById(R.id.playerTwoGamePiece);
        img1.buildDrawingCache();
        img2.buildDrawingCache();
        Bitmap map1 = img1.getDrawingCache();
        Bitmap map2 = img2.getDrawingCache();

        SharedPreferences sharedPreferences =
                this.getSharedPreferences("com.example.datastorageexample",
                        Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("one", ((TextView)findViewById(R.id.playerOneName)).getText().toString()).apply();
        sharedPreferences.edit().putString("two", ((TextView)findViewById(R.id.playerTwoName)).getText().toString()).apply();
        sharedPreferences.edit().putString("imgOne", EncodeToBase64(map1)).apply();
        sharedPreferences.edit().putString("imgTwo", EncodeToBase64(map2)).apply();
    }

    public void playGameOnClick(View view)
    {
        CreateSharedPref();
        Intent intent = new Intent(this, GameBoard.class);
        startActivity(intent);
    }

    public String EncodeToBase64(Bitmap image) {
        Bitmap myImage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        myImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
}
