package com.example.proyecto_;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Elige_Difi extends AppCompatActivity {
    static boolean Facil=true;
    MediaPlayer mediaPlayer;
    static boolean suena = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elige_difi);
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
    public void facilDificil(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.facil:
                if(checked)
                     Facil= true;
                break;
            case R.id.dificil:
                if(checked){
                    Facil = false;
                }
                break;
            default:
                Facil = true;
                break;

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void ok (View view){
        Intent intent = new Intent(this,Ahorcado.class);
        Bundle bundle = new Bundle();
        int persoIn = 5;
        String totalf = "";
        InputStream fichero;
        if(Facil){
            fichero = getResources().openRawResource(R.raw.facil);
        }else{
            fichero = getResources().openRawResource(R.raw.dificil);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fichero));
        try {
            totalf = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String []palabras = totalf.split(",");

        bundle.putStringArray("palabras",palabras);
        bundle.putInt("persoIn",persoIn);
        intent.putExtras(bundle);
        mediaPlayer.stop();
        startActivity(intent);



    }
}