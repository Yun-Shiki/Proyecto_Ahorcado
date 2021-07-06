package com.example.proyecto_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Personalizar extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    static int persoIn;
    static boolean suena = true;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        mediaPlayer = MediaPlayer.create(this, R.raw.suturno);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

    }
    public void sonido(View view){
        ImageButton botonS = (ImageButton) findViewById(R.id.imageButton);
        if(suena){
            botonS.setImageResource(android.R.drawable.ic_lock_silent_mode);
            mediaPlayer.pause();
            suena = false;

        }else{
            botonS.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
            suena = true;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        persoIn = Character.getNumericValue(item.charAt(0));


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        persoIn = 5;

    }
    public void enviar(View view){
        Intent intent = new Intent(this,Ahorcado.class);
        Bundle bundle = new Bundle();
        EditText texto = (EditText)findViewById(R.id.palabras);
        String palabras = texto.getText().toString();
        String [] palabra;
        if(palabras.indexOf(",") != -1){
            palabra = palabras.split(",");
        }else{
            palabra = new String[1];
            palabra[0] = palabras;
        }
        bundle.putStringArray("palabras",palabra);
        bundle.putInt("persoIn",persoIn);
        intent.putExtras(bundle);
        mediaPlayer.stop();
        startActivity(intent);

    }
}