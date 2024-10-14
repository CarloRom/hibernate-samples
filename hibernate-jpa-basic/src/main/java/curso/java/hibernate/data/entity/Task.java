package curso.java.hibernate.data.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "TBL_TASK")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Relación uno a uno con Scope
    @JoinColumn(name = "scope_Id") // Asegúrate de que el nombre de la columna sea correcto
    private Scope scope;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", scope=" + (scope != null ? scope.getName() : "None") +
                '}';
    }
}
