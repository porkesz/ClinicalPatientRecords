package cpr.dao;

import org.springframework.data.repository.CrudRepository;

import cpr.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer>{

}
