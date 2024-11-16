package com.klu.jfsd.project.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.klu.jfsd.project.entity.Professional;
import com.klu.jfsd.project.entity.User;
import com.klu.jfsd.project.repository.ProfessionalRepository;

@Service
public class ProfessionalService {

	@Autowired
	ProfessionalRepository profrepo;
	
	@Autowired
	UserService userservice;
	
	
	public String addProf(Professional p)
	{
		profrepo.save(p);
		User u=new User();
		u.setUsername(p.getName());
		u.setPassword(p.getPassword());
		u.setRole("professional");
		u.setRole_specified_id(p.getId());
		userservice.createUser(u);
		
		return "Professional Added Successfully";
	}
}
