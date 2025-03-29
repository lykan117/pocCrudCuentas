package com.example.cuentas.controller;

import com.example.cuentas.model.Cuenta;
import com.example.cuentas.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerTodas() throws IOException {
        return ResponseEntity.ok(cuentaService.obtenerTodas());
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Cuenta> obtenerPorId(@PathVariable Long numeroCuenta) throws IOException {
        return cuentaService.obtenerPorNumero(numeroCuenta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public  ResponseEntity<Map<String, String>> crear(@RequestBody Cuenta nueva) throws IOException {
        cuentaService.crear(nueva);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cuenta creada");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<String> actualizar(@PathVariable Long numeroCuenta, @RequestBody Cuenta actualizada) throws IOException {
        if (cuentaService.actualizar(numeroCuenta, actualizada)) {
            return ResponseEntity.ok("Cuenta actualizada");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<String> eliminar(@PathVariable Long numeroCuenta) throws IOException {
        if (cuentaService.eliminar(numeroCuenta)) {
            return ResponseEntity.ok("Cuenta eliminada");
        }
        return ResponseEntity.notFound().build();
    }
}
