package com.personal.clientela_proyecto_java.service;

import com.personal.clientela_proyecto_java.model.TipoCliente;
import com.personal.clientela_proyecto_java.repository.TipoClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoClienteService {

    private final TipoClienteDAO tipoClienteDAO;

    @Autowired
    public TipoClienteService(TipoClienteDAO tipoClienteDAO) {
        this.tipoClienteDAO = tipoClienteDAO;
    }

    public List<TipoCliente> listarTiposCliente() {
        return tipoClienteDAO.listarTodos();
    }
}
