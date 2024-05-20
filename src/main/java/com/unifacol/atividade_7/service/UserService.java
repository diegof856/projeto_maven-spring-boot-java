package com.unifacol.atividade_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifacol.atividade_7.model.User;
import com.unifacol.atividade_7.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public void addUser(User user) {
		repository.save(user);
	}
	
	public Long searchByName(String name) {
		User user = repository.findByName(name);
		return user.getId();
	}
	
	public User findById(Long id) {
		User user  = repository.getReferenceById(id) ;
		return user;
	}
	
	public User updateById(Long id, String name, String email) {
		
		 User user = repository.getReferenceById(id);
		 user.setName(name);
		 user.setEmail(email);
		 return repository.save(user);
	}
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
