package com.example.luiscerqueira.proyectotcg;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase para la activity de juego 1v1 en el mismo dispositivo
 *
 * @author Luis Cerqueira
 */
public class JuegoIaActivity extends Activity {

    public MediaPlayer mediaPlayer;
    public AudioManager audioManager;
    private boolean pausa=false;
    public ViewIA pantallaJuego;
    public Jugador jugador1;
    public Jugador jugador2;
    SharedPreferences preferenciasJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        pantallaJuego=new ViewIA(this);
        pantallaJuego.setKeepScreenOn(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(pantallaJuego);
        audioManager=(AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.bensoundepic);
        int v=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mediaPlayer.setVolume(v / 2, v / 2);
        mediaPlayer.start();

        //Pruebas
        jugador1=new Jugador(20,1);

        if(jugador1==null) {
            Log.i("CONSTRUCTOR PRIN", "JUGADOR1:NULL");
        }else{
            Log.i("CONSTRUCTOR PRIN", "JUGADOR1:NO NULL");
        }
        jugador2=new Jugador(20,0);

        //Jugador1

        String nombreJugador=getIntent().getStringExtra("JUGADOR");
        SharedPreferences preferencias=getSharedPreferences("Generales",Context.MODE_APPEND);
        for(int i=0;i<preferencias.getAll().size();i++){
            if (preferencias.getString(String.format("JUGADOR"+i),"NULL").equals(nombreJugador)){
                preferenciasJugador = getSharedPreferences(preferencias.getString(String.format("JUGADOR" + i), "NULL"), Context.MODE_PRIVATE);
                jugador1.setNombre(preferencias.getString(String.format("JUGADOR" + i), "NULL"));
                jugador1.setRecursosIniciales(preferenciasJugador.getInt("RECURSOS", 0));
                jugador1.setVidasIniciales(preferenciasJugador.getInt("VIDAS", 0));
                jugador1.setCartasIniciales(preferenciasJugador.getInt("MANO", 0));
                jugador1.setPoder(preferenciasJugador.getInt("PODER", 0));
                jugador1.setDinero(preferenciasJugador.getInt("DINERO", 0));

                Log.d("SHAREDPREF", "NOMBRE:" + jugador1.getNombre());
                Log.d("SHAREDPREF", "MANO:" + jugador1.getCartasIniciales());
                Log.d("SHAREDPREF", "RECURSOS:" + jugador1.getRecursosIniciales());
                Log.d("SHAREDPREF", "VIDAS:" + jugador1.getVidasIniciales());
                Log.d("SHAREDPREF", "DINERO:" + jugador1.getDinero());
                Log.d("SHAREDPREF", "PODER:" + jugador1.getPoder());
            }
        }

        Bitmap cartaFront= BitmapFactory.decodeResource(getResources(), R.drawable.frontcard);
//        Carta cartaPrueba2=new Carta(this,jugador1,jugador2,R.drawable.saberchibi,R.drawable.cardbackprueba,R.drawable.circulo);
        CardRelampago cardRelampago=new CardRelampago(this, jugador1, jugador2);
        //PRUEBAS
        ArrayList<Carta> cartas=new ArrayList<Carta>();
        SQLiteDatabase sqLiteDB=(new BDSQLite(this,"cartas",null,1)).getReadableDatabase();
        if(sqLiteDB!=null){
            Cursor c=sqLiteDB.rawQuery("select * from cartas",null);
            Carta carta=null;
            if(c.moveToFirst()){
                int jugador;
                String nombre="";
                int imagen;
                int cantidad;
                int coste;
                int tipo;
                int[] datos = new int[17];
                int[] datos2 = new int[17];
                boolean[] boleans = new boolean[20];

                do{
                    jugador=c.getInt(c.getColumnIndex("jugador"));
                    nombre=c.getString(c.getColumnIndex("nombre"));
                    imagen=c.getInt(c.getColumnIndex("imagen"));
                    coste=c.getInt(c.getColumnIndex("coste"));
                    cantidad=c.getInt(c.getColumnIndex("cantidad"));
                    tipo=c.getInt(c.getColumnIndex("tipo"));
                    datos[0]=c.getInt(c.getColumnIndex("dañoEnemigo"));
                    datos[1]=(c.getInt(c.getColumnIndex("curaEnemigo")));
                    datos[2]=(c.getInt(c.getColumnIndex("cartasEnemigo")));
                    datos[3]=(c.getInt(c.getColumnIndex("descarteEnemigo")));
                    datos[4]=(c.getInt(c.getColumnIndex("recursosEnemigo")));
                    datos[5]=(c.getInt(c.getColumnIndex("moverMesaAManoEnemigo")));
                    datos[6]=(c.getInt(c.getColumnIndex("moverDescarteAmanoEnemigo")));
                    datos[7]=(c.getInt(c.getColumnIndex("moverDeckAmanoEnemigo")));
                    datos[8]=(c.getInt(c.getColumnIndex("moverMesaADeckEnemigo")));
                    datos[9]=(c.getInt(c.getColumnIndex("moverDescarteADeckEnemigo")));
                    datos[10]=(c.getInt(c.getColumnIndex("moverManoADeckEnemigo")));
                    datos[11]=(c.getInt(c.getColumnIndex("moverMesaADescarteEnemigo")));
                    datos[12]=(c.getInt(c.getColumnIndex("moverManoADescarteEnemigo")));
                    datos[13]=(c.getInt(c.getColumnIndex("moverDeckADescarteEnemigo")));
                    datos[14]=(c.getInt(c.getColumnIndex("moverDescarteAMesaEnemigo")));
                    datos[15]=(c.getInt(c.getColumnIndex("moverManoAMesaEnemigo")));
                    datos[16]=(c.getInt(c.getColumnIndex("moverDeckAMesaEnemigo")));
                    datos2[0]=(c.getInt(c.getColumnIndex("dañoOwner")));
                    datos2[1]=(c.getInt(c.getColumnIndex("curaOwner")));
                    datos2[2]=(c.getInt(c.getColumnIndex("cartasOwner")));
                    datos2[3]=(c.getInt(c.getColumnIndex("descarteOwner")));
                    datos2[4]=(c.getInt(c.getColumnIndex("recursosOwner")));
                    datos2[5]=(c.getInt(c.getColumnIndex("moverMesaAManoOwner")));
                    datos2[6]=(c.getInt(c.getColumnIndex("moverDescarteAmanoOwner")));
                    datos2[7]=(c.getInt(c.getColumnIndex("moverDeckAmanoOwner")));
                    datos2[8]=(c.getInt(c.getColumnIndex("moverMesaADeckOwner")));
                    datos2[9]=(c.getInt(c.getColumnIndex("moverDescarteADeckOwner")));
                    datos2[10]=(c.getInt(c.getColumnIndex("moverManoADeckOwner")));
                    datos2[11]=(c.getInt(c.getColumnIndex("moverMesaADescarteOwner")));
                    datos2[12]=(c.getInt(c.getColumnIndex("moverManoADescarteOwner")));
                    datos2[13]=(c.getInt(c.getColumnIndex("moverDeckADescarteOwner")));
                    datos2[14]=(c.getInt(c.getColumnIndex("moverDescarteAMesaOwner")));
                    datos2[15]=(c.getInt(c.getColumnIndex("moverManoAMesaOwner")));
                    datos2[16]=(c.getInt(c.getColumnIndex("moverDeckAMesaOwner")));
                    boleans[0]=(c.getInt(c.getColumnIndex("onMoveMesaADescarte"))>0);
                    boleans[1]=(c.getInt(c.getColumnIndex("onMoveMesaADeck"))>0);
                    boleans[2]=(c.getInt(c.getColumnIndex("onMoveMesaAMano"))>0);
                    boleans[3]=(c.getInt(c.getColumnIndex("onMoveDescarteAMesa"))>0);
                    boleans[4]=(c.getInt(c.getColumnIndex("onMoveDescarteADeck"))>0);
                    boleans[5]=(c.getInt(c.getColumnIndex("onMoveDescarteAMano"))>0);
                    boleans[6]=(c.getInt(c.getColumnIndex("onMoveDeckADescarte"))>0);
                    boleans[7]=(c.getInt(c.getColumnIndex("onMoveDeckAMesa"))>0);
                    boleans[8]=(c.getInt(c.getColumnIndex("onMoveDeckAMano"))>0);
                    boleans[9]=(c.getInt(c.getColumnIndex("onMoveManoADescarte"))>0);
                    boleans[10]=(c.getInt(c.getColumnIndex("onMoveManoAMesa"))>0);
                    boleans[11]=(c.getInt(c.getColumnIndex("onMoveManoADeck"))>0);
                    boleans[12]=(c.getInt(c.getColumnIndex("onStartTurnTable"))>0);
                    boleans[13]=(c.getInt(c.getColumnIndex("onStartTurnHand"))>0);
                    boleans[14]=(c.getInt(c.getColumnIndex("onStartTurnDiscard"))>0);
                    boleans[15]=(c.getInt(c.getColumnIndex("onStartTurnDeck"))>0);
                    boleans[16]=(c.getInt(c.getColumnIndex("onEndTurnTable"))>0);
                    boleans[17]=(c.getInt(c.getColumnIndex("onEndTurnHand"))>0);
                    boleans[18]=(c.getInt(c.getColumnIndex("onEndTurnDiscard"))>0);
                    boleans[19]=(c.getInt(c.getColumnIndex("onEndTurnDeck"))>0);
                    Tipos tipos;
                    if(tipo==Tipos.HECHIZO.ordinal()){
                        tipos=Tipos.HECHIZO;
                    }
                    else if(tipo==Tipos.PERMANENTE.ordinal()){
                        tipos=Tipos.PERMANENTE;
                    }
                    else if(tipo==Tipos.MAGO.ordinal()){
                        tipos=Tipos.MAGO;
                    }
                    else{
                        tipos=Tipos.HECHIZO;
                    }
                    for(int j=0;j<cantidad;j++){
                        carta=new Carta(this,jugador1,jugador2,nombre,imagen,coste,tipos,datos,datos2,boleans);
                        jugador1.getDeck().add(carta);
                    }
                }while(c.moveToNext());
            }

        }else{
            sqLiteDB.close();
        }
        sqLiteDB.close();
        while(jugador1.getDeck().size()<20){
            cardRelampago=new CardRelampago(this, jugador1, jugador2);
            jugador1.getDeck().add(cardRelampago);
        }
        jugador1.setActivo(true);
        jugador1.setVidas(jugador1.getVidasIniciales());
        jugador1.setRecursos(jugador1.getRecursosIniciales());
        jugador1.barajar();
        jugador1.moveFromDeckToHand(jugador1.getCartasIniciales());
        //Jugador2
//        cartaPrueba2=new Carta(this,jugador2, jugador1,R.drawable.saberchibi, R.drawable.cardbackprueba, R.drawable.circulo);

//        jugador2.getDeck().add(cartaPrueba2);
        jugador2.setDeck(jugador1.getDeck());
        for(int i=0;i<jugador2.getDeck().size();i++){
            jugador2.getDeck().get(i).setEnemigo(jugador1);
            jugador2.getDeck().get(i).setOwner(jugador2);
        }

        while(jugador2.getDeck().size()<20){
            cardRelampago=new CardRelampago(this, jugador2, jugador1);
            jugador2.getDeck().add(cardRelampago);
        }
        jugador2.setActivo(false);
        jugador2.barajar();
        jugador2.moveFromDeckToHand(3);

//        Log.i("MAZO2","TAMAÑO:"+jugador2.getDeck().size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juego, menu);
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            if (hasFocus) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pantallaJuego.getHilo().setFuncionando(false);
        pausa=true;
        mediaPlayer.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pantallaJuego.getHilo().setFuncionando(false);
        pausa=true;
        mediaPlayer.pause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pantallaJuego.getHilo().setFuncionando(true);
        if(pausa){
            pausa=false;
            mediaPlayer.start();
        }
        mediaPlayer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pantallaJuego.getHilo().setFuncionando(true);
        if(pausa){
            pausa=false;
            mediaPlayer.start();
        }
        mediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pantallaJuego.getHilo().setFuncionando(false);
        pausa=true;
        mediaPlayer.pause();
    }
}

