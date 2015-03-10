package com.example.luiscerqueira.proyectotcg;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
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
    ImageView imagenCentral;
    ImageView imagenCarta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_combinar);
        imagenCarta=(ImageView)findViewById(R.id.imageView);
        imagenCentral=(ImageView)findViewById(R.id.imageView2);
        Button btnCombinar=(Button)findViewById(R.id.btnCombinar);
        Button btnCaracteristicas=(Button)findViewById(R.id.btnCaracteristicas);
        Button btnImagen=(Button)findViewById(R.id.btnImagen);

        Bitmap imagen1=((BitmapDrawable)imagenCarta.getDrawable()).getBitmap();
            Bitmap imagen2=((BitmapDrawable)imagenCentral.getDrawable()).getBitmap();
        imagenCombinada=overlay(imagen1,imagen2);

        btnCombinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap imagen1=((BitmapDrawable)imagenCarta.getDrawable()).getBitmap();
                Bitmap imagen2=((BitmapDrawable)imagenCentral.getDrawable()).getBitmap();
                imagenCombinada=overlay(imagen1,imagen2);
                DialogoCombinar dCombinar=new DialogoCombinar();
                dCombinar.show(getFragmentManager(),"Combinar");
            }
        });
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

            Log.i("IMAGEN","NULL3?"+(bImagen==null?"SI":"NO")+" "+picturePath);
            Log.i("IMAGEN","NULL2?"+(imagenCentral.getDrawable()==null?"SI":"NO"));

        }
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
