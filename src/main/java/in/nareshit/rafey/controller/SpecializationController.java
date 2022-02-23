package in.nareshit.rafey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.nareshit.rafey.entity.Specialization;
import in.nareshit.rafey.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;
	
	@GetMapping("/all")
	public String viewAll(Model model) {
		List<Specialization> list=service.getAllSpecializations();
		model.addAttribute("list", list);
		return "SpecializationData";
	}
}
