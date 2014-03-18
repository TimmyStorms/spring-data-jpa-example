package com.github.timmystorms.spring4;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
    
    Set<Person> findByName(String name);
    
    @Query(value = "SELECT * FROM PERSON p WHERE p.name like :pattern", nativeQuery = true)
    Set<Person> searchNatively(@Param("pattern") String pattern);
    
    @Query(value = "FROM Person p WHERE p.name like :pattern")
    Set<Person> search(@Param("pattern") String pattern);

}
