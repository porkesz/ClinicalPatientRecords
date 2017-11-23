package cpr.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cpr.model.Employe;

public interface EmployeRepository extends CrudRepository<Employe, Integer>{
	Optional<Employe> findByEmail(String email);
}
