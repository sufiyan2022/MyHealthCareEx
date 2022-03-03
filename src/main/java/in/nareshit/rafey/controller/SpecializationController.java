package in.nareshit.rafey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.rafey.entity.Specialization;
import in.nareshit.rafey.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;
	
	/*
	 * 1.Show Register Page
	 */
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";
	}
	
	
	/*
	 * 2. On Submit Form save Data
	 */
	
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization specialization, Model model) {
		Long id=service.saveSpecialization(specialization);
		String msg="Record ("+id+") is created!";
		model.addAttribute("msg",msg);
		return "SpecializationRegister";
	}
	
	/*
	 *  1.Display all Specializaion
	 */
	
	 
	@GetMapping("/all")
	public String viewAll(Model model) {
		List<Specialization> list=service.getAllSpecializations();
		model.addAttribute("list", list);
		return "SpecializationData";
	}
}
