package cf.ac.uk.shophub.shophubapp.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Business {
    private int businessID;
    private String name;
    private String description;
    private String email;
}