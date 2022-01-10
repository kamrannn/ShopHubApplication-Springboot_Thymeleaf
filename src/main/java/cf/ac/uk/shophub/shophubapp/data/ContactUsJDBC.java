package cf.ac.uk.shophub.shophubapp.data;

import cf.ac.uk.shophub.shophubapp.data.interfaces.ContactUsRepository;
import cf.ac.uk.shophub.shophubapp.domain.ContactUs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContactUsJDBC implements ContactUsRepository {

    private final JdbcTemplate jdbc;
    private final RowMapper<ContactUs> contactUsRowMapper;

    @Autowired
    public ContactUsJDBC(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        contactUsRowMapper = (rs, i) -> new ContactUs(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("message")
        );
    }


    @Override
    public List<ContactUs> findAllContactUs() {
        return jdbc.query(
                "select id ,name, email, message from contact_us",
                new Object[]{},
                contactUsRowMapper);
    }

    @Override
    public void saveContactUs(ContactUs contactUs) {
        try {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc).withTableName("contact_us");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("name", contactUs.getName());
            parameters.put("email", contactUs.getEmail());
            parameters.put("message", contactUs.getMessage());
            insert.execute(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
