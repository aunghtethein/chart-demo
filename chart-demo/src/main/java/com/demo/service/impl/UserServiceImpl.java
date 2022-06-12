package com.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ds.User;
import com.demo.repo.UserRepo;
import com.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo repo;
	
	@Override
	public List<User> findAll() {
		return repo.findAll();
	}
	@Override
	public List<User> listAll(LocalDate startDate, LocalDate endDate) {
		if(startDate != null && endDate != null) {
			return repo.findByDateCreatedBetween(startDate, endDate);
		}
		return repo.findAll();
	}
	

}
