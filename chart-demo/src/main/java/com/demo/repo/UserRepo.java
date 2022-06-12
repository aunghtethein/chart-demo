package com.demo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.ds.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	List<User> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate);

}
