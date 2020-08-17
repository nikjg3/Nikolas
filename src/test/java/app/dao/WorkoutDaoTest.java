package app.dao;

import app.models.Workout;
import org.flywaydb.core.Flyway;
import org.h2.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Basic Workout DAO Testing
 * Tests are intentionally incomplete.
 */
class WorkoutDaoTest {
    @BeforeEach
    void setup(){
        //Use a different DB for testing
        String DB = "jdbc:h2:file:./target/testdb";
        //Set the environment property so that DBUtils point to the same file DB.
        System.setProperty(DBUtils.DB_URL,DB);
        //Get Flyway instance
        Flyway flyway = Flyway.configure().dataSource(DB, "sa","").load();
        //Clean testing DB before each test to make sure we have a consistent state
        flyway.clean();
        //Set up db
        flyway.migrate();
    }

    @Test
    void get() throws SQLException {
        Workout workout = WorkoutDao.INSTANCE.get(1l);
        assertNotNull(workout);
        assertEquals("Basic Workout I", workout.getName());
        System.out.println(workout.getDescription());

    }
}