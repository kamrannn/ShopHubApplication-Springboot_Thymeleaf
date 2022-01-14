package cf.ac.uk.shophub.shophubapp.data.interfaces;

import cf.ac.uk.shophub.shophubapp.domain.ContactUs;

import java.util.List;

public interface ContactUsRepository {
    List<ContactUs> findAllContactUs();

    void saveContactUs(ContactUs contactUs);

    void deleteAllContactUs();
}
