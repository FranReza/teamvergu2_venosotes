package mx.edu.itl.c171000063.appasistencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.edu.itl.c171000063.appasistencias.db.DbHelper;
import mx.edu.itl.c171000063.appasistencias.entidades.Alumnos;

public class ListaAlumnos extends AppCompatActivity {


    ArrayList<String> listaInformacion;
    ArrayList<Alumnos> listaAlumnos;

    Button btn18, btn19;

    DbHelper conn;

    String opcion = "SELECT * FROM Alumnos";
    String opcion2 = "SELECT * FROM Alumnos";
    String opcion3 = "SELECT * FROM Alumnos";

    ListView listViewAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alumnos);

        conn = new DbHelper(getApplicationContext(), "Base de datos",null,1);

        listViewAlumnos = findViewById( R.id.listViewAlumnos );
        consultarListaAlumnos();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewAlumnos.setAdapter(adapter);

        btn18 = findViewById( R.id.button18 );
        btn19 = findViewById( R.id.button19);
        String hola = "jhon";
    }

    public void btn18click( View v ){
        Intent intent = new Intent( this, ListaAlumnos.class);
        startActivity(intent);

    }


    //VEER QEU PEDO CON ESTA MADRE
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate ( R.menu.menu_lista_alumnos, menu );
        return super.onCreateOptionsMenu(menu);
    }


        private void consultarListaAlumnos() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Alumnos alumnos = null;
        listaAlumnos = new ArrayList<Alumnos>();
        if (opcion == null){

        }else{

        }
        Cursor cursor = db.rawQuery(opcion, null );

        while ( cursor.moveToNext() ) {
            alumnos = new Alumnos();
            alumnos.setNcontrol(cursor.getString(0));
            alumnos.setNombre(cursor.getString(1));

            listaAlumnos.add(alumnos);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i = 0; i < listaAlumnos.size(); i ++) {
            listaInformacion.add(listaAlumnos.get(i).getNcontrol() + " - " + listaAlumnos.get(i).getNombre());
        }
    }
}