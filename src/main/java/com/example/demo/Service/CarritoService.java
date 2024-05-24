package com.example.demo.Service;
import com.example.demo.Entity.Carrito;
import java.util.List;

public interface CarritoService {
    List<Carrito> obtenerCarritos();
    Carrito buscarCarrito(Long id);
    Carrito crearCarrito(Carrito carrito);
    Carrito actualizarCarrito(Carrito carrito);
    void eliminarCarrito(Long id);
}
