package cpr.dao;

import org.springframework.data.repository.CrudRepository;

import cpr.model.Disease;

public interface DiseaseRepository  extends CrudRepository<Disease, Integer>{

}
