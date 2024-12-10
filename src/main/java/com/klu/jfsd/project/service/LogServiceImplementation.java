package com.klu.jfsd.project.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.klu.jfsd.project.entity.Log;
import com.klu.jfsd.project.repository.LogRepository;




@Service
public class LogServiceImplementation implements LogService{
	@Autowired
	private LogRepository logrepo;

	@Override
	public String LogRegistration(Log log) {
		logrepo.save(log);
		return "Log Saved";
	}

}
