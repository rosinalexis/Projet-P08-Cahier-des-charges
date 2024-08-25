package com.livrai.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDao {

    private Connection connection;

    private Connection getConnection() {
        if (connection == null) {
            loadDatabase();
        }
        return connection;
    }

    protected <T> T execute(SqlQuery<T> query) {
        try {
            return query.execute(getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void loadDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            String url = "jdbc:mysql://db:3306/livrai";  // "db" est le nom du service MySQL défini dans docker-compose.yml
            String username = "livraiuser";
            String password = "livraipassword";
            connection = DriverManager.getConnection( url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected interface SqlQuery<T> {
        T execute(Connection connection) throws SQLException;
    }

}
