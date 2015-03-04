package com.example.luiscerqueira.proyectotcg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class ActivityCombinar extends ActionBarActivity {

    public Bitmap imagenCombinada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_combinar);
        ImageView imagenCarta=(ImageView)findViewById(R.id.imageView);
        ImageView imagenCirculo=(ImageView)findViewById(R.id.imageView2);
        Button btnCombinar=(Button)findViewById(R.id.btnCombinar);
        Bitmap imagen1=((BitmapDrawable)imagenCarta.getDrawable()).getBitmap();
            Bitmap imagen2=((BitmapDrawable)imagenCirculo.getDrawable()).getBitmap();
        imagenCombinada=overlay(imagen1,imagen2);
        btnCombinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoCombinar dCombinar=new DialogoCombinar();
                dCombinar.show(getFragmentManager(),"Combinar");
            }
        });
    }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_combinar, menu);
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
