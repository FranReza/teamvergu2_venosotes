package mx.edu.itl.c171000063.appasistencias.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import mx.edu.itl.c171000063.appasistencias.entidades.Alumnos;

public class dbHelper extends SQLiteOpenHelper {

    String TABLA_ALUMNOS = "create table Alumnos(Ncontrol text Primary Key, Nombre text)";
    String TABLA_ASISTENCIA = "create table Asistencia(Ncontrol text Primary Key, Nombre text, Aistencia text)";

    public dbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_ALUMNOS);
        db.execSQL(TABLA_ASISTENCIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
