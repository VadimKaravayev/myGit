package com.epam.preprod.karavayev.converter;

import java.sql.SQLException;

public interface Converter<F, T> {
    T convert(F from) throws SQLException;
}
