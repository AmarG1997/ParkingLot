package bridgelabz;

import com.briddgelabz.AirportSecurity;
import com.briddgelabz.ParkingLotException;
import com.briddgelabz.ParkingSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingSystem parkingSystem = new ParkingSystem();
    Object vehicle = new Object();

    @Test
    public void whenDriverWantsToPark_shouldBeAbleToPark() throws ParkingLotException {
        parkingSystem.park(vehicle);
        boolean isPark = parkingSystem.isVehicle(this.vehicle);
        Assert.assertTrue(isPark);
    }

    @Test
    public void whenDriverWantsToUnPark_shouldBeAbleToUnPark() throws ParkingLotException {
        boolean result = parkingSystem.unPark(vehicle);
        parkingSystem.getDetails();
        Assert.assertEquals(true, result);
    }

    @Test
    public void whenDriverSayWrongName_shouldThrowException() throws ParkingLotException {
            parkingSystem.unPark("WrongName");

    }

    @Test
    public void whenParkingLotIsFull_shouldReturnFalse() {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean park = true;
        for (int i = 1; i <= 101; i++) {

             parkingSystem.park(vehicle);
        }
    }

    @Test
    public void whenParkingLotIsFull_shouldAirportSecurityRedirectIsTrue() {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean park = true;
        for (int i = 1; i <= 101; i++) {

          parkingSystem.park(vehicle);
        }
    }

    @Test
    public void whenParkingLotIsFull_thenUnparkAnyCar_shouldAirportSecurityRedirectIsFalse() throws ParkingLotException {
        ParkingSystem parkingSystem = new ParkingSystem();
        boolean park = true;
        for (int i = 1; i <= 99; i++) {
            parkingSystem.park(vehicle);
        }
        parkingSystem.park(vehicle);
        parkingSystem.getDetails();
        boolean park1 = parkingSystem.unPark(vehicle);
        parkingSystem.park(vehicle);
        parkingSystem.getDetails();
        Assert.assertEquals(false, new AirportSecurity().isRedirect);
    }
}
