package com.example.luiscerqueira.proyectotcg;

        import android.app.Activity;
        import android.content.pm.ActivityInfo;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.Window;
        import android.view.WindowManager;

        import java.util.ArrayList;


public class JuegoActivity extends Activity {

    public ViewJuego pantallaJuego;
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
        jugador2=new Jugador(20,0);
        //Jugador1
        Bitmap cartaBack= BitmapFactory.decodeResource(getResources(), R.drawable.cardbackprueba);
        Bitmap cartaFront= BitmapFactory.decodeResource(getResources(), R.drawable.frontcard);
        Bitmap cartaFront2= BitmapFactory.decodeResource(getResources(), R.drawable.saberchibi);
        Carta cartaPrueba=new Carta(this, jugador1, jugador2);
        Carta cartaPrueba2=new Carta(this,jugador1,jugador2,cartaFront2,cartaBack,BitmapFactory.decodeResource(getResources(), R.drawable.circle4a));
        CardRelampago cardRelampago=new CardRelampago(this, jugador1, jugador2);
        CardHeal cardHeal=new CardHeal(this,jugador1,jugador2);
        CardRitual cardRitual=new CardRitual(this,jugador1,jugador2);
        CardMentalSpiral cardMentalSpiral=new CardMentalSpiral(this,jugador1,jugador2);
        CardBurningSign cardBurningSign=new CardBurningSign(this,jugador1,jugador2);
        CardIgniteMemories cardIgniteMemories=new CardIgniteMemories(this,jugador1,jugador2);
        CardNightmare cardNightmare=new CardNightmare(this,jugador1,jugador2);
        CardTransfusion cardTransfusion=new CardTransfusion(this,jugador1,jugador2);
        jugador1.getDeck().add(cartaPrueba2);
        jugador1.getDeck().add(cardRelampago);
        jugador1.getDeck().add(cardHeal);
        jugador1.getDeck().add(cardRitual);
        jugador1.getDeck().add(cardMentalSpiral);
        jugador1.getDeck().add(cardBurningSign);
        jugador1.getDeck().add(cardIgniteMemories);
        jugador1.getDeck().add(cardMentalSpiral);
        jugador1.getDeck().add(cardNightmare);
        jugador1.getDeck().add(cardTransfusion);
        jugador1.setActivo(true);
        //Jugador2
        cartaBack= BitmapFactory.decodeResource(getResources(), R.drawable.cardbackprueba);
        cartaFront= BitmapFactory.decodeResource(getResources(), R.drawable.frontcard);
        cartaFront2= BitmapFactory.decodeResource(getResources(), R.drawable.saberchibi);
        cartaPrueba=new Carta(this, jugador2, jugador1);
        cartaPrueba2=new Carta(this,jugador2, jugador1,cartaFront2,cartaBack,BitmapFactory.decodeResource(getResources(), R.drawable.circle4a));
        cardRelampago=new CardRelampago(this, jugador2, jugador1);
        cardHeal=new CardHeal(this,jugador2,jugador1);
        cardRitual=new CardRitual(this,jugador2,jugador1);
        jugador2.getDeck().add(cartaPrueba);
        jugador2.getDeck().add(cartaPrueba2);
        jugador2.getDeck().add(cardRelampago);
        jugador2.getDeck().add(cardHeal);
        jugador2.getDeck().add(cardRitual);
        jugador2.setActivo(false);
        Log.i("MAZO2","TAMAÑO:"+jugador2.getDeck().size());
        pantallaJuego.invalidate();
    }
    public void mandarInvalidar(){
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

