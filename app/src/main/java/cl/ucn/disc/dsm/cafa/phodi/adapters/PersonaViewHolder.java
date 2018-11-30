package cl.ucn.disc.dsm.cafa.phodi.adapters;

import android.view.View;
import android.widget.TextView;

import cl.ucn.disc.dsm.cafa.phodi.R;
import lombok.Getter;

public final class PersonaViewHolder {
    @Getter
    private TextView nombre;
    @Getter
    private TextView cargo;
    @Getter
    private TextView unidad;
    @Getter
    private TextView oficina;
    @Getter
    private TextView email;
    @Getter
    private TextView telefono;

    public PersonaViewHolder(View view) {
        this.nombre = view.findViewById(R.id.tv_nombre);
        this.cargo = view.findViewById(R.id.tv_cargo);
        this.unidad = view.findViewById(R.id.tv_unidad);
        this.oficina = view.findViewById(R.id.tv_oficina);
        this.email = view.findViewById(R.id.tv_email);
        this.telefono = view.findViewById(R.id.tv_telefono);
    }

}
