package com.example.projecte;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projecte.databinding.FragmentRecyclerPersonajesBinding;
import com.example.projecte.databinding.ViewholderPersonajeBinding;

import java.util.List;


public class RecyclerPersonajesFragment extends Fragment {

    private FragmentRecyclerPersonajesBinding binding;
    PersonajesViewModel personajesViewModel;
    private NavController navController;
    /*private Context context;

    public RecyclerPersonajesFragment(Context context) {
        this.context= context;
    }*/


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerPersonajesBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        personajesViewModel = new ViewModelProvider(requireActivity()).get(PersonajesViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoPersonaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nuevoPersonajeFragment);
            }
        });

        PersonajesAdapter personajesAdapter = new PersonajesAdapter();

        binding.recyclerView.setAdapter(personajesAdapter);

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
                Personaje personaje = personajesAdapter.obtenerPersonaje(posicion);
                personajesViewModel.eliminar(personaje);

            }
        }).attachToRecyclerView(binding.recyclerView);

        obtenerPersonajes().observe(getViewLifecycleOwner(), new Observer<List<Personaje>>(){
            @Override
            public void onChanged(List<Personaje> personajes) {
                personajesAdapter.establecerLista(personajes);
            }
        });
    }
    LiveData<List<Personaje>> obtenerPersonajes(){
        return personajesViewModel.obtener();
    }

    class PersonajesAdapter extends RecyclerView.Adapter<PersonajeViewHolder> {

        List<Personaje> personajes;

        @NonNull
        @Override
        public PersonajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PersonajeViewHolder(ViewholderPersonajeBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PersonajeViewHolder holder, int position) {
            Personaje personaje = personajes.get(position);
            //holder.binding.raza.setImageDrawable();
            holder.binding.raza.setImageResource(personaje.imagen);
            holder.binding.nombre.setText(personaje.nombre);
            holder.binding.descripcion.setText(personaje.descripcion);
            holder.binding.votacion.setRating(personaje.votacion);
            holder.binding.votacion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float votacion, boolean fromUser) {
                    if (fromUser) {
                        personajesViewModel.actualizar(personaje, votacion);
                    }
                }

            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    personajesViewModel.seleccionar(personaje);
                    navController.navigate(R.id.action_mostrarPersonajeFragment);
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

    class PersonajeViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPersonajeBinding binding;

        public PersonajeViewHolder(ViewholderPersonajeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        //
    }
}