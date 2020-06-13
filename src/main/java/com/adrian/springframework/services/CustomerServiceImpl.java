package com.adrian.springframework.services;

import com.adrian.springframework.domain.Customer;
import com.adrian.springframework.repos.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Flux<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<ResponseEntity<Customer>> getCustomerById(String id) {
        return customerRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<Customer> createNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<ResponseEntity<Customer>> updateCustomer(String id, Customer customer) {
        return customerRepository.findById(id)
                .flatMap(foundCustomer -> {
                    foundCustomer.setFirstName(customer.getFirstName());
                    foundCustomer.setLastName(customer.getLastName());
                    return customerRepository.save(foundCustomer);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @Override
    public Mono<ResponseEntity<Customer>> patchCustomer(String id, Customer customer) {
        return customerRepository.findById(id)
                .flatMap(foundCustomer -> {

                    if(foundCustomer.getFirstName() != null)
                        foundCustomer.setFirstName(customer.getFirstName());

                    if(foundCustomer.getLastName() != null)
                        foundCustomer.setLastName(customer.getLastName());

                    return customerRepository.save(foundCustomer);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCustomerById(String id) {
        return customerRepository.findById(id)
                .flatMap(foundCustomer -> customerRepository.delete(foundCustomer)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
