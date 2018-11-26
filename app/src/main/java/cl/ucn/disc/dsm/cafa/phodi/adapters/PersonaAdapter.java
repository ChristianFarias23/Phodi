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

    public void cargar(List<Persona> personas) {
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

    public void ordenarPorNombre(boolean asc) {
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

        PersonaViewHolder holder;

        // Inflar la vista solo si es nula, si no, reutilizarla.
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fila_persona, parent, false);

            holder = new PersonaViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PersonaViewHolder) convertView.getTag();
        }

        // Llenar los textViews con los datos de la persona:
        // Si su dato es "null", no mostrarlo.
        holder.nombre.setText(persona.getNombre().equals("null") ? "Sin Nombre" : persona.getNombre());
        holder.cargo.setText(persona.getCargo().equals("null") ? "" : persona.getCargo());
        holder.unidad.setText(persona.getUnidad().equals("null") ? "" : persona.getUnidad());
        holder.oficina.setText(persona.getOficina().equals("null") ? "" : persona.getOficina());
        holder.email.setText(persona.getEmail().equals("null") ? "" : persona.getEmail());
        holder.telefono.setText(persona.getTelefono().equals("null") ? "" : persona.getTelefono());

        // Devolver la vista con la informacion de la persona.
        return convertView;
    }

    private static class PersonaViewHolder {
        TextView nombre;
        TextView cargo;
        TextView unidad;
        TextView oficina;
        TextView email;
        TextView telefono;

        public PersonaViewHolder(View view) {
            this.nombre = view.findViewById(R.id.tv_nombre);
            this.cargo = view.findViewById(R.id.tv_cargo);
            this.unidad = view.findViewById(R.id.tv_unidad);
            this.oficina = view.findViewById(R.id.tv_oficina);
            this.email = view.findViewById(R.id.tv_email);
            this.telefono = view.findViewById(R.id.tv_telefono);
        }
    }
}
