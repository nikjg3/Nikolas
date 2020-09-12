package app.dao;


import app.models.Weight;
import org.h2.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuqing Wu, 2020. email: s3808626@student.rmit.edu.au
 */

public class WeightDao {

    public static final WeightDao INSTANCE = new WeightDao();

    private static String SELECT_ALL;
    private static String SELECT_BY_ID;
    private static String INSERT;
    private static String UPDATE;

    private WeightDao(){}

    public List<Weight> getAll() throws SQLException {
        Connection connection = DBUtils.getConnection();
        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery(SELECT_ALL);
        List<Weight> weights = new ArrayList<>();
        while (rs.next()) {
            weights.add(mapWeight(rs));
        }
        connection.close();
        return weights;
    }



    public Weight get(Long id) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(SELECT_BY_ID);
        stm.setLong(1, id);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            Weight m = mapWeight(rs);
            return m;
        }
        connection.close();
        throw new SQLException("No Weight with id = " + id);
    }



    public Weight create(Weight weight) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, weight.getName());
        stm.setString(2, weight.getwtime());

        stm.executeUpdate();
        ResultSet generatedKeys = stm.getGeneratedKeys();
        if (generatedKeys.next()) {
            weight.setId(generatedKeys.getLong(1));
        } else {
            connection.close();
            throw new SQLException("Creating weight failed, no ID obtained.");
        }
        connection.close();
        return weight;
    }



    public int update(Weight weight) throws SQLException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement stm = connection.prepareStatement(UPDATE);
        stm.setString(1, weight.getName());
        stm.setString(2, weight.getwtime());
        return stm.executeUpdate();
    }



    private Weight mapWeight(ResultSet rs) throws SQLException {
        Weight weight = new Weight(rs.getString(2), StringUtils.javaDecode(rs.getString(3)));
        weight.setId(rs.getLong(1));
        return weight;
    }


}
