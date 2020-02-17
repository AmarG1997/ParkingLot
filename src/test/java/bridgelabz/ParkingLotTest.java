package bridgelabz;

import com.briddgelabz.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingSystem parkingSystem = new ParkingSystem();
    ParkingLotOwner owner = new ParkingLotOwner();
    Vehicle vehicle = new Vehicle();

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
        parkingSystem.park(new Vehicle());
        try {
            parkingSystem.park(new Vehicle());
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking Lot Is Full", e.getMessage());
        }

    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldInformAirportSecurity() {
        try {
            for (int i = 0; i < 100; i++)
                parkingSystem.park(new Vehicle());
        } catch (ParkingLotException e) {
        }
        Assert.assertTrue(new AirportSecurity().parkingLot);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFullAndRemoveOneVehicle_shouldInformAirportSecurity() throws ParkingLotException {
        parkingSystem.park(new Vehicle());
        try {
            parkingSystem.park(new Vehicle());
        } catch (ParkingLotException e) {
        }
        parkingSystem.unPark(vehicle);
        Assert.assertFalse(new AirportSecurity().parkingLot);
    }

    @Test
    public void whenEnterDetails_shouldReturnParkingLotNumber() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle();
        parkingSystem.park(vehicle1);
        int slotNo = parkingSystem.getSlotNo(vehicle);
        Assert.assertEquals(1, slotNo);
    }

    @Test
    public void givenVehicle_whenUnparked_shouldReturnCharges() throws ParkingLotException {
        parkingSystem.park(new Vehicle());
        parkingSystem.getDetails();
        parkingSystem.unPark(vehicle);
        Object details = owner.getDetails();
        Assert.assertEquals(vehicle.getTime(), details);
    }

    @Test
    public void givenAVehicle_whenParked_shouldParkedEvenly() throws ParkingLotException {
        parkingSystem.park(new Vehicle());
        parkingSystem.park(new Vehicle());
        parkingSystem.park(new Vehicle());
        parkingSystem.park(new Vehicle());
        parkingSystem.park(new Vehicle());
        parkingSystem.park(new Vehicle());
        parkingSystem.park(new Vehicle());
        parkingSystem.park(new Vehicle());
        parkingSystem.park(new Vehicle());
        parkingSystem.park(new Vehicle());
        parkingSystem.park(vehicle);
        int slotNo = parkingSystem.getSlotNo(vehicle);
        Assert.assertEquals(78, slotNo);
    }
}
