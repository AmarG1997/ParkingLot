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

    @Before
    public void setUp() throws Exception {
        parkingSystem.park(vehicle);
    }

    @Test
    public void givenAVehicle_whenParked_shouldReturnTrue() throws ParkingLotException {
        boolean vehicleParked = parkingSystem.isVehicleParked(vehicle);
        Assert.assertTrue(vehicleParked);
    }

    @Test
    public void givenAVehicle_whenUnParked_shouldReturnTrue() throws ParkingLotException {
        boolean isUnParked = parkingSystem.unPark(vehicle);
        Assert.assertTrue(isUnParked);
    }

    @Test
    public void givenAVehicle_whenUnParkedAnotherVariable_shouldReturnFalse() throws ParkingLotException {
        boolean isUnParked = parkingSystem.unPark(new Object());
        Assert.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldReturnFull() throws ParkingLotException {
        parkingSystem.park(new Object());
        try {
            parkingSystem.park(new Object());
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking Lot Is Full", e.getMessage());
        }

    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldInformAirportSecurity() {
        try {
            parkingSystem.park(new Object());
            parkingSystem.park(new Object());
        } catch (ParkingLotException e) {
        }
        Assert.assertTrue(new AirportSecurity().parkingLot);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFullAndRemoveOneVehicle_shouldInformAirportSecurity() throws ParkingLotException {
        parkingSystem.park(new Object());
        try {
            parkingSystem.park(new Object());
        } catch (ParkingLotException e) {
        }
        parkingSystem.unPark(vehicle);
        Assert.assertFalse(new AirportSecurity().parkingLot);
    }
}
