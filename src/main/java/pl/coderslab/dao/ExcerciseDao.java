package pl.coderslab.dao;

import pl.coderslab.model.Excercise;
import pl.coderslab.model.User;
import pl.coderslab.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ExcerciseDao {
    private static final String CREATE_EXCERCISE_QUERY =
            "INSERT INTO excercise(title, description) VALUES (?, ?)";
    private static final String READ_EXCERCISE_QUERY =
            "SELECT * FROM excercise where id = ?";
    private static final String UPDATE_EXCERCISE_QUERY =
            "UPDATE excercise SET title = ?, description = ? where id = ?";
    private static final String DELETE_EXCERCISE_QUERY =
            "DELETE FROM excercise WHERE id = ?";
    private static final String FIND_ALL_EXCERCISE_QUERY =
            "SELECT * FROM excercise";
    private static final String FIND_ALL_ASSIGNED_TO_USER_ID =
            "SELECT excercise.id, excercise.title, excercise.description, solution.updated FROM excercise JOIN solution ON excercise.id = solution.excercise_id JOIN users ON users.id = solution.user_id WHERE solution.user_id = ? ORDER BY created ASC";
    private static final String FIND_ALL_NOT_ASSIGNED_TO_USER_ID =
            "SELECT * FROM excercise LEFT JOIN solution ON excercise.id = solution.excercise_id WHERE user_id != ? OR user_id is NULL";





    public Excercise create(Excercise excercise) {
        try (Connection conn = ConnectionUtil.getConnection()) {

            PreparedStatement statement =
                    conn.prepareStatement(CREATE_EXCERCISE_QUERY, Statement.RETURN_GENERATED_KEYS); // statement.return_generated_keys to jest id autoinkrementacji
            statement.setString(1, excercise.getTitle());
            statement.setString(2, excercise.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                excercise.setId(resultSet.getInt(1));
            }
            return excercise;
        } catch (SQLException e) {
            System.out.println("Can't create excercise. excerciseId =  " + excercise.getId());
            return null;
        }
    }

    public Excercise read(int excerciseId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_EXCERCISE_QUERY);
            statement.setInt(1, excerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Excercise excercise = new Excercise();
                excercise.setId(resultSet.getInt("id"));
                excercise.setTitle(resultSet.getString("title"));
                excercise.setDescription(resultSet.getString("description"));
                return excercise;
            }
        } catch (SQLException e) {
            System.out.println("Error while reading excercise. excerciseID = " + excerciseId);
        }
        return null;
    }

    public void update(Excercise excercise) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_EXCERCISE_QUERY);
            statement.setString(1, excercise.getTitle());
            statement.setString(2, excercise.getDescription());
            statement.setInt(3, excercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating excercise. excerciseId = " + excercise.getId());
        }
    }

    public void delete(int excerciseId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_EXCERCISE_QUERY);
            statement.setInt(1, excerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting excercise. excerciseId = " + excerciseId);
        }
    }

    public ArrayList<Excercise> findAll() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            ArrayList<Excercise> excercises = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXCERCISE_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Excercise excercise = new Excercise();
                excercise.setId(resultSet.getInt("id"));
                excercise.setTitle(resultSet.getString("title"));
                excercise.setDescription(resultSet.getString("description"));
                excercises.add(excercise);
            }
            return excercises;
        } catch (SQLException e) {
            System.out.println("Error while looking for all excercises array");
        }
        return null;
    }

    public ArrayList<Excercise> findAllNotAssignedToUserId(int userId){
        try (Connection connection = ConnectionUtil.getConnection()){
            ArrayList<Excercise> excercises = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_ASSIGNED_TO_USER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Excercise excercise = new Excercise();
                excercise.setId(resultSet.getInt("id"));
                excercise.setTitle(resultSet.getString("title"));
                excercise.setDescription(resultSet.getString("description"));
                excercises.add(excercise);
            }
            return excercises;
        } catch (SQLException e){
            System.out.println("Can't find exercises not assigned to user id: " + userId);
        }
        return null;
    }

    public ArrayList<Excercise> findAllAssignedToUserId(int userId){
        try (Connection connection = ConnectionUtil.getConnection()){
            ArrayList<Excercise> excercises = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_ASSIGNED_TO_USER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Excercise excercise = new Excercise();
                excercise.setId(resultSet.getInt("id"));
                excercise.setTitle(resultSet.getString("title"));
                excercise.setDescription(resultSet.getString("description"));
                excercise.setUpdated(resultSet.getString("updated"));
                excercises.add(excercise);
            }
            return excercises;
        } catch (SQLException e){
            System.out.println("Can't find exercises assigned to user id: " + userId);
        }
        return null;
    }

}

