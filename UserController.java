package com.practise.controlller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practise.entity.User;

@RestController
public class UserController 
{
	    @Autowired
	SessionFactory factory;
		
	// {"username":"akash3","password":"akash","mobno":1234,"emailid":"hh@jk"}
	
	@PostMapping("saveUser")
	public String saveUser(@RequestBody User user)
	{
		Session session=factory.openSession();
		
		Transaction tx=session.beginTransaction();
		
				session.save(user);
				
		tx.commit();
		
		return "record saved";
	}
	
	
	// {"username":"akash3","password":"akash","mobno":1234,"emailid":"hh@jk"}
	
	@PutMapping("updateUser")
	public String updateUser(@RequestBody User user)
	{
		Session session=factory.openSession();
		
		Transaction tx=session.beginTransaction();
		
				session.update(user);
				
		tx.commit();
		
		return "record updated";
	}
	
	
	//localhost:8080/deleteUser/akash
	
	@DeleteMapping("deleteUser/{username}")
	public String deleteUser(@PathVariable String username)
	{
	
		Session session=factory.openSession();
	
		User userfromdb=session.load(User.class, username);
		
		Transaction tx=session.beginTransaction();
		
				session.delete(userfromdb);
				
		tx.commit();
		
		return "record deleted";
	}
	
	
		
	// localhost:8080/getUser/akash
	
	@GetMapping("getUser/{username}")
	public User getUser(@PathVariable String username)
	{
		Session session=factory.openSession();
		
		return session.get(User.class, username);
	}

	
	@GetMapping("getAllUser")
	public List<User> getAllUser()
	{
		Session session=factory.openSession();
		
		return session.createCriteria(User.class).list();
		
	}
	
}
