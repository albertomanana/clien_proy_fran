package com.personal.clientela_proyecto_java.service;

import com.personal.clientela_proyecto_java.model.Cliente;
import com.personal.clientela_proyecto_java.repository.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteService(@Qualifier("clienteDAOEnMemoria") ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void guardarCliente(Cliente cliente) {
        clienteDAO.guardar(cliente);
    }

    public void actualizarCliente(Cliente cliente) {
        clienteDAO.actualizar(cliente);
    }

    public void eliminarCliente(int id) {
        clienteDAO.eliminar(id);
    }

    public Cliente obtenerClientePorId(int id) {
        return clienteDAO.obtenerPorId(id);
    }

    public List<Cliente> listarTodosLosClientes() {
        return clienteDAO.listarTodos();
    }
}
