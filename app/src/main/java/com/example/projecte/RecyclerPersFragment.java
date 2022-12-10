package com.example.projecte;

import android.app.Person;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecte.databinding.FragmentRecyclerPersBinding;
import com.example.projecte.databinding.VholderPersonajeBinding;

import java.util.List;


public class RecyclerPersFragment extends Fragment {

    private FragmentRecyclerPersBinding binding;
    private PersViewModel personajesViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerPersBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        personajesViewModel = new ViewModelProvider(requireActivity()).get(PersViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANewPers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_recyclerPersFragment_to_newPersFragment);
            }
        });

        PersonajesAdapter elementosAdapter = new PersonajesAdapter();

        binding.recyclerView.setAdapter(elementosAdapter);

        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Personaje personaje = elementosAdapter.obtenerPersonaje(posicion);
                personajesViewModel.eliminar(personaje);

            }
        }).attachToRecyclerView(binding.recyclerView);

        personajesViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Personaje>>() {
            @Override
            public void onChanged(List<Personaje> personajes) {
                elementosAdapter.establecerLista(personajes);
            }
        });
    }

    class PersonajesAdapter extends RecyclerView.Adapter<PersViewHolder> {

        List<Personaje> personajes;

        @NonNull
        @Override
        public PersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PersViewHolder(VholderPersonajeBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PersViewHolder holder, int position) {

            Personaje personaje = personajes.get(position);
            holder.binding.raza.setImageResource(personaje.imagen);
            holder.binding.nombre.setText(personaje.nombre);
            holder.binding.descripcion.setText(personaje.descripcion);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    personajesViewModel.seleccionar(personaje);
                    navController.navigate(R.id.action_recyclerPersFragment_to_showPersFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return personajes != null ? personajes.size() : 0;
        }

        public void establecerLista(List<Personaje> personajes){
            this.personajes = personajes;
            notifyDataSetChanged();
        }

        public Personaje obtenerPersonaje(int posicion){
            return personajes.get(posicion);
        }
    }

    static class PersViewHolder extends RecyclerView.ViewHolder {
        private final VholderPersonajeBinding binding;

        public PersViewHolder(VholderPersonajeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}