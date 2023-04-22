package forum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import forum.model.Post;
import forum.service.PostService;

@Controller
public class PostControl {
	Logger logger = LoggerFactory.getLogger(PostControl.class);

    private final PostService service;

    public PostControl(PostService service) {
    	logger.info("Entering PostControl.PostControl()");

        this.service = service;
    	logger.info("Exiting PostControl.PostControl()");

    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
    	logger.info("Entering PostControl.save()");

        service.save(post);
    	logger.info("Exiting PostControl.save()");

        return "redirect:/index";
    }

    @RequestMapping("/edit")
    public String edit(Model model,
                       @RequestParam(required = false) Integer id) {
    	logger.info("Entering PostControl.edit()");

        Post post = new Post();
        if (id != null) {
            post = service.get(id);
        }
        model.addAttribute("post", post);
    	logger.info("Exiting PostControl.edit()");

        return "edit";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int id) {
    	logger.info("Entering PostControl.delete()");

        service.delete(id);
    	logger.info("Exiting PostControl.delete()");

        return "redirect:/index";
    }

}
