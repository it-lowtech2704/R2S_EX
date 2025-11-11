package com.springboot.mapper;


import com.springboot.dto.CustomerDTO;
import com.springboot.entity.Customer;

public class CustomerMapper {

    public static Customer toEntity (CustomerDTO dto) {
       Customer c = new Customer();

       c.setCustomerID(dto.getCustomerID());
       c.setAddress(dto.getAddress());
       c.setCity(dto.getCity());
       c.setCountry(dto.getCountry());
       c.setCustomerName(dto.getCustomerName());
       c.setContactName(dto.getContactName());
       c.setPostalcode(dto.getPostalCode());

       return c;
    }

    public  static CustomerDTO toDTO (Customer customer) {
           CustomerDTO d = new CustomerDTO();

           d.setCustomerID(customer.getCustomerID());
           d.setAddress(customer.getAddress());
           d.setCity(customer.getCity());
           d.setCountry(customer.getCountry());
           d.setCustomerName(customer.getCustomerName());
           d.setContactName(customer.getContactName());
           d.setPostalCode(customer.getPostalcode());

           return d;
    }
}
