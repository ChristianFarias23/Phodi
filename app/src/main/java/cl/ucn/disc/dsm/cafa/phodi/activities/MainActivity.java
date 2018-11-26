package cl.ucn.disc.dsm.cafa.phodi.activities;

import android.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import android.support.design.widget.Snackbar;

import cl.ucn.disc.dsm.cafa.phodi.R;
import cl.ucn.disc.dsm.cafa.phodi.adapters.PersonaAdapter;
import cl.ucn.disc.dsm.cafa.phodi.models.Persona;

public class MainActivity extends AppCompatActivity {
    // TODO: Implementar lectura de JSON, Optimizar MainActivity (onCreate), Utilizar fuente Myriad.

    /**
     * El listView que muestra a las personas.
     */
    private ListView listView;

    /**
     * El adaptador que 'convierte' personas a views en la listView.
     */
    private PersonaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Obtener las personas (Ejemplo):
        // TODO: Asignar la obtencion de personas a otra clase.

        // Crear el adaptador.
        if (this.adapter == null)
            this.adapter = new PersonaAdapter(this, Persona.fromJson(getJsons()));

        // Asignar el adaptador al listView de la main activity.
        listView = findViewById(R.id.lv_personas);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.adapter != null && this.adapter.isEmpty()){
            Toast.makeText(getApplicationContext(), "Cargando lista... ", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mas, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item11_por_id:
                Toast.makeText(getApplicationContext(), "Ordenando por ID... ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item11_por_nombre:
                Toast.makeText(getApplicationContext(), "Ordenando por Nombre... ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item11_por_email:
                Toast.makeText(getApplicationContext(), "Ordenando por Email... ", Toast.LENGTH_SHORT).show();
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
