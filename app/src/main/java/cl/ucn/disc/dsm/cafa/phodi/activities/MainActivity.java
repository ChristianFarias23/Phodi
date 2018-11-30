package cl.ucn.disc.dsm.cafa.phodi.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import org.json.JSONArray;
import java.io.InputStream;
import java.util.ArrayList;

import cl.ucn.disc.dsm.cafa.phodi.R;
import cl.ucn.disc.dsm.cafa.phodi.adapters.PersonaAdapter;
import cl.ucn.disc.dsm.cafa.phodi.adapters.PersonaViewHolder;
import cl.ucn.disc.dsm.cafa.phodi.models.Persona;

public class MainActivity extends AppCompatActivity {

    /**
     * El listView que muestra a las personas.
     */
    private ListView listView;

    /**
     * El adaptador que 'convierte' personas a views en la listView.
     */
    private PersonaAdapter adapter;

    /**
     * La lista inicial de personas.
     */
    static ArrayList<Persona> listaInicial;

    // Necesarios para copiar al portapapeles.
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Obtener las personas:
        if (savedInstanceState == null){
            listaInicial = Persona.fromJson(getJsons());
        }

        // Crear el adaptador.
        if (this.adapter == null) {
            this.adapter = new PersonaAdapter(this, listaInicial);
            adapter.ordenarPorNombre(false);
        }

        // Asignar el adaptador al listView de la main activity.
        listView = findViewById(R.id.lv_personas);
        listView.setAdapter(adapter);

        // Agregar un listener a la listview.
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener nombre y telefono del item seleccionado.
                PersonaViewHolder personaSelected = ((PersonaViewHolder)view.getTag());
                String pNombre = personaSelected.getNombre().getText().toString();
                String pTelefono = personaSelected.getTelefono().getText().toString();

                if (!pTelefono.equals("Telefono no disponible")) {
                    // Obtener solo el numero.
                    pTelefono = pTelefono.substring(5);
                    if (pTelefono.length() > 12)
                        pTelefono = pTelefono.substring(0, 13);

                    // Copiar al portapapeles.
                    myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    myClip = ClipData.newPlainText("Telefono", pTelefono);
                    myClipboard.setPrimaryClip(myClip);
                    Toast.makeText(MainActivity.this, "Se ha copiado el Telefono de " + pNombre, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Telefono no disponible", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.adapter != null && this.adapter.isEmpty()){
            Toast.makeText(getApplicationContext(), "Cargando lista... ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menu de opciones.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mas, menu);

        // El buscador que se encuentra en el toolbar.
        MenuItem buscador = menu.findItem(R.id.item2_buscar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(buscador);

        // Se le agrega un listener.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {return true;}

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter == null) return false;

                // Si la busqueda esta vacia, entonces mostrar todas las personas.
                if (newText.isEmpty()){
                    adapter.cargar(listaInicial);
                }else {
                    // Si contiene algo, buscar todas las personas que coincidan.
                    ArrayList<Persona> tempPersonas = new ArrayList<>();
                    for (Persona p : listaInicial) {
                        if (p.getNombre().toLowerCase().contains(newText.toLowerCase())) {
                            tempPersonas.add(p);
                        }
                    }
                    adapter.cargar(tempPersonas);
                }
                // Notificar al adaptador que los datos han cambiado.
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Se puede expandir para mas opciones, por ahora solo existe ordenamiento por nombre.
        switch(item.getItemId()){
            case R.id.item11_nombre_asc:
                Toast.makeText(getApplicationContext(), "Ordenando...", Toast.LENGTH_SHORT).show();
                adapter.ordenarPorNombre(true);
                adapter.notifyDataSetChanged();
                break;
            case R.id.item11_nombre_desc:
                Toast.makeText(getApplicationContext(), "Ordenando...", Toast.LENGTH_SHORT).show();
                adapter.ordenarPorNombre(false);
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Lee el archivo y retorna un array de jsons.
     * @return Un array de jsons.
     */
    private JSONArray getJsons(){
        String json;
        InputStream istream = null;
        JSONArray jArray = null;
        try {
            istream = getAssets().open("ucn.json");
            int size = istream.available();
            byte[] buffer = new byte[size];
            istream.read(buffer);
            istream.close();

            json = new String(buffer, "UTF-8");
            jArray = new JSONArray(json);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jArray;
    }


}
