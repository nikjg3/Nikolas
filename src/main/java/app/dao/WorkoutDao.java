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
    private static String INSERT = "INSERT INTO workouts(name, description) VALUES(?,?)";
    private static String UPDATE = "UPDATE workouts SET name = ?, description = ? WHERE id=?";
    private static String DELETE = "DELETE FROM workouts WHERE id=?";


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


    /**
     * Create a new record in the DB.
     * @param workout
     * @return
     * @throws SQLException
     */
    public Workout create(Workout workout) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, workout.getName());
        stm.setString(2, workout.getDescription());

        stm.executeUpdate();
        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
            workout.setId(generatedKeys.getLong(1));
        } else {
            connection.close();
            throw new SQLException("Creating workout failed, no ID obtained.");
        }
        connection.close();
        return workout;
    }



    /**
     * Update an existing record.
     * @param workout The workout to update
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    public int update(Workout workout) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(UPDATE);
        stm.setString(1, workout.getName());
        stm.setString(2, workout.getDescription());
        stm.setLong(3,workout.getId());
        return stm.executeUpdate();
    }



    public int delete(Long id)throws SQLException{
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(DELETE);
        stm.setLong(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Workout m = mapWorkout(rs);
            throw new SQLException("Delete fail");
        }
        connection.close();
        return 1;//1 mean Delete success
    }



    /**
     * Simple mapping method from a {@link ResultSet} to a {@link Workout} object.
     * @param rs
     * @return
     * @throws SQLException
     */
    private Workout mapWorkout(ResultSet rs) throws SQLException {
        Workout workout = new Workout(rs.getString(2), StringUtils.javaDecode(rs.getString(3)));
        workout.setId(rs.getLong(1));
        return workout;
    }
}
