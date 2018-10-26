package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.ConnectionSingleton;
import main.controller.GestoreEccezioni;
import main.model.Gomma;
import main.model.Task;

public class TaskDAO {
	
	private final String QUERY_ALL = "SELECT * FROM tasks";
	private final String QUERY_INSERT = "INSERT INTO tasks (name, status) values (?,?)";
	private final String QUERY_UPDATE = "UPDATE tasks SET name = ? WHERE name = ?"; //devi ordinare per id!!
	private final String QUERY_DELETE = "DELETE FROM tasks WHERE name = ?";

	public TaskDAO() {
		
	}
	
	public List<Task> getAllTask () {
        List<Task> tasks = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(QUERY_ALL);
           while (resultSet.next()) {
        	   int id = resultSet.getInt("id");
               String name = resultSet.getString("name");
               String status = resultSet.getString("status");
               tasks.add(new Task(id,name, status));
           }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
	
	public boolean insertTask(Task task) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getStatus());
            return preparedStatement.execute();
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }

    }
	
	public boolean deleteTask(Task task) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setString(1, task.getName());
            return preparedStatement.execute();
        }
        catch (SQLException e) {
            GestoreEccezioni.getInstance().gestisciEccezione(e);
            return false;
        }
	}
    	public boolean updateTask(Task task) {
            Connection connection = ConnectionSingleton.getInstance();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setString(1, task.getName());
                preparedStatement.setString(2, task.getStatus());
                return preparedStatement.execute();
            }
            catch (SQLException e) {
                GestoreEccezioni.getInstance().gestisciEccezione(e);
                return false;
            }

    }
}
