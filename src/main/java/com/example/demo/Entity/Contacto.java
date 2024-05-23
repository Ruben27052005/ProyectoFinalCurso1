package com.example.demo.Entity;
import java.util.ArrayList;
import java.util.List;

public class Contacto {
    private static List<Contacto> contactos = new ArrayList<>();
    private static long nextId = 1;

    private Long id;
    private String nombre;
    private String telefono;
    private String email;

    // Constructor vacío
    public Contacto() {
    }

    // Constructor con todos los campos excepto id
    public Contacto(String nombre, String telefono, String email) {
        this.id = nextId++;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    // Métodos CRUD

    // Crear un nuevo contacto
    public static Contacto crearContacto(String nombre, String telefono, String email) {
        Contacto nuevoContacto = new Contacto(nombre, telefono, email);
        contactos.add(nuevoContacto);
        return nuevoContacto;
    }

    // Leer todos los contactos
    public static List<Contacto> obtenerContactos() {
        return contactos;
    }

    // Leer un contacto por su ID
    public static Contacto buscarContacto(Long id) {
        for (Contacto contacto : contactos) {
            if (contacto.getId().equals(id)) {
                return contacto;
            }
        }
        return null;
    }

    // Actualizar un contacto
    public static void actualizarContacto(Contacto contacto) {
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getId().equals(contacto.getId())) {
                contactos.set(i, contacto);
                return;
            }
        }
    }

    // Eliminar un contacto por su ID
    public static void eliminarContacto(Long id) {
        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getId().equals(id)) {
                contactos.remove(i);
                return;
            }
        }
    }

    // Getters y setters (métodos de acceso)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
