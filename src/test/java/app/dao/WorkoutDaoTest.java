package app.dao;

import app.models.Workout;
import org.flywaydb.core.Flyway;
import org.h2.util.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

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
    void getAll() throws SQLException {
        List<Workout> all = WorkoutDao.INSTANCE.getAll();
        assertEquals(2,all.size());
        assertEquals("Basic Workout I", all.get(0).getName());
        assertEquals("Treadmill Workout I", all.get(1).getName());
    }

    @Test
    void get() throws SQLException {
        Workout workout = WorkoutDao.INSTANCE.get(1l);
        assertNotNull(workout);
        assertEquals("Basic Workout I", workout.getName());
        System.out.println(workout.getDescription());

    }

    @Test
    void create() throws SQLException {
        Workout w = new Workout("First 10K Run", "Your first 10K run");
        Workout workout = WorkoutDao.INSTANCE.create(w);
        assertNotNull(workout.getId());
        assertEquals(3,workout.getId());
        assertEquals("First 10K Run",workout.getName());
        assertEquals("Your first 10K run",workout.getDescription());

    }

    @Test
    void update() throws SQLException {
        Workout workout = WorkoutDao.INSTANCE.create(new Workout("First 10K Run", "Your first 10K run"));
        assertNotNull(workout);
        assertEquals("First 10K Run", workout.getName());
        workout.setName("5K Run");
        int result = WorkoutDao.INSTANCE.update(workout);
        //query updated 1 record
        assertEquals(1, result);
        //get if back from the db
        Workout wo2 = WorkoutDao.INSTANCE.get(workout.getId());
        assertNotNull(wo2);
        assertEquals("5K Run", wo2.getName());
    }
}