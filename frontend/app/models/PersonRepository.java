package models;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides CRUD functionality for accessing people. Spring Users auto-magically takes care of many standard
 * operations here.
 */
public interface PersonRepository extends JpaRepository<Person, String> {
    
}