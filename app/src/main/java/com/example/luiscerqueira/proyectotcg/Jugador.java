package com.example.luiscerqueira.proyectotcg;

        import android.os.Parcel;
        import android.os.Parcelable;
        import android.util.Log;
        import java.util.Collections.*;
        import java.util.Collections;
        import java.util.Random;

        import java.util.ArrayList;
        import java.util.UUID;

/**
 * Clase donde se almacenan datos que corresponden al jugador
 *
 * @author Luis Cerqueira
 * Created by Luis Cerqueira on 22/01/2015.
 */
public class Jugador implements Parcelable{

    final int CARTAS_MESA=4;
    final int CARTAS_MANO=5;
    private String nombre="Predeterminado";
    private int recursosIniciales=0;
    private int vidasIniciales=20;
    private int cartasIniciales=1;
    private ArrayList<Carta> deck=new ArrayList<Carta>();
    private ArrayList<Carta> mano=new ArrayList<Carta>();
    private ArrayList<Carta> descarte=new ArrayList<Carta>();
    private ArrayList<Carta> mesa=new ArrayList<Carta>();
    private int vidas;
    private int recursos;
    private boolean activo=false;
    private boolean tocandoMano=false;
    private boolean tocandoDeck=false;
    private boolean tocandoMesa=false;
    private boolean tocandoDescarte=false;
    private boolean tocandoFinTurno=false;

