package com.klu.jfsd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.jfsd.project.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
