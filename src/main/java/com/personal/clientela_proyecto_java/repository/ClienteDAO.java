package com.personal.clientela_proyecto_java.repository;

import com.personal.clientela_proyecto_java.model.Cliente;
import java.util.List;

public interface ClienteDAO {
    void guardar(Cliente cliente);

    void actualizar(Cliente cliente);

    void eliminar(int id);

    Cliente obtenerPorId(int id);

    List<Cliente> listarTodos();
}
