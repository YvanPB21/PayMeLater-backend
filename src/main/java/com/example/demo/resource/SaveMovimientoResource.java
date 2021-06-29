package com.example.demo.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
@Getter@Setter
public class SaveMovimientoResource {
    @NotNull
    @Lob
    private Double total;
}
