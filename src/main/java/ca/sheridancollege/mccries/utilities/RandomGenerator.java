package ca.sheridancollege.mccries.utilities;

import java.time.LocalTime;
import java.util.Random;

/* Name: Sarah McCrie
* Assignment: Assignment #1
* Date: October 5, 2023
* Program: A1_mccries
*/

public class RandomGenerator {

	public static Long generateRandomId() {
		Random i = new Random(LocalTime.now().toNanoOfDay());
		return i.nextLong(111111111, 999999999);
	}
}
