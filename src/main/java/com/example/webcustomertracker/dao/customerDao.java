package com.example.webcustomertracker.dao;

import com.example.webcustomertracker.entity.Customer;

import java.util.List;

public interface customerDao {
    public List<Customer> getCustomers(int sort);
    public void saveCustomer(Customer customer);
    public Customer getCustomer(int id);
    public void deleteCustomer(int id);
    public List<Customer> search(String searchName);
}
