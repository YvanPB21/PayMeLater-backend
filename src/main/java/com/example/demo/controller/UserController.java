package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.resource.SaveUserResource;
import com.example.demo.resource.UserResource;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "User")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        try{
            List<User> users = userService.getAll();
            return new ResponseEntity<List<User>>(users,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> GetAllById(@PathVariable("id") Integer id){
        try{
            Optional<User> user = userService.getById(id);
            if(user.isPresent()){
                return new ResponseEntity<User>(user.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result){
        if( result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        User userAux = userService.createUser(user);
        return  ResponseEntity.status( HttpStatus.CREATED).body(userAux);
    }
    @PutMapping("/users/{id}")
    public UserResource updateUser(@PathVariable(name = "id") Integer id, @RequestBody SaveUserResource resource){
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(user, id));
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Integer Id) {
        return userService.deleteUser(Id);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }
    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
