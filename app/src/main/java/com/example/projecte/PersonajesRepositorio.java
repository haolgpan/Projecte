package com.example.projecte;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PersonajesRepositorio {

    PersonajesBaseDeDatos.PersonajesDao personajesDao;

    LiveData<List<Personaje>> masVotacion() {
        return personajesDao.masVotacion();
    }

    LiveData<List<Personaje>> buscar(String t) {
        return personajesDao.buscar(t);
    }

    PersonajesRepositorio(Application application){
        personajesDao = PersonajesBaseDeDatos.obtenerInstancia(application).obtenerPersonajesDao();
    }
    Executor executor = Executors.newSingleThreadExecutor();


    PersonajesRepositorio(){
        personajesDao.insertar(new Personaje(R.drawable.ainz,"Ainz Ooal Gown", "Ainz Ooal Gown (アインズ・ウール・ゴウン), formerly known as Momonga (モモンガ), is the main protagonist of the Overlord series. He is the guildmaster of Ainz Ooal Gown, Overlord of the Great Tomb of Nazarick, and the creator of Pandora's Actor."));
        personajesDao.insertar(new Personaje(R.drawable.albeldo, "Albeldo", "Albedo (アルベド) is the Overseer of the Guardians of the Great Tomb of Nazarick. She is in charge of the general management and supervision of the activities done by the seven Floor Guardians, meaning that she ranks above the other NPCs in Nazarick."));
        personajesDao.insertar(new Personaje(R.drawable.aura,"Aura Bella Fiora","Aura Bella Fiora (アウラ・ベラ・フィオーラ) is a dark elf and one of the twin Floor Guardians on the 6th Floor in the Great Tomb of Nazarick."));
        personajesDao.insertar(new Personaje(R.drawable.cocytus,"Cocytus","Cocytus (コキュートス) is a Floor Guardian of the 5th Floor in the Great Tomb of Nazarick. He is the creation of Warrior Takemikazuchi. "));
        personajesDao.insertar(new Personaje(R.drawable.demiurge,"Demiurge","Demiurge (デミウルゴス) is the Floor Guardian of the 7th Floor of the Great Tomb of Nazarick and the Commander of the NPC defenses. He is the creation of Ulbert Alain Odle. "));
        personajesDao.insertar(new Personaje(R.drawable.gargantua,"Gargantua","Gargantua (ガルガンチュア) is the 4th Floor Guardian of the Great Tomb of Nazarick. Unlike the other Guardians, it is not a Custom NPC but a bonus won by Ainz Ooal Gown. "));
        personajesDao.insertar(new Personaje(R.drawable.victim,"Victim","Gargantua (ガルガンチュア) is the 4th Floor Guardian of the Great Tomb of Nazarick. Unlike the other Guardians, it is not a Custom NPC but a bonus won by Ainz Ooal Gown. "));
        personajesDao.insertar(new Personaje(R.drawable.mare,"Mare Bello Fiore","Mare Bello Fiore (マーレ・ベロ・フィオーレ) is a dark elf and one of the twin Floor Guardians on the 6th Floor of the Great Tomb of Nazarick. Mare is the younger twin brother of Aura Bella Fiora. Alongside his sister, he was created by Bukubukuchagama."));
        personajesDao.insertar(new Personaje(R.drawable.pandora,"Pandora's Actor","Pandora's Actor (パンドラズ・アクター) is an Area Guardian of the Treasury and the financial manager of Nazarick. He is responsible for the upkeep of Nazarick and Ainz Ooal Gown. He was created by Momonga himself. "));
        personajesDao.insertar(new Personaje(R.drawable.shalltear,"Shalltear Bloodfallen","Shalltear Bloodfallen (シャルティア・ブラッドフォールン) is a true vampire and the Floor Guardian of the first to third floors in the Great Tomb of Nazarick. She was created by Peroroncino. "));
    }

    LiveData<List<Personaje>> obtener() {
        return personajesDao.obtener();
    }

    void insertar(Personaje personaje){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                personajesDao.insertar(personaje);
            }
        });
    }


    void eliminar(Personaje personaje) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                personajesDao.eliminar(personaje);
            }
        });
    }

    public void actualizar(Personaje personaje, float votacion) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                personaje.votacion = votacion;
                personajesDao.actualizar(personaje);
            }
        });
    }
}
