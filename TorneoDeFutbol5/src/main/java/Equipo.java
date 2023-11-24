import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Equipo {
    private String nombreEquipo;
    private List<Jugador> jugadores;
    private int puntos;
    private int diferenciaDeGoles;
    private List<Partido> partidos;
    private int partidosJugados;
    private int goles;

    public Equipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
        this.jugadores = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.puntos = 0;
        this.partidosJugados = 0;
    }

    public static void inscribirEquipo(List<Equipo> equipos, Scanner scanner) {
        System.out.print("\nIngrese el nombre del equipo: ");
        String nombreEquipo = scanner.nextLine();

        boolean equipoExistente = false;
        for (Equipo equipo : equipos) {
            if (equipo.getNombreEquipo().equalsIgnoreCase(nombreEquipo)) {
                equipoExistente = true;
                break;
            }
        }

        if (!equipoExistente) {
            Equipo nuevoEquipo = new Equipo(nombreEquipo);
            equipos.add(nuevoEquipo);
            System.out.println("Equipo inscripto correctamente.");
        } else {
            System.out.println("\nYa existe un equipo con ese nombre.");
        }
    }

    public void inscribirJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void agregarPartido(Partido partido) {
        partidos.add(partido);
    }

    public int getPuntos() {
        return this.puntos;
    }

    public int getDiferenciaDeGoles() {
        return 0;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getGoles() {
        return goles;
    }

    public List<Partido> getPartidos() {
        List<Partido> partidos = new ArrayList<>();
        return partidos;
    }

    public void agregarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void agregarGoles(int cantidadGoles) {
        this.goles += cantidadGoles;
        this.diferenciaDeGoles += cantidadGoles;
    }

    public void incrementarPartidosJugados() {
        this.partidosJugados++;
    }

    public void incrementarPuntos(int puntos) {
        this.puntos += puntos;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Equipo: " + nombreEquipo + "\n");

        if (!jugadores.isEmpty()) {
            result.append("Jugadores del equipo:\n");
            for (Jugador jugador : jugadores) {
                result.append(jugador).append("\n");
            }
        }

        return result.toString();
    }
}