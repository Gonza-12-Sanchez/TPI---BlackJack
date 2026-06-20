import Cartas.Carta;
import Mesa.VistaMesa;
import Personas.Crupier;
import Personas.Jugador;
import Validaciones.LecturaConsola;

import java.util.ArrayList;

public class Partida {
    //Atributos
    private ArrayList<Jugador> jugadores;
    private Crupier crupier;
    private boolean terminoPartida;
    private int numeroRonda;

    //Constructor
    public Partida(ArrayList<Jugador> jugadores, Crupier crupier) {
        this.jugadores = jugadores;
        this.crupier = crupier;
        this.terminoPartida = false;
        this.numeroRonda = 1;
    }

    //METODOS
    public void jugar(){
        System.out.println("--****** INICIAMOS LA PARTIDA ******--");

        //Simulamos una partida
        while(!terminoPartida) {
            //Mostramos el numero de ronda
            System.out.println("---- RONDA " + numeroRonda +" ----");

            //Barajamos las cartas
            System.out.println("El crupier baraja las cartas...");
            this.crupier.getBaraja().barajar();

            //Repartimos la mano inicial
            System.out.println("Reparte la mano inicial :D");
            this.crupier.repartirManoInicial(this.jugadores);

            //Turno de los jugadores
            turnoJugadores(this.jugadores, this.crupier);

            //Turno del crupier
            turnoCrupier(this.crupier);

            System.out.println();

            //Mostramos las cartas jugadas durante la ronda
            VistaMesa.cartasJugadasRonda(this.jugadores, this.crupier, this.numeroRonda);

            //Mostramos los resultados de la ronda
            VistaMesa.determinarGanadores(this.jugadores, this.crupier, this.numeroRonda);

            //Determinamos si termino la partido o no
            if (verificarFinPartida(this.jugadores)){
                this.terminoPartida =  true;
            }

            //Reiniciamos la mano del crupier y de los jugadores
            this.crupier.reiniciarMano();
            for(Jugador j : this.jugadores) {
                j.reiniciarMano();
            }

            //Tambien hacemos que el crupier reinicie la baraja
            this.crupier.getBaraja().reiniciarBarja();

            this.numeroRonda++;
        }
    }

    //Metodo logica turno de los jugadores
    public static void turnoJugadores(ArrayList<Jugador> jugadores, Crupier crupier){
        //Ofrecemos a los distintos jugadores un menu de opciones
        for(Jugador jugador:  jugadores) {
            if(!jugador.getEliminado()){
                while(!jugador.getPerdio() && !jugador.getSePlanto()){
                    System.out.println("-------------TURNO DE "+jugador.getNombre()+"-------------");
                    System.out.println("PUNTOS ACTUALES: "+jugador.getPuntos());
                    System.out.println("VIDA: "+jugador.getVida());
                    System.out.println("¿Que desea hacer?");
                    System.out.println("1. Pedir una carta");
                    System.out.println("2. Plantarse");
                    System.out.println("3. Ver cartas en mano");
                    int opcion = LecturaConsola.ingresarEntero("Seleccione una opcion:");

                    //Verificamos que la opcion ingresada sea valida
                    while(opcion != 1 && opcion != 2 && opcion != 3){
                        System.out.println("Debe ingresar 1, 2 o 3");
                        opcion = LecturaConsola.ingresarEntero("Seleccione una opcion:");
                    }

                    //Realizamos la opcion ingresada
                    switch(opcion) {
                        case 1:
                            //Crupier entrega una carta al jugador
                            crupier.darUnaCarta(jugador);

                            //Verificamos si se paso de los 21 puntos
                            if (jugador.comprobarSiPerdioPorPuntos()) {
                                System.out.println(jugador.getNombre() + " se paso de los 21 puntos!");
                            }
                            break;
                        case 2:
                            //El jugador se planta
                            jugador.setSePlanto(true);
                            System.out.println(jugador.getNombre() + " se planto!");
                            break;
                        case 3:
                            //Mostramos las cartas en mano del jugador
                            System.out.println("- Cartas en Mano -");
                            jugador.mostrarCartasEnMano();
                            break;
                    }
                }
            }
        }
    }

    //Metodo logica turno del crupier
    public static void turnoCrupier(Crupier crupier) {
        System.out.println("-------------TURNO DEL CRUPIER-------------");
        //Mostramos la mano y los puntos iniciales del crupier
        System.out.println("Puntos iniciales: "+crupier.calcularPuntos());
        System.out.println("- Cartas en mano iniciales del crupier -");
        crupier.mostrarCartasEnMano();

        //Verificamos si el crupier debe seguir sacando cartas
        while(crupier.calcularPuntos()<17 && !crupier.getPerdio()){
            System.out.println("El crupier pide otra carta!");
            Carta carta = crupier.getBaraja().obtenerSiguienteCartaDisponible();
            //Si la carta no es nula, la mostramos por pantalla, se la entregamos al crupier
            if(carta != null){
                crupier.recibirCarta(carta);
                System.out.println("El crupier recibio la siguiente carta: " + carta);
            }

            //Verificamos si el crupier perdio
            if(crupier.comprobarSiPerdioPorPuntos()){
                System.out.println("El crupier perdio, se paso de los 21 puntos!");
            }else {
                System.out.println("Puntos actuales: "+crupier.calcularPuntos());
            }
        }

        //Si tiene 17 puntos o mas, directamnete de planta
        if (!crupier.getPerdio()) {
            System.out.println("El crupier se planta!");
            crupier.setSePlanto(true);
        }
    }

    //Metodo para verificar si ya terminamos la partida
    public static boolean verificarFinPartida(ArrayList<Jugador> jugadores){
        //Verificamos cuantos jugadores fueron eliminados con un acumulador
        //Esto lo hacemos con el fin de saber si ya termino la partida
        int cantJugadores = 0;
        for (Jugador jugador : jugadores) {
            if (!jugador.getEliminado()) {
                cantJugadores++;
            }
        }

        //Si queda solo un jugador no eliminado, significa que ya tenemos un ganador
        //Si todos los jugadores fueron eliminados, significa que fue un empate y nadie gano
        //En caso contrario, todavia no termina la partida
        if(cantJugadores == 1){
            for (Jugador jugador : jugadores) {
                if(!jugador.getEliminado()){
                    System.out.println("EL ULTIMO EN PIE FUEEEE " + jugador.getNombre()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
            }
            return true;
        } else if (cantJugadores == 0) {
            System.out.println("Todos los jugadores fueron tocados por el crupier :c");
            return true;
        }else {
            return false;
        }

    }
}