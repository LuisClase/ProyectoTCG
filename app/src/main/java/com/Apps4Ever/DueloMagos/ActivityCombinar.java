package com.Apps4Ever.DueloMagos;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Clase para la activity de combinar, permite la creacion y guardado de tus propias cartas
 * de forma personalizable
 *
 * @author Luis Cerqueira
 */
public class ActivityCombinar extends ActionBarActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    public Bitmap imagenCombinada;
    Bitmap imagenMarco;
    Bitmap imagenLinea;
    Bitmap imagenColumna;
    Bitmap imagenTemp;
    Button btnGuardar;
    ImageView imagenCentral;
    ImageView imagenCarta;
    EditText textoNombreCarta;
    Carta cartaPropia;
    int puntos=0;

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

        cartaPropia=new Carta(getApplicationContext(),null,null);

        textoNombreCarta=(EditText)findViewById(R.id.editText);
        final TextView txtNombre=(TextView)findViewById(R.id.txtNombreCarta);

        Button btnCaracteristicas=(Button)findViewById(R.id.btnCaracteristicas);
        Button btnCaracteristicasJugador=(Button)findViewById(R.id.btnCaracteristicasJugador);
        Button btnImagen=(Button)findViewById(R.id.btnImagen);
        Button btnGuardar=(Button)findViewById(R.id.btnGuardar);

        Bitmap imagen1=imagenMarco;
        Bitmap imagen2=((BitmapDrawable)imagenCentral.getDrawable()).getBitmap();
        imagenCombinada=overlay(imagen1,imagen2);
        imagenCarta.setImageBitmap(imagenCombinada);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO GUARDAR EN CARPETA PROPIA
                TratamientoImagenes ti=new TratamientoImagenes();
                if(txtNombre.getText().toString().trim().equals("")){
                    txtNombre.setText("Predefinido");
                }
                ti.saveToInternalSorage(((BitmapDrawable)imagenCarta.getDrawable()).getBitmap(),getApplicationContext(),txtNombre.getText().toString().trim());
            }
        });

        textoNombreCarta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Bitmap imagen1=imagenMarco;
                Bitmap imagen2=((BitmapDrawable)imagenCentral.getDrawable()).getBitmap();
                imagenCombinada=overlay(imagen1,imagen2);
                imagenCarta.setImageBitmap(imagenCombinada);
            }
        });

        btnCaracteristicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoCombinar dialogoCombinar=new DialogoCombinar();
                dialogoCombinar.show(getFragmentManager(),"Combinar");
            }
        });

        btnCaracteristicasJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoCombinarPropio dialogoCombinarPropio=new DialogoCombinarPropio();
                dialogoCombinarPropio.show(getFragmentManager(),"Combinar");
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

            Bitmap imagen1=imagenMarco;
            Bitmap imagen2=((BitmapDrawable)imagenCentral.getDrawable()).getBitmap();
            imagenCombinada=overlay(imagen1,imagen2);
            imagenCarta.setImageBitmap(imagenCombinada);
            Log.i("IMAGEN","NULL3?"+(bImagen==null?"SI":"NO")+" "+picturePath);
            Log.i("IMAGEN","NULL2?"+(imagenCentral.getDrawable()==null?"SI":"NO"));

        }
    }


    /**
     * Funcion que superpone dos imagenes y les añade texto para crear una carta
     * @param bmp1 Bitmap base para la superposicion
     * @param bmp2 Bitmap que se pondra por encima
     * @return
     */
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
        int tamañoLetraNombre=100;
        paint.setTextSize(tamañoLetra);

                //TEXTO CARTA
        int cont=0;
        int contHabilidades=0;
        String texto = "";
        if(cartaPropia.getDañoEnemigo()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getDañoEnemigo()+"Daño(s).";
        }
        if(cartaPropia.getDescarteEnemigo()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getDescarteEnemigo()+"Descarte(s).";
        }
        if(cartaPropia.getCartasEnemigo()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getCartasEnemigo()+"carta(s).";
        }
        if(cartaPropia.getCuraEnemigo()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getCuraEnemigo()+"cura(s).";
        }
        if(cartaPropia.getRecursosEnemigo()!=0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getRecursosEnemigo()+"recurso(s).";
        }
        if(cartaPropia.getMoverMesaAManoEnemigo()!=0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getMoverMesaAManoEnemigo()+"mover de mesa a mano.";
        }
        if(cartaPropia.getMoverDescarteAManoEnemigo()!=0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getMoverDescarteAManoEnemigo()+"mover de descarte a mano.";
        }
        if(cartaPropia.getMoverMesaADeckEnemigo()!=0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getMoverMesaADeckEnemigo()+"mover de mesa a deck.";
        }
        if(cartaPropia.getMoverDescarteADeckEnemigo()!=0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getMoverDescarteADeckEnemigo()+"mover de descarte a deck.";
        }
        if(cartaPropia.getMoverManoADeckEnemigo()!=0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getMoverManoADeckEnemigo()+"mover de mano a deck.";
        }
        if(cartaPropia.getMoverMesaADescarteEnemigo()!=0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getMoverMesaADescarteEnemigo()+"destruir de mesa.";
        }
        if(cartaPropia.getMoverDeckADescarteEnemigo()!=0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                texto+="\n";
            }
            texto+=cartaPropia.getMoverDeckADescarteEnemigo()+" deck.";
        }
        if(contHabilidades>0) {
            texto="Enemigo:"+texto+"\n";
            contHabilidades=0;
            cont=0;
        }
        String textoTemp="";
        if(cartaPropia.getDañoOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getDañoOwner()+"Daño(s).";
        }
        if(cartaPropia.getDescarteOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getDescarteOwner()+"descarte(s).";
        }
        if(cartaPropia.getCuraOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getCuraOwner()+"cura(s).";
        }
        if(cartaPropia.getRecursosOwner()!=0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getRecursosOwner()+"recurso(s).";
        }
        if(cartaPropia.getCartasOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getCartasOwner()+"carta(s).";
        }
        if(cartaPropia.getMoverMesaAManoOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getMoverMesaAManoOwner()+"mover a mano.";
        }
        if(cartaPropia.getMoverDescarteAManoOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getMoverDescarteAManoOwner()+"mover de descarte a mano.";
        }
        if(cartaPropia.getMoverMesaADeckOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getMoverMesaADeckOwner()+"mover de mesa a deck.";
        }
        if(cartaPropia.getMoverDescarteADeckOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getMoverDescarteADeckOwner()+"mover de descarte a deck.";
        }
        if(cartaPropia.getMoverManoADeckOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getMoverManoADeckOwner()+"mover de mano a deck.";
        }
        if(cartaPropia.getMoverMesaADescarteOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getMoverMesaADescarteOwner()+"destruir de mesa.";
        }
        if(cartaPropia.getMoverDeckADescarteOwner()>0){
            contHabilidades++;
            cont++;
            if(cont>2){
                cont=0;
                textoTemp+="\n";
            }
            textoTemp+=cartaPropia.getMoverDeckADescarteOwner()+" deck.";
        }
        if(contHabilidades>0){
            texto+="Propio:"+textoTemp;
        }
        //owner.setRecursos(owner.getRecursos()-getCoste());
        if(cartaPropia.getTipo().ordinal()==Tipos.PERMANENTE.ordinal()){
            texto+=" cada turno.";
        }
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

