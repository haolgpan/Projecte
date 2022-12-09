package com.example.projecte;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecyclerVotacionFragment extends RecyclerPersonajesFragment{
    @Override
    LiveData<List<Personaje>> obtenerPersonajes(){
        return personajesViewModel.masVotacion();
    }
}
