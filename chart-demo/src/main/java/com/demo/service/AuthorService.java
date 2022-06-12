package com.demo.service;

import java.util.List;


import com.demo.ds.Author;

public interface AuthorService {
	Author save(Author author);
	Author findAuthor(int id);
	List<Author> findAllAuthors();
}
