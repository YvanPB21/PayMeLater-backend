package com.example.demo.resource;

import com.example.demo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoResource extends AuditModel {

    private Integer id;
    private Double total;

}
