package com.example.demo.Entity;

public class Carrito {

    private Long id;
    private Double precioTotal;

    public Carrito() {
    }

    public Carrito(Long id, Double precioTotal) {
        this.id = id;
        this.precioTotal = precioTotal;
    }

    public Carrito(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Override
    public String toString() {
        return "Carrito [id=" + id + ", precioTotal=" + precioTotal + "]";
    }
}
