package catastrophe.users.jpa;

import java.util.Collection;

public class User {
	int score;
	String name;
	Collection<String> base64Image;

	public User() {
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
