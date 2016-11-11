package catastrophe.users.jpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

@ApplicationScoped
public class UserManager {

	@Resource
	UserTransaction utx;

	@PersistenceContext(unitName = "catastrophe.users")
	private EntityManager entityManager;

	public UserManager() {
	}

	public void saveOrPersist(Person entity) {
		try {
			// TODO if (entity.getId() > 0) {
			// entityManager.merge(entity);
			// } else {
			getEm().persist(entity);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private EntityManager getEm() {
		return entityManager;
	}

	public List<Person> findAll() {
		CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
		CriteriaQuery<Person> cq = criteriaBuilder.createQuery(Person.class);
		Root<Person> root = cq.from(Person.class);
		cq.select(root);
		cq.orderBy(criteriaBuilder.desc(root.get("score")));
		List<Person> users = getEm().createQuery(cq).getResultList();
		// Fill in a user so that the table isn't just one row
		users.add(new Person("Felix"));
		return users;
	}

	public Person findByName(String name) {
		return getEm().find(Person.class, name);
	}

	public void clearAll() {
		EntityManager em = getEm();
		try {
			utx.begin();
			em.createQuery("DELETE FROM Person").executeUpdate();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void create(String name) {
		Person c = new Person();
		c.setName(name);
		// Write to the database
		saveOrPersist(c);
	}

	public void updateScore(String userName, int score, String image) {
		if (userName != null) {
			EntityManager em = getEm();
			Person person = em.find(Person.class, userName);
			if (person == null) {
				System.out.println("Making a new user, " + userName);
				person = new Person();
				person.setName(userName);
			}

			person.setScore(person.getScore() + score);

			Set<Artwork> images = person.getImages();
			if (images == null) {
				images = new HashSet<>();
			}
			images.add(new Artwork(person, image));
			person.setImages(images);
			try {
				utx.begin();
				em.merge(person);
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			findAll();

		} else {
			System.err.println("Warning: Trying to add a score to a null user.");
		}
	}

}
