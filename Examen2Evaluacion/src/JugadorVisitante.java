import java.util.Arrays;
import java.util.Random;

public class JugadorVisitante extends Jugador {

    final Random r;

    public JugadorVisitante(String id, String nombre, String nacionalidad, int edad, int dorsal, String posicion, boolean expulsado, String equipo, int altura) {
        super(id, nombre, nacionalidad, edad, dorsal, posicion, expulsado, equipo, altura);
        this.r = new Random();
    }

    @Override
    public String accion() {
        int indice = r.nextInt(accionesJugador.length);
        return accionesJugador[indice];
    }

    @Override
    public String toString() {
        return "JugadorVisitante: " +super.toString();
    }
}
