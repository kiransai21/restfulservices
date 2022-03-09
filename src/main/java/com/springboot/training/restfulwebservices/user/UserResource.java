package com.springboot.training.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	private UserDAO userDao;

	@GetMapping(path = "/find-user/{id}")
	public EntityModel<User> findUser(@PathVariable int id){
		User user = userDao.findUser(id);
		if(user == null)
			throw new UserNotFoundException("id- "+id);
		
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkToAllUsers = linkTo(methodOn(this.getClass()).getAllUsers());
		model.add(linkToAllUsers.withRel("all - users"));
		
		return model;
	}
	
	@GetMapping(path = "/get-users")
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	@PostMapping(path = "/add-user")
		public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
			User savedUser = userDao.addUser(user);
			URI uri = ServletUriComponentsBuilder.
					fromPath("/find-user/{id}").buildAndExpand(savedUser.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
	
	@DeleteMapping(path = "/delete-user/{id}")
		public String deleteUser(@PathVariable int id){
			User user = userDao.deleteUser(id);
			if(user == null){
				throw new UserNotFoundException("id- "+id);
			}
			return "User with id number " +user.getName()+ " is deleted";
		}
	
}
