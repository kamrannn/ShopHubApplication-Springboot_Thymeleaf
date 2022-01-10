package cf.ac.uk.shophub.shophubapp.web.controllers.customer;

import cf.ac.uk.shophub.shophubapp.service.BusinessSearch;
import cf.ac.uk.shophub.shophubapp.service.dto.BusinessDTO;
import cf.ac.uk.shophub.shophubapp.service.security.MyUserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserPageController {
    private BusinessSearch businessSearch;

    public UserPageController(BusinessSearch aBusinessSearch){
        businessSearch = aBusinessSearch;
    }

    /**
     * Method is executed on a URL request to /user.
     *
     * Method takes the username of the current user and plugs it
     * into a query to search for businesses that said user is subscribed to.
     *
     * Method adds the query result to the model
     * @return
     * Returns the html for the user page.
     */
    @GetMapping({"user"})
    public String userPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        List<BusinessDTO> businessDTOList = businessSearch.findSubbedBiz(username);
        model.addAttribute("businessList", businessDTOList);
        return "user-page.html";
    }
}
