package com.github.timmystorms.spring4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Person() {
    }

    public Person(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Person) {
            final Person other = (Person) obj;
            return new EqualsBuilder().append(name, other.name).append(id, other.id).isEquals();
        }
        return false;
    }

}
