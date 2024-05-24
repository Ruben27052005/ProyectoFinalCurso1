package com.example.demo.Entity;

import java.util.List;
import java.util.ArrayList;

public class JuegoCarrito {
    private Long id;
    private List<Juego> juegos;

    public JuegoCarrito() {
        this.juegos = new ArrayList<>();
    }

    public JuegoCarrito(Long id) {
        this.id = id;
        this.juegos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(List<Juego> juegos) {
        this.juegos = juegos;
    }

    public void agregarJuego(Juego juego) {
        this.juegos.add(juego);
    }


    @Override
    public String toString() {
        return "JuegoCarrito [id=" + id + ", juegos=" + juegos + "]";
    }
}
