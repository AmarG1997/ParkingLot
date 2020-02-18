import com.bridgelabz.contoller.ParkingSystem;
import com.bridgelabz.model.Vehicle;
import com.bridgelabz.model.VehicleType;
import com.bridgelabz.service.AirportSecurity;
import com.bridgelabz.service.ParkingLotException;
import com.bridgelabz.service.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ParkingLotTest {

    ParkingSystem parkingSystem ;
    ParkingLotOwner owner = new ParkingLotOwner();
    Vehicle vehicle;

    @Before
    public void setUp() throws Exception {
        vehicle= new Vehicle(false, VehicleType.SMALL);
        parkingSystem = new ParkingSystem(100,4);
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
        boolean isUnParked = parkingSystem.unPark(new Vehicle(true,VehicleType.LARGE));
        Assert.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldReturnFull() throws ParkingLotException {
        for (int i = 0; i < parkingSystem.PARKINGLOTSIZE -1; i++) {
            parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        }
        try {
            parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking Lot Is Full", e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldInformAirportSecurity() {
        try {
            for (int i = 0; i < parkingSystem.PARKINGLOTSIZE; i++)
                parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        } catch (ParkingLotException e) {
        }
        Assert.assertTrue(new AirportSecurity().parkingLot);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFullAndToggleBack_shouldInformAirportSecurity() throws ParkingLotException {
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        try {
            parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        } catch (ParkingLotException e) {
        }
        parkingSystem.unPark(vehicle);
        Assert.assertFalse(new AirportSecurity().parkingLot);
    }

    @Test
    public void whenEnterDetails_shouldReturnParkingLotNumber() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle(false,VehicleType.SMALL);
        parkingSystem.park(vehicle1);
        int slotNo = parkingSystem.getSlotNo(vehicle);
        Assert.assertEquals(1, slotNo);
    }

    @Test
    public void givenVehicle_whenUnparked_shouldReturnParkedTime() throws ParkingLotException {
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        parkingSystem.unPark(vehicle);
        Object details = owner.getDetails();
        Assert.assertEquals(vehicle.getTime(), details);
    }

    @Test
    public void givenAVehicle_whenParked_shouldParkedEvenly() throws ParkingLotException {
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        parkingSystem.park(new Vehicle(false,VehicleType.SMALL));
        int slotNo = parkingSystem.getSlotNo(vehicle);
        Assert.assertEquals(1, slotNo);
    }

    @Test
    public void givenAVehicle_whenDriverIsHandicap_shouldParkedInNeighbourSlot() throws ParkingLotException {
        parkingSystem.park(new Vehicle(true,VehicleType.SMALL));
        Assert.assertEquals(26,parkingSystem.getSlotNo(vehicle));
    }

    @Test
    public void givenALargeVehicle_whenParked_shoulReturnTrue() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle(false,VehicleType.LARGE);
        parkingSystem.park(vehicle1);
        boolean vehicleParked = parkingSystem.isVehicleParked(vehicle1);
        Assert.assertTrue(vehicleParked);
    }

    @Test
    public void givenAVehicle_whenParkedWhiteCars_shouldInformPolice() throws ParkingLotException {
        Vehicle vehicle = new Vehicle(false,VehicleType.SMALL,"white");
        Vehicle vehicle1 = new Vehicle(false,VehicleType.SMALL,"white");
        parkingSystem.park(vehicle);
        parkingSystem.park(vehicle1);
        Map<Integer,Vehicle> vehicleList = parkingSystem.getDetails("white");
        Assert.assertEquals("white",vehicleList.get(51).getColor());
    }
}
