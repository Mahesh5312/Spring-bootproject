package com.hcl.demo.rest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hcl.demo.entities.*;
import com.hcl.demo.service.*;
import com.hcl.demo.helper.IAuthenticationFacade;

/**
* The class UsersController
*/
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsersController {

	private static final Logger log = LogManager.getLogger(UsersController.class);

	/** The usersService. */
	@Autowired
	private IUsersService usersService;

	@Autowired
    private IAuthenticationFacade authenticationFacade;	

	/**
	 *<b> 
	 *<p> Get a single Users Details
	 *
     * @param userId the userId
	 * @return the Users
	 */
	 
	 
	@RequestMapping(value = "/users/{userId}", method=RequestMethod.GET  , produces={"application/json"})
	public Users getUser(@PathVariable(value="userId", required=true) int userId) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the UsersController.getUser method with UserId: {}", userId);
		Users usersResponse = usersService.getUser(userId);
		log.info("Exiting from the UsersController.getUser method with UserId: {} in {}ms \n", userId, System.currentTimeMillis()-startTime);
		return usersResponse;
	}
	
	/**
	 *<b> 
	 *<p> Get all Users
	 *
     * @param page the page
     * @param size the size
     * @param orderBy the orderBy
	 * @return the List of Users
	 */
	 
	 
	@RequestMapping(value = "/users", method=RequestMethod.GET  , produces={"application/json"})
	public List<Users> getallUsers(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="size", required=false) Integer size, @RequestParam(value="orderBy", required=false) String orderBy) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the UsersController.getallUsers method ");
		List<Users> usersResponse = usersService.getallUsers(page, size, orderBy);
		log.info("Exiting from the UsersController.getallUsers method in {}ms \n", System.currentTimeMillis()-startTime);
		return usersResponse;
	}
	
	/**
	 *<b> 
	 *<p> User Creation Service
	 *
     * @param CreateUser the CreateUser
	 * @return the Users
	 */
	 
	 
	@RequestMapping(value = "/users", method=RequestMethod.POST , consumes={"application/json"} , produces={"application/json"})
	public Users createUser(@RequestBody(required=true) Users CreateUser) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the UsersController.createUser method ");
		Users usersResponse = usersService.createUser(CreateUser);
		log.info("Exiting from the UsersController.createUser method in {}ms \n", System.currentTimeMillis()-startTime);
		return usersResponse;
	}
	
	/**
	 *<b> 
	 *<p> User Deletion Service
	 *
     * @param userId the userId
	 * @return the Users
	 */
	 
	 
	@RequestMapping(value = "/users", method=RequestMethod.DELETE  , produces={"application/json"})
	public Users deleteUser(@RequestParam(value="userId", required=true) int userId) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the UsersController.deleteUser method ");
		Users usersResponse = usersService.deleteUser(userId);
		log.info("Exiting from the UsersController.deleteUser method in {}ms \n", System.currentTimeMillis()-startTime);
		return usersResponse;
	}
	

}