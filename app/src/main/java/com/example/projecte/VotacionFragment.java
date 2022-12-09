package com.example.projecte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import android.view.View;

import com.example.projecte.databinding.FragmentVotacionBinding;

public class VotacionFragment extends AppCompatActivity {

    FragmentVotacionBinding binding;
    private PersonajesViewModel personajesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = FragmentVotacionBinding.inflate(getLayoutInflater())).getRoot());
        personajesViewModel = new ViewModelProvider(this).get(PersonajesViewModel.class);
        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_recycler_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView,navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.nuevoPersonajeFragment
                        || destination.getId() == R.id.mostrarPersonajeFragment) {
                    binding.bottomNavView.setVisibility(View.GONE);
                } else {
                    binding.bottomNavView.setVisibility(View.VISIBLE);
                }

                if (destination.getId() == R.id.recyclerBuscarFragment){
                    binding.searchView.setVisibility(View.VISIBLE);
                    binding.searchView.setIconified(false);
                    binding.searchView.requestFocusFromTouch();
                } else {
                    binding.searchView.setVisibility(View.GONE);
                }
            }
        });
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                personajesViewModel.establecerTerminoBusqueda(newText);
                return false;
            }
        });
    }
}