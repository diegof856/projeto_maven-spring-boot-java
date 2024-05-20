package com.unifacol.atividade_7.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.unifacol.atividade_7.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	
}