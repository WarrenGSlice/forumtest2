package forum.repos;

import org.springframework.data.repository.CrudRepository;
import forum.model.SubPost;


public interface SubPostRepository extends CrudRepository<SubPost, Integer> {
    Iterable<SubPost> findAllByPostIdOrderByIdAsc(int id);
}
