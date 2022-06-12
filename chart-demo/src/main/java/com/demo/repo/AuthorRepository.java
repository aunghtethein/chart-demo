package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ds.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
