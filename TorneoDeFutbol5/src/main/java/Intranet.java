import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Intranet {

    static Scanner scanner = new Scanner(System.in);
    private static List<Equipo> equipos;

    public static void menuIntranet(Scanner scanner, List<Equipo> equipos) throws IOException {

        Intranet.equipos = new ArrayList<>();

        int opcion = 0;

        while (opcion != 3) {
            System.out.println("INTRANET\n");
            System.out.println("1. Cargar resultados");
            System.out.println("2. Imprimir resultados del torneo");
            System.out.println("3. Volver al menú principal\n");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    TorneoResultados torneoResultados = new TorneoResultados(Intranet.equipos, scanner);
                    torneoResultados.cargarResultados(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}