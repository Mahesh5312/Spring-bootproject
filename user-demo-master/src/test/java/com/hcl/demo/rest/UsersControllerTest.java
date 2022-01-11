package com.hcl.demo.rest;

import java.util.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.skyscreamer.jsonassert.JSONAssert;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.hcl.demo.entities.*;
import com.hcl.demo.service.*;
import com.hcl.demo.rest.*;
import com.hcl.demo.helper.DateUtil;
import com.hcl.demo.helper.AuthenticationFacade;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UsersController.class)
@AutoConfigureMockMvc(addFilters = false)

@ContextConfiguration(classes = {UsersService.class, UsersController.class, DateUtil.class, AuthenticationFacade.class})
public class UsersControllerTest {

	@MockBean 
	private UsersService usersService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private DateUtil dateutil;
	
	@Autowired
    private AuthenticationFacade authenticationFacade;
	

	@Test
	public void getUserTest() throws Exception {
   	    String expected = "{\"userId\":5, \"username\":\"teststring\", \"password\":\"teststring\", \"email\":\"teststring\"}";		
		Users users = new Users();
	    users.setUserId(5); 
	    users.setUsername("teststring"); 
	    users.setPassword("teststring"); 
	    users.setEmail("teststring"); 
		Mockito.when(usersService.getUser(Mockito.anyInt()
)).thenReturn(users); 
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/5")
		.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void getallUsersTest() throws Exception {
		List<Users> listObj = new ArrayList<>();
        String expected = "[{\"userId\":5, \"username\":\"teststring\", \"password\":\"teststring\", \"email\":\"teststring\"}]";		
		Users users = new Users();
	    users.setUserId(5); 
	    users.setUsername("teststring"); 
	    users.setPassword("teststring"); 
	    users.setEmail("teststring"); 
		listObj.add(users);
		Mockito.when(usersService.getallUsers(null, null, "teststring"
)).thenReturn(listObj); 
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users?orderBy=teststring")
		.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void createUserTest() throws Exception {
   	    String expected = "{\"userId\":5, \"username\":\"teststring\", \"password\":\"teststring\", \"email\":\"teststring\"}";		
		Users users = new Users();
	    users.setUserId(5); 
	    users.setUsername("teststring"); 
	    users.setPassword("teststring"); 
	    users.setEmail("teststring"); 
		Mockito.when(usersService.createUser(Mockito.any(Users.class))).thenReturn(users); 
		ObjectMapper mapper = new ObjectMapper();	
		String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
		.accept(MediaType.APPLICATION_JSON).content(jsonInString).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void deleteUserTest() throws Exception {
   	    String expected = "{\"userId\":5, \"username\":\"teststring\", \"password\":\"teststring\", \"email\":\"teststring\"}";		
		Users users = new Users();
	    users.setUserId(5); 
	    users.setUsername("teststring"); 
	    users.setPassword("teststring"); 
	    users.setEmail("teststring"); 
		Mockito.when(usersService.deleteUser(Mockito.anyInt()
)).thenReturn(users); 
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users?userId=5")
		.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(), false);
	}
	
}