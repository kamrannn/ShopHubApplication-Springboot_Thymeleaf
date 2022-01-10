package cf.ac.uk.shophub.shophubapp.data;

import cf.ac.uk.shophub.shophubapp.data.interfaces.SHRepository;
import cf.ac.uk.shophub.shophubapp.domain.Business;
import cf.ac.uk.shophub.shophubapp.domain.User;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SHRepositoryJDBC implements SHRepository {

    private final JdbcTemplate jdbc;
    private final RowMapper<Business> businessMapper;
    private final RowMapper<User> userMapper;
    private final RowMapper<User> userMapper2;

    public SHRepositoryJDBC(JdbcTemplate jdbcOperations) {
        jdbc = jdbcOperations;

        businessMapper = (rs, i) -> new Business(
                rs.getInt("businessID"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("email")
        );
        userMapper = (rs, i) -> new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role")
        );

        userMapper2 = (rs, i) -> new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getBoolean("enabled"),
                rs.getString("email")
        );
    }

    //-------------FIND A LIST OF BUSINESSES-----------
    @Override
    public List<Business> findAllBiz() {
        return jdbc.query(
                "select businessID, name, description, email  from business",
                new Object[]{},
                businessMapper);
    }


    @Override
    public List<Business> findSubbedBiz(String username) {
        return jdbc.query(
                "select business.businessID, name, description, email from business where businessid IN ( select businessid FROM usersubscriptions WHERE username=?)",
                new Object[]{username},
                businessMapper
        );
    }

    //----------USERSUBSCRIPTIONS TABLE  MODIFICATION---------
    @Override
    public void subToBiz(String username, int id) {
        try {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc).withTableName("usersubscriptions");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("username", username);
            parameters.put("businessid", id);
            insert.execute(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //-------BUSINESS TABLE MODIFICATION--------
    @Override
    public void insertBiz(Business business) {
        try {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc).withTableName("business");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("name", business.getName());
            parameters.put("description", business.getDescription());
            parameters.put("email", business.getEmail());
            insert.execute(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBiz(int bizID) {
        try {
            String deleteSubsQuery = "DELETE FROM usersubscriptions WHERE businessID = ? ";
            String deleteBizQuery = "DELETE FROM business WHERE businessID = ? ";
            jdbc.update(deleteSubsQuery, bizID);
            jdbc.update(deleteBizQuery, bizID);
            System.out.println("deleted");
        } catch (Exception e) {
            System.out.println("not deleted");
            return;
        }
    }

    //-------CRUD Operations for USER---------
    @Override
    public User findByUsername(String username) {
        try {
            return
                    jdbc.queryForObject(
                            "select username, password, role from users where username=?",
                            userMapper,
                            username);
        } catch (IncorrectResultSizeDataAccessException incorrectResultSizeDataAccessException) {
            return null;
        }
    }

    @Override
    public List<User> findAllUsers() {
        return jdbc.query(
                "select username, password,role, enabled, email  from users",
                new Object[]{},
                userMapper2);
    }

    @Override
    public void deleteUserByUsername(String username) {
        try {
            String deleteSubsQuery = "DELETE FROM usersubscriptions WHERE username = ?  ";
            String deleteUserQuery = "DELETE FROM users where username=? ";
            jdbc.update(deleteSubsQuery, username);
            jdbc.update(deleteUserQuery, username);
            System.out.println("deleted");
        } catch (Exception e) {
            System.out.println("not deleted");
            return;
        }
    }

    @Override
    public void updateUser(User user) {
        String updateUserQuery = "UPDATE users SET password= ? ,enabled = ?, email= ?, role= ? where username = ?";
        jdbc.update(updateUserQuery, user.getPassword(), user.isEnabled(), user.getEmail(), user.getRole(), user.getUsername());
    }

    @Override
    public User findUserByUsername(String username) {
        try {
            return
                    jdbc.queryForObject(
                            "select username, password,role, enabled, email  from users where username=?",
                            userMapper2,
                            username);
        } catch (IncorrectResultSizeDataAccessException incorrectResultSizeDataAccessException) {
            return null;
        }
    }

    @Override
    public void insertUser(String username, String encodedPassword, String email) {
        try {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc).withTableName("users");
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("username", username);
            parameters.put("password", encodedPassword);
            parameters.put("enabled", true);
            parameters.put("email", email);
            parameters.put("role", "CUSTOMER");

            insert.execute(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean areCredentialsUnique(String username, String email) {
        Integer usernameCount = jdbc.queryForObject(
                "SELECT count(*) FROM users WHERE username = ?", Integer.class, username);
        Integer emailCount = jdbc.queryForObject(
                "SELECT count(*) FROM users WHERE email = ?", Integer.class, email);
        if (usernameCount != 0 || emailCount != 0) {
            return false;
        }
        return true;
    }
}
