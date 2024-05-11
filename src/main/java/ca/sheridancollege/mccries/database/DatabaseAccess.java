package ca.sheridancollege.mccries.database;

import java.util.List;

import ca.sheridancollege.mccries.beans.Password;

/* Name: Sarah McCrie
* Assignment: Assignment #2
* Date: October 12, 2023
* Program: A2_mccries
*/

public interface DatabaseAccess {

	// Insert password
	public void insertPassword(Password password);

	// Hard coded insert password
	public void insertPasswordHardCoded();

	// Get list of passwords
	public List<Password> getPasswordList();

	// Get password by Id
	public Password getPasswordById(Long id);

	// Delete password for later use.
	public void deletePasswordById(Long id);

}
