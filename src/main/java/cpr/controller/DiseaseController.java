package cpr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cpr.model.Disease;
import cpr.model.Employe;
import cpr.model.Patient;
import cpr.service.DepartmentService;
import cpr.service.DiseaseService;
import cpr.service.EmployeService;
import cpr.service.PatientService;

//@PreAuthorize("hasAnyRole('USER')")
@Controller
public class DiseaseController {
	
	@Autowired
	private DiseaseService diseaseService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private EmployeService employeService;
	
	@GetMapping("/new-disease")
	public String newDisease(@RequestParam int patientId, ModelMap model){
		model.addAttribute("patient", patientService.findPatient(patientId));
		model.addAttribute("employe", getSignedEmploye());
		return "disease";
	}
	
	@GetMapping("/update-disease")
	public String updateDisease(@RequestParam int patientId, @RequestParam int diseaseId, ModelMap model){
		model.addAttribute("disease", diseaseService.findDisease(diseaseId));
		model.addAttribute("patient", patientService.findPatient(patientId));
		model.addAttribute("employe", getSignedEmploye());
		return "disease";
	}
	
	@PostMapping("/save-disease")
	public String saveDisease(@ModelAttribute Disease disease, BindingResult bindingResult, ModelMap model){
		diseaseService.save(disease);
		return "forward:/patient?id=" + disease.getPatient().getId();
	}
	
	@GetMapping("/delete-disease")
	public String deleteDisease(@RequestParam int diseaseId, @RequestParam int patientId, ModelMap model){
		diseaseService.delete(diseaseId);
		return "forward:/patient?id=" + patientId;
	}

	private Employe getSignedEmploye(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Employe actEmploye = new Employe();
		
		try {
			actEmploye = employeService.getEmployeByEmail(auth.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return actEmploye;
	}
}
