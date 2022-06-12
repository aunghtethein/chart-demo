package com.demo.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.ds.Author;
import com.demo.service.AuthorService;

@Controller
public class AuthorController {
	
	private final AuthorService authorService;
	
	public AuthorController(AuthorService authorService) {
		super();
		this.authorService = authorService;
	}

	@GetMapping("/author")
	public String authorForm(Model model) {
		model.addAttribute("author", new Author());
		return "authorForm";
	}
	
	@PostMapping("/author/create")
	public String processAuthor(@Valid Author author, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return "authorForm";
		}
		authorService.save(author);
		redirectAttributes.addFlashAttribute("success",true);
		return "redirect:/author/all";
	}
	@GetMapping("/author/all")
	public String showAllAuthors(Model model) {
		model.addAttribute("authors",authorService.findAllAuthors());
		model.addAttribute("success",model.containsAttribute("success"));
		return "authors";
	}
	
	
	

	
	
	
}