    private int deckX;
    private int deckXfin;
    private int deckY;
    private int deckYfin;
    private int manoX;
    private int manoXfin;
    private int manoY;
    private int manoYfin;
    private int descarteX;
    private int descarteXfin;
    private int descarteY;
    private int descarteYfin;
    private int mesaX;
    private int mesaXfin;
    private int mesaY;
    private int mesaYfin;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(getDeck().get(0));
//        dest.writeList(getDeck());
//        dest.writeList(getMano());
//        dest.writeList(getDescarte());
//        dest.writeList(getMesa());
        dest.writeInt(getVidas());
        dest.writeInt(getRecursos());
    }

    /**
     * Funcion para inicializar apartir de un parcelable, actualmente en sin uso
     * @param in Parcelable del que queremos leer
     */
    private void readFromParcel(Parcel in){
        getDeck().add((Carta)in.readValue(null));
//        in.readList(deck, null);
//        in.readList(mano, null);
//        in.readList(descarte, null);
//        in.readList(mesa, null);
        setVidas(in.readInt());
        setRecursos(in.readInt());
    }

    /**
     * Funcion para la gestion del parcelable jugador
     */
    public static final Creator<Jugador> CREADOR=new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel source) {
            return new Jugador(source);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    public Jugador(int vidas,int recursos){
        this.vidas=vidas;
        this.recursos=recursos;
    }

    public Jugador(Parcel in){
        readFromParcel(in);
    }

    /**
     * Funcion que se ejecuta al principio de cada turno del jugador, produce cambios en sus recursos y su mano
     * y ejecuta la funcion correspondiente de comienzo de turno en cada carta del jugador
     */
    public void startOfTurn(){
                        Log.i("TURNO", "PRINCIPIO TURNO");
        this.setRecursos(getRecursos()+1);
        moveFromDeckToHand(1);
        Log.i("TURNO", "PRINCIPIO TURNO CARTA");
        for(int i=0;i<getDeck().size();i++){
            getDeck().get(i).startOfTurnDeck();
        }
        for(int i=0;i<getMano().size();i++){
            getMano().get(i).startOfTurnHand();
        }
        for(int i=0;i<getMesa().size();i++){
            getMesa().get(i).startOfTurnTable();
        }
        for(int i=0;i<getDescarte().size();i++){
            getDescarte().get(i).startOfTurnDiscard();
        }
    }

    /**
     * Funcion que se ejecuta al final de cada turno del jugador,
     * ejecuta la funcion correspondiente de final de turno en cada carta del jugador
     */
    public void endOfTurn(){
        for(int i=0;i<getDeck().size();i++){
            getDeck().get(i).endOfTurnDeck();
        }
        for(int i=0;i<getMano().size();i++){
            getMano().get(i).endOfTurnHand();
        }
        for(int i=0;i<getMesa().size();i++){
            getMesa().get(i).endOfTurnTable();
        }
        for(int i=0;i<getDescarte().size();i++){
            getDescarte().get(i).endOfTurnDiscard();
        }
    }

    /**
     * Funcion para aleatorizar las cartas del mazo del jugador
     */
    public void barajar(){
        Random random=new Random();
        Collections.shuffle(deck,random);
    }

    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isTocandoDescarte() {
        return tocandoDescarte;
    }

    public void setTocandoDescarte(boolean tocandoDescarte) {
        this.tocandoDescarte = tocandoDescarte;
    }

    public boolean isTocandoMano() {
        return tocandoMano;
    }

    public void setTocandoMano(boolean tocandoMano) {
        this.tocandoMano = tocandoMano;
    }

    public boolean isTocandoDeck() {
        return tocandoDeck;
    }

    public void setTocandoDeck(boolean tocandoDeck) {
        this.tocandoDeck = tocandoDeck;
    }

    public boolean isTocandoMesa() {
        return tocandoMesa;
    }

    public void setTocandoMesa(boolean tocandoMesa) {
        this.tocandoMesa = tocandoMesa;
    }

    public boolean isTocandoFinTurno() {
        return tocandoFinTurno;
    }

    public void setTocandoFinTurno(boolean tocandoFinTurno) {
        this.tocandoFinTurno = tocandoFinTurno;
    }

    //Genericas

    /**
     * Funcion para el transpaso de cartas entre dos zonas del jugador
     * @param id carta que se quiere transpasar
     * @param origen arraylist de origen de la carta
     * @param destino arraylist de destino de la carta
     */
    public void moveCard(int id,ArrayList<Carta> origen,ArrayList<Carta> destino){
        for(int i=0;i<origen.size();i++){
            if(origen.get(i).getId()==id){
                destino.add(origen.get(i));
                origen.remove(i);
            }
        }
    }

    /**
     * Funcion que envia una carta a la mesa del jugador
     * @param id Carta que se quiere transpasar a la mesa
     * @param origen arraylist de origen de la carta
     */
    public void moveCardToTable(int id,ArrayList<Carta> origen){
        if(getMesa().size()<CARTAS_MESA) {
            for (int i = 0; i < origen.size(); i++) {
                if (origen.get(i).getId() == id) {
                    this.getMesa().add(origen.get(i));
                    origen.remove(i);
                }
            }
        }
    }

    /**
     * Funcion que envia una carta a la mano del jugador
     * @param id Carta que se quiere transpasar a la mano
     * @param origen arraylist de origen de la carta
     */
    public void moveCardToHand(int id,ArrayList<Carta> origen){
        if(getMano().size()<CARTAS_MANO) {
            for (int i = 0; i < origen.size(); i++) {
                if (origen.get(i).getId() == id) {
                    this.getMano().add(origen.get(i));
                    origen.remove(i);
                }
            }
        }else{
            moveCardToDiscard(id,origen);
        }
    }

    /**
     * Funcion que envia una carta al descarte del jugador
     * @param id Carta que se quiere transpasar al descarte
     * @param origen arraylist de origen de la carta
     */
    public void moveCardToDiscard(int id,ArrayList<Carta> origen){
        for(int i=0;i<origen.size();i++){
            if(origen.get(i).getId()==id){
                this.getDescarte().add(origen.get(i));
                origen.remove(i);
            }
        }
    }

    /**
     * Funcion que envia una carta al mazo del jugador
     * @param id Carta que se quiere transpasar al mazo
     * @param origen arraylist de origen de la carta
     */
    public void moveCardToDeck(int id,ArrayList<Carta> origen){
        for(int i=0;i<origen.size();i++){
            if(origen.get(i).getId()==id){
                this.getDeck().add(origen.get(i));
                origen.remove(i);
            }
        }
    }
    //Mover carta especifica desde mano

    /**
     * Funcion para mover una carta especifica de la mano a la mesa
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromHandToTable(int id){
        for(int i=0;i<this.getMano().size();i++){
            if(getMesa().size()<CARTAS_MESA) {
                if (this.getMano().get(i).getId() == id) {
                    this.getMesa().add(this.getMano().get(i));
                    this.getMano().get(i).movedFromHandToTable();
                    this.getMano().remove(i);
                }
            }
        }
    }

    /**
     * Funcion para mover una carta especifica de la mano al descarte
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromHandToDiscard(int id){
        for(int i=0;i<this.getMano().size();i++){
            if(this.getMano().get(i).getId()==id){
                this.getDescarte().add(this.getMano().get(i));
                this.getMano().get(i).movedFromHandToDiscard();
                this.getMano().remove(i);
            }
        }
    }

    /**
     * Funcion para mover una carta especifica de la mano al mazo
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromHandToDeck(int id){
        for(int i=0;i<this.getMano().size();i++){
            if(this.getMano().get(i).getId()==id){
                this.getDeck().add(this.getMano().get(i));
                this.getMano().get(i).movedFromHandToDeck();
                this.getMano().remove(i);
            }
        }
    }
    //Mover carta especifica desde Deck

    /**
     * Funcion para mover una carta especifica del mazo a la mesa
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromDeckToTable(int id){
        if(getMesa().size()<CARTAS_MESA) {
            for(int i=0;i<this.getDeck().size();i++){
                if (this.getDeck().get(i).getId() == id) {
                    this.getMesa().add(this.getDeck().get(i));
                    this.getDeck().get(i).movedFromDeckToTable();
                    this.getDeck().remove(i);
                }
            }
        }
    }

    /**
     * Funcion para mover una carta especifica del mazo al descarte
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromDeckToDiscard(int id){
        for(int i=0;i<this.getDeck().size();i++){
            if(this.getDeck().get(i).getId()==id){
                this.getDescarte().add(this.getDeck().get(i));
                this.getDeck().get(i).movedFromDeckToDiscard();
                this.getDeck().remove(i);
            }
        }
    }

    /**
     * Funcion para mover una carta especifica del mazo a la mano
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromDeckToHand(int id){
        if(getMano().size()<CARTAS_MANO) {
            for (int i = 0; i < this.getDeck().size(); i++) {
                if (this.getDeck().get(i).getId() == id) {
                    this.getMano().add(this.getDeck().get(i));
                    this.getDeck().get(i).movedFromDeckToHand();
                    this.getDeck().remove(i);
                }
            }
        }else{
            moveCardFromDeckToDiscard(id);
        }
    }
    //Mover carta especifica desde Mesa

    /**
     * Funcion para mover una carta especifica de la mesa al deck
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromTableToDeck(int id){
        for(int i=0;i<this.getMesa().size();i++){
            if(this.getMesa().get(i).getId()==id){
                this.getDeck().add(this.getMesa().get(i));
                this.getMesa().get(i).movedFromTableToDeck();
                this.getMesa().remove(i);
            }
        }
    }

    /**
     * Funcion para mover una carta especifica de la mesa al descarte
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromTableToDiscard(int id){
        for(int i=0;i<this.getMesa().size();i++){
            if(this.getMesa().get(i).getId()==id){
                this.getDescarte().add(this.getMesa().get(i));
                this.getMesa().get(i).movedFromTableToDiscard();
                this.getMesa().remove(i);
            }
        }
    }

    /**
     * Funcion para mover una carta especifica de la mesa a la mano
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromTableToHand(int id){
        if(getMano().size()<CARTAS_MANO) {
            for (int i = 0; i < this.getMesa().size(); i++) {
                if (this.getMesa().get(i).getId() == id) {
                    this.getMano().add(this.getMesa().get(i));
                    this.getMesa().get(i).movedFromTableToHand();
                    this.getMesa().remove(i);
                }
            }
        }else{
            moveCardFromTableToDiscard(id);
        }
    }
    //Mover carta especifica desde Descarte

    /**
     * Funcion para mover una carta especifica del descarte al deck
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromDiscardToDeck(int id){
        for(int i=0;i<this.getDescarte().size();i++){
            if(this.getDescarte().get(i).getId()==id){
                this.getDeck().add(this.getDescarte().get(i));
                this.getDescarte().get(i).movedFromDiscardToDeck();
                this.getDescarte().remove(i);
            }
        }
    }

    /**
     * Funcion para mover una carta especifica del descarte a la mesa
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromDiscardToTable(int id){
        if(getMesa().size()<CARTAS_MESA) {
            for (int i = 0; i < this.getDescarte().size(); i++) {
                if (this.getDescarte().get(i).getId() == id) {
                    this.getMesa().add(this.getDescarte().get(i));
                    this.getDescarte().get(i).movedFromDiscardToTable();
                    this.getDescarte().remove(i);
                }
            }
        }
    }

    /**
     * Funcion para mover una carta especifica del descarte a la mano
     * @param id carta que se quiere transpasar
     */
    public void moveCardFromDiscardToHand(int id){
        if(getMano().size()<CARTAS_MANO) {
            for (int i = 0; i < this.getDescarte().size(); i++) {
                if (this.getDescarte().get(i).getId() == id) {
                    this.getMano().add(this.getDescarte().get(i));
                    this.getDescarte().get(i).movedFromDiscardToHand();
                    this.getDescarte().remove(i);
                }
            }
        }
    }
    //Mover cartas general desde Deck

    /**
     * Funcion para mover una determinada cantidad de cartas a la mano desde el mazo
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromDeckToHand(int cantidad){
        if(getDeck().size()>=cantidad){
            Log.i("DECKtoHAND", "if");
            for(int i=0;i<cantidad;i++){
                if(getMano().size()<CARTAS_MANO) {
                    getMano().add(getDeck().get(0));
                    getDeck().get(0).movedFromDeckToHand();
                    getDeck().remove(0);
                }else{
                    moveCardFromDeckToDiscard(getDeck().get(0).getId());
                }
            }
        }else{
            Log.i("DECKtoHAND", "else");
            for(int i=0;i<getDeck().size();i++){
                if(getMano().size()<CARTAS_MANO) {
                    getMano().add(getDeck().get(0));
                    getDeck().get(0).movedFromDeckToHand();
                    getDeck().remove(0);
                }else{
                    moveCardFromDeckToDiscard(getDeck().get(0).getId());
                }
                cantidad--;
            }
            setVidas(getVidas()-cantidad);
        }
    }

    /**
     * Funcion para mover una determinada cantidad de cartas al descarte desde el mazo
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromDeckToDiscard(int cantidad){
        if(getDeck().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getDescarte().add(getDeck().get(0));
                getDeck().get(0).movedFromDeckToDiscard();
                getDeck().remove(0);
            }
        }else{
            for(int i=0;i<getDeck().size();i++){
                getDescarte().add(getDeck().get(0));
                getDeck().get(0).movedFromDeckToDiscard();
                getDeck().remove(0);
                cantidad--;
            }
            setVidas(getVidas() - cantidad);
        }
    }

    /**
     * Funcion para mover una determinada cantidad de cartas a la mesa desde el mazo
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromDeckToTable(int cantidad){
        if(getDeck().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                if(getMesa().size()<CARTAS_MESA) {
                    getMesa().add(getDeck().get(0));
                    getDeck().get(0).movedFromDeckToTable();
                    getDeck().remove(0);
                }
            }
        }else{
            for(int i=0;i<getMesa().size();i++){
                if(getMesa().size()<CARTAS_MESA) {
                    getMesa().add(getDeck().get(0));
                    getDeck().get(0).movedFromDeckToTable();
                    getDeck().remove(0);
                    cantidad--;
                }
            }
            setVidas(getVidas()-cantidad);
        }
    }
    //Mover cartas general desde Mano

    /**
     * Funcion para mover una determinada cantidad de cartas al mazo desde la mano
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromHandToDeck(int cantidad){
        if(getMano().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getDeck().add(getMano().get(0));
                getDeck().get(0).movedFromHandToDeck();
                getMano().remove(0);
            }
        }else{
            for(int i=0;i<getMano().size();i++){
                getDeck().add(getMano().get(0));
                getDeck().get(0).movedFromHandToDeck();
                getMano().remove(0);
            }
        }
    }

    /**
     * Funcion para mover una determinada cantidad de cartas al descarte desde la mano
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromHandToDiscard(int cantidad){
        if(getMano().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getDescarte().add(getMano().get(0));
                getDeck().get(0).movedFromHandToDiscard();
                getMano().remove(0);
            }
        }else{
            for(int i=0;i<getMano().size();i++){
                getDescarte().add(getMano().get(0));
                getDeck().get(0).movedFromHandToDiscard();
                getMano().remove(0);
            }
        }
    }

    /**
     * Funcion para mover una determinada cantidad de cartas a la mesa desde la mano
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromHandToTable(int cantidad){
        if(getMano().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                if(getMesa().size()<CARTAS_MESA) {
                    getMesa().add(getMano().get(0));
                    getDeck().get(0).movedFromHandToTable();
                    getMano().remove(0);
                }
            }
        }else{
            for(int i=0;i<getMesa().size();i++){
                if(getMesa().size()<CARTAS_MESA) {
                    getMesa().add(getMano().get(0));
                    getDeck().get(0).movedFromHandToTable();
                    getMano().remove(0);
                }
            }
        }
    }
    //Mover cartas general desde Descarte

    /**
     * Funcion para mover una determinada cantidad de cartas al mazo desde el descarte
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromDiscardToDeck(int cantidad){
        if(getDescarte().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getDeck().add(getDescarte().get(0));
                getDeck().get(0).movedFromDiscardToDeck();
                getDescarte().remove(0);
            }
        }else{
            for(int i=0;i<getDescarte().size();i++){
                getDeck().add(getDescarte().get(0));
                getDeck().get(0).movedFromDiscardToDeck();
                getDescarte().remove(0);
            }
        }
    }

    /**
     * Funcion para mover una determinada cantidad de cartas a la mano desde el descarte
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromDiscardToHand(int cantidad){
        if(getDescarte().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                if(getMano().size()<CARTAS_MESA) {
                    getMano().add(getDescarte().get(0));
                    getDeck().get(0).movedFromDiscardToHand();
                    getDescarte().remove(0);
                }
            }
        }else{
            for(int i=0;i<getDescarte().size();i++){
                if(getMano().size()<CARTAS_MESA) {
                    getMano().add(getDescarte().get(0));
                    getDeck().get(0).movedFromDiscardToHand();
                    getDescarte().remove(0);
                }
            }
        }
    }

    /**
     * Funcion para mover una determinada cantidad de cartas a la mesa desde el descarte
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromDiscardToTable(int cantidad){
        if(getDescarte().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                if(getMesa().size()<CARTAS_MESA) {
                    getMesa().add(getDescarte().get(0));
                    getDeck().get(0).movedFromDiscardToTable();
                    getDescarte().remove(0);
                }
            }
        }else{
            for(int i=0;i<getMesa().size();i++){
                if(getMesa().size()<CARTAS_MESA) {
                    getMesa().add(getDescarte().get(0));
                    getDeck().get(0).movedFromDiscardToTable();
                    getDescarte().remove(0);
                }
            }
        }
    }
    //Mover cartas general desde Mesa

    /**
     * Funcion para mover una determinada cantidad de cartas a la mano desde la mesa
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromTableToHand(int cantidad){

        if(getMesa().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                if(getMano().size()<CARTAS_MESA) {
                    getMano().add(getMesa().get(0));
                    getDeck().get(0).movedFromTableToHand();
                    getMesa().remove(0);
                }else{
                    moveCardFromTableToDiscard(getMesa().get(0).getId());
                }
            }
        }else{
            for(int i=0;i<getMesa().size();i++){
                if(getMano().size()<CARTAS_MESA) {
                    getMano().add(getMesa().get(0));
                    getDeck().get(0).movedFromTableToHand();
                    getMesa().remove(0);
                }else{
                    moveCardFromTableToDiscard(getMesa().get(0).getId());
                }
            }
        }
    }

    /**
     * Funcion para mover una determinada cantidad de cartas al descarte desde la mesa
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromTableToDiscard(int cantidad){
        if(getMesa().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getDescarte().add(getMesa().get(0));
                getDeck().get(0).movedFromTableToDiscard();
                getMesa().remove(0);
            }
        }else{
            for(int i=0;i<getMesa().size();i++){
                getDescarte().add(getMesa().get(0));
                getDeck().get(0).movedFromTableToDiscard();
                getMesa().remove(0);
            }
        }
    }

    /**
     * Funcion para mover una determinada cantidad de cartas al mazo desde la mesa
     * @param cantidad numero de cartas que se quieren mover
     */
    public void moveFromTableToDeck(int cantidad){
        if(getMesa().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getDeck().add(getMesa().get(0));
                getDeck().get(0).movedFromTableToDeck();
                getMesa().remove(0);
            }
        }else{
            for(int i=0;i<getMesa().size();i++){
                getDeck().add(getMesa().get(0));
                getDeck().get(0).movedFromTableToDeck();
                getMesa().remove(0);
            }
        }
    }

    //Getters & setters
    public ArrayList<Carta> getMano() {
        return mano;
    }

    public ArrayList<Carta> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Carta> deck) {
        this.deck = deck;
    }

    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public ArrayList<Carta> getDescarte() {
        return descarte;
    }

    public void setDescarte(ArrayList<Carta> descarte) {
        this.descarte = descarte;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getRecursos() {
        return recursos;
    }

    public void setRecursos(int recursos) {
        this.recursos = recursos;
    }

    public ArrayList<Carta> getMesa() {
        return mesa;
    }

    public void setMesa(ArrayList<Carta> mesa) {
        this.mesa = mesa;
    }

    public int getDeckX() {
        return deckX;
    }

    public void setDeckX(int deckX) {
        this.deckX = deckX;
    }

    public int getDeckXfin() {
        return deckXfin;
    }

    public void setDeckXfin(int deckXfin) {
        this.deckXfin = deckXfin;
    }

    public int getDeckY() {
        return deckY;
    }

    public void setDeckY(int deckY) {
        this.deckY = deckY;
    }

    public int getDeckYfin() {
        return deckYfin;
    }

    public void setDeckYfin(int deckYfin) {
        this.deckYfin = deckYfin;
    }

    public int getManoX() {
        return manoX;
    }

    public void setManoX(int manoX) {
        this.manoX = manoX;
    }

    public int getManoXfin() {
        return manoXfin;
    }

    public void setManoXfin(int manoXfin) {
        this.manoXfin = manoXfin;
    }

    public int getManoY() {
        return manoY;
    }

    public void setManoY(int manoY) {
        this.manoY = manoY;
    }

    public int getManoYfin() {
        return manoYfin;
    }

    public void setManoYfin(int manoYfin) {
        this.manoYfin = manoYfin;
    }

    public int getDescarteX() {
        return descarteX;
    }

    public void setDescarteX(int descarteX) {
        this.descarteX = descarteX;
    }

    public int getDescarteXfin() {
        return descarteXfin;
    }

    public void setDescarteXfin(int descarteXfin) {
        this.descarteXfin = descarteXfin;
    }

    public int getDescarteY() {
        return descarteY;
    }

    public void setDescarteY(int descarteY) {
        this.descarteY = descarteY;
    }

    public int getDescarteYfin() {
        return descarteYfin;
    }

    public void setDescarteYfin(int descarteYfin) {
        this.descarteYfin = descarteYfin;
    }

    public int getMesaX() {
        return mesaX;
    }

    public void setMesaX(int mesaX) {
        this.mesaX = mesaX;
    }

    public int getMesaXfin() {
        return mesaXfin;
    }

    public void setMesaXfin(int mesaXfin) {
        this.mesaXfin = mesaXfin;
    }

    public int getMesaY() {
        return mesaY;
    }

    public void setMesaY(int mesaY) {
        this.mesaY = mesaY;
    }

    public int getMesaYfin() {
        return mesaYfin;
    }

    public void setMesaYfin(int mesaYfin) {
        this.mesaYfin = mesaYfin;
    }

    public int getCARTAS_MESA() {
        return CARTAS_MESA;
    }

    public int getCARTAS_MANO() {
        return CARTAS_MANO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRecursosIniciales() {
        return recursosIniciales;
    }

    public void setRecursosIniciales(int recursosIniciales) {
        this.recursosIniciales = recursosIniciales;
    }

    public int getVidasIniciales() {
        return vidasIniciales;
    }

    public void setVidasIniciales(int vidasIniciales) {
        this.vidasIniciales = vidasIniciales;
    }

    public int getCartasIniciales() {
        return cartasIniciales;
    }

    public void setCartasIniciales(int cartasIniciales) {
        this.cartasIniciales = cartasIniciales;
    }



}

