import com.webapp.VehicleRental.domain.Booking;
import com.webapp.VehicleRental.domain.UploadFileResponse;
import com.webapp.VehicleRental.domain.User;
import com.webapp.VehicleRental.domain.VehicleListing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class VehicleListingTest {

    @Test
    void testGetters() {
        float price = (float) 00.00;
        VehicleListing vehicleListing = new VehicleListing("registration", null, null, "vehicletype", 4, "colour", "make", "model", "year", price, "info", "city", "country", "mileage");
        assertSame("registration", vehicleListing.getRegistration());
        assertSame(null, vehicleListing.getUploadFileResponse());
        assertSame(null, vehicleListing.getUser());
        assertSame("vehicletype", vehicleListing.getVehicletype());
        assertSame(4, vehicleListing.getDoors());
        assertSame("colour", vehicleListing.getColour());
        assertSame("make", vehicleListing.getMake());
        assertSame("model", vehicleListing.getModel());
        assertSame("year", vehicleListing.getYear());
        assertSame("info", vehicleListing.getInfo());
        assertSame("city", vehicleListing.getCity());
        assertSame("country", vehicleListing.getCountry());
        assertSame("mileage", vehicleListing.getMileage());
    }

    @Test
    void testSetters() {
        VehicleListing vehicleListing = new VehicleListing();
        vehicleListing.setRegistration("registration");
        vehicleListing.setUploadFileResponse(null);
        vehicleListing.setUser(null);
        vehicleListing.setVehicletype("vehicletype");
        vehicleListing.setDoors(4);
        vehicleListing.setColour("colour");
        vehicleListing.setMake("make");
        vehicleListing.setModel("model");
        vehicleListing.setYear("year");
        vehicleListing.setInfo("info");
        vehicleListing.setCity("city");
        vehicleListing.setCountry("country");
        vehicleListing.setMileage("mileage");

        assertSame("registration", vehicleListing.getRegistration());
        assertSame(null, vehicleListing.getUploadFileResponse());
        assertSame(null, vehicleListing.getUser());
        assertSame("vehicletype", vehicleListing.getVehicletype());
        assertSame(4, vehicleListing.getDoors());
        assertSame("colour", vehicleListing.getColour());
        assertSame("make", vehicleListing.getMake());
        assertSame("model", vehicleListing.getModel());
        assertSame("year", vehicleListing.getYear());
        assertSame("info", vehicleListing.getInfo());
        assertSame("city", vehicleListing.getCity());
        assertSame("country", vehicleListing.getCountry());
        assertSame("mileage", vehicleListing.getMileage());
    }

    @Test
    void testPrice() {
        VehicleListing vehicleListing = new VehicleListing();
        float price = (float) 00.00;
        vehicleListing.setPrice(price);
        assertEquals(price, vehicleListing.getPrice());
    }
}
