package com.fernandosantos.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fernandosantos.workshopmongo.domain.Post;
import com.fernandosantos.workshopmongo.domain.User;
import com.fernandosantos.workshopmongo.dto.UserDTO;
import com.fernandosantos.workshopmongo.services.PostService;
import com.fernandosantos.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;//fiz uma confusão no commit anteriores, inverti as classes postResource com userResource. agora está de acordo
	
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> Delete(@PathVariable String id) 
	{
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto,@PathVariable String id){
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
		
	}
	
	@RequestMapping(value = "/{id}/posts",method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) 
	{
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
		
	}
	
	
	
	
	
	
}

