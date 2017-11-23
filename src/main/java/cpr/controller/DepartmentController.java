package cpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import cpr.service.DepartmentService;

@PreAuthorize("hasAnyRole('USER')")
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/all-departments")
	public String allDepartments(ModelMap model){
		model.addAttribute("departments", departmentService.findAll());
		return "allDepartment";
	}
	
}
