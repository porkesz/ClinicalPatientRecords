package cpr.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cpr.model.Disease;
import cpr.model.Patient;

public interface DiseaseRepository  extends CrudRepository<Disease, Integer>{
	List<Disease> findByPatient(Patient patient);
}
