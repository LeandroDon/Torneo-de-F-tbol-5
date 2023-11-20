import java.util.List;
import java.util.Scanner;

public class Jugador {
    private String nombreJugador;
    private String apellidoJugador;
    private Integer numeroCamiseta;
    private Integer dni;
    private String obraSocial;
    private String aptoMedico;
    private boolean esCapitan;
    private boolean esSubCapitan;

    public Jugador(String nombreJugador, String apellidoJugador, Integer numeroCamiseta, Integer dni, String obraSocial,
            String aptoMedico, boolean esCapitan, boolean esSubCapitan) {
        this.nombreJugador = nombreJugador;
        this.apellidoJugador = apellidoJugador;
        this.numeroCamiseta = numeroCamiseta;
        this.dni = dni;
        this.obraSocial = obraSocial;
        this.aptoMedico = aptoMedico;
        this.esCapitan = esCapitan;
        this.esSubCapitan = esSubCapitan;
    }

    public static Jugador inscribirJugador(Scanner scanner, Equipo equipo) {
        System.out.print("\nIngrese el nombre del jugador: ");
        String nombreJugador = scanner.nextLine();
        System.out.print("Ingrese el apellido del jugador: ");
        String apellidoJugador = scanner.nextLine();
                
        int numeroCamiseta;
        boolean numeroCamisetaExistente;
        do {
        System.out.print("Ingrese el número de camiseta del jugador: ");
        numeroCamiseta = Integer.parseInt(scanner.nextLine());
        numeroCamisetaExistente = yaExisteCamiseta(numeroCamiseta, equipo.getJugadores());
        if (numeroCamisetaExistente) {
            System.out.println("\n¡El número de camiseta ya existe! Ingrese otro número.\n");
        }
        } while (numeroCamisetaExistente);
                        
        System.out.print("Ingrese el DNI del jugador: ");
        int dni = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la obra social del jugador: ");
        String obraSocial = scanner.nextLine();
        System.out.print("Ingrese el estado del apto médico del jugador: ");
        String aptoMedico = scanner.nextLine();

        boolean esCapitan = false;
        boolean esSubCapitan = false;

        if (!yaHayCapitan(equipo.getJugadores())) {
            System.out.print("¿Es el capitán? (S/N): ");
            String esCapitanInput = scanner.nextLine();
            if (esCapitanInput.equalsIgnoreCase("S")) {
                esCapitan = true;
            }
        }

        // Verificar si ya hay un subcapitán en el equipo y no es capitán
        if (!esCapitan && !yaHaySubCapitan(equipo.getJugadores())) {
            System.out.print("¿Es el subcapitán? (S/N): ");
            String esSubCapitanInput = scanner.nextLine();
            if (esSubCapitanInput.equalsIgnoreCase("S")) {
                esSubCapitan = true;
            }
        }
        return new Jugador(nombreJugador, apellidoJugador, numeroCamiseta, dni, obraSocial, aptoMedico, esCapitan, esSubCapitan);
    }

    // Método para verificar si ya hay un capitán en el equipo
    private static boolean yaHayCapitan(List<Jugador> jugadoresEquipo) {
        for (Jugador jugador : jugadoresEquipo) {
            if (jugador.isEsCapitan()) {
                return true;
            }
        }
        return false;
    }

    // Método para verificar si ya hay un subcapitán en el equipo
    private static boolean yaHaySubCapitan(List<Jugador> jugadoresEquipo) {
        for (Jugador jugador : jugadoresEquipo) {
            if (jugador.isEsSubCapitan()) {
                return true;
            }
        }
        return false;
    }

    private static boolean yaExisteCamiseta(int numeroCamiseta, List<Jugador> jugadoresEquipo) {
        for (Jugador jugador : jugadoresEquipo) {
            if (jugador.getNumeroCamiseta() == numeroCamiseta) {
                return true;
            }
        }
        return false;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getApellidoJugador() {
        return apellidoJugador;
    }

    public void setApellidoJugador(String apellidoJugador) {
        this.apellidoJugador = apellidoJugador;
    }

    public Integer getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(Integer numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public String getAptoMedico() {
        return aptoMedico;
    }

    public void setAptoMedico(String aptoMedico) {
        this.aptoMedico = aptoMedico;
    }

    public boolean isEsCapitan() {
        return esCapitan;
    }

    public void setEsCapitan(boolean esCapitan) {
        this.esCapitan = esCapitan;
    }

    public boolean isEsSubCapitan() {
        return esSubCapitan;
    }

    public void setEsSubCapitan(boolean esSubCapitan) {
        this.esSubCapitan = esSubCapitan;
    }

    @Override
    public String toString() {
        return "Jugador: " + nombreJugador + " " + apellidoJugador + " (Camiseta: " + numeroCamiseta + ")";
    }
}