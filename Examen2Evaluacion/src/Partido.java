import java.util.*;

public class Partido {
    Set<Participante> participantes;
    List<JugadorLocal> jugadoresLocales;
    List<JugadorVisitante> jugadorVisitantes;
    List<Arbitro> arbitros;
    Map<String, List<Integer>> puntuacionesJugador;
    final Random random;


    public Partido() {
        this.participantes = new HashSet<>();
        this.jugadoresLocales = new  ArrayList<>();
        this.jugadorVisitantes = new  ArrayList<>();
        this.arbitros = new ArrayList<>();
        this.puntuacionesJugador = new HashMap<>();
        this.random = new  Random();

        rellenarListaParticipantes();

        generarListasParticipantesSeparadas();
    }

    public void generarListasParticipantesSeparadas() {

        for (Participante participante : participantes) {
            if (participante instanceof JugadorLocal) {
                jugadoresLocales.add((JugadorLocal) participante);
            }

            if (participante instanceof JugadorVisitante) {
                jugadorVisitantes.add((JugadorVisitante) participante);
            }

            if (participante instanceof Arbitro) {
                arbitros.add((Arbitro) participante);
            }

            if (participante instanceof Jugador) {
                puntuacionesJugador.put(participante.nombre, new ArrayList<>(Arrays.asList(0,0,0,0)));
            }
        }

    }

    public List<Jugador> getJugadoresOrdenadosEdadNacionalidad() {

        List<Jugador> todosLosJugadores = new ArrayList<>();
        todosLosJugadores.addAll(jugadoresLocales);
        todosLosJugadores.addAll(jugadorVisitantes);

        return todosLosJugadores;

    }

    public List<Jugador> getJugadoresLocalesOrdenadosAltura() {

        List<Jugador> jugadoresLocalesOrdenadosAltura = new ArrayList<>(jugadoresLocales);

        jugadoresLocalesOrdenadosAltura.sort((j1, j2) -> Integer.compare(j2.getAltura(), j1.getAltura()));

        return jugadoresLocalesOrdenadosAltura;
    }

    public List<Jugador> getJugadoresVisitantesOrdenadosDorsal() {

        List<Jugador> jugadoresVisitantesOrdenadosDorsal = new ArrayList<>(jugadorVisitantes);

        jugadoresVisitantesOrdenadosDorsal.sort((j1, j2) -> Integer.compare(j2.getDorsal(), j1.getDorsal()));
        return jugadoresVisitantesOrdenadosDorsal;

    }

    public int getPuntosJugadorEnCuarto(String nombreJugador, int numeroCuarto) {
        if (!puntuacionesJugador.containsKey(nombreJugador) || numeroCuarto < 1 || numeroCuarto > 4) {
            return 0;
        }
        return puntuacionesJugador.get(nombreJugador).get(numeroCuarto - 1);
    }

    public int getTotalPuntoJugador(String nombreJugador) {
        if (!puntuacionesJugador.containsKey(nombreJugador)) {
            return 0;
        }
        int total = 0;
        for (int puntos : puntuacionesJugador.get(nombreJugador)) {
            total += puntos;
        }
        return total;
    }

    public int getTotalPuntoEquipo(String equipo) {
        int total = 0;
        if (equipo.equalsIgnoreCase("L")) {
            for (JugadorLocal j : jugadoresLocales) {
                total += getTotalPuntoJugador(j.nombre);
            }
        } else if (equipo.equalsIgnoreCase("V")) {
            for (JugadorVisitante j : jugadorVisitantes) total += getTotalPuntoJugador(j.nombre);
        }
        return total;
    }

    public List<Arbitro> getArbitros() {
        return this.arbitros;
    }

    public void jugar() {
        for (int i = 0; i < 400; i++) {
            int cuarto = i / 100;

            JugadorLocal jugadorLocal = jugadoresLocales.get(random.nextInt(jugadoresLocales.size()));
            JugadorVisitante jugadorVisitante = jugadorVisitantes.get(random.nextInt(jugadorVisitantes.size()));
            Arbitro arbitro = arbitros.get(random.nextInt(arbitros.size()));

            if (!jugadorLocal.isExpulsado()) {
                procesarAccion(jugadorLocal, arbitro, cuarto);
            }
            if (!jugadorVisitante.isExpulsado()) {
                procesarAccion(jugadorVisitante, arbitro, cuarto);
            }
        }
        System.out.println("Partido Finalizado");
        mostrarEstadisticas();
    }

