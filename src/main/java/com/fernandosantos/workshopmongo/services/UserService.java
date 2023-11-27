package com.fernandosantos.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandosantos.workshopmongo.domain.User;
import com.fernandosantos.workshopmongo.repository.UserRepository;

@Service
public class UserService{

	@Autowired //para instanciar automaticamente... mecanismo de injeção de dependencia do spring
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	
}
