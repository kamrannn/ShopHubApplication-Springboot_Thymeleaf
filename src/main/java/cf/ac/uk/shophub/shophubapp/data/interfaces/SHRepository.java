package cf.ac.uk.shophub.shophubapp.data.interfaces;

import cf.ac.uk.shophub.shophubapp.domain.Business;
import cf.ac.uk.shophub.shophubapp.domain.User;

import java.util.List;

public interface SHRepository {

    List<Business> findAllBiz();

    List<User> findAllUsers();

    void deleteUserByUsername(String username);

    void updateUser(User user);

    List<Business> findSubbedBiz(String username);

    void subToBiz(String username, int id);

    void insertBiz(Business business);

    void deleteBiz(int bizID);

    User findByUsername(String username);

    User findUserByUsername(String username);

    void insertUser(String username, String encodedPassword, String email);

    Boolean areCredentialsUnique(String username, String email);

}
