package cl.ucn.disc.dsm.cafa.phodi.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

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
        ArrayList<Persona> personas = new ArrayList<>();

        personas.add(Persona.builder().nombre("Christian").cargo("Academico").telefono("96759922").build());
        personas.add(Persona.builder().nombre("Lucia").cargo("Academico").telefono("14366699").build());
        personas.add(Persona.builder().nombre("Juan").cargo("Funcionario").telefono("19192039").build());
        personas.add(Persona.builder().nombre("Pedro").cargo("Academico").telefono("23948403").build());
        personas.add(Persona.builder().nombre("Diego").cargo("Funcionario").telefono("12435333").build());
        personas.add(Persona.builder().nombre("Martin").cargo("Academico").telefono("12766788").build());
        personas.add(Persona.builder().nombre("Javier").cargo("Academico").telefono("56678904").build());
        personas.add(Persona.builder().nombre("Paul").cargo("Funcionario").telefono("24648709").build());
        personas.add(Persona.builder().nombre("David").cargo("Academico").telefono("98673486").build());
        personas.add(Persona.builder().nombre("Matias").cargo("Funcionario").telefono("99005644").build());
        personas.add(Persona.builder().nombre("Ramon").cargo("Funcionario").telefono("98086744").build());
        personas.add(Persona.builder().nombre("Yamir").cargo("Academico").telefono("23557521").build());

        // Crear el adaptador.
        adapter = new PersonaAdapter(this, personas);

        // Asignar el adaptador al listView de la main activity.
        listView = findViewById(R.id.lv_personas);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Cargando lista... ", Toast.LENGTH_LONG).show();
    }
}
