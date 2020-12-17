package com.example.project_easy_recipe.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.project_easy_recipe.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeDB extends SQLiteOpenHelper {
    private static final String Nombre_BD = "recipe.bd";
    private static final int VERSION_BD = 1;
    private static final String TABLA_RECETAS = "CREATE TABLE RECETAS (ID INT PRIMARY KEY, IMAGEN TEXT, NOMBRE TEXT)";

    public RecipeDB(@Nullable Context context) {
        super(context, Nombre_BD, null, VERSION_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_RECETAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLA_RECETAS + "");
        sqLiteDatabase.execSQL(TABLA_RECETAS);
    }

    public void agregarDatos(String imagen, String nombre, int id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("INSERT INTO RECETAS VALUES ('" + id + "','" + imagen + "','" + nombre + "')");
            bd.close();
        }
    }

    public List<Recipe> mostrarDatos() {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM RECETAS", null);
        List<Recipe> listas = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                listas.add(new Recipe(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return listas;
    }

    public void eliminarDatos(int id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("DELETE FROM RECETAS WHERE ID='" + id + "'");
            bd.close();
        }
    }
}
