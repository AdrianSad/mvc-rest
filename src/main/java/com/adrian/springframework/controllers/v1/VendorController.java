package com.adrian.springframework.controllers.v1;

import com.adrian.springframework.config.SwaggerConfig;
import com.adrian.springframework.domain.Vendor;
import com.adrian.springframework.exceptions.ErrorResponse;
import com.adrian.springframework.exceptions.NotFoundException;
import com.adrian.springframework.services.VendorService;
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


@Api(tags = {SwaggerConfig.VENDOR_CONTROLLER_TAG})
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors/";

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "List of vendors.", notes = "This will get u every vendor in the list.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Vendor> getVendorList(){
        return vendorService.getAllVendors();
    }

    @ApiOperation(value = "Get vendor by id.", notes = "Returns existing vendor.")
    @GetMapping("{id}")
    public Mono<ResponseEntity<Vendor>> getVendorById(@PathVariable @ApiParam(value = "ID of vendor to return") String id){
        return vendorService.getVendorById(id);
    }

    @ApiOperation(value = "Create vendor")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Vendor> createNewVendor(@RequestBody @Valid @ApiParam(value = "Required vendor object to create") Vendor vendor){
        return vendorService.createNewVendor(vendor);
    }

    @ApiOperation(value = "Update existing vendor")
    @PutMapping("{id}")
    public Mono<ResponseEntity<Vendor>> updateVendor(@PathVariable @Valid @ApiParam(value = "ID of vendor to update") String id, @RequestBody @ApiParam(value = "Required vendor object to update") Vendor vendor){
        return vendorService.updateVendor(id, vendor);
    }

    @ApiOperation(value = "Update vendor's data")
    @PatchMapping("{id}")
    public Mono<ResponseEntity<Vendor>> patchVendor(@PathVariable @Valid @ApiParam(value = "ID of vendor to update") String id, @RequestBody @ApiParam(value = "Required vendor object to update") Vendor vendor){
        return vendorService.patchVendor(id, vendor);
    }

    @ApiOperation(value = "Delete existing vendor")
    @DeleteMapping("{id}")
    public void patchVendor(@PathVariable @ApiParam(value = "ID of vendor to delete") String id){
        vendorService.deleteVendorById(id);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Vendor with the same field already exists"));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleTweetNotFoundException(NotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
