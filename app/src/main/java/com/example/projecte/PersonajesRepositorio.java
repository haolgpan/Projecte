package com.example.projecte;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PersonajesRepositorio {

    PersonajesBaseDeDatos.PersonajesDao personajesDao;

    LiveData<List<Personaje>> masVotacion() {
        return personajesDao.masVotacion();
    }

    LiveData<List<Personaje>> buscar(String t) {
        return personajesDao.buscar(t);
    }

    PersonajesRepositorio(Application application){
        personajesDao = PersonajesBaseDeDatos.obtenerInstancia(application).obtenerPersonajesDao();
    }
    Executor executor = Executors.newSingleThreadExecutor();

    LiveData<List<Personaje>> obtener() {
        return personajesDao.obtener();
    }

    void insertar(Personaje personaje){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                personajesDao.insertar(personaje);
            }
        });
    }


    void eliminar(Personaje personaje) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                personajesDao.eliminar(personaje);
            }
        });
    }

    public void actualizar(Personaje personaje, float votacion) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                personaje.votacion = votacion;
                personajesDao.actualizar(personaje);
            }
        });
    }
}
