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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nareshit.rafey.entity.Doctor;
import in.nareshit.rafey.exception.DoctorNotFoundException;
import in.nareshit.rafey.service.IDoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	IDoctorService service;

//	1. show Register page
	@GetMapping("/register")
	public String showReg(@RequestParam(value = "message",required = false)String message,
			Model model
			)  {
		model.addAttribute("message", message);
		return "DoctorRegister";
	}

//	2. save on Submit.
	@PostMapping("/save")
	public String save(@ModelAttribute Doctor doctor, RedirectAttributes attributes) {//read the form data by @ModelAttribute and send the data to ui using @Model
		Long id=service.saveDoctor(doctor);
		attributes.addAttribute("message", "Doctor ("+id+") is created!");
		return "redirect:register";
	}

//	3. display data
	@GetMapping("/all")
	public String display(@RequestParam(value="message", required=false)String message, Model model) {
		List<Doctor> list=service.getAllDoctors();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "DoctorData";
	}
	
//	4. delete by id
	@GetMapping("/delete")
	public String delete(@RequestParam("id")Long id, RedirectAttributes attributes) {
		String message=null;
		try {
			service.removeDoctor(id);
			message="Doctor removed!";
		}catch(DoctorNotFoundException exp) {
			exp.printStackTrace();
			message=exp.getMessage();
		}
		attributes.addAttribute("message", message);
		return "redirect:all";
	}

//	5. show edit
	
	@GetMapping("/edit")
	public String edit(
			@RequestParam("id")Long id,
			Model model,
			RedirectAttributes attributes
			)
	{
		String page = null;
		try {
			Doctor doc = service.getOneDoctor(id);
			model.addAttribute("doctor", doc);
			page = "DoctorEdit";
		} catch (DoctorNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}

//	6. do update
	
	@PostMapping("/update")
	public String doUpdate(
			@ModelAttribute Doctor doctor,
			RedirectAttributes attributes
			) 
	{
		service.updateDoctor(doctor);
		attributes.addAttribute("message", doctor.getId()+", updated!");
		return "redirect:all";
	}

//	7. email and mobile duplicate validation (AJAX)

//	8. excel export

}
