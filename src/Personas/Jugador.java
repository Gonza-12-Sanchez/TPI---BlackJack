package Personas;

public class Jugador extends Persona {
    //Atributos
    private String nombre;
    private int vida;
    private boolean eliminado;

    //Constructor
    public Jugador(String nombre) {
        super();
        this.vida = 2;
        this.nombre = nombre;
        this.eliminado = false;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }
    public int getVida() {return vida;}
    public boolean getEliminado() {return eliminado;}

    //Metodos
    public void restarVida(){
        this.vida -= 1;
        if (this.vida == 0) {
            this.eliminado = true;
            System.out.println("El jugador "+this.nombre+" ha sido eliminado!");
        }
    }

}
