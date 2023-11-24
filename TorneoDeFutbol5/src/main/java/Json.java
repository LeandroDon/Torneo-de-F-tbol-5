import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Json {

    private static final Gson gson = new Gson();

    public static void guardarEquiposEnJSON(List<Equipo> equipos, String nombreArchivo) throws IOException {
        JsonArray jsonArrayEquipos = new JsonArray();
        JsonArray jsonArrayEquiposExistentes = FileUtils.readJSONFromFile(nombreArchivo);
        // jsonArrayEquipos.addAll(jsonArrayEquiposExistentes);

        for (Equipo equipo : equipos) {
            JsonObject jsonEquipo = new JsonObject();
            jsonEquipo.addProperty("nombreEquipo", equipo.getNombreEquipo());

            JsonArray jsonArrayJugadores = new JsonArray();
            for (Jugador jugador : equipo.getJugadores()) {
                JsonObject jsonJugador = new JsonObject();
                jsonJugador.addProperty("nombreJugador", jugador.getNombreJugador());
                jsonJugador.addProperty("apellidoJugador", jugador.getApellidoJugador());
                jsonJugador.addProperty("numeroCamiseta", jugador.getNumeroCamiseta());
                jsonJugador.addProperty("dni", jugador.getDni());
                jsonJugador.addProperty("obraSocial", jugador.getObraSocial());
                jsonJugador.addProperty("aptoMedico", jugador.getAptoMedico());
                jsonJugador.addProperty("esCapitan", jugador.isEsCapitan());
                jsonJugador.addProperty("esSubCapitan", jugador.isEsSubCapitan());

                jsonArrayJugadores.add(jsonJugador);
            }

            jsonEquipo.add("jugadores", jsonArrayJugadores);
            jsonArrayEquipos.add(jsonEquipo);
        }

        FileUtils.writeJSONToFile(jsonArrayEquipos, nombreArchivo);

        System.out.println("Equipos guardados en JSON correctamente.");
    }

    public static List<Equipo> cargarEquiposDesdeJSON(List<Equipo> equipos, String nombreArchivo) {
        equipos.clear();

        JsonArray jsonArrayEquipos = FileUtils.readJSONFromFile(nombreArchivo);

        for (int i = 0; i < jsonArrayEquipos.size(); i++) {
            JsonObject jsonEquipo = jsonArrayEquipos.get(i).getAsJsonObject();
            String nombreEquipo = jsonEquipo.get("nombreEquipo").getAsString();

            Equipo equipoExistente = equipos.stream()
                    .filter(equipo -> equipo.getNombreEquipo().equals(nombreEquipo))
                    .findFirst()
                    .orElse(null);

            if (equipoExistente == null) {
                equipoExistente = new Equipo(nombreEquipo);
                equipos.add(equipoExistente);
            }

            equipoExistente.getJugadores().clear();

            JsonArray jsonArrayJugadores = jsonEquipo.getAsJsonArray("jugadores");
            for (int j = 0; j < jsonArrayJugadores.size(); j++) {
                JsonObject jsonJugador = jsonArrayJugadores.get(j).getAsJsonObject();
                Jugador jugador = new Jugador(
                        jsonJugador.get("nombreJugador").getAsString(),
                        jsonJugador.get("apellidoJugador").getAsString(),
                        jsonJugador.get("numeroCamiseta").getAsInt(),
                        jsonJugador.get("dni").getAsInt(),
                        jsonJugador.get("obraSocial").getAsString(),
                        jsonJugador.get("aptoMedico").getAsString(),
                        jsonJugador.get("esCapitan").getAsBoolean(),
                        jsonJugador.get("esSubCapitan").getAsBoolean());
                equipoExistente.inscribirJugador(jugador);
            }
        }
        return equipos;
    }

    public static class FileUtils {

        public static JsonArray readJSONFromFile(String nombreArchivo) {
            JsonArray jsonArray = new JsonArray();
            try (FileInputStream fileInputStream = new FileInputStream(nombreArchivo);
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,
                            StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonArray;
        }

        public static void writeJSONToFile(JsonArray jsonArray, String nombreArchivo) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(nombreArchivo, StandardCharsets.UTF_8))) {
                gson.toJson(jsonArray, bufferedWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}