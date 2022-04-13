package com.trackit.trackit.service;

import com.trackit.trackit.model.Employee;
import com.trackit.trackit.repository.EmployeeRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.trackit.trackit.TestData.employee;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestEmployeeService {
    /* ------------------------------------------- REPOSITORIES -------------------------------------------- */

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    /* ------------------------------------------- REPOSITORIES -------------------------------------------- */

    /* ------------------------------------------- ADDITIONAL DATA -------------------------------------------- */

    private String employeeUsername;
    private String employeePassword;

    /* ------------------------------------------- ADDITIONAL DATA -------------------------------------------- */

    /* ------------------------------------------- TEST CONTAINERS SETUP -------------------------------------------- */

    static int POSTGRES_PORT = 5432;

    @ClassRule
    public static PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("testcontainers")
            .withUsername("postgres")
            .withPassword("postgres")
            .withInitScript("sql/init_postgres.sql");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            TestPropertyValues.of(
                    "spring.datasource.url=" + postgres.getJdbcUrl(),
                    "spring.data.postgres.host=" + postgres.getContainerIpAddress(),
                    "spring.data.postgres.port=" + postgres.getMappedPort(POSTGRES_PORT),
                    "spring.data.postgres.username=" + postgres.getUsername(),
                    "spring.data.postgres.password=" + postgres.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    /* ------------------------------------------- TEST CONTAINERS SETUP -------------------------------------------- */

    /* ------------------------------------------- SET-UP & CLEAN-UP -------------------------------------------- */

    @Transactional
    @BeforeAll
    public void setUp(){
        employeeUsername = employee.getUsername();
        employeePassword = employee.getPassword();

        employee.setUsername(HashingStaticService.hashString(employee.getUsername()));
        employee.setPassword(HashingStaticService.hashString(employee.getPassword()));

        employeeRepository.save(employee);

        employeeService = new EmployeeService(employeeRepository);
    }

    @Transactional
    @AfterAll
    public void cleanUp(){
        employeeRepository.deleteAll();
    }

    /* ------------------------------------------- SET-UP & CLEAN-UP -------------------------------------------- */

    /* ------------------------------------------- TESTS -------------------------------------------- */

    @Test
    public void testGetEmployeeDataByEmployeeId(){
        Employee testEmployee = employeeService.getEmployeeDataByEmployeeId(employee.getEmployeeId());

        boolean receivedTheRightEmployee = testEmployee.getEmployeeId().equals(employee.getEmployeeId());

        assertThat(receivedTheRightEmployee).isTrue();
    }

    @Test
    public void testGetEmployeeByUsernameAndPassword(){
        Employee testEmployee = employeeService.getEmployeeByUsernameAndPassword(
                employeeUsername,
                employeePassword
        );

        boolean receivedTheRightEmployee = testEmployee.getEmployeeId().equals(employee.getEmployeeId());

        assertThat(receivedTheRightEmployee).isTrue();
    }

    @Test
    public void testRegisterNewEmployee(){
        Employee employeeToRegister = new Employee(
                "registerUsername",
                "registerPassword",
                "registerPersonnelNumber",
                "registerFirstName",
                "registerLastName"
        );

        employeeService.registerNewEmployee(
                employeeToRegister.getUsername(),
                employeeToRegister.getPassword(),
                employeeToRegister.getPersonnelNumber(),
                employeeToRegister.getFirstName(),
                employeeToRegister.getLastName()
        );

        Employee testEmployee = employeeService.getEmployeeByUsernameAndPassword(
                employeeToRegister.getUsername(),
                employeeToRegister.getPassword()
        );

        boolean receivedTheRightEmployee = testEmployee.getUsername().equals(HashingStaticService.hashString(employeeToRegister.getUsername())) && testEmployee.getPassword().equals(HashingStaticService.hashString(employeeToRegister.getPassword()));

        assertThat(receivedTheRightEmployee).isTrue();
    }

    /* ------------------------------------------- TESTS -------------------------------------------- */
}
