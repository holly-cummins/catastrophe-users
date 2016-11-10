package catastrophe.users.jpa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Person
 * 
 */
@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String name;
	private int score;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Artwork> images;

	public Person() {
		super();

	}

	public Person(String string) {
		this.name = string;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<Artwork> getImages() {
		return images;
	}

	public void setImages(Set<Artwork> images) {
		this.images = images;
	}
}
