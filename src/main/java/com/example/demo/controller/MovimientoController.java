package com.example.demo.controller;

import com.example.demo.model.Movimiento;
import com.example.demo.resource.MovimientoResource;
import com.example.demo.resource.SaveMovimientoResource;
import com.example.demo.service.MovimientoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MovimientoController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private MovimientoService movimientoService;
    
    @GetMapping("/customers/{customerId}/movimientos")
    public List<MovimientoResource> getAllMovimientosByPostId(@PathVariable(name = "customerId") Integer customerId) {
        return movimientoService.getAllMovimientosByCustomerId(customerId).stream().map(this::convertToResource).collect(Collectors.toList());
    }

    @GetMapping("/customers/{customerId}/movimientos/{movimientoId}")
    public MovimientoResource getMovimientoByIdAndPostId(@PathVariable(name = "customerId") Integer customerId,
                                                   @PathVariable(name = "movimientoId") Integer movimientoId) {
        return convertToResource(movimientoService.getMovimientoByIdAndCustomerId(customerId, movimientoId));
    }

    @PostMapping("/customers/{customerId}/movimientos")
    public MovimientoResource createMovimiento(@PathVariable(name = "customerId") Integer customerId,
                                         @Valid @RequestBody SaveMovimientoResource resource) {
        return convertToResource(movimientoService.createMovimiento(customerId, convertToEntity(resource)));
    }

    @PutMapping("/customers/{customerId}/movimientos/{movimientoId}")
    public MovimientoResource updateMovimiento(@PathVariable(name = "customerId") Integer customerId,
                                         @PathVariable(name = "movimientoId") Integer movimientoId,
                                         @Valid @RequestBody SaveMovimientoResource resource) {
        return convertToResource(movimientoService.updateMovimiento(customerId, movimientoId, convertToEntity(resource)));
    }

    @DeleteMapping("/customers/{customerId}/movimientos/{movimientoId}")
    public ResponseEntity<?> deleteMovimiento(@PathVariable(name = "customerId") Integer customerId,
                                           @PathVariable(name = "movimientoId") Integer movimientoId) {
        return movimientoService.deleteMovimiento(customerId, movimientoId);
    }

    private Movimiento convertToEntity(SaveMovimientoResource resource) {
        return mapper.map(resource, Movimiento.class);
    }

    private MovimientoResource convertToResource(Movimiento entity) {
        return mapper.map(entity, MovimientoResource.class);
    }
}
