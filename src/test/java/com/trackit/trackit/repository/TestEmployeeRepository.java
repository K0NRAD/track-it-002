package com.trackit.trackit.repository;

import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

    @Autowired
    private DailyWorkingHoursRepository dailyWorkingHoursRepository;

    @Autowired
    private DailyBreakTimesRepository dailyBreakTimesRepository;

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
        breakTime.setBreakCheckOut(LocalTime.of(13, 0, 0));
        dailyWorkingHours.setCheckOut(LocalTime.of(16, 30, 0));
        dailyWorkingHours.setTotalBreakTime(LocalTime.of(1, 0, 0));
        dailyWorkingHours.setTotalDayWorkTime(LocalTime.of(7, 0, 0));

        employeeRepository.save(employee);
        dailyWorkingHoursRepository.save(dailyWorkingHours);
        dailyBreakTimesRepository.save(breakTime);
    }

    @Transactional
    @AfterAll
    public void cleanUp(){
        dailyBreakTimesRepository.deleteAll();
        dailyWorkingHoursRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    /* ------------------------------------------- SET-UP & CLEAN-UP -------------------------------------------- */

    /* ------------------------------------------- TESTS -------------------------------------------- */

    /* ------------------------------------------- TESTS -------------------------------------------- */
}
