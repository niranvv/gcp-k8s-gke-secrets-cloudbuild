package com.ust.efx.poc.usermanagement.repository;

import com.ust.efx.poc.usermanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by niran on 27/04/19.
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
