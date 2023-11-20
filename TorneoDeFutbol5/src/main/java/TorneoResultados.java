import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TorneoResultados {
    private List<Equipo> equipos;
    private Scanner scanner;
    

    public TorneoResultados(List<Equipo> equipos, Scanner scanner) {
        this.equipos = equipos;
        this.scanner = scanner;
    }

    public void cargarEquiposDesdeJSON(String nombreArchivo) {
    Json.cargarEquiposDesdeJSON(equipos, nombreArchivo);
    }
    public void cargarResultados(Scanner scanner) {
        
        cargarEquiposDesdeJSON("equipos.json");
        List<Equipo> primerosOchoEquipos = obtenerPrimerosOchoEquipos(equipos);
        if (primerosOchoEquipos.size() < 8) {
            System.out.println("No hay suficientes equipos para formar dos zonas de cuatro. Regresando al menú.");
            return;
        }

        Collections.shuffle(primerosOchoEquipos);

        int opcion;
        do {
        System.out.println("\nCARGA DE RESULTADOS\n");
        System.out.println("1. Mostrar equipos y zonas");
        System.out.println("2. Cargar resultados de partidos de Zona A");
        System.out.println("3. Cargar resultados de partidos de Zona B");
        System.out.println("4. Tablas de Posiciones Zonas");
        System.out.println("5. Cargar resultado Tercer Puesto");
        System.out.println("6. Cargar resultado Final");
        System.out.println("7. Volver al menú principal\n");
        System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    mostrarEquiposYZonas(primerosOchoEquipos);
                    break;
                case 2:
                    System.out.println("\nCargar resultados de partidos de Zona A:\n");
                    cargarResultadosZona(primerosOchoEquipos.subList(0, 4));
                    break;
                case 3:
                    System.out.println("\nCargar resultados de partidos de Zona B:\n");
                    cargarResultadosZona(primerosOchoEquipos.subList(4, 8));
                    break;
                case 4:
                    System.out.println("\nTablas de Posiciones");
                    mostrarTablaPosiciones(primerosOchoEquipos.subList(0, 4));
                    mostrarTablaPosiciones(primerosOchoEquipos.subList(4, 8));
                    break;
                case 5:
                    System.out.println("\nCargar resultado Tercer Puesto");
                    break;
                case 6:
                    System.out.println("\nCargar resultado Final");
                    break;
                case 7:
                    System.out.println("\nVolviendo a Intranet");
                    break;
                default:
                    System.out.println("\nOpción no válida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

    private List<Equipo> obtenerPrimerosOchoEquipos(List<Equipo> equipos) {

        Collections.sort(equipos, (equipo1, equipo2) -> equipo2.getPuntos() - equipo1.getPuntos());
        return equipos.subList(0, Math.min(8, equipos.size()));
    }

    private void mostrarEquiposYZonas(List<Equipo> equipos) {
        System.out.println("\nEquipos y Zonas:\n");
        System.out.println("Zona A:");
        imprimirEquipos(equipos.subList(0, 4));
        System.out.println("\nZona B:");
        imprimirEquipos(equipos.subList(4, 8));
    }

    private void imprimirEquipos(List<Equipo> equipos) {
        for (Equipo equipo : equipos) {
            System.out.println("- " + equipo.getNombreEquipo());
        }
    }

    

    private void cargarResultadosZona(List<Equipo> equiposZona) {
        TablaPosiciones tablaZona = new TablaPosiciones(equiposZona);
        System.out.println("Equipos en la zona:");
        imprimirEquipos(equiposZona);

        for (int i = 0; i < equiposZona.size(); i++) {
            for (int j = i + 1; j < equiposZona.size(); j++) {
            Equipo equipoLocal = equiposZona.get(i);
            Equipo equipoVisitante = equiposZona.get(j);

            System.out.println("\nIngrese el resultado para " + equipoLocal.getNombreEquipo() + " vs " + equipoVisitante.getNombreEquipo() + ": ");
            System.out.print(equipoLocal.getNombreEquipo() + ": ");
            int resultadoLocal = scanner.nextInt();
            System.out.print(equipoVisitante.getNombreEquipo()+ ": ");
            int resultadoVisitante = scanner.nextInt();

            if (resultadoLocal > resultadoVisitante) {
            equipoLocal.incrementarPuntos(3);
            } else if (resultadoLocal < resultadoVisitante) {
            equipoVisitante.incrementarPuntos(3);
            } else {    
            equipoLocal.incrementarPuntos(1);
            equipoVisitante.incrementarPuntos(1);
        }

        equipoLocal.incrementarPartidosJugados();
        equipoVisitante.incrementarPartidosJugados();
        equipoLocal.agregarGoles(resultadoLocal);
        equipoVisitante.agregarGoles(resultadoVisitante);
    }

        tablaZona.ordenarTabla();
    }
}
    
    public void mostrarTablaPosiciones(List<Equipo> equiposZona) {
        Collections.sort(equiposZona, (equipo1, equipo2) -> equipo2.getPuntos() - equipo1.getPuntos());

        System.out.println("\nTabla de Posiciones:");
        System.out.printf("%-20s %-10s %-10s %-10s\n", "Equipo", "Puntos", "Partidos", "Goles");
        for (Equipo equipo : equiposZona) {
            System.out.printf("%-20s %-10s %-10s %-10s\n", equipo.getNombreEquipo(), equipo.getPuntos(), equipo.getPartidosJugados(), equipo.getGoles());
        }
    }
}