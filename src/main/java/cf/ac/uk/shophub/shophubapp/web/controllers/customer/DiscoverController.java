package cf.ac.uk.shophub.shophubapp.web.controllers.customer;

import cf.ac.uk.shophub.shophubapp.service.BusinessSearch;
import cf.ac.uk.shophub.shophubapp.service.dto.BusinessDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DiscoverController {
    private BusinessSearch businessSearch;

    public DiscoverController(BusinessSearch aBusinessSearch){
        businessSearch = aBusinessSearch;
    }

    @GetMapping({"discover"})
    public String discover(Model model) {
        List<BusinessDTO> businessDTOList;
        businessDTOList = businessSearch.findAllBiz();
        model.addAttribute("businessList", businessDTOList);
        return "discover.html";
    }

    @PostMapping({"discover"})
    public String sub(@RequestParam(value = "id") int id, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        businessSearch.subToBiz(username, id);
        return discover(model);
    }
}
