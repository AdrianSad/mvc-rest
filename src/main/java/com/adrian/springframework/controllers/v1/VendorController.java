package com.adrian.springframework.controllers.v1;

import com.adrian.springframework.domain.Vendor;
import com.adrian.springframework.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors/";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Vendor> getVendorList(){
        return vendorService.getAllVendors();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<Vendor>> getVendorById(@PathVariable String id){
        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Vendor> createNewVendor(@RequestBody Vendor vendor){
        return vendorService.createNewVendor(vendor);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Vendor>> updateVendor(@PathVariable String id, @RequestBody Vendor vendor){
        return vendorService.updateVendor(id, vendor);
    }

    @PatchMapping("{id}")
    public Mono<ResponseEntity<Vendor>> patchVendor(@PathVariable String id, @RequestBody Vendor vendor){
        return vendorService.patchVendor(id, vendor);
    }

    @DeleteMapping("{id}")
    public void patchVendor(@PathVariable String id){
        vendorService.deleteVendorById(id);
    }

}
