package cf.ac.uk.shophub.shophubapp.UserTests;

import cf.ac.uk.shophub.shophubapp.data.interfaces.SHRepository;
import cf.ac.uk.shophub.shophubapp.domain.ContactUs;
import cf.ac.uk.shophub.shophubapp.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TestUserRepository {
    @Autowired
    SHRepository shRepository;

    @Autowired
    MockMvc mvc;

    @Test
    public void adminShouldGetAll4Users() throws Exception {
        List<User> userList = shRepository.findAllUsers();
        assertEquals(4, userList.size());
    }

    @Test
    public void adminShouldBeAbleToDelete() throws Exception {
        shRepository.deleteUserByUsername("test4");
        List<User> userList = shRepository.findAllUsers();
        assertEquals(3, userList.size());
    }

    @Test
    public void adminShouldBeAbleToUpdate() throws Exception {
        User user = shRepository.findUserByUsername("test3");
        user.setEmail("testemail@gamil.com");
        user.setEnabled(false);
        shRepository.updateUser(user);
        List<User> userList = shRepository.findAllUsers();
        Assertions.assertThat(userList).last().hasFieldOrPropertyWithValue("email", "testemail@gamil.com");
    }
}
