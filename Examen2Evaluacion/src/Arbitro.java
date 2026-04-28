import java.util.Arrays;
import java.util.Random;

public class Arbitro extends Participante implements Jugable {

    static final String[] accionesArbitro = {"ANULAR_CANASTA","ANOTAR_1_PUNTO","ANOTAR_2_PUNTOS","ANOTAR_3_PUNTOS","PASOS","FALTA","EXPULSION"};
    final Random r;

    public Arbitro(String id, String nombre, String nacionalidad, int edad) {
        super(id, nombre, nacionalidad, edad);
        this.r = new Random();
    }

    @Override
    public String accion() {
        return accionesArbitro[r.nextInt(accionesArbitro.length)];
    }

    @Override
    public String toString() {
        return "Arbitro{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", edad=" + edad +
                '}';
    }
}
