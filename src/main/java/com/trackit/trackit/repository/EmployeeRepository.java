package com.trackit.trackit.repository;


import com.trackit.trackit.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT * FROM Employee WHERE username=?1 AND password=?2", nativeQuery = true)
    Employee getUserByUsernameAndPassword(String username, String password);
}
