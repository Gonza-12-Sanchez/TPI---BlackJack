package Personas;

import Cartas.Baraja;
import Cartas.Carta;

import java.util.ArrayList;

public class Crupier extends Persona {
    //Atributos
    private Baraja baraja;

    //Constructor
    public Crupier() {
        super();
        this.baraja = new Baraja();
    }

    //Getter
    public Baraja getBaraja() {
        return baraja;
    }

    //Metodos
    public void repartirManoInicial(ArrayList<Jugador> jugadores) {
        //Recorremos todos los jugadores para repartirles la mano inicial
        for (Jugador jugador : jugadores) {
            ArrayList<Carta> manoJugador = baraja.darCartas(2);
            jugador.recibirCartas(manoJugador);
        }

        //Repartimos la mano inicial al crupier
        ArrayList<Carta> manoCrupier = baraja.darCartas(2);
        this.recibirCartas(manoCrupier);
    }

    public void darUnaCarta(Jugador jugador) {
        //Obtenemos una carta disponible
        Carta carta = baraja.obtenerSiguienteCartaDisponible();

        //Si la carta no es nula, se la entregamos al jugador
        if (carta != null) {
            jugador.recibirCarta(carta);

            //Mostramos la carta que recibio el jugador
            System.out.println(jugador.getNombre() + " recibio la siguiente carta: " + carta);
        }
    }
}