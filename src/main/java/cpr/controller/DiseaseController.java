package cpr.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cpr.model.Disease;
import cpr.model.Employe;
import cpr.service.DiseaseService;
import cpr.service.EmployeService;
import cpr.service.PatientService;

@PreAuthorize("hasAnyRole('USER')")
@Controller
public class DiseaseController {

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private EmployeService employeService;

	@GetMapping("/new-disease")
	public String newDisease(@RequestParam int patientId, ModelMap model) {
		model.addAttribute("patient", patientService.findPatient(patientId));
		model.addAttribute("employe", getSignedEmploye());
		return "disease";
	}

	@RequestMapping(value = "/update-disease", method = {RequestMethod.POST, RequestMethod.GET})
	public String updateDisease(@RequestParam int patientId, @RequestParam int diseaseId, @RequestParam(value="error", required = false, defaultValue = "0") int error, ModelMap model) {
		model.addAttribute("disease", diseaseService.findDisease(diseaseId));
		model.addAttribute("patient", patientService.findPatient(patientId));
		model.addAttribute("employe", getSignedEmploye());
		if (error == 1) {
			model.addAttribute("error", true);
		}
		return "disease";
	}

	@PostMapping("/save-disease")
	public String saveDisease(@ModelAttribute Disease disease, BindingResult bindingResult, ModelMap model, String description, String start_date, String end_date) {
		if (diseaseService.validator(disease) == true) {
			if (disease.getEndDate() == "") {
				disease.setEndDate(null);
			}
			diseaseService.save(disease);
			return "forward:/patient?id=" + disease.getPatient().getId();
		}
		else {
			return "forward:/update-disease?patientId=" + disease.getPatient().getId() + "&diseaseId=" + disease.getId() + "&error=1";
		}
	}

	@GetMapping("/delete-disease")
	public String deleteDisease(@RequestParam int diseaseId, @RequestParam int patientId, ModelMap model) {
		diseaseService.delete(diseaseId);
		return "forward:/patient?id=" + patientId;
	}

	private Employe getSignedEmploye() {
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
