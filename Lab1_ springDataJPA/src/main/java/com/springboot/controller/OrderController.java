package com.springboot.controller;

import com.springboot.dto.OrderDTO;
import com.springboot.dto.OrderDetails;
import com.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll() {
        return  ResponseEntity.ok().body(orderService.getAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<OrderDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok().body(orderService.getById(id));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<OrderDTO>> getByEmployeeId(@PathVariable int employeeId) {
        return ResponseEntity.ok().body(orderService.getByEmployeeId(employeeId));
    }

    @GetMapping("/details")
    public  ResponseEntity<List<OrderDetails>> getByDetails() {
        return ResponseEntity.ok().body(orderService.getByOrderDetails());
    }

    @GetMapping("/details/employee/{employeeId}")
    public ResponseEntity<List<OrderDetails>> getByEmployeeIdAndDetails(@PathVariable int employeeId) {
        return ResponseEntity.ok().body(orderService.getByOrderDetailsByEmployeeId(employeeId));
    }


    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok().body(orderService.create(orderDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable int id, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok().body(orderService.update(id,orderDTO));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<OrderDTO> delete(@PathVariable int id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
