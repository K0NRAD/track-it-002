package com.trackit.trackit.service;

import com.trackit.trackit.model.DailyBreakTimes;
import com.trackit.trackit.model.Employee;
import com.trackit.trackit.repository.DailyBreakTimesRepository;
import com.trackit.trackit.repository.DailyWorkingHoursRepository;
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

import java.time.LocalTime;
import java.util.List;

import static com.trackit.trackit.TestData.dailyWorkingHours;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.trackit.trackit.TestData.employee;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestDailyBreakTimesService {
    /* ------------------------------------------- REPOSITORIES -------------------------------------------- */

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DailyWorkingHoursRepository dailyWorkingHoursRepository;

    @Autowired
    private DailyBreakTimesRepository dailyBreakTimesRepository;

    private DailyBreakTimesService dailyBreakTimesService;

    /* ------------------------------------------- REPOSITORIES -------------------------------------------- */

    /* ------------------------------------------- ADDITIONAL DATA -------------------------------------------- */

    DailyBreakTimes testBreakTimeCheckOut = new DailyBreakTimes(
        dailyWorkingHours,
        LocalTime.of(14, 0, 0)
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
        dailyWorkingHoursRepository.save(dailyWorkingHours);

        dailyBreakTimesRepository.save(testBreakTimeCheckOut);

        dailyBreakTimesService = new DailyBreakTimesService(dailyBreakTimesRepository, dailyWorkingHoursRepository);
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
    public void testBreakCheckIn(){
        LocalTime testBreakCheckInLocalTime = LocalTime.of(12, 0, 0);

        dailyBreakTimesService.breakCheckIn(
                dailyWorkingHours.getDailyWorkingHoursId(),
                testBreakCheckInLocalTime
        );
        dailyBreakTimesRepository.flush();

        System.out.println(dailyBreakTimesRepository.findAll());

        List<DailyBreakTimes> testBreakTimeList = dailyBreakTimesRepository.getDailyBreakTimeByDailyWorkingHoursIdAndBreakCheckIn(dailyWorkingHours.getDailyWorkingHoursId(), testBreakCheckInLocalTime);

        boolean onlyOneBreak = testBreakTimeList.size() == 1;
        DailyBreakTimes testBreakTime = testBreakTimeList.get(0);
        boolean breakCheckedInProperly = testBreakTime.getBreakCheckIn().equals(testBreakCheckInLocalTime);
        boolean rightBreakCheckedInProperly = testBreakTime.getDailyWorkingHours().getDailyWorkingHoursId().equals(dailyWorkingHours.getDailyWorkingHoursId());

        assertThat(onlyOneBreak).isTrue();
        assertThat(breakCheckedInProperly).isTrue();
        assertThat(rightBreakCheckedInProperly).isTrue();
    }

    @Test
    public void testBreakCheckOut(){
        // Break : 14:00:00 - 14:15:00
        LocalTime testBreakCheckOutTime = LocalTime.of(15, 0, 0);

        dailyBreakTimesService.breakCheckOut(
            testBreakTimeCheckOut.getDailyBreakTimesId(),
            testBreakCheckOutTime
        );

        DailyBreakTimes testDailyBreakTimes = dailyBreakTimesRepository.getById(testBreakTimeCheckOut.getDailyBreakTimesId());

        boolean breakCheckedOutProperly = testDailyBreakTimes.getBreakCheckOut().equals(testBreakCheckOutTime);
        boolean rightBreakCheckedOut = testDailyBreakTimes.getDailyBreakTimesId().equals(testBreakTimeCheckOut.getDailyBreakTimesId());

        assertThat(breakCheckedOutProperly).isTrue();
        assertThat(rightBreakCheckedOut).isTrue();
    }

    public void testGetBreakTimesByDailyWorkingHoursId(){
        // Works directly with the repository method, if the repository method is working, this will work too.
    }
    /* ------------------------------------------- TESTS -------------------------------------------- */
}
