package cl.ucn.disc.dsm.cafa.phodi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
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
     * @param context La instancia del activity.
     * @param personas La lista de personas que se quiere manejar.
     */
    public PersonaAdapter(Context context, List<Persona> personas){
        inflater = LayoutInflater.from(context);
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
        TextView tv_telefono = convertView.findViewById(R.id.tv_telefono);
        TextView tv_cargo = convertView.findViewById(R.id.tv_cargo);


        // Llenar los textViews con los datos de la persona (Ejemplo):
        tv_nombre.setText(persona.getNombre());
        tv_telefono.setText(persona.getTelefono());
        tv_cargo.setText(persona.getCargo());

        // Devolver la vista con la informacion de la persona.
        return convertView;
    }
}