package com.personal.clientela_proyecto_java.controller;

import com.personal.clientela_proyecto_java.model.Cliente;
import com.personal.clientela_proyecto_java.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Listar todos los clientes
    @GetMapping({ "", "/" })
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodosLosClientes());
        return "clientes/index";
    }

    // Mostrar formulario para nuevo cliente
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    // Guardar nuevo cliente
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/clientes";
    }

    // Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }

    // Actualizar cliente
    @PostMapping("/actualizar")
    public String actualizarCliente(@ModelAttribute Cliente cliente) {
        clienteService.actualizarCliente(cliente);
        return "redirect:/clientes";
    }

    // Eliminar cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable int id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes";
    }
}
