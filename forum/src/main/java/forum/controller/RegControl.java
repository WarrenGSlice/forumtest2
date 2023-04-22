package forum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import forum.model.User;
import forum.repos.AuthorityRepository;
import forum.repos.UserRepository;

@Controller
public class RegControl {
	Logger logger = LoggerFactory.getLogger(RegControl.class);

    private final UserRepository userRep;
    private final AuthorityRepository authRep;
    private final PasswordEncoder encoder;

    public RegControl(UserRepository userRep, AuthorityRepository authRep, PasswordEncoder encoder) {
    	logger.info("Entering RegControl.RegControl()");

        this.userRep = userRep;
        this.authRep = authRep;
        this.encoder = encoder;
    	logger.info("Exiting RegControl.RegControl()");

    }

    @GetMapping("/reg")
    public String reg() {
    	logger.info("Entering RegControl.reg()");
    	logger.info("Exiting RegControl.reg()");

        return "reg";
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
    	logger.info("Entering RegControl.save()");

        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authRep.findByAuthority("ROLE_USER"));
        userRep.save(user);
    	logger.info("Exiting RegControl.reg()");

        return "redirect:/login";
    }
}
