package mx.edu.itl.c171000063.appasistencias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;

import teclag.c18130597.androlib.util.permisos.ChecadorDePermisos;
import teclag.c18130597.androlib.util.permisos.PermisoApp;

public class MainActivity extends AppCompatActivity {

    private PermisoApp[] permisoReq = new PermisoApp[]{
            new PermisoApp ( Manifest.permission.READ_EXTERNAL_STORAGE, "Almacenamiento", true),
            new PermisoApp ( Manifest.permission.WRITE_EXTERNAL_STORAGE, "Almacenamiento", true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChecadorDePermisos.checarPermisos(this, permisoReq);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == ChecadorDePermisos.CODIGO_PEDIR_PERMISOS) {
            ChecadorDePermisos.verificarPermisosSolicitados (this, permisoReq, permissions, grantResults );
        }


    }
}
