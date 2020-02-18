package com.bridgelabz;

import com.bridgelabz.contoller.ParkingSystem;
import com.bridgelabz.service.AirportSecurity;
import com.bridgelabz.service.ParkingLotException;
import com.bridgelabz.service.ParkingLotOwner;
import com.bridgelabz.model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingSystem parkingSystem ;
    ParkingLotOwner owner = new ParkingLotOwner();
    Vehicle vehicle;

    @Before
    public void setUp() throws Exception {
        vehicle= new Vehicle();
        parkingSystem = new ParkingSystem(100,4);
        parkingSystem.park(vehicle,false);
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
        for (int i = 0; i < parkingSystem.parkingLotSize-1; i++) {
            parkingSystem.park(new Vehicle(),false);
        }
        try {
            parkingSystem.park(new Vehicle(),false);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Parking Lot Is Full", e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFull_shouldInformAirportSecurity() {
        try {
            for (int i = 0; i < parkingSystem.parkingLotSize; i++)
                parkingSystem.park(new Vehicle(),false);
        } catch (ParkingLotException e) {
        }
        Assert.assertTrue(new AirportSecurity().parkingLot);
    }

    @Test
    public void givenAVehicle_whenParkingLotIsFullAndToggleBack_shouldInformAirportSecurity() throws ParkingLotException {
        parkingSystem.park(new Vehicle(),false);
        try {
            parkingSystem.park(new Vehicle(),false);
        } catch (ParkingLotException e) {
        }
        parkingSystem.unPark(vehicle);
        Assert.assertFalse(new AirportSecurity().parkingLot);
    }

    @Test
    public void whenEnterDetails_shouldReturnParkingLotNumber() throws ParkingLotException {
        Vehicle vehicle1 = new Vehicle();
        parkingSystem.park(vehicle1,false);
        int slotNo = parkingSystem.getSlotNo(vehicle);
        Assert.assertEquals(1, slotNo);
    }

    @Test
    public void givenVehicle_whenUnparked_shouldReturnCharges() throws ParkingLotException {
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.getDetails();
        parkingSystem.unPark(vehicle);
        Object details = owner.getDetails();
        Assert.assertEquals(vehicle.getTime(), details);
    }

    @Test
    public void givenAVehicle_whenParked_shouldParkedEvenly() throws ParkingLotException {
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.park(new Vehicle(),false);
        int slotNo = parkingSystem.getSlotNo(vehicle);
        Assert.assertEquals(1, slotNo);
    }

    @Test
    public void givenAVehicle_whenDriverIsHandicap_shouldParkedInNeighbourSlot() throws ParkingLotException {
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.park(new Vehicle(),true);
        parkingSystem.getDetails();
        parkingSystem.park(new Vehicle(),true);
        parkingSystem.park(new Vehicle(),true);
        parkingSystem.park(new Vehicle(),true);
        parkingSystem.park(new Vehicle(),false);
        parkingSystem.getDetails();
        Assert.assertEquals(51,parkingSystem.getSlotNo(vehicle));
    }
}
