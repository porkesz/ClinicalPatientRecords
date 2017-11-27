package cpr.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cpr.dao.DiseaseRepository;
import cpr.model.Disease;
import cpr.model.Employe;


@Service
@Transactional
public class DiseaseService {
	
	private final DiseaseRepository diseaseRepository;

	public DiseaseService(DiseaseRepository diseaseRepository) {
		this.diseaseRepository = diseaseRepository;
	}
	
	public List<Disease> findAll(){
		List<Disease> diseases = new ArrayList<>();
		for(Disease disease : diseaseRepository.findAll()){
			diseases.add(disease);
		}
		return diseases;
	}

	public Disease findDisease(int id){
		return diseaseRepository.findOne(id);
	}
	
	public void save(Disease disease){
		diseaseRepository.save(disease);
	}
}