//        while(alto>((bmp1.getHeight()-imagenLinea.getHeight())-(tempY))){
//            tamañoLetra--;
//            paint.setTextSize(tamañoLetra);
//            alto = Math.abs(paint.ascent()) + Math.abs(paint.descent());
//
//            Log.i("LETRA2","TAMAÑO MAXIMO: "+((bmp1.getHeight()-imagenLinea.getHeight())-(tempY)));
//            Log.i("LETRA2","TAMAÑO: "+tamañoLetra);
//            Log.i("LETRA2","ALTO: "+alto);
//        }

        Log.i("LETRA3", "FUERA WHILE: " + tamañoLetra);
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        canvas.drawRect(tempX,tempY-paint.getTextSize(),bmp1.getWidth()-imagenColumna.getWidth(),bmp1.getHeight()-imagenLinea.getHeight(),paint);

        TextPaint mTextPaint=new TextPaint();
        mTextPaint.setTextSize(tamañoLetra);
        StaticLayout mTextLayout=new StaticLayout(texto,mTextPaint,canvas.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f,false);
        canvas.save();
        canvas.translate(tempX-(float)(tamañoLetra*1.5),tempY-tamañoLetra);
        mTextLayout.draw(canvas);
        canvas.restore();
        paint.setColor(Color.BLACK);


        //TEXTO NOMBRE CARTA


        paint.setTextSize(tamañoLetra);
        String textoNombre = textoNombreCarta.getText().toString();