    private void procesarAccion(Jugador j, Arbitro a, int cuarto) {
        String accionJugador = j.accion();
        String accionArbitro = a.accion();
        int puntos = 0;

        if ((accionJugador.equals("MATE") || accionJugador.equals("TIRO_2_PUNTOS")) && accionArbitro.equals("ANOTAR_2_PUNTOS")) {
            puntos = 2;
        } else if (accionJugador.equals("TIRO_LIBRE") && accionArbitro.equals("ANOTAR_1_PUNTO")) {
            puntos = 1;
        } else if (accionJugador.equals("TIRO_3_PUNTOS") && accionArbitro.equals("ANOTAR_3_PUNTOS")) {
            puntos = 3;
        }

        if (puntos > 0) {
            int puntosActuales = puntuacionesJugador.get(j.nombre).get(cuarto);
            puntuacionesJugador.get(j.nombre).set(cuarto, puntosActuales + puntos);
        }
        if (accionJugador.equals("COMETER_FALTA") && accionArbitro.equals("EXPULSION")) {
            j.setExpulsado(true);
        }
    }

    private void mostrarEstadisticas() {
        System.out.println("Resultados:");
        System.out.println("Local: " + getTotalPuntoEquipo("L"));
        System.out.println("Visitante: " + getTotalPuntoEquipo("V"));
    }

    private void rellenarListaParticipantes() {

        Participante jl1 = new JugadorLocal("VLC01", "Víctor Luengo", "Española", 32, 15, "Escolta", false, "Pamesa Valencia", 196);
        Participante jl2 = new JugadorLocal("VLC02", "Nacho Rodilla", "Española", 30, 11, "Base", false, "Pamesa Valencia", 192);
        Participante jl3 = new JugadorLocal("VLC03", "Bernard Hopkins", "USA", 31, 12, "Ala-Pívot", false, "Pamesa Valencia", 197);
        Participante jl4 = new JugadorLocal("VLC01", "Víctor Luengo", "Española", 32, 15, "Escolta", false, "Pamesa Valencia", 196);
        Participante jl5 = new JugadorLocal("VLC04", "Fabricio Oberto", "Argentina", 27, 7, "Pívot", false, "Pamesa Valencia", 208);
        Participante jl6 = new JugadorLocal("VLC05", "Rafa Martínez", "Española", 34, 17, "Escolta", false, "Pamesa Valencia", 190);
        Participante jl7 = new JugadorLocal("VLC06", "Bojan Dubljevic", "Montenegro", 29, 14, "Pívot", false, "Pamesa Valencia", 205);
        Participante jl8 = new JugadorLocal("VLC07", "Dejan Tomasevic", "Serbia", 30, 13, "Pívot", false, "Pamesa Valencia", 208);
        Participante jl9 = new JugadorLocal("VLC08", "Chris Jones", "USA", 30, 7, "Base", false, "Pamesa Valencia", 188);

        participantes.add(jl1);participantes.add(jl2);participantes.add(jl3);participantes.add(jl4);
        participantes.add(jl5);participantes.add(jl6);participantes.add(jl7);participantes.add(jl8);participantes.add(jl9);

        Participante jv1 = new JugadorVisitante("RMD01", "Sergio Llull", "Española", 36, 23, "Base", false, "Real Madrid", 190);
        Participante jv2 = new JugadorVisitante("RMD02", "Walter Tavares", "Cabo Verde", 31, 22, "Pívot", false, "Real Madrid", 220);
        Participante jv3 = new JugadorVisitante("RMD03", "Facundo Campazzo", "Argentina", 32, 7, "Base", false, "Real Madrid", 181);
        Participante jv4 = new JugadorVisitante("RMD04", "Rudy Fernández", "Española", 38, 5, "Alero", false, "Real Madrid", 196);
        Participante jv5 = new JugadorVisitante("RMD05", "Mario Hezonja", "Croacia", 28, 11, "Alero", false, "Real Madrid", 203);
        Participante jv6 = new JugadorVisitante("RMD06", "Dzanan Musa", "Bosnia", 24, 31, "Escolta", false, "Real Madrid", 205);
        Participante jv7 = new JugadorVisitante("RMD07", "Guerschon Yabusele", "Francia", 28, 28, "Ala-Pívot", false, "Real Madrid", 204);
        Participante jv8 = new JugadorVisitante("RMD08", "Gabriel Deck", "Argentina", 29, 14, "Alero", false, "Real Madrid", 198);

        participantes.add(jv1);participantes.add(jv2);participantes.add(jv3);participantes.add(jv4);
        participantes.add(jv5);participantes.add(jv6);participantes.add(jv7);participantes.add(jv8);

        // Árbitros
        Participante arb1 = new Arbitro("ARB01", "Juan Carlos García González", "Española", 55);
        Participante arb2 = new Arbitro("ARB02", "Antonio Conde", "Española", 51);
        Participante arb3 = new Arbitro("ARB03", "Emilio Pérez Pizarro", "Española", 53);

        participantes.add(arb1);participantes.add(arb2);participantes.add(arb3);

    }

}
