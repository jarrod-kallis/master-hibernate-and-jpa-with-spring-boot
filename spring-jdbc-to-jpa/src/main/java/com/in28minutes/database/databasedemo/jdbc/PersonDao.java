package com.in28minutes.database.databasedemo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

class PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
	Person p = new Person();

	p.setId(rs.getInt("id"));
	p.setName(rs.getString("name"));
	p.setLocation(rs.getString("location"));
	p.setDob(rs.getDate("dob"));

	return p;
    }

}

@Repository
public class PersonDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Person> findAll() {
	// We can use a BeanPropertyRowMapper to map the DB results directly to
	// the Person class as long as the columns & field names match exactly.
	// Person Table "BIRTH_DATE" = Person Class "birthDate"
	// return jdbcTemplate.query("select * from person", new
	// BeanPropertyRowMapper<Person>(Person.class));

	return jdbcTemplate.query("select * from person", new PersonRowMapper());
    }

    public Person findById(int id) {
	return jdbcTemplate.queryForObject("select * from person where id = ?", new Object[] { id },
		new BeanPropertyRowMapper<Person>(Person.class));
    }

    public List<Person> findByLike(String fieldName, String fieldValue) {
	return jdbcTemplate.query("select * from person where " + fieldName + " like ?",
		new Object[] { "%" + fieldValue + "%" }, new BeanPropertyRowMapper<Person>(Person.class));
    }

    public int deleteById(int id) {
	return jdbcTemplate.update("delete from person where id = ?", new Object[] { id });
    }

    public int insert(Person p) {
	return jdbcTemplate.update("insert into person (id, name, location, dob) values (?, ?, ?, ?)",
		new Object[] { p.getId(), p.getName(), p.getLocation(), p.getDob() });
    }

    public int update(Person p) {
	return jdbcTemplate.update("update person set name = ?, location = ?, dob = ? where id = ?",
		new Object[] { p.getName(), p.getLocation(), p.getDob(), p.getId() });
    }
}
