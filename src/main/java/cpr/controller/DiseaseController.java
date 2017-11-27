package cpr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cpr.model.Disease;
import cpr.model.Patient;
import cpr.service.DepartmentService;
import cpr.service.DiseaseService;

//@PreAuthorize("hasAnyRole('USER')")
@Controller
public class DiseaseController {
	
	@Autowired
	private DiseaseService diseaseService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/disease")
	public String allDepartments(ModelMap model){
		List<Disease> d = diseaseService.findAll();
		model.addAttribute("disease", d.get(0));
		model.addAttribute("departments", departmentService.findAll());
		return "disease";
	}
	
	@PostMapping("/save-disease")
	public String savePatient(@ModelAttribute Disease disease, BindingResult bindingResult, ModelMap model){
		System.out.println(disease.toString());
		return "";
	}

}
