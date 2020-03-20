/**
 * 
 */
package com.sbouhaddi.services;

import java.util.List;

import com.sbouhaddi.api.v1.model.CategoryDTO;

/**
 * @author bouhaddisabri
 *
 */
public interface CategoryService  {
	List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);

}
