package com.example.jaguilar.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
{
    //public
    //public Spinner spin2 = (Spinner)findViewById(R.id.spinnerPlayerTwo);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] gamePieces = {"Circle", "Square", "Triangle", "Pentagon"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activity_main, gamePieces);
        Spinner spin1 = (Spinner)findViewById(R.id.spinnerPlayerOne);
        spin1.setAdapter(adapter);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                  //Test
            }
        });

    }


}
