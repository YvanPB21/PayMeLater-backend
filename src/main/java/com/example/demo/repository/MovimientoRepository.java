package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByCustomerId(Integer customerId);
    Optional<Movimiento> findByIdAndCustomerId(Integer id, Integer customerId);
}
