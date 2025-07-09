package com.rfn.controle_equipamentos_ti.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfn.controle_equipamentos_ti.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findUserByEmail(String email);
}