package bridgelabz;

import com.briddgelabz.AirportSecurity;
import com.briddgelabz.ParkingLotException;
import com.briddgelabz.ParkingSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingSystem parkingSystem = new ParkingSystem();

    @Before
    public void setUp() throws ParkingLotException {
        boolean park = parkingSystem.park("Amar", "MH-15-FE5310", "White");
        boolean park1 = parkingSystem.park("Mayur", "MH-15-BT1057", "White");
        boolean park2 = parkingSystem.park("Dipak", "MH-15-FE4141", "White");
        boolean park3 = parkingSystem.park("sonam", "MH-15-FE41415", "black");
    }

    @Test
    public void whenDriverWantsToPark_shouldBeAbleToPark() throws ParkingLotException {
        boolean park = parkingSystem.park("Amar", "MH-15-FE5310", "White");
        parkingSystem.getDetails();
        Assert.assertEquals(true, park);
    }

    @Test
    public void whenDriverWantsToUnPark_shouldBeAbleToUnPark() throws ParkingLotException {
        boolean result = parkingSystem.unPark("mayur");
        parkingSystem.getDetails();
        Assert.assertEquals(true, result);
    }

    @Test
    public void whenDriverSayWrongName_shouldThrowException() throws ParkingLotException {
        try {
            parkingSystem.unPark("WrongName");
        } catch (ParkingLotException e) {
            Assert.assertEquals("NO CAR DATA FOUND", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void whenParkingLotIsFull_shouldReturnFalse() {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean park = true;
        for (int i = 1; i <= 101; i++) {
            try {
                park = parkingSystem.park("ABC", "MH-15-FE5310", "White");
            } catch (ParkingLotException e) {
                Assert.assertEquals("PARKING_LOT_IS_FULL", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Test
    public void whenParkingLotIsFull_shouldAirportSecurityRedirectIsTrue() {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean park = true;
        for (int i = 1; i <= 101; i++) {
            try {
                park = parkingSystem.park("ABC", "MH-15-FE5310", "White");
            } catch (ParkingLotException e) {
                Assert.assertEquals(true, new AirportSecurity().isRedirect);
                e.printStackTrace();
            }
        }
    }

    @Test
    public void whenParkingLotIsFull_thenUnparkAnyCar_shouldAirportSecurityRedirectIsFalse() throws ParkingLotException {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean park = true;
        for (int i = 1; i <= 99; i++) {
            park = parkingSystem.park("ABC " + i, "MH-15-FE5310", "White");
        }
        parkingSystem.park("XYZ", "MH-15-FE5310", "White");
        parkingSystem.getDetails();
        boolean park1 = parkingSystem.unPark("ABC 12");
        parkingSystem.park("XYZ 1", "MH-15-FE5310", "White");
        parkingSystem.getDetails();
        Assert.assertEquals(false, new AirportSecurity().isRedirect);
    }
}
