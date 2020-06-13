package com.adrian.springframework.controllers.v1;


import com.adrian.springframework.config.SwaggerConfig;
import com.adrian.springframework.domain.Customer;
import com.adrian.springframework.exceptions.ErrorResponse;
import com.adrian.springframework.exceptions.NotFoundException;
import com.adrian.springframework.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Api(tags = {SwaggerConfig.CUSTOMER_CONTROLLER_TAG})
@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "List of customers", notes = "This will get a list of customers.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Customer> getListOfCustomers(){
        return customerService.getAllCustomers();
    }

    @ApiOperation(value = "Get customer by id", notes = "Returns a single customer if exists.")
    @GetMapping("{id}")
    public Mono<ResponseEntity<Customer>> getCustomerById(@PathVariable @ApiParam(value = "ID of customer to return") String id){
        return customerService.getCustomerById(id);
    }

    @ApiOperation(value = "Create customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> createNewCustomer(@RequestBody @Valid @ApiParam(value = "Required customer object to create") Customer customer){
        return customerService.createNewCustomer(customer);
    }

    @ApiOperation(value = "Update a existing customer")
    @PutMapping("{id}")
    public Mono<ResponseEntity<Customer>> updateNewCustomer(@PathVariable @Valid @ApiParam(value = "ID of customer to update") String id, @RequestBody @ApiParam(value = "Required customer object to update") Customer customer){
        return customerService.updateCustomer(id, customer);
    }

    @ApiOperation(value = "Update customer's data")
    @PatchMapping("{id}")
    public Mono<ResponseEntity<Customer>> patchCustomer(@PathVariable @Valid @ApiParam(value = "ID of customer to update") String id, @RequestBody @ApiParam(value = "Required customer object to update") Customer customer){
        return customerService.patchCustomer(id, customer);
    }

    @ApiOperation(value = "Delete existing customer")
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable @ApiParam(value = "ID of customer to delete") String id){
        return customerService.deleteCustomerById(id);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Customer with the same field already exists"));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleTweetNotFoundException(NotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
