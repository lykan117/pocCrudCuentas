package com.example.cuentas.model;

import com.opencsv.bean.CsvBindByName;

public class Cuenta {

    @CsvBindByName
    private Long id;

    @CsvBindByName
    private Long numeroCuenta;

    @CsvBindByName
    private String direccion;

    @CsvBindByName
    private String telefono;

    @CsvBindByName
    private String fechaRegistro;

    @CsvBindByName
    private String fechaUpdate;

    public Cuenta() {}

    public Cuenta(Long id, Long numeroCuenta, String direccion, String telefono, String fechaRegistro, String fechaUpdate) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.fechaUpdate = fechaUpdate;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(Long numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getFechaUpdate() { return fechaUpdate; }
    public void setFechaUpdate(String fechaUpdate) { this.fechaUpdate = fechaUpdate; }
}
