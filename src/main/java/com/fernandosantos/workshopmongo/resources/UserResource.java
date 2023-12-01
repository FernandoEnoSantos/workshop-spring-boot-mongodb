package com.fernandosantos.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fernandosantos.workshopmongo.domain.Post;
import com.fernandosantos.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class UserResource {
	
	@Autowired
	private PostService service;
	
																							//3 e ai o collect(Collectors.toList() volta para um lista só que agora de DTO

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) //@PathVariable indica que o id informado no método casa com o informa no Id do requeste, la no value = "/{id}"
	{
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	
	
}

