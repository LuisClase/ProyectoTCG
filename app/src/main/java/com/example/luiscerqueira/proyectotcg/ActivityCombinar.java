package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ActivityCombinar extends ActionBarActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    public Bitmap imagenCombinada;
    Bitmap imagenMarco;
    Bitmap imagenLinea;
    Bitmap imagenColumna;
    Bitmap imagenTemp;
    ImageView imagenCentral;
    ImageView imagenCarta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_combinar);
        imagenLinea=BitmapFactory.decodeResource(getResources(),R.drawable.imagen3);
        imagenColumna=BitmapFactory.decodeResource(getResources(),R.drawable.imagen2);
        imagenTemp=BitmapFactory.decodeResource(getResources(),R.drawable.imagen);
        imagenCarta=(ImageView)findViewById(R.id.imageView);
        imagenCentral=(ImageView)findViewById(R.id.imageView2);
        imagenMarco=((BitmapDrawable)imagenCarta.getDrawable()).getBitmap();
        Button btnCombinar=(Button)findViewById(R.id.btnCombinar);
        Button btnCaracteristicas=(Button)findViewById(R.id.btnCaracteristicas);
        Button btnImagen=(Button)findViewById(R.id.btnImagen);

        Bitmap imagen1=imagenMarco;
        Bitmap imagen2=((BitmapDrawable)imagenCentral.getDrawable()).getBitmap();
        imagenCombinada=overlay(imagen1,imagen2);

        btnImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            imagenCentral=(ImageView)findViewById(R.id.imageView2);
            Bitmap bImagen=BitmapFactory.decodeFile(picturePath);
            Log.i("IMAGEN","NULL?"+(bImagen==null?"SI":"NO")+" "+picturePath);
            if(bImagen==null) {
//                FileUtils fileUtils=new FileUtils();
                Log.i("IMAGEN","CAMBIO A OTRA2");
                bImagen=BitmapFactory.decodeFile(FileUtils.getFile(this,selectedImage).getPath());
                imagenCentral.setImageBitmap(bImagen);
            }
            imagenCentral.setImageBitmap(bImagen);

            Bitmap imagen1=imagenMarco;
            Bitmap imagen2=((BitmapDrawable)imagenCentral.getDrawable()).getBitmap();
            imagenCombinada=overlay(imagen1,imagen2);
            imagenCarta.setImageBitmap(imagenCombinada);
            Log.i("IMAGEN","NULL3?"+(bImagen==null?"SI":"NO")+" "+picturePath);
            Log.i("IMAGEN","NULL2?"+(imagenCentral.getDrawable()==null?"SI":"NO"));

        }
    }

    public Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
//        bmp2=Bitmap.createScaledBitmap(bmp2,(int)(bmp1.getWidth()/1.2),(int)(bmp1.getHeight()/1.5),true);
        bmp2 = Bitmap.createScaledBitmap(bmp2, imagenTemp.getWidth(), imagenTemp.getHeight(), true);
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
//        canvas.drawBitmap(bmp2, bmp1.getWidth()/7, bmp1.getHeight()/7, null);
        canvas.drawBitmap(bmp2, imagenColumna.getWidth(), imagenLinea.getHeight(), null);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        int tamañoLetra = 100;
        paint.setTextSize(tamañoLetra);
        String texto = "Prueba \nTexto \nCarta";
//        Rect bounds = new Rect();/
//
//        paint.getTextBounds(texto, 0, texto.length(), bounds);
        float tamañoTexto = paint.measureText(texto);
        float tempX = bmp1.getWidth() - (bmp1.getWidth() - (float) (bmp1.getWidth() * 0.1));
        float tempY = bmp1.getHeight() - (bmp1.getHeight() - (float) (bmp1.getHeight() * 0.81));

        Float alto = Math.abs(paint.ascent() + paint.descent());
        while (tamañoTexto > ((bmp1.getWidth() - imagenColumna.getWidth()) - tempX)) {
            tamañoLetra--;
            paint.setTextSize(tamañoLetra);
            tamañoTexto = paint.measureText(texto);
            Log.i("LETRA", "TAMAÑO MAXIMO: " + ((bmp1.getWidth() - imagenColumna.getWidth()) - tempX));
            Log.i("LETRA", "TAMAÑO TEXTO: " + tamañoTexto);
            Log.i("LETRA", "TAMAÑO: " + tamañoLetra);
        }
//        paint.getTextBounds(texto, 0, texto.length(), bounds);
        alto = Math.abs(paint.ascent()) + Math.abs(paint.descent());

        while(alto>((bmp1.getHeight()-imagenLinea.getHeight())-(tempY))){
            tamañoLetra--;
            paint.setTextSize(tamañoLetra);
            alto = Math.abs(paint.ascent()) + Math.abs(paint.descent());

            Log.i("LETRA2","TAMAÑO MAXIMO: "+((bmp1.getHeight()-imagenLinea.getHeight())-(tempY)));
            Log.i("LETRA2","TAMAÑO: "+tamañoLetra);
            Log.i("LETRA2","ALTO: "+alto);
        }

        Log.i("LETRA3", "FUERA WHILE: " + tamañoLetra);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(tempX,tempY-paint.getTextSize(),bmp1.getWidth()-imagenColumna.getWidth(),bmp1.getHeight()-imagenLinea.getHeight(),paint);

        paint.setColor(Color.BLACK);
        canvas.drawText(texto,tempX,tempY,paint);
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
