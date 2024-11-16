package com.klu.jfsd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.klu.jfsd.project.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	 User findByUsernameAndPassword(String username,String password);

}
