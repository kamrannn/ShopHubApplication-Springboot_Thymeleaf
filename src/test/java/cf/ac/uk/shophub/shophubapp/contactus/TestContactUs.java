package cf.ac.uk.shophub.shophubapp.contactus;

import cf.ac.uk.shophub.shophubapp.data.interfaces.ContactUsRepository;
import cf.ac.uk.shophub.shophubapp.domain.ContactUs;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestContactUs {

    @Autowired
    ContactUsRepository contactUsRepository;

    @Test
    public void testAddNewContactUs() {
        ContactUs contactUs = new ContactUs("name", "email", "message");

        contactUsRepository.saveContactUs(contactUs);

        Iterable<ContactUs> allContactUs = contactUsRepository.findAllContactUs();
        Assertions.assertThat(allContactUs).extracting(ContactUs::getName).containsOnly("name");

        contactUsRepository.deleteAllContactUs();
        Assertions.assertThat(contactUsRepository.findAllContactUs()).isEmpty();
    }

    @Test
    public void testDeleteAllContactUs() {
        ContactUs contactUs = new ContactUs("name", "email", "message");

        contactUsRepository.saveContactUs(contactUs);

        Iterable<ContactUs> allContactUs = contactUsRepository.findAllContactUs();
        Assertions.assertThat(allContactUs).extracting(ContactUs::getName).containsOnly("name");

        Assertions.assertThat(contactUsRepository.findAllContactUs()).isEmpty();
    }
}
