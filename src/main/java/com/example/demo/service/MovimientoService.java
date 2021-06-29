package com.example.demo.service;

import com.example.demo.model.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovimientoService {
    List<Movimiento> getAllMovimientosByCustomerId(Integer customerId);
    Movimiento getMovimientoByIdAndCustomerId(Integer customerId, Integer movimientoId);
    Movimiento createMovimiento(Integer customerId, Movimiento movimiento);
    Movimiento updateMovimiento(Integer customerId, Integer movimientoId, Movimiento movimientoDetails);
    ResponseEntity<?> deleteMovimiento(Integer customerId, Integer movimientoId);

   
}
