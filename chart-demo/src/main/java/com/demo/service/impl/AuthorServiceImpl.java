package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ds.Author;
import com.demo.repo.AuthorRepository;
import com.demo.service.AuthorService;


@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepo;
	@Override
	public Author save(Author author) {
	
		return authorRepo.save(author);
	}

	@Override
	public Author findAuthor(int id) {
	
		return authorRepo.findById(id).orElse(null);
	}

	@Override
	public List<Author> findAllAuthors() {
	
		return authorRepo.findAll();
	}
	
	
	
	

	

}
