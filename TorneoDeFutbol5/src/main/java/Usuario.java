import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    private String usuario;
    private String contrasena;

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public static void cargarUsuariosDesdeJSON(List<Usuario> usuarios, String nombreArchivo) throws IOException {
        Gson gson = new Gson();
        JsonArray jsonArrayUsuarios;

        try (FileInputStream fileInputStream = new FileInputStream(nombreArchivo);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            jsonArrayUsuarios = gson.fromJson(bufferedReader, JsonArray.class);
        }

        for (int i = 0; i < jsonArrayUsuarios.size(); i++) {
            JsonObject jsonUsuario = jsonArrayUsuarios.get(i).getAsJsonObject();
            String usuario = jsonUsuario.get("usuario").getAsString();
            String contrasena = jsonUsuario.get("contrasena").getAsString();

            usuarios.add(new Usuario(usuario, contrasena));
        }
    }

    public static boolean autenticarUsuario(List<Usuario> usuarios, Scanner scanner) throws IOException {
        int intentos = 3;
    
        while (intentos > 0) {
            System.out.println("\nIngrese usuario y contraseña para continuar.\n");
            System.out.print("Usuario: ");
            String usuarioIngresado = scanner.next().trim();
            scanner.nextLine();
    
            System.out.print("Contraseña: ");
            String contrasenaIngresada = scanner.next().trim();
            scanner.nextLine();
   
            for (Usuario usuario : usuarios) {
                if (usuario.getUsuario().equals(usuarioIngresado) && usuario.getContrasena().equals(contrasenaIngresada)) {
                    System.out.println("\nAutenticación exitosa. ¡Bienvenido, " + usuarioIngresado + "!\n");
                    return true;
                }
            }
    
            System.out.println("Autenticación fallida. Usuario o contraseña incorrectos. Intentos restantes: " + (--intentos));
    
            if (intentos > 0) {
                System.out.println("Por favor, inténtelo de nuevo.");
            }
        }
    
        System.out.println("Agotados los intentos. Volviendo al menú principal.");
        return false;
    }
}