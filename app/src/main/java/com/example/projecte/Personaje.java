package com.example.projecte;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Personaje {
    @PrimaryKey(autoGenerate = true)
    int id;
    int imagen;
    String nombre;
    float votacion;
    String descripcion;

    public Personaje(int imagen,String nombre, String descripcion) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}

