package cpr.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cpr.dao.PatientRepository;
import cpr.model.Patient;

@Service
@Transactional
public class PatientService {

	private final PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	public List<Patient> findAll(){
		List<Patient> patients = new ArrayList<>();
		for(Patient patient : patientRepository.findAll()){
			patients.add(patient);
		}
		return patients;
	}
	
	public Patient findPatient(int id){
		return patientRepository.findOne(id);
	}
	
	public void save(Patient patient){
		patientRepository.save(patient);
	}
	
	public void delete(int id){
		patientRepository.delete(id);
	}
}
