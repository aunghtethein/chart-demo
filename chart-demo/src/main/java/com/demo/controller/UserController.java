package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.demo.ds.User;
import com.demo.repo.UserRepo;
import com.demo.service.UserService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Controller
public class UserController {
	@Autowired
    private UserRepo userRepo;
	@Autowired
	private UserService userService;
    
    
    @GetMapping("/")
	public String index() {
		return "home";
	}

    @GetMapping("/add-user")
    public String showSignupForm(@ModelAttribute User user, Model model){
    	model.addAttribute("user",new User());
        return "add-user";
    }
    @PostMapping("/add-process")
    public String addUser(@ModelAttribute @Valid User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "add-user";
        }
        userRepo.save(user);
        return "redirect:/user-list";
    }
    @GetMapping("/user-list")
	public String showUserList(Model model, @RequestParam(value = "startDate",required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,@RequestParam(value = "endDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate ) {
		
		model.addAttribute("users",userService.listAll(startDate, endDate));
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		
	
		return "user-list";
	}
   
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable int id, Model model){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("INVALID USER ID" + id));
        model.addAttribute("user",user);
        return "update-user";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable int id,@Valid User user,BindingResult result,Model model){
        if (result.hasErrors()){
            user.setId(id);
            return "update-user";
        }
        userRepo.save(user);
        return "redirect:/user-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id,Model model){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("INVALID USER ID :" + id));
        userRepo.delete(user);
        return "redirect:/user-list";
    }
   /*
    @GetMapping("/chart")
	public String getAll(Model model) {	
		List<String> name= userService.findAll().stream().map(x->x.getName()).collect(Collectors.toList());
		List<Integer> ot = userService.findAll().stream().map(x->x.getOt()).collect(Collectors.toList());
		model.addAttribute("name", name);
		model.addAttribute("ot", ot);
	return "barchart";
	
	}
	*/
    @GetMapping("/chart")
	public String showChartList(Model model, @RequestParam(value = "startDate",required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,@RequestParam(value = "endDate",required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate ) {
		
    	if(startDate != null && endDate != null) {
    		List<String> name= userService.listAll(startDate, endDate).stream().map(x->x.getName()).collect(Collectors.toList());
    		List<Integer> ot = userService.listAll(startDate, endDate).stream().map(x->x.getOt()).collect(Collectors.toList());
    		model.addAttribute("name", name);
    		model.addAttribute("ot", ot);
    	}else {
    		List<String> name= userService.findAll().stream().map(x->x.getName()).collect(Collectors.toList());
    		List<Integer> ot = userService.findAll().stream().map(x->x.getOt()).collect(Collectors.toList());
    		model.addAttribute("name", name);
    		model.addAttribute("ot", ot);
    	}
    	
		return "barchart";
	}
    
    
    
   
    /*
    @RequestMapping("/linechartdata")
	@ResponseBody
	public String getDataFromDB(){
		List<User> dataList = dataDAO.findAll();
		JsonArray jsonArrayCategory = new JsonArray();
		JsonArray jsonArraySeries = new JsonArray();
		JsonObject jsonObject = new JsonObject();
		dataList.forEach(data->{
			jsonArrayCategory.add(data.getCategory());
			jsonArraySeries.add(data.getSeries());
		});
		jsonObject.add("categories", jsonArrayCategory);
		jsonObject.add("series", jsonArraySeries);
		return jsonObject.toString();
	}
    */
 
}
