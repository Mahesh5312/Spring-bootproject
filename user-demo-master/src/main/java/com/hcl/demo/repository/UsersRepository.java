package com.hcl.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.hcl.demo.entities.Users;

/**
 * The Interface UsersRepository.
 */
public interface UsersRepository extends JpaRepository<Users, Integer>{

}
