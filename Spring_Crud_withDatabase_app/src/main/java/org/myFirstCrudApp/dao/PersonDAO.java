package org.myFirstCrudApp.dao;

import org.myFirstCrudApp.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from Person",new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from Person where id=?",new Object[]{id},new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
        //return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person values(1,?,?,?)",person.getName(),person.getAge(),person.getEmail());
    }
    public void update(int id ,Person person) {
        jdbcTemplate.update("UPDATE Person set name=?,age=?,email=? where id=?",
                person.getName(),person.getAge(), person.getEmail(),person.getId());

    }

    public void delete(int id){
        jdbcTemplate.update("delete from Person where id=?",id);

    }
}
