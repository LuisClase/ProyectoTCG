package com.example.luiscerqueira.proyectotcg;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.Calendar;

/**
 * Clase para la gestion de la activity principal del juego
 *
 * @author Luis Cerqueira
 */
public class MainActivity extends Activity {

    public MediaPlayer mediaPlayer;
    public AudioManager  audioManager;
    private boolean pausa=false;
    private PendingIntent pendingIntent;
    SharedPreferences preferencias;
    Jugador jugador;
    TextView txtJugador;

    private void cambiarJugador(int numJugador){
        if(!preferencias.getString(String.format("JUGADOR"+numJugador),"NULL").equals("NULL")) {
            String nombre=preferencias.getString(String.format("JUGADOR" + numJugador), "NULL");
            SharedPreferences preferenciasJugador = getSharedPreferences(nombre, Context.MODE_PRIVATE);
            jugador.setNombre(preferencias.getString(String.format("JUGADOR" + numJugador), "NULL"));

            Log.d("SHAREDPREF", "NOMBRE:" + nombre);
            Log.d("SHAREDPREF", "SIZE: " + preferenciasJugador.getAll().size());
            Log.d("SHAREDPREF", "CONTAINS: " + preferenciasJugador.contains("RECURSOS"));
            Log.d("SHAREDPREF", "MANO:" + preferenciasJugador.getInt("RECURSOS", 0));
            Log.d("SHAREDPREF", "RECURSOS:" + preferenciasJugador.getInt("VIDAS", 0));
            Log.d("SHAREDPREF", "VIDAS:" + preferenciasJugador.getInt("VIDAS", 0));

            jugador.setRecursosIniciales(preferenciasJugador.getInt("RECURSOS", 0));
            jugador.setVidasIniciales(preferenciasJugador.getInt("VIDAS", 0));
            jugador.setCartasIniciales(preferenciasJugador.getInt("MANO", 0));
            jugador.setPoder(preferenciasJugador.getInt("PODER", 0));
            jugador.setDinero(preferenciasJugador.getInt("DINERO", 0));

            Log.d("SHAREDPREF", "NOMBRE:" + jugador.getNombre());
            Log.d("SHAREDPREF", "MANO:" + jugador.getCartasIniciales());
            Log.d("SHAREDPREF", "RECURSOS:" + jugador.getRecursosIniciales());
            Log.d("SHAREDPREF", "VIDAS:" + jugador.getVidasIniciales());
            Log.d("SHAREDPREF", "DINERO:" + jugador.getDinero());
            Log.d("SHAREDPREF", "PODER:" + jugador.getPoder());
        }
    }

