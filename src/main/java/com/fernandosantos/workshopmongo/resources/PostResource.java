package com.fernandosantos.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernandosantos.workshopmongo.domain.Post;
import com.fernandosantos.workshopmongo.resources.util.URL;
import com.fernandosantos.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	

	
	@Autowired
	private PostService service; //fiz uma confusão no commit anteriores, inverti as classes postResource com userResource. agora está de acordo
	
																							

	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) //@PathVariable indica que o id informado no método casa com o informa no Id do requeste, la no value = "/{id}"
	{
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(value = "/titlesearch",method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text",defaultValue = "") String text) //@PathVariable indica que o id informado no método casa com o informa no Id do requeste, la no value = "/{id}"
	{
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);

	}
	
	@RequestMapping(value = "/fullsearch",method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch
	(@RequestParam(value = "text",defaultValue = "") String text,
	@RequestParam(value = "minDate",defaultValue = "") String minDate,
	@RequestParam(value = "maxDate",defaultValue = "") String maxDate
			) 
	{
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L)); //new date aqui gera data minima do sistema
		Date max = URL.convertDate(maxDate, new Date()); //new date aqui gera data maxima do sistema
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);

	}
	
	
}

