package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer>{

	Roles findByName(String roleName);
}