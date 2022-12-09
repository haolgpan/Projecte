package com.example.projecte;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecyclerBusquedaFragment extends RecyclerPersonajesFragment{
    @Override
    LiveData<List<Personaje>> obtenerPersonajes() {
        return personajesViewModel.buscar();
    }
}
