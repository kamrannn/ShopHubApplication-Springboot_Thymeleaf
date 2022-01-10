package cf.ac.uk.shophub.shophubapp.api.controllers;

import cf.ac.uk.shophub.shophubapp.service.BusinessSearch;
import cf.ac.uk.shophub.shophubapp.service.dto.BusinessDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class APIController {
    private BusinessSearch businessSearch;

    public APIController(BusinessSearch aBusinessSearch) {
        businessSearch = aBusinessSearch;
    }

    @GetMapping("discover")
    public ResponseEntity<List<BusinessDTO>> findAllBiz() {
        List<BusinessDTO> businessDTOList;
        businessDTOList = businessSearch.findAllBiz();
        return ResponseEntity.ok(businessDTOList);
    }

    @GetMapping("user")
    public ResponseEntity<List<BusinessDTO>> findSubbedBiz(@RequestParam(value = "username") String username) {
        List<BusinessDTO> businessDTOList;
        businessDTOList = businessSearch.findSubbedBiz(username);
        return ResponseEntity.ok(businessDTOList);
    }
}
