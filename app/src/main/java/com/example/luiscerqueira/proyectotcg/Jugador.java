package com.example.luiscerqueira.proyectotcg;

        import android.util.Log;

        import java.util.ArrayList;

/**
 * Created by Luis Cerqueira on 22/01/2015.
 */
public class Jugador {

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

    public Jugador(int vidas,int recursos){
        this.vidas=vidas;
        this.recursos=recursos;
    }

    public void startOfTurn(){
        this.setRecursos(getRecursos()+1);
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
        moveCardFromDeckToHand(1);
    }
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
    public void moveCard(int id,ArrayList<Carta> origen,ArrayList<Carta> destino){
        for(int i=0;i<origen.size();i++){
            if(origen.get(i).getId()==id){
                destino.add(origen.get(i));
                origen.remove(i);
            }
        }
    }
    public void moveCardToTable(int id,ArrayList<Carta> origen){
        for(int i=0;i<origen.size();i++){
            if(origen.get(i).getId()==id){
                this.getMesa().add(origen.get(i));
                origen.remove(i);
            }
        }
    }
    public void moveCardToHand(int id,ArrayList<Carta> origen){
        for(int i=0;i<origen.size();i++){
            if(origen.get(i).getId()==id){
                this.getMano().add(origen.get(i));
                origen.remove(i);
            }
        }
    }
    public void moveCardToDiscard(int id,ArrayList<Carta> origen){
        for(int i=0;i<origen.size();i++){
            if(origen.get(i).getId()==id){
                this.getDescarte().add(origen.get(i));
                origen.remove(i);
            }
        }
    }
    public void moveCardToDeck(int id,ArrayList<Carta> origen){
        for(int i=0;i<origen.size();i++){
            if(origen.get(i).getId()==id){
                this.getDeck().add(origen.get(i));
                origen.remove(i);
            }
        }
    }
    //Mover carta especifica desde mano
    public void moveCardFromHandToTable(int id){
        for(int i=0;i<this.getMano().size();i++){
            if(this.getMano().get(i).getId()==id){
                this.getMesa().add(this.getMano().get(i));
                this.getMano().get(i).movedFromHandToTable();
                this.getMano().remove(i);
            }
        }
    }
    public void moveCardFromHandToDiscard(int id){
        for(int i=0;i<this.getMano().size();i++){
            if(this.getMano().get(i).getId()==id){
                this.getDescarte().add(this.getMano().get(i));
                this.getMano().get(i).movedFromHandToDiscard();
                this.getMano().remove(i);
            }
        }
    }
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
    public void moveCardFromDeckToTable(int id){
        for(int i=0;i<this.getDeck().size();i++){
            if(this.getDeck().get(i).getId()==id){
                this.getMesa().add(this.getDeck().get(i));
                this.getDeck().get(i).movedFromDeckToTable();
                this.getDeck().remove(i);
            }
        }
    }
    public void moveCardFromDeckToDiscard(int id){
        for(int i=0;i<this.getDeck().size();i++){
            if(this.getDeck().get(i).getId()==id){
                this.getDescarte().add(this.getDeck().get(i));
                this.getDeck().get(i).movedFromDeckToDiscard();
                this.getDeck().remove(i);
            }
        }
    }
    public void moveCardFromDeckToHand(int id){
            for (int i = 0; i < this.getDeck().size(); i++) {
                if (this.getDeck().get(i).getId() == id) {
                    this.getMano().add(this.getDeck().get(i));
                    this.getDeck().get(i).movedFromDeckToHand();
                    this.getDeck().remove(i);
                }
            }
    }
    //Mover carta especifica desde Mesa
    public void moveCardFromTableToDeck(int id){
        for(int i=0;i<this.getMesa().size();i++){
            if(this.getMesa().get(i).getId()==id){
                this.getDeck().add(this.getMesa().get(i));
                this.getMesa().get(i).movedFromTableToDeck();
                this.getMesa().remove(i);
            }
        }
    }
    public void moveCardFromTableToDiscard(int id){
        for(int i=0;i<this.getMesa().size();i++){
            if(this.getMesa().get(i).getId()==id){
                this.getDescarte().add(this.getMesa().get(i));
                this.getMesa().get(i).movedFromTableToDiscard();
                this.getMesa().remove(i);
            }
        }
    }
    public void moveCardFromTableToHand(int id){
        for(int i=0;i<this.getMesa().size();i++){
            if(this.getMesa().get(i).getId()==id){
                this.getMano().add(this.getMesa().get(i));
                this.getMesa().get(i).movedFromTableToHand();
                this.getMesa().remove(i);
            }
        }
    }
    //Mover carta especifica desde Descarte
    public void moveCardFromDiscardToDeck(int id){
        for(int i=0;i<this.getDescarte().size();i++){
            if(this.getDescarte().get(i).getId()==id){
                this.getDeck().add(this.getDescarte().get(i));
                this.getDescarte().get(i).movedFromDiscardToDeck();
                this.getDescarte().remove(i);
            }
        }
    }
    public void moveCardFromDiscardToTable(int id){
        for(int i=0;i<this.getDescarte().size();i++){
            if(this.getDescarte().get(i).getId()==id){
                this.getMesa().add(this.getDescarte().get(i));
                this.getDescarte().get(i).movedFromDiscardToTable();
                this.getDescarte().remove(i);
            }
        }
    }
    public void moveCardFromDiscardToHand(int id){
        for(int i=0;i<this.getDescarte().size();i++){
            if(this.getDescarte().get(i).getId()==id){
                this.getMano().add(this.getDescarte().get(i));
                this.getDescarte().get(i).movedFromDiscardToHand();
                this.getDescarte().remove(i);
            }
        }
    }
    //Mover cartas general desde Deck
    public void moveFromDeckToHand(int cantidad){
        if(getDeck().size()>=cantidad){
            Log.i("DECKtoHAND", "if");
            for(int i=0;i<cantidad;i++){
                getMano().add(getDeck().get(0));
                getDeck().get(0).movedFromDeckToHand();
                getDeck().remove(0);
            }
        }else{
            Log.i("DECKtoHAND", "else");
            for(int i=0;i<getDeck().size();i++){
                getMano().add(getDeck().get(0));
                getDeck().get(0).movedFromDeckToHand();
                getDeck().remove(0);
                cantidad--;
            }
            setVidas(getVidas()-cantidad);
        }
    }
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
            }
        }
    }
    public void moveFromDeckToTable(int cantidad){
        if(getDeck().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getMesa().add(getDeck().get(0));
                getDeck().get(0).movedFromDeckToTable();
                getDeck().remove(0);
            }
        }else{
            for(int i=0;i<getMesa().size();i++){
                getMesa().add(getDeck().get(0));
                getDeck().get(0).movedFromDeckToTable();
                getDeck().remove(0);
            }
        }
    }
    //Mover cartas general desde Mano

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
    public void moveFromHandToTable(int cantidad){
        if(getMano().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getMesa().add(getMano().get(0));
                getDeck().get(0).movedFromHandToTable();
                getMano().remove(0);
            }
        }else{
            for(int i=0;i<getMesa().size();i++){
                getMesa().add(getMano().get(0));
                getDeck().get(0).movedFromHandToTable();
                getMano().remove(0);
            }
        }
    }
    //Mover cartas general desde Descarte

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

    public void moveFromDiscardToHand(int cantidad){
        if(getDescarte().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getMano().add(getDescarte().get(0));
                getDeck().get(0).movedFromDiscardToHand();
                getDescarte().remove(0);
            }
        }else{
            for(int i=0;i<getDescarte().size();i++){
                getMano().add(getDescarte().get(0));
                getDeck().get(0).movedFromDiscardToHand();
                getDescarte().remove(0);
            }
        }
    }
    public void moveFromDiscardToTable(int cantidad){
        if(getDescarte().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getMesa().add(getDescarte().get(0));
                getDeck().get(0).movedFromDiscardToTable();
                getDescarte().remove(0);
            }
        }else{
            for(int i=0;i<getMesa().size();i++){
                getMesa().add(getDescarte().get(0));
                getDeck().get(0).movedFromDiscardToTable();
                getDescarte().remove(0);
            }
        }
    }
    //Mover cartas general desde Mesa

    public void moveFromTableToHand(int cantidad){

        if(getMesa().size()>=cantidad){
            for(int i=0;i<cantidad;i++){
                getMano().add(getMesa().get(0));
                getDeck().get(0).movedFromTableToHand();
                getMesa().remove(0);
            }
        }else{
            for(int i=0;i<getMesa().size();i++){
                getMano().add(getMesa().get(0));
                getDeck().get(0).movedFromTableToHand();
                getMesa().remove(0);
            }
        }
    }

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

}

