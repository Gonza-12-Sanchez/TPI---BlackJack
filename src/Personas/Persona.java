package Personas;

import Cartas.Carta;

import java.util.ArrayList;

public abstract class Persona {
    //Atributos
    protected int puntos;
    protected ArrayList<Carta> cartasEnMano;
    protected boolean perdio;
    protected boolean sePlanto;

    //Constructor
    public Persona() {
        this.puntos = 0;
        this.cartasEnMano = new ArrayList<>();
        this.perdio = false;
        this.sePlanto = false;
    }

    //Getters
    public int getPuntos() {
        return puntos;
    }

    public ArrayList<Carta> getCartasEnMano() {
        return this.cartasEnMano;
    }

    public boolean getPerdio() {
        return perdio;
    }

    public boolean getSePlanto() {
        return sePlanto;
    }

    //Setter
    public void setSePlanto(boolean sePlanto) {
        this.sePlanto = sePlanto;
    }

    //Metodos
    public int calcularPuntos() {
        //Recorremos carta por carta y sumamos sus valores reales para calcular el total de puntos
        this.puntos = 0;
        for (Carta carta : this.cartasEnMano) {
            this.puntos += carta.valorRealCarta();
        }
        return this.puntos;
    }

    public void recibirCarta(Carta carta) {
        //Agregamos la carta recibida a las cartas en mano, y actualizamos los puntos totales
        // utilizando el metodo "calcularPuntos()"
        this.cartasEnMano.add(carta);
        calcularPuntos();
    }

    public void recibirCartas(ArrayList<Carta> cartasIniciales) {
        //Agregamos las cartas recibidas a las cartas en mano y actualizamos los puntos totales
        // utilizando el metodo "calcularPuntos()"
        this.cartasEnMano.addAll(cartasIniciales);
        calcularPuntos();
    }

    public boolean comprobarSiPerdioPorPuntos(){
        //Comprobamos si la persona se paso de puntos, o no
        //Ademas, si se paso de puntos, cambiamos el estado del atributo perdio a true
        if(this.puntos > 21){
            this.perdio = true;
            return true;
        }
        return false;
    }

    public void mostrarCartasEnMano(){
        //Recorremos carta por carta las cartas en mano y las mostramos por consola
        for(Carta carta: this.cartasEnMano){
            System.out.println(carta);
        }
    }

    public void reiniciarMano() {
        //Reiniciamos todos los valores, a sus valores iniciales
        this.cartasEnMano.clear();
        this.puntos = 0;
        this.perdio = false;
        this.sePlanto = false;
    }
}
