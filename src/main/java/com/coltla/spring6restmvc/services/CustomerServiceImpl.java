package com.coltla.spring6restmvc.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.coltla.spring6restmvc.models.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        Customer billy = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .firstName("Billy")
                .lastName("Cohen")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Customer cameron = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .firstName("Cameron")
                .lastName("Davidson")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Customer diane = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .firstName("Diane")
                .lastName("Everitt")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        this.customerMap.put(billy.getId(), billy);
        this.customerMap.put(cameron.getId(), cameron);
        this.customerMap.put(diane.getId(), diane);
    }

    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<Customer>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id) {
        log.debug("Get Customer by ID => " + id.toString());

        return customerMap.get(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        customerMap.put(createdCustomer.getId(), createdCustomer);

        return createdCustomer;
    }

    @Override
    public void updateCustomer(UUID id, Customer customer) {
        Customer updatedCustomer = customerMap.get(id);
        updatedCustomer.setVersion(customer.getVersion());
        updatedCustomer.setFirstName(customer.getFirstName());
        updatedCustomer.setLastName(customer.getLastName());
        updatedCustomer.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerMap.remove(id);
    }
}
