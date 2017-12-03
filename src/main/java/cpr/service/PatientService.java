package cpr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public boolean validation(Patient patient) {
		String name=patient.getFirstname()+patient.getLastname();
		for (int i=0;i<name.length();i++) {
			int c=name.charAt(i);
			if ( !(((c>=65) && (c<=90)) || ((c>=97) && (c<=122))) ) {		
				return false;
			}
		}
		
		String email =patient.getEmail();
		Pattern p = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();
		if (!matchFound) {
		    return false;
		}
		
		String phone=patient.getTelephone();
		
		if (phone.length() < 10) {
			return false;
		}
		
		for (int i=0;i<phone.length();i++) {
			int c=phone.charAt(i);
			if ( !((c>=48) && (c<=57)) ) {
				return false;
			}
		}
		
		return true;
	}
}
