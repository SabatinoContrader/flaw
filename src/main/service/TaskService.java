package main.service;

import java.util.List;

import main.dao.GommaDAO;
import main.dao.TaskDAO;
import main.model.Task;

public class TaskService {

	private TaskDAO taskDAO;

    public TaskService() {
        this.taskDAO = new TaskDAO();
    }

    public List<Task> getAllTask () {
        return this.taskDAO.getAllTask();
    }

    public boolean insertTask (Task task) {
        return this.taskDAO.insertTask(task);
    }
    
    public void deleteTask (Task task) {
        this.taskDAO.deleteTask(task);
    }
    
    public boolean updateTask (Task task) {
        return this.taskDAO.updateTask(task);
    }

}
