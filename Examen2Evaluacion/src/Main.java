import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static Partido partido = new Partido();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String opcion = "";
        Scanner scanner = new Scanner(System.in);


        String menu = """
                SIMULADOR PARTIDO BALONCESTO
                ****************************
                                
                1.- Mostrar árbitros               
                2.- Mostrar jugadores (ordenados por edad y nacionalidad)\s
                3.- Mostrar jugadores locales (ordenados altura)\s
                4.- Mostrar jugadores visitantes (ordenados dorsal)\s
                5.- Jugar partido\s
                6.- Mostrar puntos jugador cuarto\s
                7.- Mostrar puntos jugador partido (4 cuartos)\s
                8.- Mostrar puntos equipo. Local(L) o Visitante (V)\s
                0.- Salir\s
                
                Escoge una opción: """;

        do{
            System.out.print(menu);
            opcion = scanner.nextLine();

            switch (opcion){
                case "1":
                    mostrarArbitros();
                    break;
                case "2":
                    mostrarJugadoresOrdenadosEdadNacionalidad();
                    break;
                case "3":
                    mostrarJugadoresLocalesAltura();
                    break;
                case "4":
                    mostrarJugadoresVisitantesDorsal();
                    break;
                case "5":
                    partido.jugar();
                    break;
                case "6":
                    mostrarPuntosJugadorCuarto();
                    break;
                case "7":
                    mostrarPuntosJugadorPartido();
                    break;
                case "8":
                    mostrarTotalPuntosLocalVisitante();
                    break;
                case "0":
                    System.out.println("Saliendo ....");


            }

        }while(!opcion.equals("0"));

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        partido.jugar();

    }

    private static void mostrarTotalPuntosLocalVisitante() {
        System.out.println("Mostrar puntos de un equipo en el partido (L|V)");
        String equipo = scanner.nextLine();
        String nombreEquipo;

        if (equipo.equals("L") || equipo.equals("V")) {
            int total = partido.getTotalPuntoEquipo(equipo);
            if (equipo.equals("L")) {
                nombreEquipo = "Local";
            } else {
                nombreEquipo = "Visitante";
            }
            System.out.println("Total puntos equipo " + nombreEquipo + ": " + total);
        } else {
            System.out.println("Equipo no válido.");
        }

    }

    private static void mostrarPuntosJugadorPartido() {

        System.out.println("Mostrar puntos de un jugador en el partido");
        System.out.print("Dime el nombre del jugador:");
        String nombre = scanner.nextLine();

        int total = partido.getTotalPuntoJugador(nombre);
        if (total == 0 && !nombre.isEmpty()) {
            System.out.println("El jugador " + nombre + " no existe o no ha anotado puntos.");
        } else {
            System.out.println("La puntuación total de " + nombre + " en los 4 cuartos es: " + total);
        }
    }

    private static void mostrarPuntosJugadorCuarto() {

        System.out.println("Mostrar puntos de un jugador en un cuarto determinado");
        System.out.print("Dime el nombre del jugador:");
        String nombre = scanner.nextLine();
        System.out.print("Dime el cuarto (1/2/3/4):");
        int  cuarto = scanner.nextInt();

        int puntos = partido.getPuntosJugadorEnCuarto(nombre, cuarto);
        System.out.println("El jugador " + nombre + " anotó " + puntos + " puntos en el cuarto " + cuarto);

    }

    private static void mostrarJugadoresVisitantesDorsal() {
        System.out.println("Jugadores visitantes (ordenados por dorsal)");
        Partido partido = new Partido();
        partido.getJugadoresVisitantesOrdenadosDorsal().forEach(System.out::println);
    }

    private static void mostrarJugadoresLocalesAltura() {
        System.out.println("Jugadores locales (ordenados por altura)");
        partido.getJugadoresLocalesOrdenadosAltura().forEach(System.out::println);
    }

    private static void mostrarJugadoresOrdenadosEdadNacionalidad() {
        System.out.println("Jugadores del partido (ordenados por edad y por nacionalidad)");
        List<Jugador> lista = partido.getJugadoresOrdenadosEdadNacionalidad();

        Collections.sort(lista);

        for (Jugador jugador : lista) {
            System.out.println(jugador);
        }

    }

    private static void mostrarArbitros() {
        System.out.println("Árbitros del partido:");
        for (Arbitro arbitro: partido.getArbitros()) {
            System.out.println(arbitro);
        }
    }
}