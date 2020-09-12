package app.dao;

import app.models.Program;
import org.h2.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramDao {

    /**
     * Workout DAO Singleton.
     * There are better ways to implement Singletons. 
     */
    public static final ProgramDao INSTANCE = new ProgramDao();

    private static String SELECT_ALL = "SELECT * FROM programs";
    private static String SELECT_BY_ID = "SELECT * FROM programs WHERE id=?";
    private static String INSERT = "INSERT INTO programs(name, description) VALUES(?,?)";
    private static String UPDATE = "UPDATE programs SET name = ?, description = ? WHERE id=?";


    private ProgramDao(){}

    /**
     * Get all programs in the DB
     * @return All programs
     * @throws SQLException
     */
    public List<Program> getAll() throws SQLException {
        Connection connection = DBUtils.getConnection();
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(SELECT_ALL);
        List<Program> programs = new ArrayList<>();
        while (rs.next()) {
            programs.add(mapProgram(rs));
        }
        connection.close();
        return programs;
    }

    /**
     * Get ONE program identified by <code>id</code>
     * @param id the id of the program
     * @return the program
     * @throws SQLException
     */
    public Program get(Long id) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID);
        stm.setLong(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Program m = mapProgram(rs);
            return m;
        }
        connection.close();
        throw new SQLException("No Workout with id = " + id);
    }


    /**
     * Create a new record in the DB.
     * @param program
     * @return
     * @throws SQLException
     */
    public Program create(Program program) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, program.getName());
        stm.setString(2, program.getInfo());

        stm.executeUpdate();
        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
            program.setId(generatedKeys.getLong(1));
        } else {
            connection.close();
            throw new SQLException("Creating program failed, no ID obtained.");
        }
        connection.close();
        return program;
    }



    /**
     * Update an existing record.
     * @param program The workout to update
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    public int update(Program program) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(UPDATE);
        stm.setString(1, program.getName());
        stm.setString(2, program.getInfo());
        stm.setLong(3,program.getId());
        return stm.executeUpdate();
    }




    /**
     * Simple mapping method from a {@link ResultSet} to a {@link Workout} object.
     * @param rs
     * @return
     * @throws SQLException
     */
    private Program mapProgram(ResultSet rs) throws SQLException {
        Program program = new Program(rs.getString(2), StringUtils.javaDecode(rs.getString(3)));
        program.setId(rs.getLong(1));
        return program;
    }
}
