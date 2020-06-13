package com.adrian.springframework.services;

import com.adrian.springframework.domain.Customer;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CustomerService {

    Flux<Customer> getAllCustomers();

    Mono<ResponseEntity<Customer>> getCustomerById(String id);

    Mono<Customer> createNewCustomer(Customer customer);

    Mono<ResponseEntity<Customer>> updateCustomer(String id, Customer customer);

    Mono<ResponseEntity<Customer>> patchCustomer(String id, Customer customer);

    Mono<ResponseEntity<Void>> deleteCustomerById(String id);
}
