package forum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginControl {
	
	Logger logger = LoggerFactory.getLogger(LoginControl.class);


    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
    	
    	logger.info("Entering LoginControl.loginPage()");

        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect!!!";
        }

        if (logout != null) {
            errorMessage = "You have been successfully logged out!!";
        }
        model.addAttribute("errorMsg", errorMessage);
    	logger.info("Exiting LoginControl.loginPage()");

        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    	logger.info("Entering LoginControl.logoutPage()");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    	logger.info("Exiting LoginControl.logoutPage()");

        return "redirect:/login?logout=true";
    }
}
