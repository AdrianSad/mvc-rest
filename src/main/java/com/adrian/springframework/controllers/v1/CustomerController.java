package com.adrian.springframework.controllers.v1;


import com.adrian.springframework.config.SwaggerConfig;
import com.adrian.springframework.domain.Customer;
import com.adrian.springframework.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api(tags = {SwaggerConfig.CUSTOMER_CONTROLLER_TAG})
@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "This will get a list of customers.", notes = "Some notes")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Customer> getListOfCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Customer>> getCustomerById(@PathVariable String id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> createNewCustomer(@RequestBody Customer customer){
        return customerService.createNewCustomer(customer);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Customer>> updateNewCustomer(@PathVariable String id, @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }

    @PatchMapping("{id}")
    public Mono<ResponseEntity<Customer>> patchCustomer(@PathVariable String id, @RequestBody Customer customer){
        return customerService.patchCustomer(id, customer);
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable String id){
        return customerService.deleteCustomerById(id);
    }

//    @ExceptionHandler
//    public ResponseEntity handleNotFoundException(TweetNotFoundException ex) {
//        return ResponseEntity.notFound().build();
//    }
}
