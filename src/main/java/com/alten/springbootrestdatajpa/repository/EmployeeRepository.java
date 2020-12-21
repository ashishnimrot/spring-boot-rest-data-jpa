package com.alten.springbootrestdatajpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alten.springbootrestdatajpa.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	public Optional<List<Employee>> findByDeptId(int deptId);

	public Optional<List<Employee>> findBySalary(float salary);

	// <FirstName - columnname><Containing belongs to Like>
	public Optional<List<Employee>> findByFirstNameContaining(String name);

	public Optional<List<Employee>> findByFirstNameContainingIgnoreCase(String name);

	public Optional<List<Employee>> findByFirstNameNotContainingIgnoreCase(String name);

	@Query("SELECT e FROM Employee e WHERE e.firstName LIKE %:ee%")
	public Optional<List<Employee>> findByFirstNameLike(@Param("ee") String name);

//	public Optional<List<Employee>> findByFirstName(String name);
	public Optional<List<Employee>> findByFirstNameStartsWith(String firstName);

	public Optional<List<Employee>> findByFirstNameEndsWith(String firstName);

//	@Query("SELECT e.deptId, MAX(e.salary) FROM Employee GROUP BY e.deptId")
//	public List<Object[]> findMaxSalariesByDeptId(@Param("ee") List<String> firstName);

	Employee findTopByOrderBySalaryDesc();

	Employee findTopByOrderBySalaryAsc();

	List<Employee> findTop3ByOrderBySalaryDesc();

	List<Employee> findTop3ByOrderBySalaryAsc();

	List<Employee> findFirst2BySalary(int salary);

//	@Query(
//            value = "SELECT * FROM employees_tbl WHERE employees_tbl.firstName LIKE :searchTerm",
//            nativeQuery = true
//    )
//    public Optional<List<Employee>> searchWithNativeQuery(@Param("searchTerm") String searchTerm);

}
