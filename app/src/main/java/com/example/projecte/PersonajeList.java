package com.example.projecte;

import java.util.ArrayList;
import java.util.List;

public class PersonajeList {
    List<Personaje> personajes = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Personaje> personajes);
    }

    void PersonajesRepositorio(){
        personajes.add(new Personaje(R.drawable.ainz,"Ainz Ooal Gown", "Ainz Ooal Gown (アインズ・ウール・ゴウン), formerly known as Momonga (モモンガ), is the main protagonist of the Overlord series. He is the guildmaster of Ainz Ooal Gown, Overlord of the Great Tomb of Nazarick, and the creator of Pandora's Actor."));
        personajes.add(new Personaje(R.drawable.albeldo, "Albeldo", "Albedo (アルベド) is the Overseer of the Guardians of the Great Tomb of Nazarick. She is in charge of the general management and supervision of the activities done by the seven Floor Guardians, meaning that she ranks above the other NPCs in Nazarick."));
        personajes.add(new Personaje(R.drawable.aura,"Aura Bella Fiora","Aura Bella Fiora (アウラ・ベラ・フィオーラ) is a dark elf and one of the twin Floor Guardians on the 6th Floor in the Great Tomb of Nazarick."));
        personajes.add(new Personaje(R.drawable.cocytus,"Cocytus","Cocytus (コキュートス) is a Floor Guardian of the 5th Floor in the Great Tomb of Nazarick. He is the creation of Warrior Takemikazuchi. "));
        personajes.add(new Personaje(R.drawable.demiurge,"Demiurge","Demiurge (デミウルゴス) is the Floor Guardian of the 7th Floor of the Great Tomb of Nazarick and the Commander of the NPC defenses. He is the creation of Ulbert Alain Odle. "));
        personajes.add(new Personaje(R.drawable.gargantua,"Gargantua","Gargantua (ガルガンチュア) is the 4th Floor Guardian of the Great Tomb of Nazarick. Unlike the other Guardians, it is not a Custom NPC but a bonus won by Ainz Ooal Gown. "));
        personajes.add(new Personaje(R.drawable.victim,"Victim","Gargantua (ガルガンチュア) is the 4th Floor Guardian of the Great Tomb of Nazarick. Unlike the other Guardians, it is not a Custom NPC but a bonus won by Ainz Ooal Gown. "));
        personajes.add(new Personaje(R.drawable.mare,"Mare Bello Fiore","Mare Bello Fiore (マーレ・ベロ・フィオーレ) is a dark elf and one of the twin Floor Guardians on the 6th Floor of the Great Tomb of Nazarick. Mare is the younger twin brother of Aura Bella Fiora. Alongside his sister, he was created by Bukubukuchagama."));
        personajes.add(new Personaje(R.drawable.pandora,"Pandora's Actor","Pandora's Actor (パンドラズ・アクター) is an Area Guardian of the Treasury and the financial manager of Nazarick. He is responsible for the upkeep of Nazarick and Ainz Ooal Gown. He was created by Momonga himself. "));
        personajes.add(new Personaje(R.drawable.shalltear,"Shalltear Bloodfallen","Shalltear Bloodfallen (シャルティア・ブラッドフォールン) is a true vampire and the Floor Guardian of the first to third floors in the Great Tomb of Nazarick. She was created by Peroroncino. "));
    }

    List<Personaje> obtener() {
        return personajes;
    }

    void insertar(Personaje personaje, Callback callback){
        personajes.add(personaje);
        callback.cuandoFinalice(personajes);
    }

    void eliminar(Personaje personaje, Callback callback) {
        personajes.remove(personaje);
        callback.cuandoFinalice(personajes);
    }

}
