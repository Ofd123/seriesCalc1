package com.example.seriescalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    EditText startSeries, q;
    Switch choice;
    boolean multiply;
    String startingSt,SeriesMUltiplayerSt;
    double startingint,SeriesMultiplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        choice = findViewById(R.id.option);
        startSeries = findViewById(R.id.firstNum);
        q = findViewById(R.id.commonRatio);

    }



    public void toggle(View view)
    {
        if (choice.isChecked())
        {
            Toast.makeText(this, "toggled to geometric series", Toast.LENGTH_SHORT).show();
            multiply = true;
        }
        else
        {
            Toast.makeText(this, "toggled to mathematical series", Toast.LENGTH_SHORT).show();
            multiply = false;
        }
    }

    public void nextView(View view)
    {
        startingSt = startSeries.getText().toString();
        SeriesMUltiplayerSt = q.getText().toString();
        if(startingSt == null || startingSt.equals("+") || startingSt.equals("-") || startingSt.equals(" ") || startingSt.equals(".") || startingSt.equals("+.") || startingSt.equals("-.") || startingSt.equals(" .") || startingSt.equals("+-") || startingSt.equals("-+") || startingSt.equals("+-.") || startingSt.equals("-+.") || startingSt.equals("") || startingSt.equals(" "))
        {
            Toast.makeText(this, "Enter a starting number", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(SeriesMUltiplayerSt == null || SeriesMUltiplayerSt.equals("+") || SeriesMUltiplayerSt.equals("-") || SeriesMUltiplayerSt.equals(" ") || SeriesMUltiplayerSt.equals(".") || SeriesMUltiplayerSt.equals("+.") || SeriesMUltiplayerSt.equals("-.") || SeriesMUltiplayerSt.equals(" .") || SeriesMUltiplayerSt.equals("+-") || SeriesMUltiplayerSt.equals("-+") || SeriesMUltiplayerSt.equals("+-.") || SeriesMUltiplayerSt.equals("-+.") || SeriesMUltiplayerSt.equals("") || SeriesMUltiplayerSt.equals(" "))
        {
            Toast.makeText(this, "Enter the Series multiplier/divider", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            startingint = Double.parseDouble(startingSt);
            SeriesMultiplayer = Double.parseDouble(SeriesMUltiplayerSt);

            Intent showResult = new Intent(this,resultScreenActivity.class);
            showResult.putExtra("startingNum", startingint);
            showResult.putExtra("SeriesMultiplayer", SeriesMultiplayer);
            showResult.putExtra("shouldMultiply", multiply);
            startActivity(showResult);
        }

    }
}