package forum.repos;

import org.springframework.data.repository.CrudRepository;
import forum.model.User;


public interface UserRepository extends CrudRepository<User, Integer> {
}
