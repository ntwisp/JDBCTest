package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String query = """
                CREATE TABLE IF NOT EXISTS users (
                    id BIGSERIAL PRIMARY KEY,
                    name VARCHAR(255),
                    last_name VARCHAR(255),
                    age SMALLINT)
                """;
        try (Connection conn = Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("При создании таблицы пользователей произошло исключение");
        }

    }

    public void dropUsersTable() {

        String query = """
                DROP TABLE IF EXISTS users
                """;
        try (Connection conn = Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("При удалении таблицы произошло исключение");
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String query = """
                INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)
                """;
        try (Connection conn = Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Во время сохранения пользователя произошло исключение");
        }

    }

    public void removeUserById(long id) {
        String query = """
                DELETE FROM users WHERE id = ?
                """;
        try (Connection conn = Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("При удалении пользователя по id произошло исключение");
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, last_name, age FROM users";
        try (Connection conn = Util.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("При попытке достать всех пользователей из базы данных произошло исключение");
        }
        return users;
    }

    public void cleanUsersTable() {
        String query = """
                DELETE FROM users
                """;
        try (Connection conn = Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("При очистке таблицы пользователей произошло исключение");
        }
    }
}
