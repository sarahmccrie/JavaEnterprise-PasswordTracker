package ca.sheridancollege.mccries.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/* Name: Sarah McCrie
* Assignment: Assignment #2
* Date: October 12, 2023
* Program: A2_mccries
*/

@Data
@NoArgsConstructor
public class Password {
	@NonNull
	private Long id;
	private String title;
	private String username;
	private String password;
	private String url;
	private String email;
	private String notes;

}
