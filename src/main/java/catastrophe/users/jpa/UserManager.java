package catastrophe.users.jpa;

import java.util.List;

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

	@PersistenceContext(unitName = "catastrophe.auth")
	private EntityManager entityManager;

	// @PersistenceUnit(unitName = "catastrophe.auth")
	// private EntityManagerFactory entityManagerFactory;

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

	public List<Person> findByName(String filter) {
		if (filter == null || filter.isEmpty()) {
			return findAll();
		}
		filter = filter.toLowerCase();
		return getEm().createNamedQuery("User.findByName", Person.class)
				.setParameter("filter", filter + "%").getResultList();
	}

	public void clearAll() {
		getEm().createQuery("DELETE FROM User").executeUpdate();

	}

	public void create(String name) {
		Person c = new Person();
		c.setName(name);
		// Write to the database
		saveOrPersist(c);
	}

	public void updateScore(String userName, int score) {
		if (userName != null) {
			EntityManager em = getEm();
			Person person = em.find(Person.class, userName);
			if (person == null) {
				System.out.println("Making a new user, " + userName);
				person = new Person();
				person.setName(userName);
			}

			person.setScore(person.getScore() + score);
			try {
				utx.begin();
				em.merge(person);
				utx.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			findAll();

		} else {
			System.err
					.println("Warning: Trying to add a score to a null user.");
		}
	}

}
