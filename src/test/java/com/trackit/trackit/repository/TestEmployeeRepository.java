package com.trackit.trackit.repository;

import com.trackit.trackit.model.Employee;
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
import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.trackit.trackit.TestData.employee;
import static com.trackit.trackit.TestData.dailyWorkingHours;
import static com.trackit.trackit.TestData.breakTime;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestEmployeeRepository {
    /* ------------------------------------------- REPOSITORIES -------------------------------------------- */

    @Autowired
    private EmployeeRepository employeeRepository;

    /* ------------------------------------------- REPOSITORIES -------------------------------------------- */

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
        employeeRepository.save(employee);
    }

    @Transactional
    @AfterAll
    public void cleanUp(){
        employeeRepository.deleteAll();
    }

    /* ------------------------------------------- SET-UP & CLEAN-UP -------------------------------------------- */

    /* ------------------------------------------- TESTS -------------------------------------------- */

    @Test
    public void testSave(){
        Employee testEmployee = employeeRepository.getById(employee.getEmployeeId());
        boolean testEmployeeExists = testEmployee.getEmployeeId().equals(employee.getEmployeeId());

        assertThat(testEmployeeExists).isTrue();
    }

    @Test
    public void testGetUserByUsernameAndPassword(){
        Employee testEmployee = employeeRepository.getUserByUsernameAndPassword(employee.getUsername(), employee.getPassword());

        boolean testEmployeeExists = testEmployee.getEmployeeId().equals(employee.getEmployeeId());

        assertThat(testEmployeeExists).isTrue();
    }

    /* ------------------------------------------- TESTS -------------------------------------------- */
}
