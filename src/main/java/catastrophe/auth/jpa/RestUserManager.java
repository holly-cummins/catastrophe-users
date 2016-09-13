package catastrophe.auth.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("auth")
public class RestUserManager {

	// The web says we can inject other CDI things but not a JPA
	// thing, so delegate. This definitely has to live in the web project
	// (or where the beans.xml is).
	@Inject
	UserManager userManager;

	@Path("leaderboard")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Person> findAll() {
		System.out.println("Returning leaderboard");
		return userManager.findAll();
	}

	public List<Person> findByName(String filter) {
		return userManager.findByName(filter);
	}

	public void clearAll() {
		userManager.clearAll();

	}

	@Path("updateScore")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public void updateScore(@QueryParam("userName") String userName,
			@QueryParam("score") int score) {
		System.out.println("Auth service updating " + userName + " with score " + score + ".");
		userManager.updateScore(userName, score);
	}

}
