package com.adrian.springframework.repos;

import com.adrian.springframework.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
