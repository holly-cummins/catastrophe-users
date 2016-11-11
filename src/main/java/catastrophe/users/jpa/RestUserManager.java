package catastrophe.users.jpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("users")
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

	@Path("{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Set<Artwork> findByName(@PathParam("userName") String userName) {
		Person person = userManager.findByName(userName);
		if (person != null) {
			return person.getImages();
		} else {
			return new HashSet<>();
		}
	}

	@Path("clearScores")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String clearAll() {
		userManager.clearAll();
		return "All scores deleted.";

	}

	@Path("updateScore")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public void updateScore(@QueryParam("userName") String userName, @QueryParam("score") int score,
			@QueryParam("image") String base64Image) {
		System.out.println("User service updating " + userName + " with score " + score + ".");
		userManager.updateScore(userName, score, base64Image);
	}

}
