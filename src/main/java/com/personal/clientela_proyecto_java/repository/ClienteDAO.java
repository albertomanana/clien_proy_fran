package com.personal.clientela_proyecto_java.repository;

import com.personal.clientela_proyecto_java.model.Cliente;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad Cliente.
 */
public interface ClienteDAO {

    /** Guarda un nuevo cliente en la base de datos o memoria. */
    void guardar(Cliente cliente);

    /** Actualiza los datos de un cliente existente. */
    void actualizar(Cliente cliente);

    /** Elimina un cliente según su número de identificación. */
    void eliminar(int id);

    /** Obtiene un cliente específico buscando por su ID. */
    Cliente obtenerPorId(int id);

    /** Devuelve la lista completa de todos los clientes registrados. */
    List<Cliente> listarTodos();
}
