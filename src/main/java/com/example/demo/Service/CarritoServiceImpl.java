package com.example.demo.Service;
import com.example.demo.Entity.Carrito;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements CarritoService {

    private final List<Carrito> carritos = new ArrayList<>();

    @Override
    public List<Carrito> obtenerCarritos() {
        return new ArrayList<>(carritos);
    }

    @Override
    public Carrito buscarCarrito(Long id) {
        Optional<Carrito> carrito = carritos.stream().filter(c -> c.getId().equals(id)).findFirst();
        return carrito.orElse(null);
    }

    @Override
    public Carrito crearCarrito(Carrito carrito) {
        carritos.add(carrito);
        return carrito;
    }

    @Override
    public Carrito actualizarCarrito(Carrito carrito) {
        Carrito existingCarrito = buscarCarrito(carrito.getId());
        if (existingCarrito != null) {
            existingCarrito.setPrecioTotal(carrito.getPrecioTotal());
        }
        return existingCarrito;
    }

    @Override
    public void eliminarCarrito(Long id) {
        carritos.removeIf(c -> c.getId().equals(id));
    }
}

