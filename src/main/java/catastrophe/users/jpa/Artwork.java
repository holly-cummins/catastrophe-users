package catastrophe.users.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Entity implementation class for Entity: User
 * 
 */
@Entity
public class Artwork implements Serializable {

	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@Id
	private String id;

	@Lob
	private String data;

	public Artwork() {

	}

	public Artwork(Person user, String string) {
		this.data = string;
	}

	public String getImage() {
		return data;
	}

	public void setImage(String image) {
		this.data = image;
	}
}
