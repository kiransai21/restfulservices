package com.springboot.training.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	
	private static ArrayList<User> users = new ArrayList<>();
	private int count = 3;
	
	static{
		users.add(new User(1, "sai", new Date()));
		users.add(new User(2, "kiran", new Date()));
		users.add(new User(3, "Manasa", new Date()));
	}
	
	public User findUser(int id){
		
		for(User user: users){
			if( id == user.getId()){
				return user;
			}
		}
		return null;
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User addUser(User user){
		if(user.getId() == null){
			user.setId(++count);
		}
		users.add(user);
		return user;
	}
	
	public User deleteUser(int id){
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()){
			User user = iterator.next();
			if(user.getId() == id){
				iterator.remove();
				return user;
			}
		}
		return null;		
	}

}
