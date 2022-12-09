package com.example.projecte;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;

import com.example.projecte.databinding.FragmentMostrarPersonajeBinding;


public class MostrarPersonajeFragment extends Fragment {
    private FragmentMostrarPersonajeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarPersonajeBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PersonajesViewModel personajesViewModel = new ViewModelProvider(requireActivity()).get(PersonajesViewModel.class);

        personajesViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Personaje>() {
            @Override
            public void onChanged(Personaje personaje) {
                binding.raza.setImageResource(personaje.imagen);
                binding.nombre.setText(personaje.nombre);
                binding.descripcion.setText(personaje.descripcion);
                binding.votacion.setRating(personaje.votacion);
                binding.votacion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float votacion, boolean fromUser) {
                        if(fromUser) {
                            personajesViewModel.actualizar(personaje, votacion);
                        }
                    }
                });
            }
        });
    }
}