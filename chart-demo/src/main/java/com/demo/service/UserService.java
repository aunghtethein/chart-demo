package com.demo.service;

import java.time.LocalDate;
import java.util.List;
import com.demo.ds.User;

public interface UserService {

	List<User> findAll();
	List<User> listAll(LocalDate startDate, LocalDate endDate);
}
