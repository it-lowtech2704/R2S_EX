package com.springboot.repository;

import com.springboot.dto.OrderDetails;
import com.springboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    List<Order> findByEmployee_Id (int employeeId);

    @Query("""
    select new com.springboot.dto.OrderDetails(
      o.id, o.orderDate, e.id, e.lastname, e.firstname
    )
    from Order o
    join o.employee e
  """)

    List<OrderDetails> FindAllOrdersWithEmployeeDetails();

    @Query("""
  select new com.springboot.dto.OrderDetails(
    o.id, o.orderDate, e.id, e.lastname, e.firstname
  )
  from Order o join o.employee e
  where e.id = :employeeId
""")
    List<OrderDetails> findOrderDetailsByEmployeeId(int employeeId);
}
