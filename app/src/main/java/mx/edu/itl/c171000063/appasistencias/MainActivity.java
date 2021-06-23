package mx.edu.itl.c171000063.appasistencias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import mx.edu.itl.c171000063.appasistencias.db.DbHelper;
import teclag.c18130597.androlib.util.permisos.ChecadorDePermisos;
import teclag.c18130597.androlib.util.permisos.PermisoApp;

public class MainActivity extends AppCompatActivity {

    Button btnCrear;

    private PermisoApp[] permisoReq = new PermisoApp[]{
            new PermisoApp ( Manifest.permission.READ_EXTERNAL_STORAGE, "Almacenamiento", true),
            new PermisoApp ( Manifest.permission.WRITE_EXTERNAL_STORAGE, "Almacenamiento", true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChecadorDePermisos.checarPermisos(this, permisoReq);

        btnCrear = findViewById( R.id.Crear );

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inicializar();
            }
        });
    }

    private void Inicializar(){
        if(cantidadRegistros() == 0){
            String[] texto = leerArchivo();
            DbHelper dbHelper = new DbHelper(this, "Base de datos", null, 1);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.beginTransaction();

            for (int i = 0; i < texto.length; i++ ){
                String[] linea = texto[i].split(",");
                ContentValues contentValues = new ContentValues();
                contentValues.put("Ncontrol", linea[0]);
                contentValues.put("Nombre", linea[1]);
                db.insert("Alumnos", null, contentValues);
            }
            Toast.makeText(this, "Registros insertados " + texto.length, Toast.LENGTH_LONG).show();
            db.setTransactionSuccessful();;
            db.endTransaction();
        }else{
            Toast.makeText(this, "La tabla ya esta creada ", Toast.LENGTH_LONG).show();
        }
    }

    //CUENTA LA CNATIDAD DE REGISTROS EN LA TABLA
    private long cantidadRegistros(){
        DbHelper dbHelper = new DbHelper(this, "Base de datos", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long cn = DatabaseUtils.queryNumEntries(db,"Alumnos");
        db.close();
        return cn;
    }

    private String[] leerArchivo(){
        InputStream inputStream = getResources().openRawResource(R.raw.alumnos);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            int i = inputStream.read();
            while (i != -1){
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString().split("\n");
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == ChecadorDePermisos.CODIGO_PEDIR_PERMISOS) {
            ChecadorDePermisos.verificarPermisosSolicitados (this, permisoReq, permissions, grantResults );
        }


    }

    public void btnListaAlu( View v ) {
        Intent intent = new Intent( this, ListaAlumnos.class);
        startActivity(intent);
    }



}
