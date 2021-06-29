package com.example.demo.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveUserResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String password;

    private Boolean islogged;
}
