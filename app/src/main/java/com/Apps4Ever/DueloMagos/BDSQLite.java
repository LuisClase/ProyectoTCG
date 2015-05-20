package com.Apps4Ever.DueloMagos;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Clase para el manejo y gestion de la base de datos SQLite que utiliza el juego
 * para la gestion de las cartas de cada jugador
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 27/02/2015.
 */
public class BDSQLite extends SQLiteOpenHelper {


    String sqlCreateTable="CREATE TABLE cartas(" +
            "jugador INTEGER, nombre TEXT, imagen INT, coste INT, cantidad INT, tipo INT, " +
            "creada BOOLEAN NOT NULL CHECK (onEndTurnDeck IN (0,1)), " +
            "dañoEnemigo INT, curaEnemigo INT, cartasEnemigo INT, descarteEnemigo INT, " +
            "recursosEnemigo INT, moverMesaAManoEnemigo INT, moverDescarteAmanoEnemigo INT, " +
            "moverDeckAmanoEnemigo INT, moverMesaADeckEnemigo INT, moverDescarteADeckEnemigo INT, " +
            "moverManoADeckEnemigo INT, moverMesaADescarteEnemigo INT, moverManoADescarteEnemigo INT, " +
            "moverDeckADescarteEnemigo INT, moverDescarteAMesaEnemigo INT, moverManoAMesaEnemigo INT, " +
            "moverDeckAMesaEnemigo INT, " +
            "dañoOwner INT, curaOwner INT, cartasOwner INT, descarteOwner INT, " +
            "recursosOwner INT, moverMesaAManoOwner INT, moverDescarteAmanoOwner INT, " +
            "moverDeckAmanoOwner INT, moverMesaADeckOwner INT, moverDescarteADeckOwner INT, " +
            "moverManoADeckOwner INT, moverMesaADescarteOwner INT, moverManoADescarteOwner INT, " +
            "moverDeckADescarteOwner INT, moverDescarteAMesaOwner INT, moverManoAMesaOwner INT, " +
            "moverDeckAMesaOwner INT, " +
            "onMoveMesaADescarte BOOLEAN NOT NULL CHECK (onMoveMesaADescarte IN (0,1)), " +
            "onMoveMesaADeck BOOLEAN NOT NULL CHECK (onMoveMesaADeck IN (0,1)), " +
            "onMoveMesaAMano BOOLEAN NOT NULL CHECK (onMoveMesaAMano IN (0,1)), " +
            "onMoveDescarteAMesa BOOLEAN NOT NULL CHECK (onMoveDescarteAMesa IN (0,1)), " +
            "onMoveDescarteADeck BOOLEAN NOT NULL CHECK (onMoveDescarteADeck IN (0,1)), " +
            "onMoveDescarteAMano BOOLEAN NOT NULL CHECK (onMoveDescarteAMano IN (0,1)), " +
            "onMoveDeckADescarte BOOLEAN NOT NULL CHECK (onMoveDeckADescarte IN (0,1)), " +
            "onMoveDeckAMesa BOOLEAN NOT NULL CHECK (onMoveDeckAMesa IN (0,1)), " +
            "onMoveDeckAMano BOOLEAN NOT NULL CHECK (onMoveDeckAMano IN (0,1)), " +
            "onMoveManoADescarte BOOLEAN NOT NULL CHECK (onMoveManoADescarte IN (0,1)), " +
            "onMoveManoAMesa BOOLEAN NOT NULL CHECK (onMoveManoAMesa IN (0,1)), " +
            "onMoveManoADeck BOOLEAN NOT NULL CHECK (onMoveManoADeck IN (0,1)), " +
            "onStartTurnTable BOOLEAN NOT NULL CHECK (onStartTurnTable IN (0,1)), " +
            "onStartTurnHand BOOLEAN NOT NULL CHECK (onStartTurnHand IN (0,1)), " +
            "onStartTurnDiscard BOOLEAN NOT NULL CHECK (onStartTurnDiscard IN (0,1)), " +
            "onStartTurnDeck BOOLEAN NOT NULL CHECK (onStartTurnDeck IN (0,1)), " +
            "onEndTurnTable BOOLEAN NOT NULL CHECK (onEndTurnTable IN (0,1)), " +
            "onEndTurnHand BOOLEAN NOT NULL CHECK (onEndTurnHand IN (0,1)), " +
            "onEndTurnDiscard BOOLEAN NOT NULL CHECK (onEndTurnDiscard IN (0,1)), " +
            "onEndTurnDeck BOOLEAN NOT NULL CHECK (onEndTurnDeck IN (0,1)) " +
            " )";

