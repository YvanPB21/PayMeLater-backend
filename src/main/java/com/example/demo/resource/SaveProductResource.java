package com.example.demo.resource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter@Setter
public class SaveProductResource {

    @NotNull
    @NotBlank
    @Size(max = 255)
    private String name;

    private Double price;

    private String url;

    private Integer position;
}
