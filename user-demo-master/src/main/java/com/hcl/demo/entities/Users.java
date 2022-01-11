package com.hcl.demo.entities;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import java.util.*;

/**
* The class Users
*/

@Entity
@Table (name="Users")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	/** The userId .*/
	@Column(name="userId")
	private Integer userId;
	
	/** The username .*/
	@Column(name="username")
	private String username;
	
	/** The password .*/
	@Column(name="password")
	private String password;
	
	/** The email .*/
	@Column(name="email")
	private String email;
    
}