//        Rect bounds = new Rect();/
//
//        paint.getTextBounds(texto, 0, texto.length(), bounds);
        float tamañoTextoNombre = paint.measureText(textoNombre);
        float tempX2 = bmp1.getWidth() - (bmp1.getWidth() - (float) (bmp1.getWidth() * 0.1));

        Float alto2 = Math.abs(paint.ascent() + paint.descent());
        while (tamañoTextoNombre > ((bmp1.getWidth() - imagenColumna.getWidth()) - tempX)) {
            tamañoLetraNombre--;
            paint.setTextSize(tamañoLetraNombre);
            tamañoTextoNombre = paint.measureText(textoNombre);
            Log.i("LETRA3", "TAMAÑO MAXIMO: " + ((bmp1.getWidth() - imagenColumna.getWidth()) - tempX));
            Log.i("LETRA3", "TAMAÑO TEXTO: " + tamañoTextoNombre);
            Log.i("LETRA3", "TAMAÑO: " + tamañoLetraNombre);
        }
//        paint.getTextBounds(texto, 0, texto.length(), bounds);
        alto2 = Math.abs(paint.ascent()) + Math.abs(paint.descent());

//        while(alto2>imagenLinea.getHeight()){
//            tamañoLetraNombre--;
//            paint.setTextSize(tamañoLetraNombre);
//            alto2 = Math.abs(paint.ascent()) + Math.abs(paint.descent());
//
//            Log.i("LETRA4","TAMAÑO MAXIMO: "+(imagenLinea.getHeight()));
//            Log.i("LETRA4","TAMAÑO: "+tamañoLetraNombre);
//            Log.i("LETRA4","ALTO: "+alto2);
//        }

        Log.i("LETRA5", "FUERA WHILE: " + tamañoLetraNombre);
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        canvas.drawRect(tempX,tempY-paint.getTextSize(),bmp1.getWidth()-imagenColumna.getWidth(),bmp1.getHeight()-imagenLinea.getHeight(),paint);

        TextPaint mTextPaint2=new TextPaint();
        mTextPaint2.setTextSize(tamañoLetraNombre);
        StaticLayout mTextLayout2=new StaticLayout(textoNombre,mTextPaint2,canvas.getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f,false);
        canvas.save();
//        canvas.translate((float)(tempX2*1.4)-(float)(tamañoLetra),(imagenLinea.getHeight()/3)*2-tamañoLetra);
        mTextLayout2.draw(canvas);
        canvas.restore();
        paint.setTextSize(70);
        canvas.drawText(""+(Math.abs(puntos)/4),(float)(tempX2*1.2),(float)(imagenLinea.getHeight()*1.4),paint);
//        canvas.drawText(texto,tempX,tempY,paint);
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
