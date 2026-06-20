import Personas.Crupier;
import Personas.Jugador;
import Validaciones.LecturaConsola;

import java.util.ArrayList;

public class Main {
    //Utilizamos el main para pedir los datos escenciales antes de jugar

    public static void main(String[] args) {
        //Monstramos un mensaje inicial
        System.out.println("-- BIENVENIDOS AL BLACKJACK --");

        //Creamos un array de jugadores, y solicitamos al usuario que ingrese la cantidad de jugadores
        ArrayList<Jugador> jugadores = new ArrayList<>();
        int cantidadJugadores = LecturaConsola.ingresarEntero("¿Cuantos jugadores desea ingresar? (min 2)");

        //Validamos que la cantidad de jugadores sea mayor a 1
        while(cantidadJugadores<2){
            System.out.println("Debe ingresar como minimo 2 jugadores");
            cantidadJugadores = LecturaConsola.ingresarEntero("¿Cuantos jugadores desea ingresar?");
        }

        //Agregamos los jugadores correspondientes
        for(int i = 0; i < cantidadJugadores; i++) {
            String nombre = LecturaConsola.ingresarOpcionNoVacia("Ingresa el nombre del jugador "+(i+1)+":");
            jugadores.add(new Jugador(nombre));
        }

        //Instanciamos un crupier
        Crupier crupierMesa = new Crupier();

        //Instanciamos una partida
        Partida partida = new Partida(jugadores, crupierMesa);

        //Ahora con los datos necesarios, iniciamos el juego
        partida.jugar();
    }
}
