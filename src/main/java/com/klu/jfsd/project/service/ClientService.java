package com.klu.jfsd.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.klu.jfsd.project.entity.Client;
import com.klu.jfsd.project.entity.Professional;
import com.klu.jfsd.project.entity.User;
import com.klu.jfsd.project.repository.ClientRepository;
import com.klu.jfsd.project.repository.ProfessionalRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository repo;
	@Autowired
	UserService userservice;
	
	@Autowired
	ProfessionalRepository profrepo;
	
	public String addClient(Client c)
	{
		repo.save(c);
		User u=new User();
		u.setUsername(c.getName());
		u.setPassword(c.getPassword());
		u.setRole("client");
		u.setRole_specified_id(c.getId());
		userservice.createUser(u);
		
		return "Client Added Successfully";
	}
	
	public List<Professional> getProf()
	{
		return profrepo.findAll();
	}

	public List<Client> retClients() {
		return repo.findAll();
	}
	
	
	@Autowired
	JavaMailSender mail;
	public String sendEmail(String fromemail,String toemail,String subject,String text)
	{
		try
		{
		SimpleMailMessage smm=new SimpleMailMessage();
		smm.setFrom(fromemail);
		smm.setTo(toemail);
		smm.setSubject(subject);
		smm.setText(text);
		mail.send(smm);
		return "email sent Successfully";
		}
		catch (Exception e) 
		{
			return e.getMessage();
		}
		
	
	}
	
	
	
	
}
