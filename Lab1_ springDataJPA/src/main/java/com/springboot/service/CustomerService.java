package com.springboot.service;

import com.springboot.dto.CustomerDTO;
import com.springboot.entity.Customer;
import com.springboot.exception.NotFoundException;
import com.springboot.mapper.CustomerMapper;
import com.springboot.repository.CustomerRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<CustomerDTO> getAll() {
        return customerRepo.findAll()
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    public CustomerDTO getById(Long id) {
        Customer c = customerRepo.findById(id)
                // Nếu NotFoundException hiện chỉ nhận int, dùng bản nhận String:
                .orElseThrow(() -> new NotFoundException("Customer not found with id = " + id));
        return CustomerMapper.toDTO(c);
    }

    @Transactional
    public CustomerDTO create(CustomerDTO dto) {
        Customer saved = customerRepo.save(CustomerMapper.toEntity(dto));
        return CustomerMapper.toDTO(saved);
    }

    @Transactional
    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer c = customerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id = " + id));

        c.setAddress(dto.getAddress());
        c.setCustomerName(dto.getCustomerName());
        c.setCity(dto.getCity());
        c.setCountry(dto.getCountry());
        c.setContactName(dto.getContactName());
        c.setPostalcode(dto.getPostalCode());

        return CustomerMapper.toDTO(customerRepo.save(c));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!customerRepo.existsById(id)) {
            throw new NotFoundException("Customer not found with id = " + id);
        }
        customerRepo.deleteById(id);
    }
}
