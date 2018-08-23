package com.epam.preprod.karavayev.converter;

import com.epam.preprod.karavayev.constant.DbColumn;
import com.epam.preprod.karavayev.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetToUser implements Converter<ResultSet, User> {

    @Override
    public User convert(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(DbColumn.USER_ID));
        user.setFirstName(resultSet.getString(DbColumn.FISRT_NAME));
        user.setLastName(resultSet.getString(DbColumn.LAST_NAME));
        user.setEmail(resultSet.getString(DbColumn.EMAIL));
        user.setPassword(resultSet.getString(DbColumn.PASSWORD));
        user.setSubscription(resultSet.getInt(DbColumn.SUBSCRIPTION) == 1);
        return user;
    }
}
