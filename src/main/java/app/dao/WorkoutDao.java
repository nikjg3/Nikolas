package app.dao;

import app.models.Workout;
import org.h2.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutDao {

    /**
     * Workout DAO Singleton.
     * There are better ways to implement Singletons. 
     */
    public static final WorkoutDao INSTANCE = new WorkoutDao();

    private static String SELECT_BY_ID = "SELECT * FROM workouts WHERE id=?";


    private WorkoutDao(){}

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
