package cl.ucn.disc.dsm.cafa.phodi.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;

import lombok.Builder;
import lombok.Getter;

@Builder
public final class Persona {

    public Persona(int id, String nombre, String cargo, String unidad, String email, String telefono, String oficina) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.unidad = unidad;
        this.email = email;
        this.telefono = telefono;
        this.oficina = oficina;
    }

    public Persona(JSONObject obj) {
        try {
            this.id = obj.getInt("id");
            this.nombre = obj.getString("nombre");
            this.cargo = obj.getString("cargo");
            this.unidad = obj.getString("unidad");
            this.email = obj.getString("email");
            this.telefono = obj.getString("telefono");
            this.oficina = obj.getString("oficina");

        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

    public static ArrayList<Persona> fromJson(JSONArray array) {
        ArrayList<Persona> personas = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                personas.add(new Persona(array.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return personas;
    }



    /**
     * El ID de la persona dentro de la base de datos.
     */
    @Getter
    private int id;

    /**
     * El nombre de la persona.
     */
    @Getter
    private String nombre;

    /**
     * El cargo de la persona (Funcionario/Academico).
     */
    @Getter
    private String cargo;

    /**
     * La unidad a la que pertenece la persona.
     */
    @Getter
    private String unidad;

    /**
     * El email de la persona.
     */
    @Getter
    private String email;

    /**
     * El telefono de la persona.
     */
    @Getter
    private String telefono;

    /**
     * La oficina en donde se puede encontrar a la persona.
     */
    @Getter
    private String oficina;
}

