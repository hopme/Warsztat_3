package pl.coderslab.dao;

import pl.coderslab.model.Group;
import pl.coderslab.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class GroupDao {

    private static final String CREATE_GROUP_QUERY =
            "INSERT INTO groups (name) VALUES (?)";
    private static final String READ_GROUP_QUERY =
            "SELECT * FROM groups where id = ?";
    private static final String UPDATE_GROUP_QUERY =
            "UPDATE groups SET name = ? where id = ?";
    private static final String DELETE_GROUP_QUERY =
            "DELETE FROM groups WHERE id = ?";
    private static final String FIND_ALL_GROUP_QUERY =
            "SELECT * FROM groups";

    //przetestuj GROUP I ZROB RESZTE!

    public Group create(Group group) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            System.out.println("Can't create group. groupId =  " + group.getId());
            return null;
        }
    }

    public Group read(int groupId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_GROUP_QUERY);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        } catch (SQLException e) {
            System.out.println("Error while reading group. groupId = " + groupId);
        }
        return null;
    }

    public void update(Group group) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating group. groupId = " + group.getId());
        }
    }

    public void delete(int groupId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_QUERY);
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting group. grouId = " + groupId);
        }
    }

    public ArrayList findAll() {
        try (Connection conn = ConnectionUtil.getConnection()){
            ArrayList groups = new ArrayList();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups.add(group);
            }
            return groups;
        } catch (SQLException e) {
            System.out.println("Error while looking for all groups array");;
        }
        return null;
    }
}
