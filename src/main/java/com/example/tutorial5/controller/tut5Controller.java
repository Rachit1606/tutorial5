package com.example.tutorial5.controller;


import com.example.tutorial5.repository.tut5Repository;
import com.example.tutorial5.request.Request;
import com.example.tutorial5.request.User;
import com.example.tutorial5.response.Response;
import com.example.tutorial5.response.getAllResponse;
import com.example.tutorial5.response.getResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class tut5Controller {

    @Autowired
    tut5Repository repo;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers()
    {
        getAllResponse res = new getAllResponse();

        List<User> u = repo.findAll();
        try{
            if(u.isEmpty())
            {
                res.setSuccess(false);
                res.setMessage("No user found");
                res.setUsers(u);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            else {
                res.setSuccess(true);
                res.setMessage("Users retrieved");
                res.setUsers(u);
                return ResponseEntity.status(HttpStatus.OK).body(res);
            }
        }catch (Exception e)
        {
            if (e instanceof RuntimeException) {
                res.setSuccess(false);
                res.setMessage("Bad Request: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            else {
                res.setSuccess(false);
                res.setMessage("Internal Server Error: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id)
    {
        getResponse res = new getResponse();
        Optional<User> u = repo.findById(id);
        try {
            if(u.isEmpty())
            {
                res.setSuccess(false);
                res.setUser(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            else {
                res.setSuccess(true);
                res.setUser(u.orElse(new User()));
                return  ResponseEntity.status(HttpStatus.OK).body(res);
            }
        }catch (Exception e)
        {
            if (e instanceof RuntimeException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
    }
    @PostMapping("/add")
    ResponseEntity<?> addUser(@RequestBody Request request)
    {
        Response res = new Response();
        try {
            if(request.getEmail() == null || request.getFirstName() == null)
            {
                res.setSuccess(false);
                res.setMessage("Bad Request: email or firstName is not present");
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            else {
                User u = new User();
                u.setEmail(request.getEmail());
                u.setFirstName(request.getFirstName());
                repo.save(u);
                res.setSuccess(true);
                res.setMessage("User added");
                return ResponseEntity.status(HttpStatus.OK).body(res);

            }
        }catch (Exception e)
        {
            res.setSuccess(false);
            res.setMessage(e.getMessage());
            if (e instanceof RuntimeException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
        }
    }
    @PutMapping("/update/{id}")
    ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody Request request)
    {
        Response res = new Response();
        try {
            if(request.getEmail() == null || request.getFirstName() == null)
            {
                res.setSuccess(false);
                res.setMessage("Bad Request: email or firstName is not present");
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            Optional<User> u = repo.findById(id);
            if (u.isEmpty())
            {
                res.setSuccess(false);
                res.setMessage("User with id: "+id+" not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
            }
            else{
                User user = new User(id,request.getEmail(), request.getFirstName());
                repo.save(user);
                res.setSuccess(true);
                res.setMessage("User updated");
                return ResponseEntity.status(HttpStatus.OK).body(res);
            }


        }catch (Exception e)
        {
            res.setSuccess(false);
            res.setMessage(e.getMessage());
            if (e instanceof RuntimeException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
        }


    }
}
