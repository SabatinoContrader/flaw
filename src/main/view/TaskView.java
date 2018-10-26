package main.view;

import java.util.List;
import java.util.Scanner;

import main.MainDispatcher;
import main.controller.Request;
import main.model.Task;
import main.service.TaskService;

public class TaskView implements View {

	private TaskService taskService;
    private String mode;
    
    public TaskView () {
        this.taskService = new TaskService();
        this.mode = "all";
    }

      @Override
      public void showResults(Request request) {
         this.mode  = (String) request.get("mode");
      }
      public void showOptions() {

          Scanner scanner = new Scanner(System.in);
          String name = null;
          String status = null;
          switch (mode) {
              case "read":
                  List<Task> tasks = taskService.getAllTask();
                  System.out.println("----- Tasks disponibili -----");
                  System.out.println();
                  tasks.forEach(task -> System.out.println(task.getId()+"Nome: "+task.getName()+"\nStato: "+task.getStatus()+"\n"));
                  System.out.println("Premi invio per tornare al menù");
                  getInput();
                  break;
              case "delete":
                  System.out.println("Inserisci nome della task da eliminare:");
                  name = getInput();
                  taskService.deleteTask(new Task(0, name, status));
                  System.out.println("Task eliminata. Premi invio per tornare al menù");
                  getInput();
                  break;
              case "update":
                  System.out.println("Inserisci nome della task da modificare:");
                  name = getInput();
                  System.out.println("Inserisci nuovo nome:");
                  String name2 = getInput();
                  taskService.updateTask(new Task(0, name, name2));
                  System.out.println("Task modificata. Premi invio per tornare al menù");
                  getInput();
                  break;
              case "insert":
                  System.out.println("Inserisci i dati della nuova task:");
                  System.out.println("Nome:");
                  name = getInput();
                  System.out.println("Stato:");
                  status = getInput();
                  taskService.insertTask(new Task(0, name, status));
          }
      }

      @Override
      public String getInput() {
          Scanner scanner = new Scanner(System.in);
          return scanner.nextLine();
    }

      @Override
      public void submit() {
          MainDispatcher.getInstance().callAction("Home", "doControl", null);
      }
}
