package cl.ucn.disc.dsm.cafa.phodi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import cl.ucn.disc.dsm.cafa.phodi.R;
import cl.ucn.disc.dsm.cafa.phodi.models.Persona;

public final class PersonaAdapter extends BaseAdapter {

    /**
     * Lista de personas que manejara este adaptador.
     */
    List<Persona> personas;

    /**
     * Infla las vistas.
     */
    private LayoutInflater inflater;

    /**
     * Constructor del adaptador.
     *
     * @param context  La instancia del activity.
     * @param personas La lista de personas que se quiere manejar.
     */
    public PersonaAdapter(Context context, List<Persona> personas) {
        inflater = LayoutInflater.from(context);
        this.personas = personas;
    }

    public void cargar(List<Persona> personas){
        this.personas = personas;
    }

    @Override
    public int getCount() {
        return personas.size();
    }

    @Override
    public Persona getItem(int position) {
        return personas.get(position);
    }

    public void ordenarPorNombre(boolean asc){
        // Ordenamiento descendente.
        if (!asc)
            Collections.sort(this.personas, (p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
        else
            //Ordenamiento ascendente.
            Collections.sort(this.personas, (p1, p2) -> p2.getNombre().compareToIgnoreCase(p1.getNombre()));

        this.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return personas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener a la persona.
        final Persona persona = getItem(position);

        // Inflar la vista.
        if (convertView == null)
            convertView = inflater.inflate(R.layout.fila_persona, parent, false);

        // Obtener las referencias a los textViews de la vista que queremos (re)usar.
        TextView tv_nombre = convertView.findViewById(R.id.tv_nombre);
        TextView tv_cargo = convertView.findViewById(R.id.tv_cargo);
        TextView tv_unidad = convertView.findViewById(R.id.tv_unidad);
        TextView tv_oficina = convertView.findViewById(R.id.tv_oficina);
        TextView tv_email = convertView.findViewById(R.id.tv_email);
        TextView tv_telefono = convertView.findViewById(R.id.tv_telefono);


        // Llenar los textViews con los datos de la persona (Ejemplo):
        tv_nombre.setText(persona.getNombre());
        tv_cargo.setText(persona.getCargo());
        tv_unidad.setText(persona.getUnidad());
        tv_oficina.setText(persona.getOficina());
        tv_email.setText(persona.getEmail());
        tv_telefono.setText(persona.getTelefono());

        // Devolver la vista con la informacion de la persona.
        return convertView;
    }
}
