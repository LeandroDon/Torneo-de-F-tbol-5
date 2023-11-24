import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TorneoFutbol5 {

    public static List<Equipo> equipos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        Usuario.cargarUsuariosDesdeJSON(listaUsuarios, "usuarios.json");
        Json.cargarEquiposDesdeJSON(equipos, "equipos.json");

        System.out.println(
                "\n\033[36m* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *" +
                        "\n\033[37m*                                                                                   *"
                        +
                        "\n\033[36m*                       Bienvenido al Torneo de Fútbol 5 del                        *"
                        +
                        "\n\033[37m*                               CLUB HACHA Y HACHA                                  *"
                        +
                        "\n\033[36m*                                                                                   *"
                        +
                        "\n\033[37m* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");

        int opcion;
        do {
            System.out.println("\nMENÚ PRINCIPAL\n");
            System.out.println("1. Torneo de Fútbol 5");
            System.out.println("2. Intranet");
            System.out.println("3. Salir\n");
            System.out.print("Seleccione una opción (1-3): ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Json.cargarEquiposDesdeJSON(equipos, "equipos.json");
                    menuTorneo();
                    break;
                case 2:
                    if (Usuario.autenticarUsuario(listaUsuarios, scanner)) {
                        Intranet.menuIntranet(scanner, equipos);
                    }
                    break;
                case 3:
                    System.out.println("Gracias por participar en el torneo.");
                    break;
                case 4:
                    Json.guardarEquiposEnJSON(equipos, "equipos.json");
                    System.out.println("Equipos guardados correctamente.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (opcion != 3);
        scanner.close();
    }

    public static void menuTorneo() throws IOException {

        List<Usuario> listaUsuarios = new ArrayList<>();
        Usuario.cargarUsuariosDesdeJSON(listaUsuarios, "usuarios.json");
        Json.cargarEquiposDesdeJSON(equipos, "equipos.json");

        int subOpcion;
        do {
            System.out.println("\n\nTORNEO DE FÚTBOL 5\n");
            System.out.println("1. Inscribir equipo");
            System.out.println("2. Agregar jugador a un equipo");
            System.out.println("3. Mostrar equipos y jugadores");
            System.out.println("4. Volver al menú principal\n");
            System.out.print("Elija una opción: ");
            subOpcion = scanner.nextInt();
            scanner.nextLine();

            switch (subOpcion) {
                case 1:
                    Equipo.inscribirEquipo(equipos, scanner);
                    break;
                case 2:
                    mostrarListaEquiposNumerada();
                    System.out.print("\nElija el número de equipo al que desea agregar un jugador: ");
                    int numeroEquipo = scanner.nextInt();
                    scanner.nextLine();

                    if (numeroEquipo >= 1 && numeroEquipo <= equipos.size()) {
                        Equipo equipoSeleccionado = equipos.get(numeroEquipo - 1);
                        Jugador nuevoJugador = Jugador.inscribirJugador(scanner, equipoSeleccionado);
                        equipoSeleccionado.inscribirJugador(nuevoJugador);
                        System.out.println("El jugador ha sido agregado al equipo con éxito.");
                    } else {
                        System.out.println("Número de equipo no válido.");
                    }
                    break;
                case 3:
                    for (Equipo equipo : equipos) {
                        System.out.println();
                        System.out.println("Equipo: " + equipo.getNombreEquipo());
                        if (!equipo.getJugadores().isEmpty()) {
                            System.out.println("Jugadores del equipo:");
                            for (Jugador jugador : equipo.getJugadores()) {
                                System.out.println(jugador);
                            }
                        } else {
                            System.out.println("Este equipo no tiene jugadores.");
                        }
                    }
                    break;
                case 4:
                    Json.guardarEquiposEnJSON(equipos, "equipos.json");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } while (subOpcion != 4);
    }

    private static void mostrarListaEquiposNumerada() {
        System.out.println("\nEquipos existentes:");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombreEquipo());
        }
    }
}