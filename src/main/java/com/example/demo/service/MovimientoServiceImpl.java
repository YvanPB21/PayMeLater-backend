package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Movimiento;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoServiceImpl implements MovimientoService{
    
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public List<Movimiento> getAllMovimientosByCustomerId(Integer customerId) {
        return movimientoRepository.findByCustomerId(customerId);
    }

    @Override
    public Movimiento getMovimientoByIdAndCustomerId(Integer customerId, Integer movimientoId) {
        return movimientoRepository.findByIdAndCustomerId(movimientoId, customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Movimiento not found with Id " + movimientoId +
                                " and CustomerId " + customerId));
    }

    @Override
    public Movimiento createMovimiento(Integer customerId, Movimiento movimiento) {
        return customerRepository.findById(customerId).map(customer -> {
            movimiento.setCustomer(customer);
            return movimientoRepository.save(movimiento);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Customer", "Id", customerId));
    }

    @Override
    public Movimiento updateMovimiento(Integer customerId, Integer movimientoId, Movimiento movimientoDetails) {
        if(!customerRepository.existsById(customerId))
            throw new ResourceNotFoundException("Customer", "Id", customerId);
        return movimientoRepository.findById(movimientoId).map(movimiento -> {
                movimiento.setTotal(movimientoDetails.getTotal());
            return movimientoRepository.save(movimiento);
        }).orElseThrow(() -> new ResourceNotFoundException("Movimiento", "Id", movimientoId));
    }

    @Override
    public ResponseEntity<?> deleteMovimiento(Integer customerId, Integer movimientoId) {
        return movimientoRepository.findByIdAndCustomerId(movimientoId, customerId).map(movimiento -> {
            movimientoRepository.delete(movimiento);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Comment not found with Id " + movimientoId + " and CustomerId " + customerId));
    }
}
