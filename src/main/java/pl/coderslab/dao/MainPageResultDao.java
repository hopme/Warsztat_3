package pl.coderslab.dao;

import pl.coderslab.model.MainPageResult;
import pl.coderslab.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainPageResultDao {

    private static final String FIND_RECENT_RESULTS =
            "SELECT excercise.title as 'Tytuł zadania', users.username as 'Autor rozwiązania', solution.updated as 'Data dodania', excercise.id as 'exId'" +
                    "FROM excercise " +
                    "INNER JOIN solution ON excercise.id = solution.excercise_id " +
                    "JOIN users ON solution.user_id = users.id ORDER BY solution.updated limit ?";

    private static final String FIND_BY_USER_ID =
            "SELECT excercise.title as 'Tytuł zadania', users.username as 'Autor rozwiązania', solution.updated as 'Data dodania', excercise.id as 'exId'" +
                    "FROM excercise " +
                    "INNER JOIN solution ON excercise.id = solution.excercise_id " +
                    "JOIN users ON solution.user_id = users.id WHERE user_id = ? ORDER BY solution.updated";


    public ArrayList findRecent(int ammount) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            ArrayList mainPageResults = new ArrayList();
            PreparedStatement statement = conn.prepareStatement(FIND_RECENT_RESULTS);
            statement.setInt(1, ammount);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                MainPageResult mainPageResult = new MainPageResult();
                mainPageResult.setTitle(resultSet.getString("Tytuł zadania"));
                mainPageResult.setAuthor(resultSet.getString("Autor rozwiązania"));
                if(resultSet.getString("Data dodania") == null)
                {
                    mainPageResult.setUpdated("N/A");
                } else {
                    mainPageResult.setUpdated(resultSet.getString("Data dodania"));
                }
                mainPageResult.setExId(resultSet.getInt("exId"));
                mainPageResults.add(mainPageResult);
            }
            return mainPageResults;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while looking for most recent solutions.");
        }
        return null;
    }

    public ArrayList findByUserId(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            ArrayList mainPageResults = new ArrayList();
            PreparedStatement statement = conn.prepareStatement(FIND_BY_USER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                MainPageResult mainPageResult = new MainPageResult();
                mainPageResult.setTitle(resultSet.getString("Tytuł zadania"));
                mainPageResult.setAuthor(resultSet.getString("Autor rozwiązania"));
                mainPageResult.setUpdated(resultSet.getString("Data dodania"));
                mainPageResult.setExId(resultSet.getInt("exId"));
                mainPageResults.add(mainPageResult);
            }
            return mainPageResults;
        } catch (SQLException e) {
            System.out.println("Error while looking for most recent solutions.");
        }
        return null;
    }

}
