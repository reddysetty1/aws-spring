package com.example.awsdemoapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.awsdemoapp.Entity.Users;
import com.example.awsdemoapp.Exception.ResourceNotFoundException;
import com.example.awsdemoapp.Repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController 
{

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<Users> getAllusers()
    {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable(value = "id") long id)
    {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user found with the ID"));
    }

    @PostMapping("/create")
    public String createUser(@RequestBody Users users)
    {
        userRepository.save(users);
        return "New user created";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@RequestBody Users users, @PathVariable(value = "id") long id)
    {
        Users existing = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user found with this Id"));
        existing.setFirstName(users.getFirstName());
        existing.setLastName(users.getLastName());
        existing.setEmail(users.getEmail());

        userRepository.save(existing);

        return "User updated";
    }

    @DeleteMapping("/delete/{id}")
    public String DeleteUser(@PathVariable(value = "id") long id)
    {
        Users existing = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user found to delete"));
        userRepository.delete(existing);
        return "User Deleted";
    }
    
}
