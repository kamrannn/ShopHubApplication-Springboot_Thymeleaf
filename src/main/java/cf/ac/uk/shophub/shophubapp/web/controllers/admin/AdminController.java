package cf.ac.uk.shophub.shophubapp.web.controllers.admin;

import cf.ac.uk.shophub.shophubapp.data.ContactUsJDBC;
import cf.ac.uk.shophub.shophubapp.data.SHRepositoryJDBC;
import cf.ac.uk.shophub.shophubapp.domain.ContactUs;
import cf.ac.uk.shophub.shophubapp.domain.User;
import cf.ac.uk.shophub.shophubapp.service.BusinessSearch;
import cf.ac.uk.shophub.shophubapp.service.dto.BusinessDTO;
import cf.ac.uk.shophub.shophubapp.web.forms.BusinessForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private BusinessSearch businessSearch;
    @Autowired
    private SHRepositoryJDBC repositoryJDBC;
    @Autowired
    private ContactUsJDBC contactUsJDBC;

    public AdminController(BusinessSearch aBusinessSearch) {
        businessSearch = aBusinessSearch;
    }

    @GetMapping("")
    public String admin(Model model) {
        return "admin.html";
    }

    @GetMapping("/new-business")
    public String getNewBusinessForm(Model model) {
        BusinessForm businessForm = new BusinessForm();
        model.addAttribute("businessForm", businessForm);
        return "new-business.html";
    }

    @PostMapping("/post-business")
    public String newBusinessPost(BusinessForm businessForm, BindingResult bindingResult, Model model) {
        BusinessDTO businessDTO = new BusinessDTO(
                1, //WARNING: arbitrary value, this should be avoided!
                businessForm.getName(),
                businessForm.getDescription(),
                businessForm.getEmail()
        );
        if (bindingResult.hasErrors()) {
            return "";
        }
        businessSearch.insertBiz(businessDTO);
        return "redirect:/discover";
    }

    @GetMapping("/delete-business")
    public String getDeletableBusinesses(Model model) {
        List<BusinessDTO> businessDTOList;
        businessDTOList = businessSearch.findAllBiz();
        model.addAttribute("businessList", businessDTOList);
        return "delete-business.html";
    }

    @PostMapping({"/delete-business"})
    public String deleteBusiness(@RequestParam(value = "bizID") int bizID, Model model) {
        businessSearch.deleteBiz(bizID);
        return getDeletableBusinesses(model);
    }


    @GetMapping("/list-users")
    public String getAllUsers(Model model) {
        List<User> userList = repositoryJDBC.findAllUsers();
        model.addAttribute("usersList", userList);
        return "users-list";
    }

    @GetMapping("/delete-user/{username}")
    public String deleteUserByUsername(@PathVariable(value = "username") String username) {
        // call delete employee method
        repositoryJDBC.deleteUserByUsername(username);
        return "redirect:/admin/list-users";
    }

    @GetMapping("/update-user/{username}")
    public String showFormForUpdate(@PathVariable(value = "username") String username, Model model) {
        // get employee from the service
        User user = repositoryJDBC.findUserByUsername(username);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute User user) {
        repositoryJDBC.updateUser(user);
        return "redirect:/admin/list-users";
    }

    @GetMapping("/list-contact-us")
    public String getAllContactsUs(Model model) {
        List<ContactUs> contactUsList = contactUsJDBC.findAllContactUs();
        model.addAttribute("contactUsList", contactUsList);
        return "contact-us-list";
    }

    @GetMapping("/save-contact")
    public String saveContactUs(Model model) {
        ContactUs contactUs = new ContactUs();
        model.addAttribute("contactUs", contactUs);
        return "add-contact-us";
    }


    @PostMapping("/save-contact")
    public String saveContactUs(@Valid ContactUs contactUs, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-contact-us";
        } else {
            contactUsJDBC.saveContactUs(contactUs);
            return "redirect:/";
        }
    }


}
