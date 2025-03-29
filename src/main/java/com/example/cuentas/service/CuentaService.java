package com.example.cuentas.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.example.cuentas.model.Cuenta;
import com.example.cuentas.util.CsvUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class CuentaService {

    public List<Cuenta> obtenerTodas() throws IOException {
        return CsvUtils.leerCuentas();
    }

    public Optional<Cuenta> obtenerPorNumero(Long numeroCuenta) throws IOException {
        return CsvUtils.leerCuentas().stream()
                .filter(c -> c.getNumeroCuenta().equals(numeroCuenta))
                .findFirst();
    }

    public void crear(Cuenta nueva) throws IOException {
        List<Cuenta> cuentas = CsvUtils.leerCuentas();
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        nueva.setFechaRegistro(now);
        nueva.setFechaUpdate("-");
        cuentas.add(nueva);
        CsvUtils.escribirCuentas(cuentas);
    }


    public boolean actualizar(Long numeroCuenta, Cuenta actualizada) throws IOException {
        List<Cuenta> cuentas = CsvUtils.leerCuentas();
        boolean actualizado = false;

        for (Cuenta c : cuentas) {
            if (c.getNumeroCuenta().equals(numeroCuenta)) {
                c.setDireccion(actualizada.getDireccion());
                c.setTelefono(actualizada.getTelefono());
                c.setFechaUpdate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                actualizado = true;
                break;
            }
        }

        if (actualizado) {
            CsvUtils.escribirCuentas(cuentas);
        }
        return actualizado;
    }


    public boolean eliminar(Long numeroCuenta) throws IOException {
        List<Cuenta> cuentas = CsvUtils.leerCuentas();
        boolean eliminado = cuentas.removeIf(c -> c.getNumeroCuenta().equals(numeroCuenta));
        if (eliminado) {
            CsvUtils.escribirCuentas(cuentas);
        }
        return eliminado;
    }
}
