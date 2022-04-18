package in.nareshit.rafey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.rafey.entity.Specialization;
import in.nareshit.rafey.exception.SpecializationNotFoundException;
import in.nareshit.rafey.service.ISpecializationService;
import in.nareshit.rafey.view.SpecializationExcelView;

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
	 * (to read form data.)
	 */
	
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization specialization, Model model) {
		Long id=service.saveSpecialization(specialization);
		String msg="Record ("+id+") is created!";
		model.addAttribute("msg",msg);
		return "SpecializationRegister";
	}
	
	/*
	 *  3.Display all Specializaion
	 */
	
	 
	@GetMapping("/all")
	public String viewAll(Model model,
			@RequestParam(value="message", required=false) String message) {
		List<Specialization> list=service.getAllSpecializations();
//		System.out.println(list.getClass().getName());
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "SpecializationData";
	}
	
	/*
	 *4. Delete by id
	 */
	@GetMapping("/delete")
	public String deleteData(@RequestParam Long id,
			RedirectAttributes attributes) {
		try {
		service.removeSpecialization(id);
		attributes.addAttribute("message", "Record ("+id+") is removed");
		}catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}
	
	/*
	 * 5. fetch data into edit page by id
	 * 
	 * End user clicks on link, may enter ID manually.
	 * if entered id is present in DB
	 * > load Row as Object
	 * > Send to Edit page
	 * > Fill in form
	 * else
	 * > redirect to all (data page)
	 * > show error message (Not found)
	 */
	
//	@GetMapping("/edit")
//	public String showEditPage(@RequestParam Long id,Model model){
//		Specialization spec=service.getOneSpecialization(id);
//		model.addAttribute("specialization", spec);
//		return "SpecializationEdit";
//	}
	
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id,Model model, RedirectAttributes attributes){
		String page=null;
		try{
			Specialization spec=service.getOneSpecialization(id);
			model.addAttribute("specialization", spec);
			page="SpecializationEdit";
		}catch(SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page="redirect:all";
		}
		return page;
	}
	
	/**
	 * 6. Update Form data and redirect to all
	 */
	
	@PostMapping("/update")
	public String updateData(@ModelAttribute Specialization specialization, RedirectAttributes attributes) {
		service.updateSpecialization(specialization);
		attributes.addAttribute("message", "Record ("+specialization.getId()+") is updated");
		return "redirect:all";
	}
	
	/**
	 *  7. Read code and check with service
	 *  	Return message back to UI(from same page from where request has came)
	 */
	
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code, @RequestParam Long id) {
		String message="";
		if(service.isSpecCodeExist(code)) {   //register check
			message=code + ", already exist";
		}else if(id!=0 && service.isSpecCodeExistForEdit(code, id)) { //edit check
			message=code + ",already exist";
		}
		return message;
	}
	
	/**
	 * 8. Export data to Excel file
	 */
	
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m=new ModelAndView();
		m.setView(new SpecializationExcelView());
		
//		read data from db
		List<Specialization> list=service.getAllSpecializations();
		
//		send to Excel Impl class
		m.addObject("list", list);
		return m;
	}
}	
