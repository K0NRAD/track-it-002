package com.trackit.trackit.repository;

import com.trackit.trackit.model.DailyBreakTimes;
import com.trackit.trackit.model.DailyWorkingHours;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.trackit.trackit.TestData.employee;
import static com.trackit.trackit.TestData.dailyWorkingHours;
import static com.trackit.trackit.TestData.breakTime;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestDailyBreakTimesRepository {
    /* ------------------------------------------- REPOSITORIES -------------------------------------------- */

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DailyWorkingHoursRepository dailyWorkingHoursRepository;

    @Autowired
    private DailyBreakTimesRepository dailyBreakTimesRepository;


    /* ------------------------------------------- REPOSITORIES -------------------------------------------- */

    /* ------------------------------------------- ADDITIONAL TEST DATA -------------------------------------------- */

    DailyBreakTimes secondBreakTime = new DailyBreakTimes(
            dailyWorkingHours,
            LocalTime.of(14, 0, 0)
    );

    /* ------------------------------------------- ADDITIONAL TEST DATA -------------------------------------------- */


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
        dailyBreakTimesRepository.save(breakTime);
        dailyBreakTimesRepository.save(secondBreakTime);
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
    public void testSave(){
        DailyBreakTimes testBreakTime = dailyBreakTimesRepository.getById(breakTime.getDailyBreakTimesId());

        boolean testBreakTimeExists = testBreakTime.getDailyBreakTimesId().equals(breakTime.getDailyBreakTimesId());

        assertThat(testBreakTimeExists).isTrue();
    }

    @Test
    public void testBreakCheckOut(){
        LocalTime breakCheckOutTime = LocalTime.of(13, 0, 0);
        dailyBreakTimesRepository.breakCheckOut(
                breakTime.getDailyBreakTimesId(),
                breakCheckOutTime
        );

        DailyBreakTimes testBreakTime = dailyBreakTimesRepository.getById(breakTime.getDailyBreakTimesId());

        boolean breakCheckOutTimeChangedProperly = testBreakTime.getBreakCheckOut().equals(breakCheckOutTime);

        assertThat(breakCheckOutTimeChangedProperly).isTrue();
    }

    @Test
    public void testGetAllBreaksByDailyWorkingHoursIdAndDate(){
        // Check out the break times
        LocalTime breakCheckOutTime1 = LocalTime.of(13, 0, 0);
        LocalTime breakCheckOutTime2 = LocalTime.of(14, 30, 0);

        dailyBreakTimesRepository.breakCheckOut(
                breakTime.getDailyBreakTimesId(),
                breakCheckOutTime1
        );
        dailyBreakTimesRepository.breakCheckOut(
                secondBreakTime.getDailyBreakTimesId(),
                breakCheckOutTime2
        );

        // Get the list that needs to be tested
        List<DailyBreakTimes> dailyBreakTimesList = dailyBreakTimesRepository.getAllBreaksByDailyWorkingHoursIdAndDate(
            dailyWorkingHours.getDailyWorkingHoursId(),
                LocalDate.now()
        );

        // Update break times
        breakTime = dailyBreakTimesRepository.getById(breakTime.getDailyBreakTimesId());
        secondBreakTime = dailyBreakTimesRepository.getById(breakTime.getDailyBreakTimesId());

        // Check to see if the list contains the 2 break times
        boolean firstBreakTimeIsInsideList = dailyBreakTimesList.contains(breakTime);
        System.out.println(firstBreakTimeIsInsideList);
        boolean secondBreakTimeIsInsideList = dailyBreakTimesList.contains(secondBreakTime);
        System.out.println(secondBreakTimeIsInsideList);

        assertThat(firstBreakTimeIsInsideList).isTrue();
        assertThat(secondBreakTimeIsInsideList).isTrue();
    }

    @Test
    public void testGetDailyBreakTimeByDailyWorkingHoursIdAndBreakCheckIn(){
        List<DailyBreakTimes> testBreaksList = dailyBreakTimesRepository.getDailyBreakTimeByDailyWorkingHoursIdAndBreakCheckIn(dailyWorkingHours.getDailyWorkingHoursId(), breakTime.getBreakCheckIn());

        boolean onlyOneBreak = testBreaksList.size() == 1;

        DailyBreakTimes testBreakTime = testBreaksList.get(0);

        boolean breakHasCorrectCheckInTime = testBreakTime.getBreakCheckIn().equals(breakTime.getBreakCheckIn());
        boolean receivedTheRightBreak = testBreakTime.getDailyWorkingHours().getDailyWorkingHoursId().equals(breakTime.getDailyWorkingHours().getDailyWorkingHoursId());

        assertThat(onlyOneBreak).isTrue();
        assertThat(breakHasCorrectCheckInTime).isTrue();
        assertThat(receivedTheRightBreak).isTrue();
    }

    @Test
    public void testGetBreakTimesByDailyWorkingHoursId(){
        List<DailyBreakTimes> testBreaksList = dailyBreakTimesRepository.getBreakTimesByDailyWorkingHoursId(dailyWorkingHours.getDailyWorkingHoursId());

        assertThat(testBreaksList.size() == 2).isTrue();
        assertThat(testBreaksList.contains(breakTime)).isTrue();
        assertThat(testBreaksList.contains(secondBreakTime)).isTrue();
    }
    /* ------------------------------------------- TESTS -------------------------------------------- */
}
