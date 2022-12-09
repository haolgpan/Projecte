package com.example.projecte;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

@Database(entities = { Personaje.class }, version = 1, exportSchema = false)
public abstract class PersonajesBaseDeDatos extends RoomDatabase {

    public abstract PersonajesDao obtenerPersonajesDao();
    @Dao
    interface PersonajesDao {
        @Query("SELECT * FROM Personaje")
        LiveData<List<Personaje>> obtener();
        @Query("SELECT * FROM Personaje ORDER BY votacion DESC")
        LiveData<List<Personaje>> masVotacion();

        @Query("SELECT * FROM Personaje WHERE nombre LIKE '%' || :t || '%'")
        LiveData<List<Personaje>> buscar(String t);

        @Insert
        void insertar(Personaje personaje);

        @Update
        void actualizar(Personaje personaje);

        @Delete
        void eliminar(Personaje personaje);
    }

    private static volatile PersonajesBaseDeDatos INSTANCIA;

    static PersonajesBaseDeDatos obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (PersonajesBaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                                    PersonajesBaseDeDatos.class, "personajes.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCIA;
    }
}

