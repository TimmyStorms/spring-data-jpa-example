package com.github.timmystorms.spring4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PersonRepostioryTest {
    
    @Autowired
    private PersonRepository repo;
    
    @Before
    public void init() {
        repo.deleteAll();
    }
    
    @Test
    public void createPerson() {
        Person person = repo.save(new Person("John Doe"));
        Assert.assertNotNull(person.getId());
    }
    
    @Test
    public void findPersonByName() {
        Person person = repo.save(new Person("John Doe"));
        Assert.assertEquals(person, repo.findByName("John Doe").iterator().next());
    }
    
    @Test
    public void searchPersonNatively() {
        Person person = repo.save(new Person("John Doe"));
        Assert.assertEquals(person, repo.searchNatively("%ohn%").iterator().next());
    }
    
    @Test
    public void searchPerson() {
        Person person = repo.save(new Person("John Doe"));
        Assert.assertEquals(person, repo.search("%ohn%").iterator().next());
    }
    
    @Test
    public void findPagedPersons() {
        for (int i = 0; i < 10; i++) {
            repo.save(new Person("John Doe"));
        }
        Page<Person> result = repo.findAll(new PageRequest(0, 5));
        Assert.assertEquals(10, result.getTotalElements());
        Assert.assertEquals(2, result.getTotalPages());
        Assert.assertEquals(5, result.getNumberOfElements());
        Assert.assertEquals(0, result.getNumber());
        result = repo.findAll(new PageRequest(2, 5));
        Assert.assertEquals(10, result.getTotalElements());
        Assert.assertEquals(2, result.getTotalPages());
        Assert.assertEquals(0, result.getNumberOfElements());
        Assert.assertEquals(2, result.getNumber());
    }

}