    private void crearJugador(int numJugador,String nombre){
        final SharedPreferences preferencias=getSharedPreferences("Generales", Context.MODE_APPEND);
        final SharedPreferences.Editor editor=preferencias.edit();
        editor.putString(String.format("JUGADOR"+numJugador),nombre);
        editor.apply();

        String nombre2=preferencias.getString(String.format("JUGADOR" + numJugador), "NULL");
        SharedPreferences preferenciasJugador = getSharedPreferences(nombre, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorJugador = preferenciasJugador.edit();
        Log.d("PREFERENCIAS-NUEVO", "NOMBRE: " + nombre2);
        editorJugador.putInt("RECURSOS", 0);
        editorJugador.putInt("VIDAS", 20);
        editorJugador.putInt("MANO", 1);
        editorJugador.putInt("PODER", 0);
        editorJugador.putInt("DINERO", 50);
        editorJugador.apply();

        Log.d("PREFERENCIAS-NUEVO","RECURSOS: "+preferenciasJugador.getInt("RECURSOS",0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        preferencias=getSharedPreferences("Generales",Context.MODE_PRIVATE);

        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.MONTH,2);
//        calendar.set(Calendar.YEAR,2015);
//        calendar.set(Calendar.DAY_OF_MONTH,18);

        calendar.set(Calendar.HOUR_OF_DAY,10);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.AM_PM,Calendar.AM);

        final Intent intent=new Intent(MainActivity.this, Recibidor.class);
        pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,0);


        //Leer SharedPreferences
        jugador=new Jugador(20,0);
        final SharedPreferences preferencias=getSharedPreferences("Generales",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("JUGADOR0","Predeterminado");
        editor.apply();
        for(int i=0;i<preferencias.getAll().size();i++){
            Log.d("JUGADOR",String.format("JUGADOR"+i));
            String jugador=preferencias.getString(String.format("JUGADOR"+i),"NULL");
            Log.d("JUGADORES",jugador);
        }

        cambiarJugador(0);

        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);

        audioManager=(AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.bensoundofeliasdream);
        int v=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mediaPlayer.setVolume(v / 2, v / 2);
        mediaPlayer.start();

        txtJugador=(TextView) findViewById(R.id.txtJugador);
        txtJugador.setText(jugador.getNombre());
        FloatingActionsMenu fam = (FloatingActionsMenu) findViewById(R.id.fam);

        FloatingActionButton fbtnNuevojugador=new FloatingActionButton(getBaseContext());
        fbtnNuevojugador.setTitle("Nuevo jugador");
        fbtnNuevojugador.setSize(FloatingActionButton.SIZE_MINI);
        fbtnNuevojugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runThreadNuevoJugador();
            }
        });
        fam.addButton(fbtnNuevojugador);

        FloatingActionButton fbtnCambiarJugador=new FloatingActionButton(getBaseContext());
        fbtnCambiarJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CAMBIO", "ANTES runThread");
                runThreadCambioJugador();
            }
        });
        fbtnCambiarJugador.setTitle("Cambiar jugador");
        fbtnCambiarJugador.setSize(FloatingActionButton.SIZE_MINI);
        fam.addButton(fbtnCambiarJugador);

        FloatingActionButton fBtnTienda=new FloatingActionButton(getBaseContext());
        fBtnTienda.setTitle("Tienda");
        fBtnTienda.setSize(FloatingActionButton.SIZE_MINI);

        fBtnTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, ActivityTienda.class);
                intent1.putExtra("JUGADOR", jugador.getNombre());
                startActivity(intent1);
            }
        });
        fam.addButton(fBtnTienda);

        Button btnTienda=(Button)findViewById(R.id.btnTienda);
        btnTienda.setVisibility(View.INVISIBLE);

        Button btnJugarIA=(Button)findViewById(R.id.btnJugarIA);
        btnJugarIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BOTTON","ONCLICK");
                Intent intent=new Intent(MainActivity.this,JuegoIaActivity.class);
                intent.putExtra("JUGADOR",jugador.getNombre());
                startActivity(intent);
                mediaPlayer.pause();
                pausa=true;
            }
        });

        Button btnJugar=(Button)findViewById(R.id.btnJugar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,JuegoActivity.class);
                intent.putExtra("JUGADOR",jugador.getNombre());
                startActivity(intent);
                mediaPlayer.pause();
                pausa=true;
            }
        });

        Button btnMazos=(Button)findViewById(R.id.btnMazos);
        btnMazos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityMazos.class);
                startActivity(intent);
                mediaPlayer.pause();
                pausa=true;
            }
        });

        Button btnPrueba=(Button)findViewById(R.id.btnCombinar);
        btnPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivityCombinar.class);
                startActivity(intent);
                mediaPlayer.pause();
                pausa=true;
            }
        });
    }

    private void runThreadNuevoJugador(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Nuevo jugador");

                final EditText input=new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setText("Nombre");
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!input.getText().toString().trim().equals("")) {
                            crearJugador(preferencias.getAll().size(), input.getText().toString());
                        }
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    private void runThreadCambioJugador() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("CAMBIO","ANTES BUILDER");
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                int cont=0;
                for(int i=0;i<preferencias.getAll().size();i++){
                    if (!preferencias.getString(String.format("JUGADOR"+i),"NULL").equals("NULL")){
                        cont++;
                        Log.d("CAMBIO","CONT ++"+cont);
                    }
                }
//                String items[]= {
//                    "PRUEBA","PRUEBA2"
//                };
                String items[]=new String[cont];
                Log.d("CAMBIO","CONT def"+cont);
                cont=0;
                for(int i=0;i<preferencias.getAll().size();i++){
                    if (!preferencias.getString(String.format("JUGADOR"+i),"NULL").equals("NULL")){
                        Log.d("SEGUNDO BUCLE","nombre:"+preferencias.getString(String.format("JUGADOR"+i),"NULL"));
                        Log.d("SEGUNDO BUCLE","CONT "+cont);
                        items[cont]=preferencias.getString(String.format("JUGADOR"+i),"NULL");
                        cont++;
                    }
                }
                for (int i=0;i<items.length;i++){
                    Log.d("ITEMS","ITEM:"+i+" "+items[i]);
                }
                Log.d("CAMBIO","ANTES SEINGLE CHOICE ITEMS");
                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("ONCLICKCAMBIO","WHICH "+which);
                        cambiarJugador(which);
                        txtJugador.setText(jugador.getNombre());
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setTitle("Selecciona perfil");
                Log.d("CAMBIO", "ANTES SHOW");
                builder.show();
                Log.d("CAMBIO", "TODO OK");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        pausa=true;
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(pausa){
            pausa=false;
            mediaPlayer.start();
        }
        mediaPlayer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    protected void onRestart() {
        super.onRestart();
        if(pausa){
            pausa=false;
            mediaPlayer.start();
        }
        mediaPlayer.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(pausa){
            pausa=false;
            mediaPlayer.start();
        }
        mediaPlayer.start();
    }
}
