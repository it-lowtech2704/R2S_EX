package com.springboot.service;

import com.springboot.dto.CustomerDTO;
import com.springboot.entity.Customer;
import com.springboot.exception.NotFoundException;
import com.springboot.mapper.CustomerMapper;
import com.springboot.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<CustomerDTO> getAll() {
        return customerRepo.findAll().stream().map(CustomerMapper::toDTO).toList();
    }

    public  CustomerDTO getById(int id) {
        return CustomerMapper.toDTO(customerRepo.findById(id).orElseThrow(()->new NotFoundException(id)));
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        return CustomerMapper.toDTO(customerRepo.save(CustomerMapper.toEntity(customerDTO)));
    }

    public CustomerDTO update(CustomerDTO customerDTO, int id) {
         Customer customer = customerRepo.findById(id).orElseThrow(() -> new NotFoundException(id));

         customer.setAddress(customerDTO.getAddress());
         customer.setCustomerName(customerDTO.getCustomerName());
         customer.setCity(customerDTO.getCity());
         customer.setCountry(customerDTO.getCountry());
         customer.setContactName(customerDTO.getContactName());
         customer.setPostalcode(customerDTO.getPostalCode());

         return CustomerMapper.toDTO(customerRepo.save(customer));

    }

    public void deleteById(int id) {
        if (!customerRepo.existsById(id)) throw  new NotFoundException(id);
        customerRepo.deleteById(id);
    }
}

