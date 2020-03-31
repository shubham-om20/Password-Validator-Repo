package com.charter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class PasswordValidatorApplicationTests {

	 @Autowired
	private  MockMvc mockMvc;
	
	 @Test
	    public void verifyValidPasswordTest() 
	      throws Exception {
		 
		 String testValue = "Password looks good"; 
		 String patchInJson = "{\"password\":\"abc1234\"}";
		 
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	          .post("/api/validate")
	          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	          .content(patchInJson))
				 .andExpect(jsonPath("$.message", is(testValue)))
			  .andReturn();
	        
		 String resultSS = result.getResponse().getContentAsString();  
		  assertNotNull(resultSS);   
	    }
	 
	 @Test
	    public void verifyLowerCaseAndDigitTest() 
	      throws Exception {
		 
		 String testValue1 = "Password must contain at least one digit and lower character.";
		 String testValue2 = "Password must be between 5 and 12 characters long.";
		 
		 String patchInJson = "{\"password\":\"abc\"}";
		 
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	          .post("/api/validate")
	          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	          .content(patchInJson))
				 .andExpect(jsonPath("$.violations", Matchers.containsInAnyOrder(testValue1,testValue2)))
			     .andReturn();
	        
		 String resultSS = result.getResponse().getContentAsString();  
		  assertNotNull(resultSS);   
	    }
	 
	 @Test
	    public void verifyLowerCaseAndDigitWithSizeGreaterThen12Test() 
	      throws Exception {
		 
		 String testValue1 = "Password must be between 5 and 12 characters long.";
		 
		 String patchInJson = "{\"password\":\"abcdfh23456789\"}";
		 
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	          .post("/api/validate")
	          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	          .content(patchInJson))
				 .andExpect(jsonPath("$.violations", Matchers.containsInAnyOrder(testValue1)))
			     .andReturn();
	        
		 String resultSS = result.getResponse().getContentAsString();  
		  assertNotNull(resultSS);   
	    }
	 
	 @Test
	    public void verifyLowerCaseAndDigitWithSameSequenceLetterTest() 
	      throws Exception {
		 
		 String testValue1 = "Password must not contain any sequence of characters immediately followed by the same sequence.";
		 
		 String patchInJson = "{\"password\":\"andaa12\"}";
		 
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	          .post("/api/validate")
	          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	          .content(patchInJson))
				 .andExpect(jsonPath("$.violations", Matchers.containsInAnyOrder(testValue1)))
			     .andReturn();
	        
		 String resultSS = result.getResponse().getContentAsString();  
		  assertNotNull(resultSS);   
	    }
	 
	 @Test
	    public void verifyLowerCaseAndDigitWithSameSequenceDigitTest() 
	      throws Exception {
		 
		 String testValue1 = "Password must not contain any sequence of characters immediately followed by the same sequence.";
		 
		 String patchInJson = "{\"password\":\"anda112\"}";
		 
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	          .post("/api/validate")
	          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	          .content(patchInJson))
				 .andExpect(jsonPath("$.violations", Matchers.containsInAnyOrder(testValue1)))
			     .andReturn();
	        
		 String resultSS = result.getResponse().getContentAsString();  
		  assertNotNull(resultSS);   
	    }
	 
	 @Test
	    public void verifyLowerCaseAndDigitWithAllErrorScenariosTest() 
	      throws Exception {
		 
		 String testValue1 = "Password must not contain any sequence of characters immediately followed by the same sequence.";
		 String testValue2 = "Password must be between 5 and 12 characters long.";
		 String testValue3 =  "Password must contain at least one digit and lower character.";
		 
		 String patchInJson = "{\"password\":\"Abb\"}";
		 
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders
	          .post("/api/validate")
	          .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
	          .content(patchInJson))
				 .andExpect(jsonPath("$.violations", Matchers.containsInAnyOrder(testValue1,testValue2,testValue3)))
			     .andReturn();
	        
		 String resultSS = result.getResponse().getContentAsString();  
		  assertNotNull(resultSS);   
	    }
	 
	 

}
