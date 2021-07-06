package com.example.proyecto_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Ahorcado extends AppCompatActivity {
    static Palabra[] Pa;
    static int C;
    static int persoIn = 5;
    static int intentos = persoIn;
    static  boolean Ganado = false;
    static boolean suena = true;
    CountDownTimer countDownTimer;
    ArrayList<Button> bts = new ArrayList<Button>();
    static String [] abc = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    TextView resu;
    TableLayout tabla;
    MediaPlayer mediaPlayer;
    Random r = new Random();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorcado);
        mediaPlayer = MediaPlayer.create(this, R.raw.chiptronical);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        Bundle bundle = getIntent().getExtras();
        persoIn = bundle.getInt("persoIn");
        if(persoIn != 5){
            intentos = persoIn;
        }
        cambiarIma();
        String [] palabras = bundle.getStringArray("palabras");
        Pa = new Palabra[palabras.length];
        for (int i = 0; i < palabras.length ; i++) {
            Pa[i] = new Palabra(palabras[i].toUpperCase(),i);
        }
        resu = (TextView) findViewById(R.id.texto);
        tabla = (TableLayout) findViewById(R.id.mensajeT);
        do{
            C = r.nextInt(Pa.length);
        }while (Pa[C].isHecho());
        inicio();
        TextView textView = (TextView)findViewById(R.id.reloj);

        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                textView.setText(String.format(Locale.getDefault(), "%d sec.", millisUntilFinished / 1000L));
            }

            public void onFinish() {
                textView.setText("Done.");
                terminado();
            }
        }.start();




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
    public void inicio(){
        while (Pa[C].isHecho()){
            C = r.nextInt(Pa.length);
        };

        resu.setText(Pa[C].escribir());

    }

    public void enviar(View view){
        String a =((Button) view).getText().toString();

        for (int i = 0; i < abc.length ; i++) {
            if(( a.equalsIgnoreCase(abc[i]))){
                Pa[C].buscar(abc[i]);
                view.setVisibility(View.INVISIBLE);
                bts.add((Button) view);
            }
        }
        Ganado=Pa[C].gandado();
        resu.setText(Pa[C].escribir());
        cambiarIma();
        if(Ganado){
            tabla.setBackgroundResource(R.drawable.enhorabuena);
            terminado();
        }


    }

    public void terminado(){
        TextView fin = findViewById(R.id.mensaje);
        Button seguir = findViewById(R.id.seguir);
        countDownTimer.cancel();
        if(Ganado){
            fin.setText("Has ganado!");
            seguir.setText("Quieres seguir?");
            Pa[C].setHecho(true);
        }else{
            fin.setText("Has Muerto!\nVaya paquete que eres!");
            tabla.setBackgroundResource(R.drawable.perdedor);
        }
        tabla.setVisibility(View.VISIBLE);

    }

    public void seguir(View view){
        tabla.setVisibility(View.INVISIBLE);
        if(Pa[C].isHecho()){
        for (Button n:bts) {
            n.setVisibility(View.VISIBLE);
        }}
        inicio();
        countDownTimer.start();
        intentos = persoIn;
        cambiarIma();


    }

    public void salir(View view){
        Intent intent = new Intent(this,MainActivity.class);
        for (Button n:bts) {
            n.setVisibility(View.VISIBLE);
        }
        mediaPlayer.stop();
        cambiarIma();
        startActivity(intent);
    }


    public void cambiarIma(){
        ImageView ahor = findViewById(R.id.ahorcado);
        switch (intentos){
            case 5:
                ahor.setImageResource(R.drawable.elahorcado1);
                break;
            case 4:
                ahor.setImageResource(R.drawable.elahorcado2);
                break;
            case 3:
                ahor.setImageResource(R.drawable.elahorcado3);
                break;
            case 2:
                ahor.setImageResource(R.drawable.elahorcado4);
                break;
            case 1:
                ahor.setImageResource(R.drawable.elahorcado5);
                break;
            case 0:
                ahor.setImageResource(R.drawable.elahorcado6);
                terminado();
                break;


        }

    }





}