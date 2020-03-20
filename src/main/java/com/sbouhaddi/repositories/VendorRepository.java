/**
 * 
 */
package com.sbouhaddi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbouhaddi.domain.Vendor;

/**
 * @author bouhaddisabri
 *
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
