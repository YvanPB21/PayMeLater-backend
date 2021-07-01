package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String name;
    @NotNull
    @NotBlank
    @Size(max = 255)
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



}
