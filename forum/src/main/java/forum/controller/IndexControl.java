package forum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import forum.service.PostService;

@Controller
public class IndexControl {
	Logger logger = LoggerFactory.getLogger(IndexControl.class);
    private final PostService postService;

    public IndexControl(PostService postService) {
    	//Log the API call
    	logger.info("Entering IndexControl.IndexControl()");
        this.postService = postService;
    	logger.info("Exiting IndexControl.IndexControl()");

    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
    	
    	logger.info("Entering IndexControl.index()");

        model.addAttribute("posts", postService.getAll());
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        
    	logger.info("Exiting IndexControl.index()");

        return "index";
    }
}
