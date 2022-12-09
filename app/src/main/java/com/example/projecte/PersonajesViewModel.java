package com.example.projecte;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

public class PersonajesViewModel extends AndroidViewModel {

    PersonajesRepositorio personajesRepositorio;
    MutableLiveData<Personaje> personajeSeleccionado = new MutableLiveData<>();
    LiveData<List<Personaje>> masVotacion(){
        return personajesRepositorio.masVotacion();
    }
    MutableLiveData<String> terminoBusqueda = new MutableLiveData<>();

    LiveData<List<Personaje>> resultadoBusqueda = Transformations.switchMap(terminoBusqueda, new Function<String, LiveData<List<Personaje>>>() {
        @Override
        public LiveData<List<Personaje>> apply(String input) {
            return personajesRepositorio.buscar(input);
        }
    });
    LiveData<List<Personaje>> buscar(){
        return resultadoBusqueda;
    }

    void establecerTerminoBusqueda(String t){
        terminoBusqueda.setValue(t);
    }

    public PersonajesViewModel(@NonNull Application application) {
        super(application);
        personajesRepositorio = new PersonajesRepositorio(application);
    }

    LiveData<List<Personaje>> obtener(){
        return personajesRepositorio.obtener();
    }
    void insertar(Personaje personaje){
        personajesRepositorio.insertar(personaje);
    }

    void eliminar(Personaje personaje){
        personajesRepositorio.eliminar(personaje);
    }

    void actualizar(Personaje personaje, float votacion){
        personajesRepositorio.actualizar(personaje, votacion);
    }

    void seleccionar(Personaje personaje){
        personajeSeleccionado.setValue(personaje);
    }

    MutableLiveData<Personaje> seleccionado(){
        return personajeSeleccionado;
    }
}

