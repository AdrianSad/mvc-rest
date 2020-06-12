package com.adrian.springframework.services;

import com.adrian.springframework.domain.Vendor;
import com.adrian.springframework.repos.VendorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }


    @Override
    public Mono<ResponseEntity<Vendor>> getVendorById(String id) {
        return vendorRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Flux<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Mono<Vendor> createNewVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Mono<ResponseEntity<Vendor>> updateVendor(String id, Vendor vendor) {
        return vendorRepository.findById(id)
                .flatMap(foundVendor -> {
                    foundVendor.setName(vendor.getName());
                    return vendorRepository.save(foundVendor);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @Override
    public Mono<ResponseEntity<Vendor>> patchVendor(String id, Vendor vendor) {
        return vendorRepository.findById(id)
                .flatMap(foundVendor -> {

                    if(foundVendor.getName() != null)
                        foundVendor.setName(vendor.getName());

                    return vendorRepository.save(foundVendor);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteVendorById(String id) {
        return vendorRepository.findById(id)
                .flatMap(foundVendor -> vendorRepository.delete(foundVendor)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
