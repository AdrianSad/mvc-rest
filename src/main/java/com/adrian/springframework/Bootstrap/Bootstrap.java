package com.adrian.springframework.Bootstrap;

import com.adrian.springframework.domain.Category;
import com.adrian.springframework.domain.Customer;
import com.adrian.springframework.domain.Vendor;
import com.adrian.springframework.repos.CategoryRepository;
import com.adrian.springframework.repos.CustomerRepository;
import com.adrian.springframework.repos.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

        Vendor vendor1 = new Vendor();
        vendor1.setId("1");
        vendor1.setName("Vendors nr 1");
        vendorRepository.save(vendor1).block();

        Vendor vendor2 = new Vendor();
        vendor2.setId("2");
        vendor2.setName("Vendors nr 2");
        vendorRepository.save(vendor2).block();
    }

    private void loadCustomers() {

        Customer customer1 = new Customer();
        customer1.setId("1");
        customer1.setFirstName("Adrian");
        customer1.setLastName("Malolepszy");
        customerRepository.save(customer1).block();

        Customer customer2 = new Customer();
        customer2.setId("2");
        customer2.setFirstName("Ola");
        customer2.setLastName("Lepsza");
        customerRepository.save(customer2).block();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits).block();
        categoryRepository.save(nuts).block();
        categoryRepository.save(dried).block();
        categoryRepository.save(fresh).block();
        categoryRepository.save(nuts).block();
    }
}
