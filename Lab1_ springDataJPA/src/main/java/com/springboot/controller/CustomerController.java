package com.springboot.controller;

import com.springboot.dto.CustomerDTO;
import com.springboot.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
   private final CustomerService customerService;

   public  CustomerController(CustomerService customerService) {
       this.customerService = customerService;
   }

   @GetMapping
    public  ResponseEntity<List<CustomerDTO>> findAll() {
       return ResponseEntity.ok(customerService.getAll());
   }

   @GetMapping("/{id}")
    public  ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
       return ResponseEntity.ok(customerService.getById(id));
   }

   @PostMapping
    public ResponseEntity<CustomerDTO> save(@Valid @RequestBody CustomerDTO customerDTO) {
       return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customerDTO));
   }

   @PutMapping("/{id}")
    public  ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @Valid @RequestBody CustomerDTO customerDTO) {
       return ResponseEntity.ok(customerService.update(customerDTO, id));
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> delete(@PathVariable Integer id) {
       customerService.deleteById(id);
       return ResponseEntity.noContent().build();
   }

}
