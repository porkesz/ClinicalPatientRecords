package cpr.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cpr.dao.EmployeRepository;
import cpr.model.Employe;

@Service
@Transactional
public class EmployeService {

	private final EmployeRepository employeRepository;

	public EmployeService(EmployeRepository employeRepository) {
		this.employeRepository = employeRepository;
	}
	
	public List<Employe> findAll(){
		List<Employe> employees = new ArrayList<>();
		for(Employe employe : employeRepository.findAll()){
			employees.add(employe);
		}
		return employees;
	}
	
	public Employe findEmploye(int id){
		return employeRepository.findOne(id);
	}
	
	public void save(Employe employe){
		employeRepository.save(employe);
	}
	
	public void delete(int id){
		employeRepository.delete(id);
	}

}
