package com.example.opencvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        redNeuronal();
        HOG();
        Movimiento();
    }

    public Button redNeuronal;
    public Button HOG;

    public void redNeuronal(){
        redNeuronal = (Button) findViewById(R.id.butRed);
        redNeuronal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu.this,MainActivity.class);
                startActivity(toy);

            }
        });
    }

    public void HOG(){
        HOG = (Button) findViewById(R.id.butHOG);
        HOG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu.this,HOG.class);
                startActivity(toy);

            }
        });
    }
    public void Movimiento(){
        HOG = (Button) findViewById(R.id.butMovimiento);
        HOG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu.this,Movimiento.class);
                startActivity(toy);

            }
        });
    }
}