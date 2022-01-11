package com.hcl.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.hcl.demo.entities.*;
import com.hcl.demo.repository.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import com.hcl.demo.helper.*;
import com.hcl.demo.helper.IAuthenticationFacade;

/**
 * The Interface IUsersService.
 */
public interface IUsersService{

	/**
	 * getUser.
	 *
     * @param userId the userId
	  * @return the Users
	 */
	Users getUser(int userId);
	
	/**
	 * getallUsers.
	 *
     * @param page the page
     * @param size the size
     * @param orderBy the orderBy
	  * @return the List of Users
	 */
	List<Users> getallUsers(Integer page, Integer size, String orderBy);
	
	/**
	 * createUser.
	 *
     * @param createUser the createUser
	  * @return the Users
	 */
	Users createUser(Users createUser);
	
	/**
	 * deleteUser.
	 *
     * @param userId the userId
	  * @return the Users
	 */
	Users deleteUser(int userId);
	

}
