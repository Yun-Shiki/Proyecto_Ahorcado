package com.example.proyecto_;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Random;

public class Palabra {
    private String palabra;
    private int id;
    private boolean hecho;
    private boolean[] letrasencon;
    private char[] letra;
    Random r = new Random();

    public Palabra(String p, int id) {
        setPalabra(p);
        setId(id);
        setHecho(false);
        letra = p.toCharArray();
        letrasencon = new boolean[letra.length];
        generalletra();

    }

    public void generalletra() {
        int ran;
        do{
         ran = r.nextInt(this.letra.length);
        letrasencon[ran] = true;
        }while (letra[ran] == ' ');
        for (int i = 0; i < letra.length; i++) {
            if(letra[ran] == letra[i]){
                letrasencon[i] = true;
            }
        }

    }

    public void buscar(String a) {
        char b = a.charAt(0);
        boolean hay = false;
        for (int i = 0; i < letrasencon.length; i++) {
            if (letra[i] == b) {
                letrasencon[i] = true;
                hay = true;
            }
        }
        if(!hay){
            Ahorcado.intentos--;
        }
    }
    public boolean gandado(){

        for (int i = 0; i < letrasencon.length; i++) {
            if(letrasencon[i] == false){
                return false;
            }

        }
        return true;
    }

    public String escribir() {
        String resu = "";
        for (int i = 0; i < letrasencon.length; i++) {
            if (letrasencon[i]) {
                resu += " " + letra[i] + " ";
            } else if (letra[i] == ' ') {

                resu += "  \n";

            } else {
                resu += " _ ";
            }

        }
        return resu;
    }


    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHecho() {
        return hecho;
    }

    public void setHecho(boolean hecho) {
        this.hecho = hecho;
    }
}
