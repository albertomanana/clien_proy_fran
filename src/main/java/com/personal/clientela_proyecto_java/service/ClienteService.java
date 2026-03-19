package com.personal.clientela_proyecto_java.service;

import com.personal.clientela_proyecto_java.model.Cliente;
import com.personal.clientela_proyecto_java.repository.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de la lógica de negocio para la gestión de clientes.
 * Actúa como intermediario entre el controlador y la capa de persistencia
 * (DAO).
 */
@Service
public class ClienteService {

    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteService(@Qualifier("clienteDAOJdbc") ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    /**
     * Guarda un nuevo cliente utilizando el DAO correspondiente.
     */
    public void guardarCliente(Cliente cliente) {
        clienteDAO.guardar(cliente);
    }

    /**
     * Actualiza un cliente existente con los nuevos datos proporcionados.
     */
    public void actualizarCliente(Cliente cliente) {
        clienteDAO.actualizar(cliente);
    }

    /**
     * Elimina a un cliente de forma definitiva según su ID.
     */
    public void eliminarCliente(int id) {
        clienteDAO.eliminar(id);
    }

    /**
     * Busca y retorna la información de un cliente específico por ID.
     */
    public Cliente obtenerClientePorId(int id) {
        return clienteDAO.obtenerPorId(id);
    }

    /**
     * Retorna todos los clientes registrados en el sistema.
     */
    public List<Cliente> listarTodosLosClientes() {
        return clienteDAO.listarTodos();
    }
}
