package com.codingfactory.fruitroulette.ui;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;


import com.codingfactory.fruitroulette.R;
import com.codingfactory.fruitroulette.fruit.Fruity;

import java.util.List;


public class NewGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.gameplay);

        Spinner firstChoice = findViewById(R.id.first);
        Spinner secondChoice = findViewById(R.id.second);
        Spinner thirdChoice = findViewById(R.id.third);
        Spinner forthChoice = findViewById(R.id.fourth);

        List<String> fruitSelection = Fruity.getImgList();

        SpinnerAdapter fruitAdapter = new SpinnerAdapter(getApplicationContext(), fruitSelection);
        fruitAdapter.setDropDownViewResource(R.layout.dropdown_fruit);

        firstChoice.setAdapter(fruitAdapter);
        secondChoice.setAdapter(fruitAdapter);
        thirdChoice.setAdapter(fruitAdapter);
        forthChoice.setAdapter(fruitAdapter);


        AdapterView.OnItemSelectedListener toastMessage = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String spinner = adapterView.getResources().getResourceEntryName(adapterView.getId());
                    Toast.makeText(getApplicationContext(), "Your " + spinner + " fruit is " +
                            Fruity.getFruitName(i) + ".",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        };

        firstChoice.setOnItemSelectedListener(toastMessage);
        secondChoice.setOnItemSelectedListener(toastMessage);
        thirdChoice.setOnItemSelectedListener(toastMessage);
        forthChoice.setOnItemSelectedListener(toastMessage);
    }
}