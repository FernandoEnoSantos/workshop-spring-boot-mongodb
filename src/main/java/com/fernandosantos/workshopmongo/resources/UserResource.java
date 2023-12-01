package com.fernandosantos.workshopmongo.resources;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fernandosantos.workshopmongo.domain.User;
import com.fernandosantos.workshopmongo.dto.UserDTO;
import com.fernandosantos.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET) //poderia ser anotation @GetMapping no lugar
	public ResponseEntity<List<UserDTO>> findAll(){
		
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); //1 transforma List<User> em Stream para poder aplicar função Lambda.
		return ResponseEntity.ok().body(listDTO);													 //2 o .map pega cada obj da lista original e aplica a função, logo para X faça um new UserDTO(X) instancia com o proprio obj				
	}																								//3 e ai o collect(Collectors.toList() volta para um lista só que agora de DTO

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) //@PathVariable indica que o id informado no método casa com o informa no Id do requeste, la no value = "/{id}"
	{
		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
		
	}
}

