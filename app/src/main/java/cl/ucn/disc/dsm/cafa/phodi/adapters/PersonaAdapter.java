package cl.ucn.disc.dsm.cafa.phodi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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

    /**
     * Carga una lista de personas al adaptador.
     * @param personas
     */
    public void cargar(List<Persona> personas) {
        this.personas = personas;
    }

    /**
     * Obtiene la cantidad de personas que contiene este adaptador.
     * @return
     */
    @Override
    public int getCount() {
        return personas.size();
    }

    /**
     * Obtiene la persona en la posicion especificada.
     * @param position
     * @return la persona.
     */
    @Override
    public Persona getItem(int position) {
        return personas.get(position);
    }

    /**
     * Ordena la lista por nombre.
     * @param asc : Ascendente o Descendente.
     */
    public void ordenarPorNombre(boolean asc) {
        // Ordenamiento descendente.
        if (!asc)
            Collections.sort(this.personas, (p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));
        else
            //Ordenamiento ascendente.
            Collections.sort(this.personas, (p1, p2) -> p2.getNombre().compareToIgnoreCase(p1.getNombre()));
    }

    /**
     * Obtiene el id de la persona en la posicion especificada.
     * @param position
     * @return el id de la persona.
     */
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
        holder.getNombre().setText(persona.getNombre().equals("null") ? "Nombre no disponible" : persona.getNombre());
        holder.getCargo().setText(persona.getCargo().equals("null") ? "Cargo no disponible" : persona.getCargo());
        holder.getUnidad().setText(persona.getUnidad().equals("null") ? "Unidad no disponible" : persona.getUnidad());
        holder.getOficina().setText(persona.getOficina().equals("null") ? "Oficina no disponible" : persona.getOficina());
        holder.getEmail().setText(persona.getEmail().equals("null") ? "Email no disponible" : persona.getEmail());
        holder.getTelefono().setText(persona.getTelefono().equals("null") ? "Telefono no disponible" : persona.getTelefono());

        // Devolver la vista con la informacion de la persona.
        return convertView;
    }
}
