package com.epam.preprod.karavayev.db.dao.mysql;

import com.epam.preprod.karavayev.converter.Converter;
import com.epam.preprod.karavayev.converter.ResultSetToUser;
import com.epam.preprod.karavayev.db.dao.ConnectionManager;
import com.epam.preprod.karavayev.db.dao.UserDao;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.exception.DBException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlUserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlUserDaoImpl.class);
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String CREATE_USER = "insert into users first_name, last_name, email, password, subscription values(?, ?, ?, md5(?), ?)";

    private ResultSetToUser toUserConverter;

    public MysqlUserDaoImpl() {
        toUserConverter = new ResultSetToUser();
    }

    @Override
    public int create(User user) {
        int id = 0;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.isSubscription() ? 1 : 0);
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Cannot create user");
        }
        return id;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        Connection connection = ConnectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL);) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = toUserConverter.convert(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DBException("Cannot get user by email");
        }
        return user;
    }
}
