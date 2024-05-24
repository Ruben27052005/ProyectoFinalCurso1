package com.example.demo.Service;

import com.example.demo.Entity.Juego;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JuegoServiceImpl implements JuegoService {

    private final List<Juego> juegos = new ArrayList<>();

    @Override
    public List<Juego> obtenerJuegos() {
        return new ArrayList<>(juegos);
    }

    @Override
    public Juego buscarJuego(Long id) {
        Optional<Juego> juego = juegos.stream().filter(j -> j.getId().equals(id)).findFirst();
        return juego.orElse(null);
    }

    @Override
    public Juego crearJuego(Juego juego) {
        juegos.add(juego);
        return juego;
    }

    @Override
    public Juego actualizarJuego(Juego juego) {
        Juego existingJuego = buscarJuego(juego.getId());
        if (existingJuego != null) {
            existingJuego.setTitulo(juego.getTitulo());
            existingJuego.setCreador(juego.getCreador());
            existingJuego.setAnyoLanzamiento(juego.getAnyoLanzamiento());
        }
        return existingJuego;
    }

    @Override
    public void eliminarJuego(Long id) {
        juegos.removeIf(j -> j.getId().equals(id));
    }
}
