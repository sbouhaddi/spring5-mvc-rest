/**
 * 
 */
package com.sbouhaddi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbouhaddi.domain.Category;

/**
 * @author bouhaddisabri
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

	  Category findByName(String name);
}
