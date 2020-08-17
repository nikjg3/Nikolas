package app.dao;

import app.models.Workout;
import org.h2.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDao {

    /**
     * Workout DAO Singleton.
     * There are better ways to implement Singletons. 
     */
    public static final WorkoutDao INSTANCE = new WorkoutDao();

    private static String SELECT_ALL = "SELECT * FROM workouts";
    private static String SELECT_BY_ID = "SELECT * FROM workouts WHERE id=?";


    private WorkoutDao(){}

    /**
     * Get all workouts in the DB
     * @return All workouts
     * @throws SQLException
     */
    public List<Workout> getAll() throws SQLException {
        Connection connection = DBUtils.getConnection();
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(SELECT_ALL);
        List<Workout> workouts = new ArrayList<>();
        while (rs.next()) {
            workouts.add(mapWorkout(rs));
        }
        connection.close();
        return workouts;
    }

    /**
     * Get ONE workout identified by <code>id</code>
     * @param id the id of the workout
     * @return the workout
     * @throws SQLException
     */
    public Workout get(Long id) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID);
        stm.setLong(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Workout m = mapWorkout(rs);
            return m;
        }
        connection.close();
        throw new SQLException("No Workout with id = " + id);
    }

    private Workout mapWorkout(ResultSet rs) throws SQLException {
        Workout workout = new Workout(rs.getString(2), StringUtils.javaDecode(rs.getString(3)));
        workout.setId(rs.getLong(1));
        return workout;
    }


}
