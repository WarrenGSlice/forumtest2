package forum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import forum.model.Post;
import forum.model.SubPost;
import forum.service.PostService;
import forum.service.SubPostService;

import java.util.List;

@Controller
public class SubPostControl {
	Logger logger = LoggerFactory.getLogger(SubPostControl.class);

    private final PostService pServ;
    private final SubPostService subServ;

    public SubPostControl(PostService pServ, SubPostService subServ) {
    	logger.info("Entering SubPostControl.SubPostControl()");

        this.pServ = pServ;
        this.subServ = subServ;
    	logger.info("Exiting SubPostControl.SubPostControl()");

    }

    @GetMapping("/post")
    public String post(Model model, @RequestParam int postId) {
    	logger.info("Entering SubPostControl.post()");

        Post post = pServ.get(postId);
        model.addAttribute("post", post);
        List<SubPost> subs = subServ.getAllByPostId(postId);
        model.addAttribute("subs", subs);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    	logger.info("Exiting SubPostControl.SubPostControl()");

        return "post";
    }

    @PostMapping("/subpost/save")
    public String saveSubpost(@ModelAttribute SubPost sub, @RequestParam int postId) {
    	logger.info("Entering SubPostControl.saveSubpost()");

        sub.setPost(pServ.get(postId));
        subServ.save(sub);
    	logger.info("Exiting SubPostControl.saveSubpost()");

        return "redirect:/post?postId=" + postId;
    }

    @RequestMapping("/subpost/update")
    public String updateSubpost(Model model, @RequestParam int subId,
                                @RequestParam int postId) {
    	logger.info("Entering SubPostControl.updateSubpost()");

        SubPost sub = subServ.get(subId);
        model.addAttribute("sub", sub);
        model.addAttribute("postId", postId);
    	logger.info("Exiting SubPostControl.updateSubpost()");

        return "subpost/edit";
    }

    @RequestMapping("/subpost/delete")
    public String deleteSubpost(Model model, @RequestParam int subId,
                                @RequestParam int postId) {
    	logger.info("Entering SubPostControl.deleteSubpost()");

        subServ.delete(subId);
    	logger.info("Entering SubPostControl.deleteSubpost()");

        return "redirect:/post?postId=" + postId;

    }
}
