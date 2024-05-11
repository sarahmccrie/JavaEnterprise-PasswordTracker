
package ca.sheridancollege.mccries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.mccries.beans.Password;
import ca.sheridancollege.mccries.database.DatabaseAccess;
import ca.sheridancollege.mccries.utilities.RandomGenerator;

/* Name: Sarah McCrie
* Assignment: Assignment #2
* Date: October 12, 2023
* Program: A2_mccries
*/

@Controller
public class PasswordController {

	@Autowired
	private DatabaseAccess da; // DatabaseAccess object

	boolean successfulAdd = false; // Sets a boolean called successfulAdd to false

	// Get mapping for index.html page
	@GetMapping("/")
	public String index(Model model) {
		Long num = RandomGenerator.generateRandomId(); // Uses RandomGenerator to generate random 9 digit id & stores as num
		Password aPassword = new Password();
		aPassword.setId(Long.valueOf(num));
		aPassword.setTitle("");
		aPassword.setUsername("");
		aPassword.setPassword("");
		aPassword.setUrl("");
		aPassword.setEmail("");
		aPassword.setNotes("");
		model.addAttribute("password", aPassword);
		model.addAttribute("passwordList", da.getPasswordList());
		return "index";
	}

	// Get mapping for viewPasswordRecord.html
	@GetMapping("/viewPasswordRecord")
	public String viewPasswordRecord(Model model) {
		model.addAttribute("passwordList", da.getPasswordList());
		return "viewPasswordRecord";
	}

	// Get mapping for searchPasswordRecord.html
	@GetMapping("/searchPasswordRecord")
	public String searchPasswordRecord(Model model) {
		Password aPassword = new Password();
		aPassword.setId((long) 0000000);
		model.addAttribute("password", aPassword);
		return "searchPasswordRecord";
	}

	// Post mapping for index.html
	@PostMapping("/")
	public String index(Model model, @ModelAttribute Password password) {
		da.insertPassword(password);
		model.addAttribute("password", new Password());
		model.addAttribute("passwordList", da.getPasswordList());
		successfulAdd = true; // Sets the boolean to true
		model.addAttribute("successfulAdd", successfulAdd);
		return "index";
	}

	// Post mapping for searchPasswordRecord.html
	@PostMapping("/searchPasswordRecord")
	public String searchPasswordRecord(Model model, @ModelAttribute Password password) {
		Password foundPassword = da.getPasswordById(password.getId());
		boolean isIdMatching = (foundPassword != null);
		model.addAttribute("isIdMatching", isIdMatching);
		model.addAttribute("foundPassword", foundPassword);
		return "searchPasswordRecord";
	}

	// Began creating a deletePasswordById before I realized we did not have to.
	// Could be useful in the future.
	@GetMapping("/deletePasswordById/{id}")
	public String deletePasswordById(Model model, @PathVariable Long id) {
		da.deletePasswordById(id);
		model.addAttribute("password", new Password());
		model.addAttribute("passwordList", da.getPasswordList());
		return "index";
	}
}
