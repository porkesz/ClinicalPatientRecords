package cpr.dao;

import org.springframework.data.repository.CrudRepository;

import cpr.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer>{

}
