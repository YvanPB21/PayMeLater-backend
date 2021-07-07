package com.example.demo.resource;

import com.example.demo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCustomerResource extends AuditModel {
    private String name;

    private String lastname;

    private String dni;

    private String celular;

    private Double wallet;

    private Integer cuotas;

    private Integer topay;

    private Double stock;

    private String tasa;

    private String initialdate;

    private String endingdate;

    private Integer userId;
}
