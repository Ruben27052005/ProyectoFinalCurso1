package com.example.demo.Entity;

public class Juego {

    private Long id;
    private String titulo;
    private String creador;
    private Integer anyoLanzamiento;

    public Juego() {
    }

    public Juego(Long id, String titulo, String creador, Integer anyoLanzamiento) {
        this.id = id;
        this.titulo = titulo;
        this.creador = creador;
        this.anyoLanzamiento = anyoLanzamiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public Integer getAnyoLanzamiento() {
        return anyoLanzamiento;
    }

    public void setAnyoLanzamiento(Integer anyoLanzamiento) {
        this.anyoLanzamiento = anyoLanzamiento;
    }

    @Override
    public String toString() {
        return "Juego [id=" + id + ", titulo=" + titulo + ", creador=" + creador + ", anyoLanzamiento=" + anyoLanzamiento + "]";
    }
}
