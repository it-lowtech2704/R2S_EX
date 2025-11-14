package com.springboot.service;

import com.springboot.dto.OrderDTO;
import com.springboot.dto.OrderDetails;
import com.springboot.entity.Customer;
import com.springboot.entity.Employee;
import com.springboot.entity.Order;
import com.springboot.exception.NotFoundException;
import com.springboot.mapper.OrderMapper;
import com.springboot.repository.CustomerRepo;
import com.springboot.repository.EmployeeRepo;
import com.springboot.repository.OrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final EmployeeRepo employeeRepo;

    public OrderService(OrderMapper orderMapper,
                        OrderRepo orderRepo,
                        CustomerRepo customerRepo,
                        EmployeeRepo employeeRepo) {
        this.orderMapper = orderMapper;
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.employeeRepo = employeeRepo;
    }

    public List<OrderDTO> getAll() {
        return orderRepo.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }


    public List<OrderDTO> getByEmployeeId(int employeeId){
        return orderRepo.findByEmployee_Id(employeeId).stream().map(orderMapper::toDto).toList();
    }


    public List<OrderDetails> getByOrderDetails(){
      return orderRepo.FindAllOrdersWithEmployeeDetails();
    }

    public List<OrderDetails> getByOrderDetailsByEmployeeId(int employeeId){
        return orderRepo.findOrderDetailsByEmployeeId(employeeId);
    }


    public OrderDTO getById(int id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return orderMapper.toDto(order);
    }

    // CREATE
    @Transactional
    public OrderDTO create(OrderDTO dto) {
        Integer employeeId = dto.getEmployee_id();
        Long customerId    = dto.getCustomerID();

        if (employeeId == null) {
            throw new IllegalArgumentException("employeeId is required");
        }

        Employee emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(employeeId));

        Customer cus = null;
        if (customerId != null) {
            cus = customerRepo.findById(customerId)
                    .orElseThrow(() -> new NotFoundException("Customer not found with id = " + customerId));
        }

        Order order = orderMapper.toEntity(dto); // map các field đơn giản (orderDate,...)
        order.setEmployee(emp);
        order.setCustomer(cus);

        Order saved = orderRepo.save(order);
        return orderMapper.toDto(saved);
    }

    // UPDATE
    @Transactional
    public OrderDTO update(int id, OrderDTO dto) {
        Order existing = orderRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        Integer employeeId = dto.getEmployee_id();
        Long customerId    = dto.getCustomerID();

        if (employeeId == null) {
            throw new IllegalArgumentException("employeeId is required");
        }

        Employee emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(employeeId));

        Customer cus = null;
        if (customerId != null) {
            cus = customerRepo.findById(customerId)
                    .orElseThrow(() -> new NotFoundException("Customer not found with id = " + customerId));
        }

        existing.setOrderDate(dto.getOrderDate());
        existing.setEmployee(emp);
        existing.setCustomer(cus);

        Order updated = orderRepo.save(existing);
        return orderMapper.toDto(updated);
    }

    @Transactional
    public void delete(int id) {
        if (!orderRepo.existsById(id)) {
            throw new NotFoundException(id);
        }
        orderRepo.deleteById(id);
    }
}
