package cf.ac.uk.shophub.shophubapp.service.dto;

import lombok.Value;
import lombok.AllArgsConstructor;
import cf.ac.uk.shophub.shophubapp.domain.Business;

@Value
@AllArgsConstructor
public class BusinessDTO {
    int businessID;
    String name;
    String description;
    String email;

    public BusinessDTO(Business aBusiness){
        this(
                aBusiness.getBusinessID(),
                aBusiness.getName(),
                aBusiness.getDescription(),
                aBusiness.getEmail()
        );
    }

    public Business toBusiness() {
        return new Business(
                businessID,
                name,
                description,
                email
        );
    }
}
