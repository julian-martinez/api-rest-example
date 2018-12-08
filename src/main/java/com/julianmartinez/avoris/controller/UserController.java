package com.julianmartinez.avoris.controller;

import com.julianmartinez.avoris.model.messages.UserRQ;
import com.julianmartinez.avoris.model.messages.UserRS;
import com.julianmartinez.avoris.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<UserRS> addUser(@RequestBody UserRQ request){
        UserRS response = userService.addUser(request);
        if (response != null){
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<UserRS>> getUserList(){
        List<UserRS> response = userService.getUserList();
        if (response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRS> getUserById(@PathVariable(value = "id") long id){
        UserRS response = userService.getUserById(id);
        if (response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity modifyUserById(@PathVariable(value = "id") long id,
                                         @RequestBody UserRQ request){
        request.setId(id);
        userService.modifyUserById(request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeUserById(@PathVariable(value = "id") long id){
        userService.removeUserById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
