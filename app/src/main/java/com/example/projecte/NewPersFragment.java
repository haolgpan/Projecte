package com.example.projecte;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projecte.databinding.FragmentNewPersBinding;


public class NewPersFragment extends Fragment {
    private FragmentNewPersBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNewPersBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PersViewModel elementosViewModel = new ViewModelProvider(requireActivity()).get(PersViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();
                int imagen = R.drawable.defecto;
                elementosViewModel.insertar(new Personaje(imagen, nombre, descripcion));

                navController.popBackStack();
            }
        });
    }
}