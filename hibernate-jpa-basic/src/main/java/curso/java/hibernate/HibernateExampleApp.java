package curso.java.hibernate;

import curso.java.hibernate.data.EmployeeRepository;
import curso.java.hibernate.data.ScopeRepository; // Importa el ScopeRepository
import curso.java.hibernate.data.entity.Employee;
import curso.java.hibernate.data.entity.Task;
import curso.java.hibernate.data.entity.Scope;
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
  EmployeeRepository employeeRepository;

  @Autowired
  ScopeRepository scopeRepository; // Inyección del ScopeRepository

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

    emp2.setTasks(getTasks(emp2)); // Pasar emp2 a getTasks

    employeeRepository.save(emp2); // Guardar el empleado, junto con las tareas y scopes

    // Mostrar todos los empleados con sus tareas y scopes
    showAllEmployeesWithTasksAndScopes();
  }

  private Set<Task> getTasks(Employee employee) {
    Set<Task> tasks = new HashSet<>();

    // Crear el primer scope y guardarlo
    Scope scope1 = new Scope();
    scope1.setName("Report Scope");
    scope1.setDescription("Scope for report generation task");

    // Comprobar si el Scope ya existe
    Optional<Scope> existingScope = scopeRepository.findByName(scope1.getName());
    if (existingScope.isPresent()) {
      scope1 = existingScope.get(); // Utilizar el existente
    } else {
      scope1 = scopeRepository.save(scope1); // Guardar nuevo scope
    }
//El ámbito scope1, tiene varias tareas (la una y la dos) y cada tarea pertenece a un ámbito
    // Crear la primera tarea y asociarla al scope1
    Task task1 = new Task();
    task1.setTaskName("Report Generation");
    task1.setTaskDescription("Daily report generation");
    task1.setScope(scope1);
    task1.setEmployee(employee);
    tasks.add(task1);

    // Crear la segunda tarea y asociarla al scope1
    Task task2 = new Task();
    task2.setTaskName("View Generation");
    task2.setTaskDescription("Daily view generation");
    task2.setScope(scope1);
    task2.setEmployee(employee);
    tasks.add(task2);

    return tasks; // Devolver el conjunto de tareas
  }

  private void showAllEmployeesWithTasksAndScopes() {
    logger.info("List of all employees with their tasks and scopes:");
    for (Employee employee : employeeRepository.findAll()) {
      System.out.println("Empleado: " + employee.getFirstName() + " " + employee.getLastName());

      for (Task task : employee.getTasks()) {
        String scopeName = (task.getScope() != null) ? task.getScope().getName() : "Sin scope";
        String scopeDescription = (task.getScope() != null) ? task.getScope().getDescription() : "Sin descripción";
        System.out.println("  Tarea: " + task.getTaskName() +
                ", Descripción: " + task.getTaskDescription() +
                ", Scope: " + scopeName +
                ", Descripción del Scope: " + scopeDescription);
      }
    }
  }
}