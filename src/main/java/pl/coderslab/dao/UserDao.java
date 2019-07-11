package pl.coderslab.dao;

import pl.coderslab.model.Group;
import pl.coderslab.model.User;
import pl.coderslab.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?, email = ?, password = ? where id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM users";
    private static final String FIND_ALL_BY_GROUP_ID =
            "SELECT * FROM groups JOIN user_group ON groups.id = user_group.group_id JOIN users ON users.id = user_group.user_id WHERE groups.id = ?";


    public User create(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS); // statement.return_generated_keys to jest id autoinkrementacji
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Can't create user. User id = " + user.getId());
            return null;
        }
    }

    public User read(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Error while reading user. UserId = " + userId);
        }
        return null;
    }

    public void update(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating user. userId = " + user.getId());

        }
    }

    public void delete(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting user. userID = " + userId);
        }
    }

    public ArrayList<User> findAllByGroupId(int groupId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            ArrayList<User> users = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_GROUP_ID);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("users.id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Error while looking for group. groupId = " + groupId);
            return null;
        }
    }

    public ArrayList findAll() {
        try(Connection conn = ConnectionUtil.getConnection()) {
            ArrayList users = new ArrayList();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS_QUERY);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
