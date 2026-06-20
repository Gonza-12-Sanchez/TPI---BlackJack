package Cartas;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    //Atributo
    private ArrayList<Carta> cartas;

    //Constructor
    public Baraja() {
        //En el mismo constructor, creamos y definimos todas las cartas del mazo
        this.cartas = new ArrayList<>();
        String[] pintas = {"Espadas", "Bastos", "Oros", "Copas"};
        for (String pinta : pintas) {
            for (int i = 1; i <= 12; i++) {
                cartas.add(new Carta(pinta, i));
            }
        }
    }

    //Metodos
    public int consultarCartasDisponibles(){
        //Devuelve la cantidad de cartas disponibles
        return cartas.size();
    }

    public void barajar(){
        //Utilizamos una funcion para desordenar las cartas
        Collections.shuffle(cartas);
    }

    public Carta obtenerSiguienteCartaDisponible(){
        //Si todavia hay cartas en el array de cartas, significa que hay cartas disponibles
        if (!cartas.isEmpty()){
            //Removemos la carta a entregar
            return cartas.remove(0);
        }
        //Si no hay cartas disponibles mostramos un mensaje por pantalla y devolvemos null
        System.out.println("No hay cartas disponibles...\n");
        return null;
    }

    public ArrayList<Carta> darCartas(int cantidad){
        //Entregamos una cantidad determinada de cartas disponibles utilizando el metodo
        // "obtenerSiguienteCartaDisponible()"
        ArrayList<Carta> cartasDar = new ArrayList<>();
        for (int i = 0; i < cantidad; i++){
            Carta carta = obtenerSiguienteCartaDisponible();
            if (carta != null){ //Verificamos si la carta no es nula antes de entregarla
                cartasDar.add(carta);
            }
        }
        return cartasDar;
    }

    public void reiniciarBarja(){
        //Vaciamos las cartas restantes
        this.cartas.clear();
        //Volvemos a generar las cartas
        String[] pintas = {"Espadas", "Bastos", "Oros", "Copas"};
        for (String pinta : pintas) {
            for (int i = 1; i <= 12; i++) {
                cartas.add(new Carta(pinta, i));
            }
        }
    }
}
