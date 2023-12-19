package com.coltla.spring6restmvc.services;

import java.util.List;
import java.util.UUID;

import com.coltla.spring6restmvc.models.Customer;

public interface CustomerService {
    public List<Customer> getCustomers();

    public Customer getCustomerById(UUID id);

    public Customer createCustomer(Customer customer);

    public void updateCustomer(UUID id, Customer customer);

    public void deleteCustomer(UUID id);
}
