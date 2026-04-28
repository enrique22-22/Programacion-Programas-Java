import java.util.Arrays;

public abstract class Jugador extends Participante implements Jugable, Comparable<Jugador> {

    static final String[] accionesJugador = {"BOTAR_PELOTA","DRIBLAR","PASAR","TIRO_3_PUNTOS","TIRO_2_PUNTOS","TIRO_1_PUNTO","MATE","COMETER_FALTA"};

    protected int dorsal;
    protected String posicion;
    protected boolean expulsado;
    protected String equipo;
    protected int altura;


    public Jugador(String id, String nombre, String nacionalidad, int edad, int dorsal, String posicion, boolean expulsado, String equipo, int altura) {
        super(id, nombre, nacionalidad, edad);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.expulsado = expulsado;
        this.equipo = equipo;
        this.altura = altura;
    }

    public String[] getAccionesJugador() {
        return accionesJugador;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public boolean isExpulsado() {
        return expulsado;
    }

    public void setExpulsado(boolean expulsado) {
        this.expulsado = expulsado;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "{dorsal=" + dorsal +
                ", posición='" + posicion + '\'' +
                ", expulsado=" + expulsado +
                ", equipo='" + equipo + '\'' +
                ", altura=" + altura +
                ", edad=" + edad +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }

    @Override
    public int compareTo(Jugador o) {
        int comparacion = Integer.compare(o.edad, this.edad);
        if (comparacion == 0) {
            return this.getNacionalidad().compareTo(o.getNacionalidad());
        }

        return comparacion;
    }


}
