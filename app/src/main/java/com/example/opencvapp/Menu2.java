package com.example.opencvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        movimiento();
        movimiento2();
        movimiento1();
        movimiento3();
        regresar();
    }

    public void movimiento(){
        Button simple = (Button) findViewById(R.id.butSimple);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu2.this,Movimiento.class);
                startActivity(toy);
            }
        });
    }

    public void movimiento1(){
        Button butEcualizador = (Button) findViewById(R.id.butEcualizador);
        butEcualizador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu2.this,Movimiento1.class);
                startActivity(toy);
            }
        });
    }

    public void movimiento2(){
        Button butClahe = (Button) findViewById(R.id.butClahe);
        butClahe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu2.this,Movimiento2.class);
                startActivity(toy);
            }
        });
    }

    public void movimiento3(){
        Button butErode = (Button) findViewById(R.id.butErode);
        butErode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu2.this,Movimiento3.class);
                startActivity(toy);
            }
        });
    }
    public void regresar(){
        Button butSalir = (Button) findViewById(R.id.butSalir);
        butSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Menu2.this,Movimiento3.class);
                startActivity(toy);
            }
        });
    }
}