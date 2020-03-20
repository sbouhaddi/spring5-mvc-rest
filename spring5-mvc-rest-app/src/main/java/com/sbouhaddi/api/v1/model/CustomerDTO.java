/**
 * 
 */
package com.sbouhaddi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bouhaddisabri
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private Long id;
    @ApiModelProperty(value = "This is the first name", required = true)
	private String firstName;
    @ApiModelProperty(required = true )
	private String lastName;
	@JsonProperty("customerUrl")
	private String customerUrl;
}
