package com.example.demo.resource;

import com.example.demo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter@Setter
public class ProductResource extends AuditModel {

    private Integer id;

    private String name;

    private Double price;

    private String url;

    private Integer position;

}
