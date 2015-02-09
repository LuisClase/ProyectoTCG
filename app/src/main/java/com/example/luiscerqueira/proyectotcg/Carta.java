package com.example.luiscerqueira.proyectotcg;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.util.Log;

/**
 * Created by Luis Cerqueira on 22/01/2015.
 */
public class Carta {
    private String nombre;
    private int id;
    private int coste;
    private Jugador owner;
    private Jugador enemigo;
    private Bitmap imagen;
    private Bitmap imagenBack;
    private int xInicio;
    private int yInicio;
    private int xFin;
    private int yFin;

    public Carta(int id,Jugador owner,Jugador enemigo,Bitmap imagen,Bitmap imagenBack){
        this.nombre="Prueba";
        this.id=id;
        this.coste=0;
        this.xInicio=0;
        this.yInicio=0;
        this.xFin=0;
        this.yFin=0;
        this.owner=owner;
        this.enemigo=enemigo;
        this.imagen=imagen;
        this.imagenBack=imagenBack;
    }

    public Carta(Context context,int id,Jugador owner,Jugador enemigo){
        this.nombre="Prueba";
        this.id=id;
        this.coste=0;
        this.xInicio=0;
        this.yInicio=0;
        this.xFin=0;
        this.yFin=0;
        this.owner=owner;
        Log.i("OWNER","VIDAS:"+getOwner().getVidas());
        this.enemigo=enemigo;
        Bitmap temp=BitmapFactory.decodeResource(context.getResources(), R.drawable.frontcard);
        this.imagen=temp;
        temp=BitmapFactory.decodeResource(context.getResources(), R.drawable.cardbackprueba);
        this.imagenBack=temp;
    }

    public void playCard(){

    }

    public void startOfTurnTable(){
    }
    public void startOfTurnHand(){
    }
    public void startOfTurnDiscard(){
    }
    public void startOfTurnDeck(){
    }
    public void endOfTurnTable(){
    }
    public void endOfTurnHand(){
    }
    public void endOfTurnDiscard(){
    }
    public void endOfTurnDeck(){
    }

    public void movedFromDeckToHand(){}

    public void movedFromDeckToTable(){}

    public void movedFromDeckToDiscard(){}

    public void movedFromHandToDiscard(){}

    public void movedFromHandToDeck(){}

    public void movedFromHandToTable(){}

    public void movedFromTableToDiscard(){}

    public void movedFromTableToDeck(){}

    public void movedFromTableToHand(){}

    public void movedFromDiscardToTable(){}

    public void movedFromDiscardToDeck(){}

    public void movedFromDiscardToHand(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoste() {
        return coste;
    }

    public void setCoste(int coste) {
        this.coste = coste;
    }

    public Jugador getOwner() {
        return owner;
    }

    public void setOwner(Jugador owner) {
        this.owner = owner;
    }

    public Jugador getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(Jugador enemigo) {
        this.enemigo = enemigo;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public Bitmap getImagenBack() {
        return imagenBack;
    }

    public void setImagenBack(Bitmap imagenBack) {
        this.imagenBack = imagenBack;
    }

    public int getyFin() {
        return yFin;
    }

    public void setyFin(int yFin) {
        this.yFin = yFin;
    }

    public int getxInicio() {
        return xInicio;
    }

    public void setxInicio(int xInicio) {
        this.xInicio = xInicio;
    }

    public int getyInicio() {
        return yInicio;
    }

    public void setyInicio(int yInicio) {
        this.yInicio = yInicio;
    }

    public int getxFin() {
        return xFin;
    }

    public void setxFin(int xFin) {
        this.xFin = xFin;
    }
}
