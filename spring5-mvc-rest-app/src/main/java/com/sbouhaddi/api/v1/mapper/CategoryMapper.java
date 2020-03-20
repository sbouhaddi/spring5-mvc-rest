/**
 * 
 */
package com.sbouhaddi.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sbouhaddi.api.v1.model.CategoryDTO;
import com.sbouhaddi.domain.Category;

/**
 * @author bouhaddisabri
 *
 */
@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	CategoryDTO categoryToCategoryDTO(Category category);

}
