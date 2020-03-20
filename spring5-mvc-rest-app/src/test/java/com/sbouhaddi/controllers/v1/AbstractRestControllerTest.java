/**
 * 
 */
package com.sbouhaddi.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author bouhaddisabri
 *
 */
public class AbstractRestControllerTest {

	   public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
