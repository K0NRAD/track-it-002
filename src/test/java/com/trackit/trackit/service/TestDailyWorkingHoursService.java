package com.trackit.trackit.service;

import com.trackit.trackit.model.DailyBreakTimes;
import com.trackit.trackit.model.DailyWorkingHours;
import com.trackit.trackit.model.Employee;
import com.trackit.trackit.repository.DailyBreakTimesRepository;
import com.trackit.trackit.repository.DailyWorkingHoursRepository;
import com.trackit.trackit.repository.EmployeeRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.trackit.trackit.TestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestDailyWorkingHoursService {
    /* ------------------------------------------- REPOSITORIES -------------------------------------------- */

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    DailyWorkingHoursRepository dailyWorkingHoursRepository;

    @Autowired
    DailyBreakTimesRepository dailyBreakTimesRepository;

    private DailyWorkingHoursService dailyWorkingHoursService;

    /* ------------------------------------------- REPOSITORIES -------------------------------------------- */

    /* ------------------------------------------- ADDITIONAL DATA -------------------------------------------- */

    private Employee employeeForCheckOut = new Employee(
        "testUsernameCheckOut",
            "testPasswordCheckOut",
            "testPersonnelNumberCheckOut",
            "testFirstNameCheckOut",
            "testLastNameCheckOut"
    );

    private DailyWorkingHours dailyWorkingHoursForCheckOut = new DailyWorkingHours(
            employeeForCheckOut,
            LocalDate.now(),
            LocalTime.of(8, 30, 0)
    );

    // Set up the break ( 1-hour break )
    LocalTime testBreakCheckIn = LocalTime.of(12, 0, 0);
    LocalTime testBreakCheckOut = LocalTime.of(13, 0, 0);

    private DailyBreakTimes breakTimeForCheckOut = new DailyBreakTimes(
        dailyWorkingHoursForCheckOut,
        testBreakCheckIn
    );

    private Employee employeeForTestGetDailyWorkingHoursByEmployeeIdAndDate = new Employee(
            "testUsernameQuery",
            "testPasswordQuery",
            "testPersonnelNumberQuery",
            "testFirstNameQuery",
            "testLastNameQuery"
    );

    private DailyWorkingHours dailyWorkingHoursForTestGetDailyWorkingHoursByEmployeeIdAndDate = new DailyWorkingHours(
            employeeForTestGetDailyWorkingHoursByEmployeeIdAndDate,
            LocalDate.now(),
            LocalTime.of(8, 30, 0)
    );


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
    public void setUp() {
        employeeRepository.save(employee);

        // Save data for the checkout test
        breakTimeForCheckOut.setBreakCheckOut(testBreakCheckOut);
        employeeRepository.save(employeeForCheckOut);
        dailyWorkingHoursRepository.save(dailyWorkingHoursForCheckOut);
        dailyBreakTimesRepository.save(breakTimeForCheckOut);

        // Save data for the testGetDailyWorkingHoursByEmployeeIdAndDate test
        employeeRepository.save(employeeForTestGetDailyWorkingHoursByEmployeeIdAndDate);
        dailyWorkingHoursRepository.save(dailyWorkingHoursForTestGetDailyWorkingHoursByEmployeeIdAndDate);

        // Build the services
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        dailyWorkingHoursService = new DailyWorkingHoursService(dailyWorkingHoursRepository, employeeRepository, dailyBreakTimesRepository);
    }

    @Transactional
    @AfterAll
    public void cleanUp() {
        dailyBreakTimesRepository.deleteAll();
        dailyWorkingHoursRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    /* ------------------------------------------- SET-UP & CLEAN-UP -------------------------------------------- */

    /* ------------------------------------------- TESTS -------------------------------------------- */

    @Test
    public void testGetDailyWorkingHoursByEmployeeIdAndDate() {
        DailyWorkingHours testDailyWorkingHours = dailyWorkingHoursService.getDailyWorkingHoursByEmployeeIdAndDate(employeeForTestGetDailyWorkingHoursByEmployeeIdAndDate.getEmployeeId(), LocalDate.now());

        boolean receivedTheRightDailyWorkingHours = testDailyWorkingHours.getDailyWorkingHoursId().equals(dailyWorkingHoursForTestGetDailyWorkingHoursByEmployeeIdAndDate.getDailyWorkingHoursId());

        assertThat(receivedTheRightDailyWorkingHours).isTrue();
    }

    @Test
    public void testSetCheckIn() {
        LocalTime testCheckInTime = LocalTime.of(8, 30, 0);

        boolean checkInStatus = dailyWorkingHoursService.setCheckIn(
                employee.getEmployeeId(),
                testCheckInTime
        );

        DailyWorkingHours testDailyWorkingHours = dailyWorkingHoursService.getDailyWorkingHoursByEmployeeIdAndDate(
                employee.getEmployeeId(),
                LocalDate.now()
        );

        boolean checkInChangedProperly = testDailyWorkingHours.getCheckIn().equals(testCheckInTime);

        assertThat(checkInStatus).isTrue();
        assertThat(checkInChangedProperly).isTrue();
    }

    @Test
    public void testSetCheckOut() {
        // The employee has worked from 8:30 to 16:30 with a 1-hour break.
        LocalTime testCheckOut = LocalTime.of(16, 30, 0);
        LocalTime testTotalBreakTime = LocalTime.of(1, 0, 0);
        LocalTime testTotalWorkTime = LocalTime.of(7, 0, 0);

        // Set the check-out
        boolean checkOutStatus = dailyWorkingHoursService.setCheckOut(
                dailyWorkingHoursForCheckOut.getDailyWorkingHoursId(),
                testCheckOut
        );

        // Get the updated daily-working-hours entity
        DailyWorkingHours testDailyWorkingHours = dailyWorkingHoursRepository.getById(dailyWorkingHoursForCheckOut.getDailyWorkingHoursId());

        // Test the new values
        boolean receivedTheRightDailyWorkingHours = testDailyWorkingHours.getDailyWorkingHoursId().equals(dailyWorkingHoursForCheckOut.getDailyWorkingHoursId());
        boolean checkOutTimeChangedProperly = testDailyWorkingHours.getCheckOut().equals(testCheckOut);
        boolean totalBreakTimeChangedProperly = testDailyWorkingHours.getTotalBreakTime().equals(testTotalBreakTime);
        boolean totalWorkTimeChangedProperly = testDailyWorkingHours.getTotalDayWorkTime().equals(testTotalWorkTime);

        assertThat(checkOutStatus).isTrue();
        assertThat(receivedTheRightDailyWorkingHours).isTrue();
        assertThat(checkOutTimeChangedProperly).isTrue();
        assertThat(totalBreakTimeChangedProperly).isTrue();
        assertThat(totalWorkTimeChangedProperly).isTrue();
    }

    /* ------------------------------------------- TESTS -------------------------------------------- */
}
