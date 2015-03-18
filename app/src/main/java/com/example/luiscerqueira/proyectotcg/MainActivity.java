package com.example.luiscerqueira.proyectotcg;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
        import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.MONTH,2);
//        calendar.set(Calendar.YEAR,2015);
//        calendar.set(Calendar.DAY_OF_MONTH,18);

        calendar.set(Calendar.HOUR_OF_DAY,10);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.AM_PM,Calendar.AM);

        Intent intent=new Intent(MainActivity.this, Recibidor.class);
        pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(),pendingIntent);

        audioManager=(AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.bensoundofeliasdream);
        int v=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mediaPlayer.setVolume(v/2,v/2);
        mediaPlayer.start();

        Button btnJugar=(Button)findViewById(R.id.btnJugar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,JuegoActivity.class);
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
