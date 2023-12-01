package com.fernandosantos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandosantos.workshopmongo.domain.User;
import com.fernandosantos.workshopmongo.dto.UserDTO;
import com.fernandosantos.workshopmongo.repository.UserRepository;
import com.fernandosantos.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService{

	@Autowired //para instanciar automaticamente... mecanismo de injeção de dependencia do spring
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); //se fizer orElseThrow já satisfaz a necessidade de tratar se o obj não for User
																							//Nesse caso retorna usuário(se tiver) ou lança exceção personalizada
																							//Contempla o Optional
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
		
	}
}
