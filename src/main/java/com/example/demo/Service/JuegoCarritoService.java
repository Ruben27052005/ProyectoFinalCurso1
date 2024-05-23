package com.example.demo.Service;
import com.example.demo.Entity.JuegoCarrito;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JuegoCarritoService {
    private List<JuegoCarrito> carritos;

    public JuegoCarritoService() {
        this.carritos = new ArrayList<>();
    }

    public List<JuegoCarrito> obtenerJuegoCarritos() {
        return carritos;
    }

    public JuegoCarrito obtenerJuegoCarritoPorId(Long id) {
        for (JuegoCarrito carrito : carritos) {
            if (carrito.getId().equals(id)) {
                return carrito;
            }
        }
        return null;
    }

    public void agregarJuegoCarrito(JuegoCarrito juegoCarrito) {
        carritos.add(juegoCarrito);
    }

    public void eliminarJuegoCarrito(Long id) {
        JuegoCarrito carritoAEliminar = null;
        for (JuegoCarrito carrito : carritos) {
            if (carrito.getId().equals(id)) {
                carritoAEliminar = carrito;
                break;
            }
        }
        if (carritoAEliminar != null) {
            carritos.remove(carritoAEliminar);
        }
    }

    public void actualizarJuegoCarrito(JuegoCarrito juegoCarrito) {
        for (int i = 0; i < carritos.size(); i++) {
            JuegoCarrito carrito = carritos.get(i);
            if (carrito.getId().equals(juegoCarrito.getId())) {
                carritos.set(i, juegoCarrito);
                break;
            }
        }
    }

    public Object obtenerJuegoCarrito() {
        return carritos;
    }
}
