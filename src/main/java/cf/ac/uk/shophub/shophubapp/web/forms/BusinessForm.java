package cf.ac.uk.shophub.shophubapp.web.forms;

import cf.ac.uk.shophub.shophubapp.service.dto.BusinessDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessForm {
    private String name;
    private String description;
    private String email;

    public BusinessForm(BusinessDTO businessDTO) {

    }
}
