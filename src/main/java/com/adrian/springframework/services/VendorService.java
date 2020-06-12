package com.adrian.springframework.services;

import com.adrian.springframework.domain.Vendor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface VendorService {

    Mono<ResponseEntity<Vendor>> getVendorById(String id);

    Flux<Vendor> getAllVendors();

    Mono<Vendor> createNewVendor(Vendor vendor);

    Mono<ResponseEntity<Vendor>> updateVendor(String id, Vendor vendor);

    Mono<ResponseEntity<Vendor>> patchVendor(String id, Vendor vendor);

    Mono<ResponseEntity<Void>> deleteVendorById(String id);
}
