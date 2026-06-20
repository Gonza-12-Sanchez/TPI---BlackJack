package Cartas;

public class Carta {
    //Atributos
   private String pinta;
   private int numero;

   //Constructor
    public Carta(String pinta, int numero) {
        this.pinta = pinta;
        this.numero = numero;
    }

    //Getters
    public String getPinta() {
        return pinta;
    }
    public int getNumero() {
        return numero;
    }

    //Metodos
    public int valorRealCarta(){
        //Si el numero es mayor a 10, su valor real es de 10
        //Por otro lado, si el numero es menor a 10, si valor real es igual a su numero
        if (numero >= 10){
            return 10;
        }
        else{
            return numero;
        }
    }

    @Override
    public String toString() {
        return "[" + this.numero + " de " + this.pinta + "]";
    }
}
