package ca.sheridancollege.mccries.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.mccries.beans.Password;

/* Name: Sarah McCrie
* Assignment: Assignment #2
* Date: October 12, 2023
* Program: A2_mccries
*/

@Repository
public class DatabaseAccessImpl implements DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	// Insert password hardcoded method.
	public void insertPasswordHardCoded() {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "INSERT INTO PASSWORDS(id, title, username, password, url, email, notes) VALUES (123456789, 'SampleTitle', 'SampleUsername', 'SamplePassword', 'SampleURL', 'SampleEmail', 'SampleNotes')";

		int rowsAffected = jdbc.update(query, namedParameters);

		if (rowsAffected > 0)
			System.out.println("Hard coded password inserted into database");
	}

	// Insert password method.
	public void insertPassword(Password password) {

		String query = "INSERT INTO PASSWORDS(id, title, username, password, url, email, notes) VALUES (:id, :title, :username, :password, :url, :email, :notes)";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", password.getId());
		namedParameters.addValue("title", password.getTitle());
		namedParameters.addValue("username", password.getUsername());
		namedParameters.addValue("password", password.getPassword());
		namedParameters.addValue("url", password.getUrl());
		namedParameters.addValue("email", password.getEmail());
		namedParameters.addValue("notes", password.getNotes());

		int rowsAffected = jdbc.update(query, namedParameters);

		if (rowsAffected > 0)
			System.out.println("A password was inserted into database");
	}

	// Get Password List method.
	public List<Password> getPasswordList() {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM passwords";

		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Password>(Password.class));

	}

	// Get Password by Id method
	public Password getPasswordById(Long id) {

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "SELECT * FROM passwords WHERE id = :id";

		namedParameters.addValue("id", id);

		List<Password> passwords = jdbc.query(query, namedParameters,
				new BeanPropertyRowMapper<Password>(Password.class));

		return passwords.isEmpty() ? null : passwords.get(0);
	}

	// For delete functionality to be used in the future potentially.
	public void deletePasswordById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();

		String query = "DELETE FROM passwords WHERE id = :id";

		namedParameters.addValue("id", id);

		if (jdbc.update(query, namedParameters) > 0)

			System.out.println("Deleted record " + id + " from database.");
	}

}
