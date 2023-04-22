package forum.repos;

import org.springframework.data.repository.CrudRepository;
import forum.model.Post;


public interface PostRepository extends CrudRepository<Post, Integer> {
    Post findById(int id);
    Iterable<Post> findByOrderByIdAsc();
}
