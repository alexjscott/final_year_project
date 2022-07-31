import com.webapp.VehicleRental.bean.HashGenerator;
import com.webapp.VehicleRental.domain.Booking;
import com.webapp.VehicleRental.domain.User;
import com.webapp.VehicleRental.domain.VehicleListing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testGetters() {
        User user = new User("title", "name", "phone", "city", "country", "postcode", "email", "password");
        assertSame("title", user.getTitle());
        assertSame("name", user.getName());
        assertSame("phone", user.getPhone());
        assertSame("city", user.getCity());
        assertSame("country", user.getCountry());
        assertSame("postcode", user.getPostcode());
        assertSame("email", user.getEmail());
    }

    @Test
    void testSetters() {
        User user = new User();
        user.setTitle("title");
        user.setName("name");
        user.setPhone("phone");
        user.setCity("city");
        user.setCountry("country");
        user.setPostcode("postcode");
        user.setEmail("email");
        user.setPassword("password");

        assertSame("title", user.getTitle());
        assertSame("name", user.getName());
        assertSame("phone", user.getPhone());
        assertSame("city", user.getCity());
        assertSame("country", user.getCountry());
        assertSame("postcode", user.getPostcode());
        assertSame("email", user.getEmail());
    }

    @Test
    void testPassword() {
        User user = new User();
        user.setPassword("password");
        assertEquals("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", user.getPassword());
    }

    @Test
    void testId() {
        User user = new User();
        user.setId(0);
        assertEquals(0, user.getId());
    }


}
