package com.springboot.dto;

import java.time.LocalDate;


public class OrderDetails {

    private int orderId;

    private LocalDate orderDate;

    private Integer employeeId;

    private String employeeLastName;

    private String employeeFirstName;

    public OrderDetails(int orderId, LocalDate orderDate, Integer employeeId, String employeeLastName, String employeeFirstName) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.employeeId = employeeId;
        this.employeeLastName = employeeLastName;
        this.employeeFirstName = employeeFirstName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }
}