    String insert="INSERT INTO cartas VALUES " +
            "(1, 'BurningSign', "+R.drawable.cardburningsign+", 2, 0, "+Tipos.PERMANENTE.ordinal()+", 0," +
            ""+insertarCartaBurningSign()+"," +
            "(1, 'Heal', "+ R.drawable.cardheal+", 2, 0, "+Tipos.HECHIZO.ordinal()+", 0," +
            ""+insertarCartaHeal()+", " +
            "(1, 'HealingSing', "+R.drawable.cardhealingsign+", 2, 0, "+Tipos.PERMANENTE.ordinal()+", 0," +
            ""+insertarCartaHealingSign()+", " +
            "(1, 'Lightning', "+R.drawable.cardlightning+", 1, 0, "+Tipos.HECHIZO.ordinal()+", 0," +
            ""+insertarCartaLightning()+", " +
            "(1, 'MentalSpiral', "+R.drawable.cardmentalspiral2+", 2, 0, "+Tipos.HECHIZO.ordinal()+", 0," +
            ""+insertarCartaMentalSpiral()+", " +
            "(1, 'MysticalSign', "+R.drawable.cardmysticalsign+", 2, 0, "+Tipos.PERMANENTE.ordinal()+", 0," +
            ""+insertarCartaMysticalSign()+", " +
            "(1, 'NaturalHelp', "+R.drawable.cardnaturalhelp+", 0, 0, "+Tipos.HECHIZO.ordinal()+", 0," +
            ""+insertarCartaNaturalHelp()+", " +
            "(1, 'NaturalResources', "+R.drawable.cardnaturalresources+", 2, 0, "+Tipos.HECHIZO.ordinal()+", 0," +
            ""+insertarCartaNaturalResources()+", " +
            "(1, 'NaturalSign', "+R.drawable.cardnaturalsign+", 2, 0, "+Tipos.PERMANENTE.ordinal()+", 0," +
            ""+insertarCartaNaturalSign()+", " +
            "(1, 'Nightmare', "+R.drawable.cardnightmare+", 5, 0, "+Tipos.HECHIZO.ordinal()+", 0," +
            ""+insertarCartaNightmare()+", " +
            "(1, 'Ritual', "+R.drawable.cardritual+", 2, 0, "+Tipos.HECHIZO.ordinal()+", 0," +
            ""+insertarCartaRitual()+", " +
            "(1, 'VitalTransfusion', "+R.drawable.cardvitaltransfusion+", 2, 0, "+Tipos.HECHIZO.ordinal()+", 0," +
            ""+insertarCartaVitalTransfusion()+" ;";

    public BDSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BDSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("BD","ANTES ONCREATE");
        db.execSQL(sqlCreateTable);
        Log.i("BD","DESPUES ONCREATE");
        db.execSQL(insert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cartas ;");
        db.execSQL(sqlCreateTable);
        db.execSQL(insert);
        Log.i("INSERT", insert);
    }

