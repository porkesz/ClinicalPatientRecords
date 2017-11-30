package cpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cpr.model.Patient;
import cpr.service.DiseaseService;
import cpr.service.PatientService;

@PreAuthorize("hasAnyRole('USER')")
@Controller
public class MainController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private DiseaseService diseaseService;
	
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
		if (patientService.validation(patient)==true) {
			patientService.save(patient);
			model.addAttribute("patients", patientService.findAll());
			return "allPatient";
		}
		model.addAttribute("error",true);
		return "newPatient";
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
	
	@RequestMapping(value = "/patient", method = {RequestMethod.POST, RequestMethod.GET})
	public String patient(@RequestParam int id, ModelMap model) {
		Patient patient = patientService.findPatient(id);
		model.addAttribute("patient", patient);
		try {
			model.addAttribute("diseases", diseaseService.getDiseaseByPatient(patient));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "patient";
	}
}
