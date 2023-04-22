package forum.repos;

import org.springframework.data.repository.CrudRepository;
import forum.model.Authority;


public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Authority findByAuthority(String authority);
}
