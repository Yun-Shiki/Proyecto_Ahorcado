package com.example.proyecto_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    static boolean suena = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.chiptronical);
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
    public void eligeDifi(View view){
        Intent intent = new Intent(this,Elige_Difi.class);
        mediaPlayer.stop();
        startActivity(intent);


    }
    public void personalizado(View view){
        Intent intent = new Intent(this,Personalizar.class);
        mediaPlayer.stop();
        startActivity(intent);

    }
}