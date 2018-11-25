package cl.ucn.disc.dsm.cafa.phodi.models;

import lombok.Builder;
import lombok.Getter;

@Builder
public final class Persona {

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