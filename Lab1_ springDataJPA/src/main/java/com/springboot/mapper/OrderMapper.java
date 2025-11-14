package com.springboot.mapper;

import com.springboot.dto.OrderDTO;
import com.springboot.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // Entity -> DTO
    @Mapping(target = "employee_id", source = "employee.id")
    @Mapping(target = "customerID",  source = "customer.customerID")
    OrderDTO toDto(Order order);

    // DTO -> Entity
    @Mapping(target = "employee.id",        source = "employee_id")
    @Mapping(target = "customer.customerID", source = "customerID")
    Order toEntity(OrderDTO dto);
}
