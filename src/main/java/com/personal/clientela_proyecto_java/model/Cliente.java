package com.personal.clientela_proyecto_java.model;

import java.sql.Timestamp;

public class Cliente {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String direccion;
    private String fotoPath;
    private double pago1;
    private double pago2;
    private double pago3;
    private double pagoFinal;
    private double balanceTotal;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(int id, String nombre, String apellidos, String email, String telefono, String direccion,
            String fotoPath, double pago1, double pago2, double pago3, double pagoFinal) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fotoPath = fotoPath;
        this.pago1 = pago1;
        this.pago2 = pago2;
        this.pago3 = pago3;
        this.pagoFinal = pagoFinal;
        this.balanceTotal = calcularBalance();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }

    public double getPago1() {
        return pago1;
    }

    public void setPago1(double pago1) {
        this.pago1 = pago1;
    }

    public double getPago2() {
        return pago2;
    }

    public void setPago2(double pago2) {
        this.pago2 = pago2;
    }

    public double getPago3() {
        return pago3;
    }

    public void setPago3(double pago3) {
        this.pago3 = pago3;
    }

    public double getPagoFinal() {
        return pagoFinal;
    }

    public void setPagoFinal(double pagoFinal) {
        this.pagoFinal = pagoFinal;
    }

    public double getBalanceTotal() {
        return balanceTotal;
    }

    public void setBalanceTotal(double balanceTotal) {
        this.balanceTotal = balanceTotal;
    }

    public double calcularBalance() {
        double promedioPagos = (pago1 + pago2 + pago3) / 3;
        return (promedioPagos * 0.7) + (pagoFinal * 0.3);
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
