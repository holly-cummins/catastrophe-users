package catastrophe.auth.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: User
 * 
 */
@Entity
public class Person extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String name;
	private int score;

	public Person() {
		super();

	}

	public Person(String string) {
		this.name = string;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
