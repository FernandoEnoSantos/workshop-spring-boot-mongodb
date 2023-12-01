package com.fernandosantos.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandosantos.workshopmongo.domain.Post;
import com.fernandosantos.workshopmongo.repository.PostRepository;
import com.fernandosantos.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService{

	@Autowired //para instanciar automaticamente... mecanismo de injeção de dependencia do spring
	private PostRepository repo;
	
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")); //se fizer orElseThrow já satisfaz a necessidade de tratar se o obj não for User
																							//Nesse caso retorna usuário(se tiver) ou lança exceção personalizada
																							//Contempla o Optional
	}
	
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
	}

	
	
}
