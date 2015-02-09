package com.example.luiscerqueira.proyectotcg;

        import android.app.Activity;
        import android.content.pm.ActivityInfo;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.Window;
        import android.view.WindowManager;

        import java.util.ArrayList;


public class JuegoActivity extends Activity {

    ViewJuego pantallaJuego;
    public Jugador jugador1;
    public Jugador jugador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pantallaJuego=new ViewJuego(this);
        pantallaJuego.setKeepScreenOn(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(pantallaJuego);

        //Pruebas
        jugador1=new Jugador(20,0);
        Bitmap cartaBack= BitmapFactory.decodeResource(getResources(), R.drawable.cardbackprueba);
        Bitmap cartaFront= BitmapFactory.decodeResource(getResources(), R.drawable.frontcard);
        Bitmap cartaFront2= BitmapFactory.decodeResource(getResources(), R.drawable.saberchibi);
        Carta cartaPrueba=new Carta(0,jugador1,jugador2,cartaFront,cartaBack);
        Carta cartaPrueba2=new Carta(1,jugador1,jugador2,cartaFront2,cartaBack);
        CardRelampago cardRelampago=new CardRelampago(this, 2, jugador1, jugador2);
        jugador1.getDeck().add(cartaPrueba);
        jugador1.getDeck().add(cartaPrueba2);
        jugador1.getDeck().add(cardRelampago);
        pantallaJuego.invalidate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_juego, menu);
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
}

