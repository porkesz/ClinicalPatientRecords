package cpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cpr.model.Patient;
import cpr.service.PatientService;

@Controller
public class MainController {
	
	@Autowired
	private PatientService patientService;

	@GetMapping("/")
	public String home(){
		return "index";
	}
	
	@GetMapping("/all-patients")
	public String allPatients(ModelMap model){
		model.addAttribute("patients", patientService.findAll());
		return "allPatient";
	}
	
	@GetMapping("/new-patient")
	public String newPatient(){
		return "newPatient";
	}
	
	@PostMapping("/save-patient")
	public String savePatient(@ModelAttribute Patient patient, BindingResult bindingResult, ModelMap model){
		patientService.save(patient);
		model.addAttribute("patients", patientService.findAll());
		return "allPatient";
	}
	
	@GetMapping("/update-patient")
	public String updatePatient(@RequestParam int id, ModelMap model){
		model.addAttribute("patient", patientService.findPatient(id));
		return "newPatient";
	}
	
	@GetMapping("/delete-patient")
	public String deletePatient(@RequestParam int id, ModelMap model){
		patientService.delete(id);
		model.addAttribute("patients", patientService.findAll());
		return "allPatient";
	}
}
