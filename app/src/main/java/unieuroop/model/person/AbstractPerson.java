package unieuroop.model.person;

import java.util.Date;

public abstract class AbstractPersonImpl {
	
	private final String name;
	private final String surname;
	private final Date birthdayDate;
	
	public AbstractPersonImpl(final String name, final String surname, final Date birthdayDate) {
		this.name = name;
		this.surname = surname;
		this.birthdayDate = birthdayDate;
	}
	
	/**
	 * @return the name of the person 
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the surname of the person 
	 */
	public String getSurname() {
		return this.surname;
	}
	
	/**
	 * @return the birthday of the person 
	 */
	public Date getBirthdayDate() {
		return this.birthdayDate;
	}
}
