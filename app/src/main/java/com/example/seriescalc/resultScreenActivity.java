package com.example.seriescalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class resultScreenActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    String [] list = new String[20];
    ListView showingList;
    TextView firstNum,listQ,location,sumLocation;
    double sum, startingIndex, multiplier;
    boolean multiply;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        firstNum = findViewById(R.id.firstNum);
        listQ = findViewById(R.id.listQ);
        location = findViewById(R.id.location);
        sumLocation = findViewById(R.id.sumLocation);

        showingList = (ListView) findViewById(R.id.showingList);
        showingList.setOnItemClickListener(this);
        showingList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Intent getVal = getIntent();
        startingIndex = getVal.getDoubleExtra("startingNum",0);
        multiplier = getVal.getDoubleExtra("SeriesMultiplayer",1);
        multiply = getVal.getBooleanExtra("shouldMultiply",false);

        firstNum.setText("first number: " + Double.toString(startingIndex));
        listQ.setText("common ratio: " + Double.toString(multiplier));


        sum = startingIndex;
        for(int i = 0; i <20; i++)
        {
            list[i] = String.format("%.4f",sum);
            if(multiply)
            {
                sum *= multiplier;
            }
            else
            {
                sum += multiplier;
            }
        }



        ArrayAdapter<String> listSt = new ArrayAdapter<String>(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
        showingList.setAdapter(listSt);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        double totalForN = 0;
        double tempNum = 0;
        for(int i = 0; i <= position; i++)
        {
            tempNum = Double.parseDouble(String.valueOf(list[i]));
            totalForN += tempNum;
        }
        location.setText("item's location: "+ (position+1));
        sumLocation.setText("sum of the first " + (position+1) + " items: "+ String.format("%.4f",totalForN));

    }

    public void returnToFirstView(View view)
    {
        finish();
    }
}