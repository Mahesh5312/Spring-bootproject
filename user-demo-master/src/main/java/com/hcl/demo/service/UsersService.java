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
 * The Class UsersService.
 */
@Service
@Transactional
public class UsersService implements IUsersService {

	private static final Logger log = LogManager.getLogger(UsersService.class);
	
	@Autowired
	private AuditTrailHelper auditTrailHelper; 
	
	/** The usersRepository. */
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
    private IAuthenticationFacade authenticationFacade;
    
    
	/**
	 * getUser.
	 *
     * @param userId the userId
	 * @return the Users
	 */
	@Override
	public Users getUser(int userId) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the UsersService.getUser method with UserId: {}",userId);
		Users usersResponse = usersRepository.findById(userId).orElse(null);
		log.info("Exiting from the UsersService.getUser method with UserId: {} in {}ms", userId, System.currentTimeMillis()-startTime);
		return usersResponse;
		
	}
	
	/**
	 * getallUsers.
	 *
     * @param page the page
     * @param size the size
     * @param orderBy the orderBy
	 * @return the List of Users
	 */
	@Override
	public List<Users> getallUsers(Integer page, Integer size, String orderBy) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the UsersService.getallUsers method ");
		List<Users> list = new ArrayList<>();
		if(page == null &&(orderBy == null||orderBy.isEmpty())  ) { 
			list.addAll((List<Users>)usersRepository.findAll());
		 } else if(page != null &&(orderBy == null||orderBy.isEmpty())  ) { 
			list.addAll((List<Users>)usersRepository.findAll(PageRequest.of(page,size)).getContent());
		 } else if(page != null &&(orderBy != null && !orderBy.isEmpty())  ) {
			list.addAll((List<Users>)usersRepository.findAll(PageRequest.of(page, size, Sort.by(orderBy).descending())).getContent());
		 } else {
			 list.addAll((List<Users>)usersRepository.findAll(Sort.by(orderBy).descending()));
		 }
		log.info("Exiting from the UsersService.getallUsers method in {}ms", System.currentTimeMillis()-startTime);
		return list;
		
	}
	
	/**
	 * createUser.
	 *
     * @param createUser the createUser
	 * @return the Users
	 */
	@Override
	public Users createUser(Users createUser) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the UsersService.createUser method ");
		auditTrailHelper.createAuditTrail(createUser);
		Users usersResponse = usersRepository.save(createUser);
		log.info("Exiting from the UsersService.createUser method in {}ms", System.currentTimeMillis()-startTime);
		return usersResponse;
		
	}
	
	/**
	 * deleteUser.
	 *
     * @param userId the userId
	 * @return the Users
	 */
	@Override
	public Users deleteUser(int userId) {
		long startTime = System.currentTimeMillis();
		log.info("Entering into the UsersService.deleteUser");
		usersRepository.deleteById(userId);
		log.info("Exiting from the UsersService.deleteUser method in {}ms", System.currentTimeMillis()-startTime);
		return null;
		
	}
	
       
}