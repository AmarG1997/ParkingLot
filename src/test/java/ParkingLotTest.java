import com.bridgelabz.ParkingSystem;
import com.bridgelabz.enumeration.DriverType;
import com.bridgelabz.enumeration.VehicleDetails;
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
        vehicle = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL);
        parkingSystem = new ParkingSystem(100, 4);
        parkingSystem.park(vehicle);
    }

    @Test
    public void givenAVehicle_whenParked_shouldReturnTrue() throws ParkingLotException {
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
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
        boolean isUnParked = parkingSystem.unPark(new Vehicle(DriverType.HANDICAP_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        Assert.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldReturnFull() throws ParkingLotException {
        for (int i = 0; i < parkingSystem.PARKING_LOT_SIZE - 1; i++) {
            parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        }
        try {
            parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking Lot Is Full", e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldInformAirportSecurity() {
        try {
            for (int i = 0; i < parkingSystem.PARKING_LOT_SIZE; i++)
                parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        } catch (ParkingLotException e) {
        }
        Assert.assertTrue(AirportSecurity.status);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFullAndToggleBack_shouldInformAirportSecurity() throws ParkingLotException {
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        try {
            parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        } catch (ParkingLotException e) {
        }
        parkingSystem.unPark(vehicle);
        Assert.assertFalse(AirportSecurity.status);
    }

    @Test
    public void whenEnterDetails_shouldReturnParkingLotNumber() throws ParkingLotException {
        Vehicle vehicle1 = (new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(vehicle1);
        int slotNo = parkingSystem.getSlotNo(vehicle);
        Assert.assertEquals(1, slotNo);
    }

    @Test
    public void givenVehicle_whenUnparked_shouldReturnParkedTime() throws ParkingLotException {
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.unPark(vehicle);
        Object details = owner.getDetails();
        Assert.assertEquals(vehicle.getTimeAndDate(), details);
    }

    @Test
    public void givenAVehicle_whenParked_shouldParkedEvenly() throws ParkingLotException {
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        int slotNo = parkingSystem.getSlotNo(vehicle);
        Assert.assertEquals(1, slotNo);
    }

    @Test
    public void givenAVehicle_whenDriverIsHandicap_shouldParkedInNeighbourSlot() throws ParkingLotException {
        Vehicle vehicle = new Vehicle(DriverType.HANDICAP_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL);
        parkingSystem.park(new Vehicle(DriverType.HANDICAP_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.HANDICAP_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(vehicle);
        Assert.assertEquals(4, parkingSystem.getSlotNo(vehicle));
    }

    @Test
    public void givenALargeVehicle_whenParked_shoulReturnTrue() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL);
        Vehicle vehicle2 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.LARGE);
        parkingSystem.park(vehicle1);
        parkingSystem.park(vehicle2);
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        Assert.assertEquals(3, parkingSystem.getSlotNo(vehicle2));
    }

    @Test
    public void givenAVehicle_whenParkedVehicles_shouldReturnWhiteCars() throws ParkingLotException {
        Vehicle vehicle = (new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        Vehicle vehicle1 = (new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(vehicle);
        parkingSystem.park(vehicle1);
        Map<Integer, Vehicle> vehicleList = parkingSystem.getDetails(VehicleDetails.WHITE);
        Assert.assertEquals(3, vehicleList.size());
    }

    @Test
    public void givenAVehicle_whenParkedVehicle_shouldReturnBlueCarWithToyotoModel() throws ParkingLotException {
        Vehicle vehicle = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.SMALL, "ABC", "mh-15-fe53410", VehicleDetails.WHITE, VehicleDetails.TOYOTO);
        Vehicle vehicle1 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL);
        Vehicle vehicle2 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL);
        Vehicle vehicle3 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL);
        parkingSystem.park(vehicle1);
        parkingSystem.park(vehicle2);
        parkingSystem.park(vehicle3);
        parkingSystem.park(vehicle);
        Map<Integer, Vehicle> details = parkingSystem.getDetails(VehicleDetails.WHITE, VehicleDetails.TOYOTO);
        Assert.assertEquals(1, details.size());
    }

    @Test
    public void givenAVehicle_whenParkedVehicle_shouldReturnBMWCar() throws ParkingLotException {
        Vehicle vehicle = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.SMALL, "ABC", "mh-15-fe53410", VehicleDetails.BLUE, VehicleDetails.BMW);
        Vehicle vehicle4 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.SMALL, "ABC", "mh-15-fe53410", VehicleDetails.BLUE, VehicleDetails.BMW);
        Vehicle vehicle1 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.BLUE, VehicleDetails.SMALL);
        Vehicle vehicle2 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL);
        Vehicle vehicle3 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL);
        parkingSystem.park(vehicle1);
        parkingSystem.park(vehicle2);
        parkingSystem.park(vehicle3);
        parkingSystem.park(vehicle);
        parkingSystem.park(vehicle4);
        Map<Integer, Vehicle> details = parkingSystem.getDetails(VehicleDetails.BMW);
        Assert.assertEquals(2, details.size());
    }

    @Test
    public void givenAVehicle_whenParkedVehicle_shouldReturn30minBeforeParkedVehicle() throws ParkingLotException {
        Vehicle vehicle = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.SMALL, "ABC", "mh-15-fe53410", VehicleDetails.BLUE, VehicleDetails.BMW);
        Vehicle vehicle1 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.BLUE, VehicleDetails.SMALL);
        Vehicle vehicle2 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL);
        Vehicle vehicle3 = new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL);
        parkingSystem.park(vehicle1);
        parkingSystem.park(vehicle2);
        parkingSystem.park(vehicle3);
        parkingSystem.park(vehicle);
        Map<Integer, Vehicle> details = parkingSystem.getLast30MinuteParkedVehicles();
        Assert.assertEquals(5, details.size());
    }

    @Test
    public void givenAVehicles_whenParkedVehicles_shouldReturnBOrDLot() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle(DriverType.HANDICAP_DRIVER, VehicleDetails.SMALL, "ABC", "mh-15-fe53410", VehicleDetails.BLUE, VehicleDetails.BMW);
        parkingSystem.park(vehicle1);
        Map<Integer, Vehicle> lotData = parkingSystem.getLotData(1,DriverType.HANDICAP_DRIVER);
        Assert.assertEquals(1,lotData.size());
    }

    @Test
    public void givenAVehicle_whenParked_shouldReturnAllVehicles() throws ParkingLotException {
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        parkingSystem.park(new Vehicle(DriverType.NORMAL_DRIVER, VehicleDetails.WHITE, VehicleDetails.SMALL));
        Map<Integer, Vehicle> allVehicleDetails = parkingSystem.getAllVehicleDetails();
        Assert.assertEquals(6,allVehicleDetails.size());
    }
}
