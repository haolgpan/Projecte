package com.example.projecte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.projecte.databinding.FragmentAtaquesBinding;

public class AtaquesFragment extends Fragment {
    private FragmentAtaquesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentAtaquesBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AtackViewModel atackViewModel = new ViewModelProvider(this).get(AtackViewModel.class);

        atackViewModel.obtainAtack().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer atack) {
                Glide.with(AtaquesFragment.this).load(atack).into(binding.atack);
            }
        });

        atackViewModel.obtainReapeat().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String repeticion) {
                if(repeticion.equals("Character Change")){
                    binding.change.setVisibility(View.VISIBLE);
                } else {
                    binding.change.setVisibility(View.GONE);
                }
                binding.repeticion.setText(repeticion);
            }
        });
    }
}