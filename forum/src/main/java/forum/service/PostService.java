package forum.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import forum.model.Post;
import forum.repos.PostRepository;
import forum.repos.SubPostRepository;
import org.springframework.security.core.userdetails.User;
import java.util.*;


@Service
public class PostService {
    private final PostRepository rep;
    private final SubPostRepository spRep;

    public PostService(PostRepository rep, SubPostRepository spRep) {
        this.rep = rep;
        this.spRep = spRep;
    }

    public List<Post> getAll() {
        List<Post> result = new LinkedList<>();
        rep.findByOrderByIdAsc().forEach(result::add);
        return result;
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setCreated(Calendar.getInstance());
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            post.setAuthor(user.getUsername());
        } else {
            Post p = get(post.getId());
            post.setCreated(p.getCreated());
            post.setSubs(p.getSubs());
            post.setAuthor(p.getAuthor());
        }
        rep.save(post);
        return post;
    }

    public Post get(Integer id) {
        return rep.findById(id).orElse(null);
    }

    public void delete(int id) {
        Post post = rep.findById(id);
        rep.delete(post);
    }
}
