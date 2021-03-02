package co.com.valid.dto;

/**
 * Person data transfer object class
 * 
 * @author diego ibanez
 * @Since 1.0.0
 */
public class PersonDTO {

	private long id;
	private String name;
	private String lastname;
	private boolean processed;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

}