    /**
     * Funcion para la insercion en la base de datos de una carta dada
     * @param carta Carta que se introducira en la base de datos
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCarta(Carta carta) {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                0,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        if(carta.getDañoEnemigo()!=0){
            valoresEnemigo[0]=carta.getDañoEnemigo();
        }
        if(carta.getCuraEnemigo()!=0){
            valoresEnemigo[1]=carta.getCuraEnemigo();
        }
        if(carta.getCartasEnemigo()!=0){
            valoresEnemigo[2]=carta.getCartasEnemigo();
        }
        if(carta.getDescarteEnemigo()!=0){
            valoresEnemigo[3]=carta.getDescarteEnemigo();
        }
        if(carta.getRecursosEnemigo()!=0){
            valoresEnemigo[4]=carta.getRecursosEnemigo();
        }
        if(carta.getMoverMesaAManoEnemigo()!=0){
            valoresEnemigo[5]=carta.getMoverMesaAManoEnemigo();
        }
        if(carta.getMoverDescarteAManoEnemigo()!=0){
            valoresEnemigo[6]=carta.getMoverDescarteAManoEnemigo();
        }
        if(carta.getMoverMesaADeckEnemigo()!=0){
            valoresEnemigo[8]=carta.getMoverMesaADeckEnemigo();
        }
        if(carta.getMoverDescarteADeckEnemigo()!=0){
            valoresEnemigo[9]=carta.getMoverDescarteADeckEnemigo();
        }
        if(carta.getMoverManoADeckEnemigo()!=0){
            valoresEnemigo[10]=carta.getMoverManoADeckEnemigo();
        }
        if(carta.getMoverMesaADescarteEnemigo()!=0){
            valoresEnemigo[11]=carta.getMoverMesaADescarteEnemigo();
        }
        if(carta.getMoverManoADescarteEnemigo()!=0){
            valoresEnemigo[12]=carta.getMoverManoADescarteEnemigo();
        }
        if(carta.getMoverDeckADescarteEnemigo()!=0){
            valoresEnemigo[13]=carta.getMoverDescarteADeckEnemigo();
        }


        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        if(carta.getDañoOwner()!=0){
            valoresOwner[0]=carta.getDañoOwner();
        }
        if(carta.getCuraOwner()!=0){
            valoresOwner[1]=carta.getCuraOwner();
        }
        if(carta.getCartasOwner()!=0){
            valoresOwner[2]=carta.getCartasOwner();
        }
        if(carta.getDescarteOwner()!=0){
            valoresOwner[3]=carta.getDescarteOwner();
        }
        if(carta.getRecursosOwner()!=0){
            valoresOwner[4]=carta.getRecursosOwner();
        }
        if(carta.getMoverMesaAManoOwner()!=0){
            valoresOwner[5]=carta.getMoverMesaAManoOwner();
        }
        if(carta.getMoverDescarteAManoOwner()!=0){
            valoresOwner[6]=carta.getMoverDescarteAManoOwner();
        }
        if(carta.getMoverMesaADeckOwner()!=0){
            valoresOwner[8]=carta.getMoverMesaADeckOwner();
        }
        if(carta.getMoverDescarteADeckOwner()!=0){
            valoresOwner[9]=carta.getMoverDescarteADeckOwner();
        }
        if(carta.getMoverManoADeckOwner()!=0){
            valoresOwner[10]=carta.getMoverManoADeckOwner();
        }
        if(carta.getMoverMesaADescarteOwner()!=0){
            valoresOwner[11]=carta.getMoverMesaADescarteOwner();
        }
        if(carta.getMoverManoADescarteOwner()!=0){
            valoresOwner[12]=carta.getMoverManoADescarteOwner();
        }
        if(carta.getMoverDeckADescarteOwner()!=0){
            valoresOwner[1]=carta.getMoverDescarteADeckOwner();
        }
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                false,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };




        ArrayList<Integer> datos = new ArrayList<Integer>();
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }


    /**
     * Funcion para la insercion en la base de datos de una carta generica
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCarta() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                0,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                false,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo BurningSign
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaBurningSign() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                2,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                false,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                true,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo Heal
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaHeal() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                0,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                4,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                true,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo HealingSign
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaHealingSign() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                0,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                2,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                false,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                true,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo Lightning
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaLightning() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                2,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                true,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        String nombre = "";
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo MentalSpiral
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaMentalSpiral() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                0,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                1,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                1,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                true,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        String nombre = "";
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo MysticalSign
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaMysticalSign() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                0,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                1,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                false,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                true,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        String nombre = "";
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo NaturalHelp
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaNaturalHelp() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                0,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                2,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                true,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        String nombre = "";
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo NaturalResources
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaNaturalResources() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                0,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                5,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                true,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        String nombre = "";
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo NaturalSign
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaNaturalSign() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                0,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                2,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                false,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                true,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        String nombre = "";
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo Nightmare
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaNightmare() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                0,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                15,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                true,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        String nombre = "";
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo Ritual
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaRitual() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                1,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//        this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                0,
//        this.cartasOwner=valoresOwner[2];
                1,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                true,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        String nombre = "";
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

    /**
     * Funcion para la insercion en la base de datos de una carta tipo VitalTransfusion
     * @return query con la que hacer el insert en la base de datos
     */
    private String insertarCartaVitalTransfusion() {
        int[] valoresEnemigo = {
//                this.dañoEnemigo=valoresEnemigo[0];
                3,
//        this.curaEnemigo=valoresEnemigo[1];
                0,
//        this.cartasEnemigo=valoresEnemigo[2];
                0,
//        this.descarteEnemigo=valoresEnemigo[3];
                0,
//        this.recursosEnemigo=valoresEnemigo[4];
                0,
//        this.moverMesaAManoEnemigo=valoresEnemigo[5];
                0,
//        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
                0,
//        this.moverDeckAManoEnemigo=valoresEnemigo[7];
                0,
//        this.moverMesaADeckEnemigo=valoresEnemigo[8];
                0,
//        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
                0,
//        this.moverManoADeckEnemigo=valoresEnemigo[10];
                0,
//        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
                0,
//        this.moverManoADescarteEnemigo=valoresEnemigo[12];
                0,
//        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
                0,
//        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
                0,
//        this.moverManoAMesaEnemigo=valoresEnemigo[15];
                0,
//        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
                0
        };
        int[] valoresOwner = {
//                this.dañoOwner=valoresOwner[0];
                0,
//        this.curaOwner=valoresOwner[1];
                2,
//        this.cartasOwner=valoresOwner[2];
                0,
//        this.descarteOwner=valoresOwner[3];
                0,
//        this.recursosOwner=valoresOwner[4];
                0,
//        this.moverMesaAManoOwner=valoresOwner[5];
                0,
//        this.moverDescarteAManoOwner=valoresOwner[6];
                0,
//        this.moverDeckAManoOwner=valoresOwner[7];
                0,
//        this.moverMesaADeckOwner=valoresOwner[8];
                0,
//        this.moverDescarteADeckOwner=valoresOwner[9];
                0,
//        this.moverManoADeckOwner=valoresOwner[10];
                0,
//        this.moverMesaADescarteOwner=valoresOwner[11];
                0,
//        this.moverManoADescarteOwner=valoresOwner[12];
                0,
//        this.moverDeckADescarteOwner=valoresOwner[13];
                0,
//        this.moverDescarteAMesaOwner=valoresOwner[14];
                0,
//        this.moverManoAMesaOwner=valoresOwner[15];
                0,
//        this.moverDeckAMesaOwner=valoresOwner[16];
                0
        };
        boolean[]valoresPlay={
                //this.OnMoveMesaADescarte=valoresPlay[0];
                false,
                //this.OnMoveMesaADeck=valoresPlay[1];
                false,
                //this.OnMoveMesaAMano=valoresPlay[2];
                false,
                //this.OnMoveDescarteAMesa=valoresPlay[3];
                false,
                //this.OnMoveDescarteADeck=valoresPlay[4];
                false,
//        this.OnMoveDescarteAMano=valoresPlay[5];
                false,
//        this.OnMoveDeckADescarte=valoresPlay[6];
                false,
//        this.OnMoveDeckAMesa=valoresPlay[7];
                false,
//        this.OnMoveDeckAMano=valoresPlay[8];
                false,
//        this.OnMoveManoADescarte=valoresPlay[9];
                false,
//        this.OnMoveManoAMesa=valoresPlay[10];
                true,
//        this.OnMoveManoADeck=valoresPlay[11];
                false,
//        this.OnStartTurnTable=valoresPlay[12];
                false,
//        this.OnStartTurnHand=valoresPlay[13];
                false,
//        this.OnStartTurnDiscard=valoresPlay[14];
                false,
//        this.OnStartTurnDeck=valoresPlay[15];
                false,
//        this.OnEndTurnTable=valoresPlay[16];
                false,
//        this.OnEndTurnHand=valoresPlay[17];
                false,
//        this.OnEndTurnDiscard=valoresPlay[18];
                false,
//        this.OnEndTurnDeck=valoresPlay[19];
                false,
        };
        ArrayList<Integer> datos = new ArrayList<Integer>();
        String nombre = "";
        for (int i = 0; i < valoresEnemigo.length; i++) {
            datos.add(valoresEnemigo[i]);
        }
        for (int i = 0; i < valoresOwner.length; i++) {
            datos.add(valoresOwner[i]);
        }
        for (int i = 0; i < valoresPlay.length; i++) {
            if(valoresPlay[i]){
                datos.add(1);
            }else{
                datos.add(0);
            }
        }
        String insert="";
        for(int i=0;i<datos.size();i++){
            if(i<datos.size()-1) {
                insert += datos.get(i) + ", ";
            }else{
                insert += datos.get(i) + " )";
            }
        }
        return insert;
    }

//TODO ignite memories


//    private String insertarCartaIgniteMemories() {
//        int[] valoresEnemigo = {
////                this.dañoEnemigo=valoresEnemigo[0];
//                0,
////        this.curaEnemigo=valoresEnemigo[1];
//                0,
////        this.cartasEnemigo=valoresEnemigo[2];
//                0,
////        this.descarteEnemigo=valoresEnemigo[3];
//                0,
////        this.recursosEnemigo=valoresEnemigo[4];
//                0,
////        this.moverMesaAManoEnemigo=valoresEnemigo[5];
//                0,
////        this.moverDescarteAManoEnemigo=valoresEnemigo[6];
//                0,
////        this.moverDeckAManoEnemigo=valoresEnemigo[7];
//                0,
////        this.moverMesaADeckEnemigo=valoresEnemigo[8];
//                0,
////        this.moverDescarteADeckEnemigo=valoresEnemigo[9];
//                0,
////        this.moverManoADeckEnemigo=valoresEnemigo[10];
//                0,
////        this.moverMesaADescarteEnemigo=valoresEnemigo[11];
//                0,
////        this.moverManoADescarteEnemigo=valoresEnemigo[12];
//                0,
////        this.moverDeckADescarteEnemigo=valoresEnemigo[13];
//                0,
////        this.moverDescarteAMesaEnemigo=valoresEnemigo[14];
//                0,
////        this.moverManoAMesaEnemigo=valoresEnemigo[15];
//                0,
////        this.moverDeckAMesaEnemigo=valoresEnemigo[16];
//                0
//        };
//        int[] valoresOwner = {
////                this.dañoOwner=valoresOwner[0];
//                0,
////        this.curaOwner=valoresOwner[1];
//                0,
////        this.cartasOwner=valoresOwner[2];
//                0,
////        this.descarteOwner=valoresOwner[3];
//                0,
////        this.recursosOwner=valoresOwner[4];
//                0,
////        this.moverMesaAManoOwner=valoresOwner[5];
//                0,
////        this.moverDescarteAManoOwner=valoresOwner[6];
//                0,
////        this.moverDeckAManoOwner=valoresOwner[7];
//                0,
////        this.moverMesaADeckOwner=valoresOwner[8];
//                0,
////        this.moverDescarteADeckOwner=valoresOwner[9];
//                0,
////        this.moverManoADeckOwner=valoresOwner[10];
//                0,
////        this.moverMesaADescarteOwner=valoresOwner[11];
//                0,
////        this.moverManoADescarteOwner=valoresOwner[12];
//                0,
////        this.moverDeckADescarteOwner=valoresOwner[13];
//                0,
////        this.moverDescarteAMesaOwner=valoresOwner[14];
//                0,
////        this.moverManoAMesaOwner=valoresOwner[15];
//                0,
////        this.moverDeckAMesaOwner=valoresOwner[16];
//                0
//        };
//        boolean[]valoresPlay={
//                //this.OnMoveMesaADescarte=valoresPlay[0];
//                false,
//                //this.OnMoveMesaADeck=valoresPlay[1];
//                false,
//                //this.OnMoveMesaAMano=valoresPlay[2];
//                false,
//                //this.OnMoveDescarteAMesa=valoresPlay[3];
//                false,
//                //this.OnMoveDescarteADeck=valoresPlay[4];
//                false,
////        this.OnMoveDescarteAMano=valoresPlay[5];
//                false,
////        this.OnMoveDeckADescarte=valoresPlay[6];
//                false,
////        this.OnMoveDeckAMesa=valoresPlay[7];
//                false,
////        this.OnMoveDeckAMano=valoresPlay[8];
//                false,
////        this.OnMoveManoADescarte=valoresPlay[9];
//                false,
////        this.OnMoveManoAMesa=valoresPlay[10];
//                false,
////        this.OnMoveManoADeck=valoresPlay[11];
//                false,
////        this.OnStartTurnTable=valoresPlay[12];
//                false,
////        this.OnStartTurnHand=valoresPlay[13];
//                false,
////        this.OnStartTurnDiscard=valoresPlay[14];
//                false,
////        this.OnStartTurnDeck=valoresPlay[15];
//                false,
////        this.OnEndTurnTable=valoresPlay[16];
//                false,
////        this.OnEndTurnHand=valoresPlay[17];
//                false,
////        this.OnEndTurnDiscard=valoresPlay[18];
//                false,
////        this.OnEndTurnDeck=valoresPlay[19];
//                false,
//        };
//        ArrayList<Integer> datos = new ArrayList<Integer>();
//        String nombre = "";
//        for (int i = 0; i < valoresEnemigo.length; i++) {
//            datos.add(valoresEnemigo[i]);
//        }
//        for (int i = 0; i < valoresOwner.length; i++) {
//            datos.add(valoresOwner[i]);
//        }
//        for (int i = 0; i < valoresPlay.length; i++) {
//            if(valoresPlay[i]){
//                datos.add(1);
//            }else{
//                datos.add(0);
//            }
//        }
//        String insert="(";
//        for(int i=0;i<datos.size();i++){
//            if(i<datos.size()-1) {
//                insert += datos.get(i) + ", ";
//            }else{
//                insert += datos.get(i) + " )";
//            }
//        }
//        return insert;
//    }
}

