package com.unifacol.atividade_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unifacol.atividade_7.model.User;
import com.unifacol.atividade_7.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public void addUser(User user) { //Adiciona um novo usuário ao repositório.
		repository.save(user); 
	}

	public Long searchByName(String name) {
		User user = repository.findByName(name); //Busca um usuário pelo nome e retorna seu ID.
		return user.getId();
	}

	public User findById(Long id) {
		User user = repository.getReferenceById(id); //Busca um usuário pelo ID.
		return user;
	}

	public User updateById(Long id, String name, String email) { //Atualiza o nome e email de um usuário pelo ID.

		User user = repository.getReferenceById(id);
		user.setName(name);
		user.setEmail(email);
		return repository.save(user);
	}

	public void deleteById(Long id) { //Deleta um usuário pelo ID.
		repository.deleteById(id);
	}

}
