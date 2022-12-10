package com.example.projecte;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class AtackViewModel extends AndroidViewModel {
    Atack atacks;

    LiveData<Integer> atackLiveData;
    LiveData<String> repeatAtackLiveData;

    public AtackViewModel(@NonNull Application application) {
        super(application);

        atacks = new Atack();

        atackLiveData = Transformations.switchMap(atacks.ordenLiveData, new Function<String, LiveData<Integer>>() {

            String atackAnterior;

            @Override
            public MutableLiveData apply(String orden) {

                String atack = orden.split(":")[0];

                if(!atack.equals(atackAnterior)){
                    atackAnterior = atack;
                    int imagen;
                    String s;
                    switch (atack) {
                        case "A1":
                        default:
                            imagen = R.drawable.ainzbh;
                            break;
                        case "A2":
                            imagen = R.drawable.albeldoatack;
                            break;
                        case "A3":
                            imagen = R.drawable.auraat;
                            break;
                        case "A4":
                            imagen = R.drawable.cocytusat;
                            break;
                        case "A5":
                            imagen = R.drawable.demiurgeat;
                            break;
                        case "A6":
                            imagen = R.drawable.mareat;
                            break;
                        case "A7":
                            imagen = R.drawable.pandoraat;
                            break;
                        case "A8":
                            imagen = R.drawable.shalltearat;
                            break;
                    }

                    return new MutableLiveData<>(imagen);
                }
                return null;
            }
        });

        repeatAtackLiveData = Transformations.switchMap(atacks.ordenLiveData, new Function<String, LiveData<String>>() {
            @Override
            public LiveData<String> apply(String orden) {
                return new MutableLiveData<>(orden.split(":")[1]);
            }
        });
    }

    LiveData<Integer> obtainAtack(){
        return atackLiveData;
    }

    LiveData<String> obtainReapeat(){
        return repeatAtackLiveData;
    }
}
