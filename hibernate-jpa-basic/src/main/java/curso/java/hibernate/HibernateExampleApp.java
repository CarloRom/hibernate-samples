package curso.java.hibernate;

import curso.java.hibernate.data.EmployeeRepository;
import curso.java.hibernate.data.entity.Employee;
import curso.java.hibernate.data.entity.Task;
import curso.java.hibernate.data.entity.Scope; // Asegúrate de importar la clase Scope
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class HibernateExampleApp implements CommandLineRunner {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  EmployeeRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(HibernateExampleApp.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // Crear y guardar un nuevo empleado con tareas y scopes
    Employee emp2 = new Employee();
    emp2.setEmail("new Employee email");
    emp2.setFirstName("Bart");
    emp2.setLastName("Simpson");

    emp2.setTasks(getTasks());

    repository.save(emp2); // Guardar el empleado, junto con las tareas y scopes

    // Mostrar todos los empleados con sus tareas y scopes
    showAllEmployeesWithTasksAndScopes();
  }

  private Set<Task> getTasks() {
    Set<Task> tasks = new HashSet<>();

    // Crear la primera tarea y su scope
    Task task1 = new Task();
    task1.setTaskName("report generation");
    task1.setTaskDescription("Daily report generation");

    Scope scope1 = new Scope(); // Crear un Scope para la tarea 1
    scope1.setName("Report Scope");
    scope1.setDescription("Scope for report generation task");
    task1.setScope(scope1); // Asociar el Scope a la tarea 1

    tasks.add(task1); // Agregar tarea 1 a la lista de tareas

    // Crear la segunda tarea y su scope
    Task task2 = new Task();
    task2.setTaskName("view generation");
    task2.setTaskDescription("Daily view generation");

    Scope scope2 = new Scope(); // Crear un Scope para la tarea 2
    scope2.setName("View Scope");
    scope2.setDescription("Scope for view generation task");
    task2.setScope(scope2); // Asociar el Scope a la tarea 2

    tasks.add(task2); // Agregar tarea 2 a la lista de tareas

    return tasks; // Devolver el conjunto de tareas
  }

  private void showAllEmployeesWithTasksAndScopes() {
    logger.info("List of all employees with their tasks and scopes:");
    for (Employee employee : repository.findAll()) {
      System.out.println("Empleado: " + employee.getFirstName() + " " + employee.getLastName());

      for (Task task : employee.getTasks()) {
        String scopeName = (task.getScope() != null) ? task.getScope().getName() : "Sin scope";
        String scopeDescription = task.getScope().getDescription();
        System.out.println("  Tarea: " + task.getTaskName() +
                ", Descripción: " + task.getTaskDescription() +
                ", Scope: " + scopeName +
                ", ScopeDescription: " + scopeDescription);
      }
    }
  }
}
