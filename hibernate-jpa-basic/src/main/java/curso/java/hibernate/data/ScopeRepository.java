package curso.java.hibernate.data;

import curso.java.hibernate.data.entity.Employee;
import curso.java.hibernate.data.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, Integer> {
    Optional<Scope> findByName(String name);
}
