import com.webapp.VehicleRental.domain.Booking;
import com.webapp.VehicleRental.domain.User;
import com.webapp.VehicleRental.domain.VehicleListing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

public class BookingTest {

    @Test
    void testGetters() {
        Booking booking = new Booking("pickup_date", "drop_off_date", "pickup_time", "days", "price");
        assertSame("pickup_date", booking.getPickup_date());
        assertSame("drop_off_date", booking.getDrop_off_date());
        assertSame("pickup_time", booking.getPickup_time());
        assertSame("days", booking.getDays());
        assertSame("price", booking.getPrice());
    }

    @Test
    void testSetters() {
        Booking booking = new Booking();
        booking.setPickup_date("pickup_date");
        booking.setDrop_off_date("drop_off_date");
        booking.setPickup_time("pickup_time");
        booking.setDays("days");
        booking.setPrice("price");
        assertSame("pickup_date", booking.getPickup_date());
        assertSame("drop_off_date", booking.getDrop_off_date());
        assertSame("pickup_time", booking.getPickup_time());
        assertSame("days", booking.getDays());
        assertSame("price", booking.getPrice());
    }

    @Test
    void testCustomer() {
        Booking booking = new Booking();
        User user = new User();
        booking.setCustomer(user);
        assertEquals(user, booking.getCustomer());
    }

    @Test
    void testOwner() {
        Booking booking = new Booking();
        User user = new User();
        booking.setOwner(user);
        assertEquals(user, booking.getOwner());
    }

    @Test
    void testVehicleListing() {
        Booking booking = new Booking();
        VehicleListing vehicleListing = new VehicleListing();
        booking.setVehicleListing(vehicleListing);
        assertEquals(vehicleListing, booking.getVehicleListing());
    }

    @Test
    void testId() {
        Booking booking = new Booking();
        booking.setId(0);
        assertEquals(0, booking.getId());
    }
}
