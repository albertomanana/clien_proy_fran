package com.personal.clientela_proyecto_java.controller;

import com.personal.clientela_proyecto_java.model.Cliente;
import com.personal.clientela_proyecto_java.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
    public String guardarCliente(@ModelAttribute Cliente cliente, @RequestParam("fotoFile") MultipartFile fotoFile) {
        if (fotoFile != null && !fotoFile.isEmpty()) {
            String fileName = guardarArchivo(fotoFile);
            if (fileName != null) {
                cliente.setFotoPath("uploads/" + fileName);
            }
        }

        // Calcular el balance total antes de guardar (mismo peso que el profesor)
        cliente.setBalanceTotal(cliente.calcularBalance());

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
    public String actualizarCliente(@ModelAttribute Cliente cliente, @RequestParam("fotoFile") MultipartFile fotoFile) {
        Cliente clienteActual = clienteService.obtenerClientePorId(cliente.getId());

        if (fotoFile != null && !fotoFile.isEmpty()) {
            // Si hay una foto nueva, borrar la anterior si existe
            if (clienteActual.getFotoPath() != null && !clienteActual.getFotoPath().isEmpty()) {
                borrarArchivoFisico(clienteActual.getFotoPath());
            }
            String fileName = guardarArchivo(fotoFile);
            if (fileName != null) {
                cliente.setFotoPath("uploads/" + fileName);
            }
        } else {
            // Si no se sube foto, conservar la anterior
            cliente.setFotoPath(clienteActual.getFotoPath());
        }

        // Calcular el balance total antes de actualizar
        cliente.setBalanceTotal(cliente.calcularBalance());

        clienteService.actualizarCliente(cliente);
        return "redirect:/clientes";
    }

    // Suprimir solo la foto
    @GetMapping("/eliminar-foto/{id}")
    public String eliminarFoto(@PathVariable int id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente != null && cliente.getFotoPath() != null && !cliente.getFotoPath().isEmpty()) {
            borrarArchivoFisico(cliente.getFotoPath());
            cliente.setFotoPath(null);
            clienteService.actualizarCliente(cliente);
        }
        return "redirect:/clientes/editar/" + id;
    }

    private void borrarArchivoFisico(String fotoPath) {
        try {
            // Eliminar el prefijo "uploads/" para obtener el nombre del archivo si es
            // necesario
            // En este caso, el fotoPath es "uploads/filename.ext"
            String fileName = fotoPath.replace("uploads/", "");
            Path filePath = Paths.get(System.getProperty("user.dir") + "/uploads/").resolve(fileName);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String guardarArchivo(MultipartFile file) {
        try {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Validar extensión
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
            }

            if (!extension.matches(".(jpg|jpeg|png|webp)")) {
                return null;
            }

            String uniqueFileName = UUID.randomUUID().toString() + extension;
            Path filePath = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath);

            return uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Eliminar cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable int id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        if (cliente != null && cliente.getFotoPath() != null && !cliente.getFotoPath().isEmpty()) {
            borrarArchivoFisico(cliente.getFotoPath());
        }
        clienteService.eliminarCliente(id);
        return "redirect:/clientes";
    }
}
