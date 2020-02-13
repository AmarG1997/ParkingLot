package bridgelabz;

import com.briddgelabz.AirportSecurity;
import com.briddgelabz.ParkingLotException;
import com.briddgelabz.ParkingSystem;
import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    ParkingSystem parkingSystem = new ParkingSystem();
    Object vehicle = new Object();

    @Test
    public void givenAVehicle_whenParked_shouldReturnTrue() throws ParkingLotException {
        parkingSystem.park(vehicle);
        boolean isPark = parkingSystem.isVehicleParked(vehicle);
        Assert.assertTrue(isPark);
    }

    @Test
    public void givenAVehicle_whenUnParked_shouldReturnTrue() throws ParkingLotException {
        parkingSystem.park(vehicle);
        boolean isUnParked = parkingSystem.unPark(vehicle);
        Assert.assertTrue(isUnParked);
    }

    @Test
    public void givenAVehicle_whenUnParkedAnotherVariable_shouldReturnFalse() throws ParkingLotException {
        parkingSystem.park(new Object());
        boolean isUnParked = parkingSystem.unPark(vehicle);
        Assert.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldReturnFull() throws ParkingLotException {
        parkingSystem.park(vehicle);
        parkingSystem.park(new Object());
        try {
            parkingSystem.park(new Object());
        }catch (ParkingLotException e){
            Assert.assertEquals("Parking Lot Is Full",e.getMessage());
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

        boolean park1 = parkingSystem.unPark(vehicle);
        parkingSystem.park(vehicle);

        Assert.assertEquals(false, new AirportSecurity().isRedirect);
    }
}
