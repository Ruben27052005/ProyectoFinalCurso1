package com.example.demo.Service;
import com.example.demo.Entity.Juego;
import java.util.List;

public interface JuegoService {
    List<Juego> obtenerJuegos();
    Juego buscarJuego(Long id);
    Juego crearJuego(Juego juego);
    Juego actualizarJuego(Juego juego);
    void eliminarJuego(Long id);
}
