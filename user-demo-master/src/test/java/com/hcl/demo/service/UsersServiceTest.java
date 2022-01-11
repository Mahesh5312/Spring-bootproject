package com.hcl.demo.service;

import java.util.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;
import org.springframework.http.MediaType;
import java.io.File;
import org.springframework.test.web.servlet.*;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.ContextConfiguration;
import java.io.File;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.demo.entities.*;
import com.hcl.demo.service.*;
import com.hcl.demo.rest.*;
import com.hcl.demo.repository.*;
import com.hcl.demo.helper.DateUtil;
import com.hcl.demo.helper.AuditTrailHelper;
import com.hcl.demo.helper.AuthenticationFacade;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UsersService.class)
@AutoConfigureMockMvc(addFilters = false)

@ContextConfiguration(classes = {UsersService.class, UsersRepository.class, DateUtil.class, AuthenticationFacade.class, AuditTrailHelper.class})
public class UsersServiceTest {

	@MockBean
	private UsersRepository usersRepository;
	@InjectMocks
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private DateUtil dateutil;
	@Autowired
	private AuditTrailHelper auditTrailHelper;
	@Autowired
    private AuthenticationFacade authenticationFacade;

	@Test
	public void getUserTest() throws Exception {
		Users users = new Users();
	    users.setUserId(5); 
	    users.setUsername("teststring"); 
	    users.setPassword("teststring"); 
	    users.setEmail("teststring"); 
		Optional<Users> usersobj = Optional.of(users);
		Mockito.when(usersRepository.findById(Mockito.anyInt()
)).thenReturn(usersobj); 
		Assertions.assertEquals(users, usersService.getUser(5));
		Mockito.verify(usersRepository, Mockito.times(1)).findById(5); 
	}
	@Test
	public void getallUsersTest() throws Exception {
		List<Users> listObj = new ArrayList<>();
		Users users = new Users();
	    users.setUserId(5); 
	    users.setUsername("teststring"); 
	    users.setPassword("teststring"); 
	    users.setEmail("teststring"); 
		listObj.add(users);
		Mockito.when(usersRepository.findAll()).thenReturn(listObj);
		Assertions.assertEquals(listObj, usersService.getallUsers(null, null, ""));
		Mockito.verify(usersRepository, Mockito.times(1)).findAll(); 
	}
	@Test
	public void createUserTest() throws Exception {
		Users users = new Users();
	    users.setUserId(5); 
	    users.setUsername("teststring"); 
	    users.setPassword("teststring"); 
	    users.setEmail("teststring"); 
		Mockito.when(usersRepository.save(Mockito.any(Users.class))).thenReturn(users); 
		Assertions.assertEquals(users, usersService.createUser(users));
		Mockito.verify(usersRepository, Mockito.times(1)).save(users);
	}
	@Test
	public void deleteUserTest() throws Exception {
		Users users = new Users();
	    users.setUserId(5); 
	    users.setUsername("teststring"); 
	    users.setPassword("teststring"); 
	    users.setEmail("teststring"); 
		Mockito.when(usersRepository.findById(Mockito.anyInt()
)).thenReturn(Optional.of(users)).thenReturn(null); 
		Assertions.assertEquals(null, usersService.deleteUser(5));
		Mockito.verify(usersRepository, Mockito.times(1)).deleteById(5);
	}
}