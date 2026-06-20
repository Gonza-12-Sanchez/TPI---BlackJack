package Mesa;

import Cartas.Carta;
import Personas.Crupier;
import Personas.Jugador;

import java.util.ArrayList;

public class Mesa {

    public static void cartasJugadasRonda(ArrayList<Jugador>jugadores, Crupier crupier, int  numeroRonda) {
        System.out.println("******************** CARTAS JUGADAS DURANTE LA RONDA "+numeroRonda+" ********************");
        //Mostramos las cartas jugadas del crupier, y sus respectivos puntos
        System.out.println("--- Cartas Crupier ---");
        ArrayList<Carta> cartasCrupier = crupier.getCartasEnMano();
        for (Carta carta : cartasCrupier) {
            System.out.println(carta.toString());
        }
        System.out.println("Puntos: "+ crupier.calcularPuntos());

        //Mostramos las cartas jugadas de los jugadores y sus respectivos puntos
        System.out.println("--- Cartas Jugadores ---");
        for (Jugador jugador : jugadores) {
            System.out.println("Jugador: "+jugador.getNombre());
            ArrayList<Carta> cartasJugador = jugador.getCartasEnMano();
            for (Carta carta : cartasJugador) {
                System.out.println(carta.toString());
            }
            System.out.println("Puntos: "+ jugador.calcularPuntos());
            System.out.println("*--------------------*");
        }
    }

    public static void determinarGanadores(ArrayList<Jugador> jugadores, Crupier crupier,int  numeroRonda) {
        System.out.println("\n******************** RESULTADOS DE LA RONDA "+numeroRonda+" ********************");
        //Verificamos los casos en que los jugadores pierden o ganan
        //En los casos en los que pierdan los jugadores, les restamos una vida
        for (Jugador jugador : jugadores) {
            if (jugador.getPerdio()) {
                System.out.println(jugador.getNombre() + " perdió (Se pasó de 21).");
                jugador.restarVida();
            } else if (crupier.getPerdio()) {
                System.out.println(jugador.getNombre() + " gana porque el crupier se pasó.");
            } else if (jugador.getPuntos() > crupier.getPuntos()) {
                System.out.println(jugador.getNombre() + " gana por puntos (" + jugador.calcularPuntos() + " vs " + crupier.calcularPuntos() + ").");
            } else if (jugador.calcularPuntos() < crupier.calcularPuntos()) {
                System.out.println(jugador.getNombre() + " pierde contra el crupier (" + jugador.calcularPuntos() + " vs " + crupier.calcularPuntos() + ").");
                jugador.restarVida();
            } else {
                System.out.println(jugador.getNombre() + ": Empate en " + jugador.calcularPuntos() + " puntos.");
            }

            //Mostramos sus vidas restantes y mostramos si el jugador perdio
            System.out.println("Vidas restantes: "+jugador.getVida());
            System.out.println("¿Jugador eliminado? "+jugador.getEliminado());
            System.out.println("*--------------------*");
        }
        System.out.println("****************************************************************\n");
    }
}