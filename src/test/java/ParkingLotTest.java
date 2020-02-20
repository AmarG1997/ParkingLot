import com.bridgelabz.ParkingSystem;
import com.bridgelabz.enumeration.DriverType;
import com.bridgelabz.model.Vehicle;
import com.bridgelabz.service.AirportSecurity;
import com.bridgelabz.service.ParkingLotException;
import com.bridgelabz.service.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ParkingLotTest {

    ParkingSystem parkingSystem;
    ParkingLotOwner owner = new ParkingLotOwner();
    Vehicle vehicle;

    @Before
    public void setUp() throws Exception {
        vehicle = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER);
        parkingSystem = new ParkingSystem(100, 4);
        parkingSystem.park(vehicle);
    }

    @Test
    public void givenAVehicle_whenParked_shouldReturnTrue() throws ParkingLotException {
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
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
        boolean isUnParked = parkingSystem.unPark(new Vehicle(DriverType.HANDICAP));
        Assert.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldReturnFull() throws ParkingLotException {
        for (int i = 0; i < parkingSystem.PARKINGLOTSIZE - 1; i++) {
            parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        }
        try {
            parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking Lot Is Full", e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldInformAirportSecurity() {
        try {
            for (int i = 0; i < parkingSystem.PARKINGLOTSIZE; i++)
                parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        } catch (ParkingLotException e) {
        }
        Assert.assertTrue(AirportSecurity.status);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFullAndToggleBack_shouldInformAirportSecurity() throws ParkingLotException {
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        try {
            parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        } catch (ParkingLotException e) {
        }
        parkingSystem.unPark(vehicle);
        Assert.assertFalse(AirportSecurity.status);
    }

    @Test
    public void whenEnterDetails_shouldReturnParkingLotNumber() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER);
        parkingSystem.park(vehicle1);
        int slotNo = parkingSystem.getSlotNo(vehicle);
        Assert.assertEquals(1, slotNo);
    }

    @Test
    public void givenVehicle_whenUnparked_shouldReturnParkedTime() throws ParkingLotException {
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        parkingSystem.unPark(vehicle);
        Object details = owner.getDetails();
        Assert.assertEquals(vehicle.getTimeAndDate(), details);
    }

    @Test
    public void givenAVehicle_whenParked_shouldParkedEvenly() throws ParkingLotException {
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        int slotNo = parkingSystem.getSlotNo(vehicle);
        Assert.assertEquals(1, slotNo);
    }

    @Test
    public void givenAVehicle_whenDriverIsHandicap_shouldParkedInNeighbourSlot() throws ParkingLotException {
        parkingSystem.park(new Vehicle(DriverType.HANDICAP));
        Assert.assertEquals(26, parkingSystem.getSlotNo(vehicle));
    }

    @Test
    public void givenALargeVehicle_whenParked_shoulReturnTrue() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle(DriverType.LARGE_VEHICLE_DRIVER);
        Vehicle vehicle2 = new Vehicle(DriverType.LARGE_VEHICLE_DRIVER);
        parkingSystem.park(vehicle1);
        parkingSystem.park(vehicle2);
        parkingSystem.park(new Vehicle(DriverType.SMALL_VEHICLE_DRIVER));
        Assert.assertEquals(3,parkingSystem.getSlotNo(vehicle1));
    }

    @Test
    public void givenAVehicle_whenParkedVehicles_shouldReturnWhiteCars() throws ParkingLotException {
        Vehicle vehicle = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER, "white");
        Vehicle vehicle1 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER, "white");
        parkingSystem.park(vehicle);
        parkingSystem.park(vehicle1);
        Map<Integer, Vehicle> vehicleList = parkingSystem.getDetails("white");
        Assert.assertEquals("white", vehicleList.get(51).getColor());
    }

    @Test
    public void givenAVehicle_whenParkedVehicle_shouldReturnBlueCarWithToyotoModel() throws ParkingLotException {
        Vehicle vehicle = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER,  "ABC", "mh-15-fe53410", "blue", "TOYOTO");
        Vehicle vehicle1 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER,  "blue");
        Vehicle vehicle2 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER,  "white");
        Vehicle vehicle3 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER,  "white");
        parkingSystem.park(vehicle1);
        parkingSystem.park(vehicle2);
        parkingSystem.park(vehicle3);
        parkingSystem.park(vehicle);
        Map<Integer, Vehicle> details = parkingSystem.getDetails("blue", "TOYOTO");
        Assert.assertEquals("TOYOTO", details.get(2).getModel());
    }

    @Test
    public void givenAVehicle_whenParkedVehicle_shouldReturnBMWCar() throws ParkingLotException {
        Vehicle vehicle = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER, "ABC", "mh-15-fe53410", "blue", "BMW");
        Vehicle vehicle1 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER, "blue");
        Vehicle vehicle2 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER, "white");
        Vehicle vehicle3 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER, "white");
        parkingSystem.park(vehicle1);
        parkingSystem.park(vehicle2);
        parkingSystem.park(vehicle3);
        parkingSystem.park(vehicle);
        Map<Integer, Vehicle> details = parkingSystem.getDetails("BMW");
        Assert.assertEquals("BMW", details.get(2).getModel());
    }

    @Test
    public void givenAVehicle_whenParkedVehicle_shouldReturn30minBeforeParkedVehicle() throws ParkingLotException {
        Vehicle vehicle = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER, "ABC", "mh-15-fe53410", "blue", "BMW");
        Vehicle vehicle1 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER, "blue");
        Vehicle vehicle2 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER, "white");
        Vehicle vehicle3 = new Vehicle(DriverType.SMALL_VEHICLE_DRIVER, "white");
        parkingSystem.park(vehicle1);
        parkingSystem.park(vehicle2);
        parkingSystem.park(vehicle3);
        parkingSystem.park(vehicle);
        Map<Integer, Vehicle> details = parkingSystem.getLast30MinuteParkedVehicles();
        Assert.assertEquals(5, details.size());
    }
}
