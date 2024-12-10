package com.klu.jfsd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.jfsd.project.entity.Log;

public interface LogRepository extends JpaRepository<Log, Integer>{
	

}
