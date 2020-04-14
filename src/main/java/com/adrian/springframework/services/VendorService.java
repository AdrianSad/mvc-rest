package com.adrian.springframework.services;

import com.adrian.springframework.api.v1.model.VendorDTO;
import com.adrian.springframework.api.v1.model.VendorListDTO;

import java.util.List;

public interface VendorService {

    VendorDTO getVendorById(Long id);

    VendorListDTO getAllVendors();

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
