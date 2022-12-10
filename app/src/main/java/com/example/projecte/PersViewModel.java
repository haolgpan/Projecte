package com.example.projecte;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PersViewModel extends AndroidViewModel {

    PersonajeList personajeList;

    MutableLiveData<List<Personaje>> listPersonajesMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Personaje> personajeSeleccionado = new MutableLiveData<>();

    public PersViewModel(@NonNull Application application) {
        super(application);

        personajeList = new PersonajeList();

        listPersonajesMutableLiveData.setValue(personajeList.obtener());
    }

    MutableLiveData<List<Personaje>> obtener(){
        return listPersonajesMutableLiveData;
    }

    void insertar(Personaje personaje){
        personajeList.insertar(personaje, new PersonajeList.Callback() {
            @Override
            public void cuandoFinalice(List<Personaje> personajes) {
                listPersonajesMutableLiveData.setValue(personajes);
            }
        });
    }

    void eliminar(Personaje personaje){
        personajeList.eliminar(personaje, new PersonajeList.Callback() {
            @Override
            public void cuandoFinalice(List<Personaje> personajes) {
                listPersonajesMutableLiveData.setValue(personajes);
            }
        });
    }


    void seleccionar(Personaje personaje){
        personajeSeleccionado.setValue(personaje);
    }

    MutableLiveData<Personaje> seleccionado(){
        return personajeSeleccionado;
    }

}
