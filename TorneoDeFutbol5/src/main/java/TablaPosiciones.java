import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TablaPosiciones {
    private List<Equipo> equipos;

    public TablaPosiciones(List<Equipo> equipos) {
        this.equipos = equipos;
        ordenarTabla();
    }

    public TablaPosiciones(Equipo equipo, Equipo equipo2) {
    }

    public void ordenarTabla() {
        Collections.sort(equipos, new Comparator<Equipo>() {
            @Override
            public int compare(Equipo equipo1, Equipo equipo2) {
                int comparacionPorPuntos = Integer.compare(equipo2.getPuntos(), equipo1.getPuntos());
                if (comparacionPorPuntos != 0) {
                    return comparacionPorPuntos;
                }

                int comparacionPorDiferenciaDeGoles = Integer.compare(equipo2.getDiferenciaDeGoles(), equipo1.getDiferenciaDeGoles());
                if (comparacionPorDiferenciaDeGoles != 0) {
                    return comparacionPorDiferenciaDeGoles;
                }

                int resultadoDelPartido = obtenerResultadoDelPartido(equipo1, equipo2);
                return Integer.compare(resultadoDelPartido, 0);
            }
        });
    }

    private int obtenerResultadoDelPartido(Equipo equipo1, Equipo equipo2) {
    return 0;
    }

    public void mostrarTablaPosiciones() {
        System.out.println("\nTabla de Posiciones:");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombreEquipo() + " - Puntos: " + equipos.get(i).getPuntos());
        }
    }
    public Equipo getSegundoEquipo() {
        return null;
    }

    public Equipo getPrimerEquipo() {
        return null;
    }

    public Equipo getEquipoLocal() {
        return null;
    }

    public Equipo getEquipoVisitante() {
        return null;
    }

    public void setGolesLocal(int golesLocal) {
    }

    public void setGolesVisitante(int golesVisitante) {
    }
}