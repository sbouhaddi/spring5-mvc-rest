/**
 * 
 */
package com.sbouhaddi.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sbouhaddi.api.v1.model.VendorDTO;
import com.sbouhaddi.domain.Vendor;

/**
 * @author bouhaddisabri
 *
 */
@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOtoVendor(VendorDTO vendorDTO);
}