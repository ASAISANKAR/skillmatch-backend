package com.klu.jfsd.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klu.jfsd.project.entity.Professional;
import com.klu.jfsd.project.service.ProfessionalService;

@RestController
@RequestMapping("/professional")
@CrossOrigin
public class ProfessionalController {

	@Autowired
	ProfessionalService profser;
	
	@PostMapping("/add")
	public Professional addProfessional(@RequestBody Professional p)
	{
		System.out.println(p.getProfilePhoto());
		System.out.println(profser.addProf(p));
		return p;
		
	}
}
