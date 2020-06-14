package com.adrian.springframework.Bootstrap;

import com.adrian.springframework.domain.Category;
import com.adrian.springframework.domain.Customer;
import com.adrian.springframework.domain.Vendor;
import com.adrian.springframework.repos.CategoryRepository;
import com.adrian.springframework.repos.CustomerRepository;
import com.adrian.springframework.repos.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();

        loadCategories();

        loadVendors();

    }

    private void loadVendors(){

        vendorRepository.deleteAll()
                .thenMany(
                        Flux.just(
                                Vendor.builder().id("1").name("Fast-Food Bus").city("Wroclaw").address("ul. Edwarda Wittiga 123").build(),
                                Vendor.builder().id("2").name("Health-Food Stall").city("Warszawa").address("ul. Wiejska 1").build()
                        ).flatMap(vendorRepository::save))
                .thenMany(vendorRepository.findAll())
                .subscribe(cust -> log.info("Vendor created:\n" + cust.toString()));
    }

    private void loadCustomers() {

        customerRepository.deleteAll()
                .thenMany(
                        Flux.just(
                                Customer.builder().id("1").firstName("Jan").lastName("Kowalski").age(18).build(),
                                Customer.builder().id("2").lastName("Adrian").lastName("Sadurski").age(20).build()
                        ).flatMap(customerRepository::save))
                .thenMany(customerRepository.findAll())
                .subscribe(cust -> log.info("Customer created:\n" + cust.toString()));
    }

    private void loadCategories() {

        categoryRepository.deleteAll()
                .thenMany(
                        Flux.just(
                                Category.builder().name("Fruits").build(),
                                Category.builder().name("Vegetables").build(),
                                Category.builder().name("Fresh").build(),
                                Category.builder().name("Meat").build()
                        ).flatMap(categoryRepository::save))
                .thenMany(categoryRepository.findAll())
                .subscribe(cat -> log.info("Category created:\n" + cat.toString()));
    }
}
