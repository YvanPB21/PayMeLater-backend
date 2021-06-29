package com.example.demo.resource;

import com.example.demo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource extends AuditModel {
    private Integer id;
    private String email;
    private String password;
    private Boolean islogged;
}
