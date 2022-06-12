package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ds.User;
import com.demo.service.UserService;

@RestController
public class UserRestController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "api",method = RequestMethod.GET, produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<User>> findAll() {
		try {
			return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
		}catch(Exception e) {
			return new 	ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
	}
}
